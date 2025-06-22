package com.example.craftwebsite.controller;


import com.example.craftwebsite.dto.AuthRequest;
import com.example.craftwebsite.dto.ImageUpdateDTO;
// import com.example.craftwebsite.dto.AuthResponse;
import com.example.craftwebsite.dto.RegisterRequest;
import com.example.craftwebsite.dto.UserUpdateDTO;
import com.example.craftwebsite.service.AuthService;
import com.example.craftwebsite.model.User;
import com.example.craftwebsite.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")

public class AuthController {
	@Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
    	
    	try
    	{
        return ResponseEntity.ok(authService.authenticate(request));
    	}
     catch (BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body("Invalid username or password");
    }
    }
    @GetMapping("/{id}")
public ResponseEntity<User> getUserById(@PathVariable Long id) {
    return userRepository.findById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
      }
      @PutMapping("edit/{id}")
public ResponseEntity<User> updateUser(
        @PathVariable Long id,
        @RequestBody UserUpdateDTO dto) {

    return userRepository.findById(id).map(user -> {
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        if (dto.getImgpath() != null) {
            user.setImgpath(dto.getImgpath());
        }
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }).orElse(ResponseEntity.notFound().build());
}

@PutMapping("/{id}/image")
public ResponseEntity<User> updateUserImage(
        @PathVariable Long id,
        @RequestBody ImageUpdateDTO dto) {

    return userRepository.findById(id).map(user -> {
        user.setImgpath(dto.getImgpath());
        userRepository.save(user);
        return ResponseEntity.ok(user);
    }).orElse(ResponseEntity.notFound().build());
}


}