package com.example.craftwebsite.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cloudinary")
@CrossOrigin(origins = "http://localhost:3000") // Allow frontend calls
public class CloudinaryController {

    @DeleteMapping("/delete-image")
    public ResponseEntity<String> deleteImage(@RequestParam String publicId) {
        try {
            String cloudName = "dzwef3uto"; // Replace with your Cloudinary cloud name
            String apiUrl = "https://api.cloudinary.com/v1_1/" + cloudName + "/image/destroy";

            // Prepare request body
            Map<String, String> requestBody = new HashMap<>();
            requestBody.put("public_id", publicId);

            // Make DELETE request to Cloudinary
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, requestBody, String.class);

            return ResponseEntity.ok("Image deleted successfully: " + response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to delete image: " + e.getMessage());
        }
    }
}