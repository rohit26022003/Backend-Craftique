package com.example.craftwebsite.controller;

import com.example.craftwebsite.dto.ContactRequest;
import com.example.craftwebsite.model.ContactMessage;
import com.example.craftwebsite.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService service;

    // POST /api/contact
    @PostMapping("/submit")
    public ResponseEntity<ContactMessage> submitContact(@RequestBody ContactRequest request) {
        return ResponseEntity.ok(service.save(request));
    }

    // GET /api/contact
    @GetMapping("/all")
    public ResponseEntity<List<ContactMessage>> getAllContacts() {
        return ResponseEntity.ok(service.getAll());
    }

    // DELETE /api/contact/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
