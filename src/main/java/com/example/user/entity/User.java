package com.example.user.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(unique = true)
	private String username;
	@Column
	private String password;
	@Column
	private LocalDate createdAt;
	@Column
	private String role;
	@Column
	private boolean status;
	@Column(nullable = true)
	private int doctorId;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String username, String password, LocalDate createdAt, String role, boolean status,
			int doctorId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.createdAt = createdAt;
		this.role = role;
		this.status = status;
		this.doctorId = doctorId;
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

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
