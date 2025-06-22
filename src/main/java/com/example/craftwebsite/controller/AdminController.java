package com.example.craftwebsite.controller;

import com.example.craftwebsite.model.Admin;
import com.example.craftwebsite.dto.AdminResponse;
import com.example.craftwebsite.dto.AdminProfileResponse;
import com.example.craftwebsite.service.AdminService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*") // Allow React frontend access
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public String register(@RequestBody Admin admin) {
        return adminService.register(admin);
    }

    @PostMapping("/login")
    public AdminResponse login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        return adminService.login(email, password);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminProfileResponse> getAdminById(@PathVariable Long id) {
        Optional<Admin> adminOpt = adminService.getAdminById(id);
        return adminOpt.map(admin -> new AdminProfileResponse(
                admin.getId(),
                admin.getUsername(),
                admin.getEmail(),
                admin.getImgpath()
        )).map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminProfileResponse> updateAdminProfile(@PathVariable Long id, @RequestBody Admin updatedAdmin) {
        try {
            Admin savedAdmin = adminService.updateAdminProfile(id, updatedAdmin);
            AdminProfileResponse response = new AdminProfileResponse(
                    savedAdmin.getId(),
                    savedAdmin.getUsername(),
                    savedAdmin.getEmail(),
                    savedAdmin.getImgpath()
            );
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
