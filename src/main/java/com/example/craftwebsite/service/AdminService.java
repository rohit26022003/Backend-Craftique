package com.example.craftwebsite.service;

import com.example.craftwebsite.model.Admin;
import com.example.craftwebsite.dto.AdminResponse;
import com.example.craftwebsite.repository.AdminRepository;
import com.example.craftwebsite.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private JwtService jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Register new admin
    public String register(Admin admin) {
        if (adminRepository.findByEmail(admin.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        admin.setUsername(admin.getUsername());
        admin.setEmail(admin.getEmail());
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        adminRepository.save(admin);

        return "Admin registered successfully";
    }

    // Login with email and password
    public AdminResponse login(String email, String password) {
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isEmpty()) {
            throw new RuntimeException("Admin not found");
        }

        Admin admin = adminOpt.get();
        if (!passwordEncoder.matches(password, admin.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(admin.getEmail());
        return new AdminResponse(token, admin.getUsername(), admin.getId());
    }

    // Update admin password by email
    public void updatePassword(String email, String newPassword) {
        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            String encodedPassword = passwordEncoder.encode(newPassword);
            admin.setPassword(encodedPassword);
            adminRepository.save(admin);
        }
    }

    // ✅ NEW: Get Admin by ID
    public Optional<Admin> getAdminById(Long id) {
        return adminRepository.findById(id);
    }

   public Admin updateAdminProfile(Long id, Admin updatedAdmin) {
        return adminRepository.findById(id).map(admin -> {
            admin.setUsername(updatedAdmin.getUsername());
            admin.setEmail(updatedAdmin.getEmail());
            admin.setImgpath(updatedAdmin.getImgpath());
            return adminRepository.save(admin);
        }).orElseThrow(() -> new RuntimeException("Admin not found"));
    }
}
