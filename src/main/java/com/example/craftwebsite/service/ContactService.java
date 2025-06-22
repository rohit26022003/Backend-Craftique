package com.example.craftwebsite.service;

import com.example.craftwebsite.dto.ContactRequest;
import com.example.craftwebsite.model.ContactMessage;

import java.util.List;

public interface ContactService {
    ContactMessage save(ContactRequest request);
    List<ContactMessage> getAll();
    void deleteById(Long id);
}
