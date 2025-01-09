package com.bipartite.v1.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bipartite.v1.Service.UserService;

@Controller
public class AuthController {
    @Autowired
    private UserService userService;
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }
    
@PostMapping("/signup")
public String registerUser(@RequestParam String username,
                         @RequestParam String password,
                         RedirectAttributes redirectAttributes) {
    try {
        userService.registerUser(username, password);
        return "redirect:/login";
    } catch (IllegalArgumentException e) {
        redirectAttributes.addFlashAttribute("error", e.getMessage());
        return "redirect:/signup";
    }
}
    
    @GetMapping("/isLoggedIn")
    public ResponseEntity<?> isLoggedIn(@AuthenticationPrincipal UserDetails userDetails){
        try{
            return ResponseEntity.ok(userDetails.getUsername());
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}