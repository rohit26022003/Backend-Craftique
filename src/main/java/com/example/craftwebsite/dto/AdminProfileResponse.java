package com.example.craftwebsite.dto;

public class AdminProfileResponse {
    private Long id;
    private String username;
    private String email;
    private String imgpath;

    public AdminProfileResponse() {}

    public AdminProfileResponse(Long id, String username, String email, String imgpath) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.imgpath = imgpath;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getImgpath() { return imgpath; }
    public void setImgpath(String imgpath) { this.imgpath = imgpath; }
}
