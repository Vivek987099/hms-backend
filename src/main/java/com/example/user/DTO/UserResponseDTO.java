package com.example.user.DTO;

import java.time.LocalDate;

public class UserResponseDTO {
	private int id;
	private String username;
	private String role;
	private boolean status;
	private LocalDate createdAt;

	public UserResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public UserResponseDTO(int id, String username, String role, boolean status, LocalDate createdAt) {
		super();
		this.id = id;
		this.username = username;
		this.role = role;
		this.status = status;
		this.createdAt = createdAt;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}



	public boolean isStatus() {
		return status;
	}



	public void setStatus(boolean status) {
		this.status = status;
	}

}
