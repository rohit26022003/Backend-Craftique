package com.example.craftwebsite.service;

import com.example.craftwebsite.dto.AuthRequest;
import com.example.craftwebsite.dto.AuthResponse;
import com.example.craftwebsite.dto.RegisterRequest;
import com.example.craftwebsite.model.User;
import com.example.craftwebsite.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;

    // Manually defined constructor
    public AuthService(UserService userService, JwtService jwtService, AuthenticationManager authenticationManager, UserRepository userRepository) {
        this.userService = userService;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    public AuthResponse register(RegisterRequest request) {
    User user = new User();
    user.setUsername(request.getUsername());
    user.setPassword(request.getPassword());
    user.setEmail(request.getEmail());
    user.setPhone(request.getPhone());
    userService.saveUser(user);

    String token = jwtService.generateToken(user.getUsername());

    // After saving, user now has an ID (because of auto-generation)
    return new AuthResponse(token, user.getUsername(), user.getId());
}


    public AuthResponse authenticate(AuthRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
    );

    String token = jwtService.generateToken(request.getEmail());

    // Fetch user from DB
    User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found by email"));

    return new AuthResponse(token, user.getUsername(), user.getId());
}

}


/*
1. User Registration (register())
 

Accepts user details (RegisterRequest DTO).

Hashes password before saving.

Stores the user in the database.

Generates a JWT token and returns it.

2. User Login (authenticate())

Authenticates username and password.

Fetches user details from the database.

Generates a JWT token and returns it.
*/