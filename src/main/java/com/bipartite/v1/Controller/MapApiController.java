package com.bipartite.v1.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bipartite.v1.Model.ColorUpdateRequest;
import com.bipartite.v1.Model.CountryUpdateRequest;
import com.bipartite.v1.Model.FeatureColor;
import com.bipartite.v1.Model.MarkedCountry;
import com.bipartite.v1.Model.Note;
import com.bipartite.v1.Model.NoteUpdateRequest;
import com.bipartite.v1.Model.User;
import com.bipartite.v1.Repository.FeatureColorRepository;
import com.bipartite.v1.Repository.MarkedCountryRepository;
import com.bipartite.v1.Repository.NotesRepository;
import com.bipartite.v1.Repository.UserRepository;
import com.bipartite.v1.Service.FeatureColorService;
import com.bipartite.v1.Service.MarkedCountryService;
import com.bipartite.v1.Service.NoteService;
import com.bipartite.v1.Service.UserService;

@RestController
@RequestMapping("/api/map")
public class MapApiController {
    @Autowired
    FeatureColorRepository featureColorRepository;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MarkedCountryRepository markedCountryRepository;
    @Autowired
    MarkedCountryService markedCountryService;
    @Autowired
    FeatureColorService featureColorService;
    @Autowired
    NotesRepository notesRepository;
    @Autowired
    NoteService noteService;
    @PostMapping("/updateNotes")
    public ResponseEntity<?> createOrUpdateNote(@RequestBody NoteUpdateRequest request, @AuthenticationPrincipal UserDetails userDetails){
        try{
            if (userDetails == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Location", "/login")
                    .build();
            }
            User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
            Note note = notesRepository
                .findByCountryIdAndUserId(request.getCountryId(), user.getId())
                .orElse(new Note());
            note.setUserId(user.getId());
            note.setCountryId(request.getCountryId());
            note.setContent(request.getContent());
            CompletableFuture<Note> savedNote = noteService.saveNotesAsync(note);
            notesRepository.deleteAll(notesRepository.findUnecesssaryData());
            return ResponseEntity.ok(savedNote.join());

        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .header("Location", "/login")
                .build(); 
        }
    }
    @PostMapping("/updateColor")
    public ResponseEntity<?> updateColor(@RequestBody ColorUpdateRequest request, @AuthenticationPrincipal UserDetails userDetails){
        try{
            User user = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));

            FeatureColor featureColor = featureColorRepository.findByFeatureIdAndUserId(request.getFeatureId(), user.getId()).orElse(new FeatureColor());
            featureColor.setFeatureId(request.getFeatureId());
            featureColor.setUserId(user.getId());
            featureColor.setIsColored();

            CompletableFuture<FeatureColor> saveColor= featureColorService.saveFeatureColorAsync(featureColor);
            
            featureColorRepository.deleteAll(featureColorRepository.findUnnecessaryData());
            //not reaching here         
            return ResponseEntity.ok(saveColor.join());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

    }

    @PostMapping("/updateMarkedCountry")
    public ResponseEntity<?> updateCountry(@RequestBody CountryUpdateRequest request, @AuthenticationPrincipal UserDetails userDetails) {
        try {
            if (userDetails == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .header("Location", "/login")
                    .build();
            }

            User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
            
            MarkedCountry country = markedCountryRepository
                .findByMarkIdAndUserId(request.getMarkId(), user.getId())
                .orElse(new MarkedCountry());
            
            country.setUserId(user.getId());
            country.setIsMarked();
            country.setMarkId(request.getMarkId());
            CompletableFuture<MarkedCountry> savedCountry = markedCountryService.saveMarkedCountryAsync(country);

            markedCountryRepository.deleteAll(markedCountryRepository.findUnnecessaryData());
            
            return ResponseEntity.ok(savedCountry.join());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .header("Location", "/login")
                .build();
        }
    }
    @GetMapping("/getNotes")
    public ResponseEntity<?> getUserNotes(@AuthenticationPrincipal UserDetails userDetails){
        try{
            User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(()-> new RuntimeException("user not found"));
            List<Note> userNotes = notesRepository.findAllByUserId(user.getId());
            if(userNotes.isEmpty()){
                return ResponseEntity.ok(new ArrayList<Note>());
            }
            return ResponseEntity.ok(userNotes);
        }catch(Exception e){
            return ResponseEntity.status(500).body("Error retrieving colors.");
        }
    }
    @GetMapping("/getNote")
    public ResponseEntity<?> getUserNote(@RequestParam String countryId, @AuthenticationPrincipal UserDetails userDetails){
        try{
            User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(()-> new RuntimeException("User not found"));
            Optional<Note> note = notesRepository.findByCountryIdAndUserId(countryId, user.getId());
            return ResponseEntity.ok(note.orElse(null));
        }catch(Exception e){
            return ResponseEntity.status(500).body("Error retrieving note for countryID "+ countryId);
        }
    }
    @GetMapping("/markedCountries")
    public ResponseEntity<?> getUserMarkedCountries(@AuthenticationPrincipal UserDetails userDetails){
        try{
            User user = userRepository.findByUsername(userDetails.getUsername()).orElseThrow(()-> new RuntimeException("User not found"));
            List<MarkedCountry> markedCountries = markedCountryRepository.findAllByUserId(user.getId());
            if(markedCountries.isEmpty()){
                return ResponseEntity.ok(new ArrayList<MarkedCountry>());
            }
            return ResponseEntity.ok(markedCountries);
        }catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving colors.");
        }

    }
    @GetMapping("/colors")
    public ResponseEntity<?> getUserColors(@AuthenticationPrincipal UserDetails userDetails){
        try {
            User user = userRepository.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
            List<FeatureColor> colors = featureColorRepository.findAllByUserId(user.getId());
            if (colors.isEmpty()) {
                return ResponseEntity.ok(new ArrayList<FeatureColor>());
            }
            return ResponseEntity.ok(colors);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error retrieving colors.");
        }
    }
    @GetMapping("/getUsername")
    public ResponseEntity<?> getUsername(@AuthenticationPrincipal UserDetails userDetails){
        try{
            User user = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new RuntimeException("User not found"));
            return ResponseEntity.ok(user.getId());
        }catch (Exception e) {
            System.out.println("Error in updateCountry: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .header("Location", "/login")
                .build();
        }
    }

}
