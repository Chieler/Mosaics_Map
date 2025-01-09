package com.bipartite.v1.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bipartite.v1.Model.User;
import com.bipartite.v1.Repository.UserRepository;

@Service
public class UserService implements UserDetailsService{
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User
        .withUsername(user.getUsername())
        .password(user.getPassword())
        .roles("USER")
        .build();
    }
    public User registerUser(String username, String password){
        if (username == null || password == null) {
            throw new IllegalArgumentException("Username and password cannot be null");
        }
        if (username.contains(" ") || password.contains(" ")) {
            throw new IllegalArgumentException("Username and password cannot contain spaces");
        }
        if (username.length() < 8 || password.length() < 8) {
            throw new IllegalArgumentException("Username and password must be at least 8 characters long");
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}
