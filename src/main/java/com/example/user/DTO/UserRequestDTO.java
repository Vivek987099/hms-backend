package com.example.user.DTO;

import jakarta.validation.constraints.NotBlank;

public class UserRequestDTO {
	
	@NotBlank(message = "This field is required")
	private String username;
	@NotBlank(message = "This field is required")
	private String password;
	@NotBlank(message = "This field is required")
	private String role;
	public UserRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserRequestDTO(String username, String password, String role) {
		super();
		this.username = username;
		this.password = password;
		this.role = role;
	}
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
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	

}
