package com.example.craftwebsite.controller;

import com.example.craftwebsite.model.Admin;
import com.example.craftwebsite.repository.AdminRepository;
import com.example.craftwebsite.service.AdminService;
import com.example.craftwebsite.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000") // frontend allowed
@RestController
@RequestMapping("/api/admin")
public class AdminPasswordController {

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private AdminService adminService;

    @Autowired
    private OtpService otpService;

    // DTO for sending email
    public static class EmailRequest {
        public String email;
    }

    // DTO for resetting password
    public static class ResetPasswordRequest {
        public String email;
        public String otp;
        public String newPassword;
    }

    /**
     * Admin requests OTP to reset password
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<String> sendOtp(@RequestBody EmailRequest request) {
        if (request.email == null || request.email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email is required");
        }

        Optional<Admin> admin = adminRepo.findByEmail(request.email);
        if (admin.isEmpty()) {
            return ResponseEntity.badRequest().body("Admin with this email does not exist");
        }

        otpService.sendOtp(request.email);
        return ResponseEntity.ok("OTP sent to your admin email");
    }

    /**
     * Admin resets password using OTP
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

        Optional<Admin> adminOpt = adminRepo.findByEmail(request.email);
        if (adminOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Admin not found");
        }

        adminService.updatePassword(request.email, request.newPassword);
        return ResponseEntity.ok("Admin password reset successfully");
    }
}
