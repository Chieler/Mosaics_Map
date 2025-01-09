package com.bipartite.v1.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bipartite.v1.Model.Note;
import com.bipartite.v1.Repository.NotesRepository;

@Service
public class NoteService {
    @Autowired
    NotesRepository notesRepository;
    @Async
    public CompletableFuture<Note> saveNotesAsync(Note note){
        return CompletableFuture.supplyAsync(()-> notesRepository.save(note));
    }
    @Async
    public CompletableFuture<Optional<Note>>getNoteAsync(String countryId, String userId){
        return CompletableFuture.supplyAsync(() -> notesRepository.findByCountryIdAndUserId(countryId, userId));
    }
}
