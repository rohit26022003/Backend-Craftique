package com.example.craftwebsite.service;

import com.example.craftwebsite.dto.ContactRequest;
import com.example.craftwebsite.model.ContactMessage;
import com.example.craftwebsite.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository repository;

    @Override
    public ContactMessage save(ContactRequest request) {
        ContactMessage msg = new ContactMessage();
        msg.setName(request.getName());
        msg.setEmail(request.getEmail());
        msg.setMessage(request.getMessage());
        msg.setCreatedAt(LocalDateTime.now());
        return repository.save(msg);
    }

    @Override
    public List<ContactMessage> getAll() {
        return repository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
