package com.example.craftwebsite.dto;

public class RegisterRequest 
{
    private String username;
    private String password;
	private String email;
	private String phone;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
    
    
}



/*
 * 
1. User Registration
Client sends a POST request with a RegisterRequest object.

Server saves the user and returns a success message.

2. User Login
Client sends a POST request with an AuthRequest object.

Server validates the user and returns an AuthResponse with a JWT token.

3. User Requests Protected API
Client includes the JWT token in the Authorization header (Bearer <token>).

Server validates the token and allows access.
 * 
 * 
 */