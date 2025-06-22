package com.example.craftwebsite.controller;

import com.example.craftwebsite.repository.UserRepository;
import com.example.craftwebsite.model.User;
import com.example.craftwebsite.service.OtpService;
import com.example.craftwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") // Allow frontend to access this controller
@RestController
@RequestMapping("/api")
public class PasswordController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private OtpService otpService;

    // DTO for sending OTP
    public static class EmailRequest {
        public String email;
    }

    // DTO for password reset
    public static class ResetPasswordRequest {
        public String email;
        public String otp;
        public String newPassword;
    }

    // DTO for registration
    public static class RegisterRequest {
        public String email;
        public String password;
    }

    /**
     * Request an OTP to reset password
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<String> sendOtp(@RequestBody EmailRequest request) {
        if (request.email == null || request.email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }

        if (!userRepo.findByEmail(request.email).isPresent()) {
            return ResponseEntity.badRequest().body("User with this email does not exist");
        }

        otpService.sendOtp(request.email);
        return ResponseEntity.ok("OTP sent to your email");
    }

    /**
     * Reset password using OTP
     */
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        if (request.email == null || request.otp == null || request.newPassword == null ||
            request.email.isEmpty() || request.otp.isEmpty() || request.newPassword.isEmpty()) {
            return ResponseEntity.badRequest().body("All fields are required");
        }

        boolean verified = otpService.verifyOtp(request.email, request.otp);
        if (!verified) {
            return ResponseEntity.badRequest().body("Invalid or expired OTP");
        }

        Optional<User> userOpt = userRepo.findByEmail(request.email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        userService.updatePassword(request.email, request.newPassword);
        return ResponseEntity.ok("Password reset successfully");
    }

    /**
     * Register new user
     */
   
      
}
