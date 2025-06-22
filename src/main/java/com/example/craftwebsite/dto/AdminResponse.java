package com.example.craftwebsite.dto;

public class AdminResponse {
    private String token;
    private String username;
    private Long id;

    public AdminResponse(String token, String username, Long id) {
        this.token = token;
        this.username = username;
        this.id = id;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public Long getId() {
        return id;
    }
}
