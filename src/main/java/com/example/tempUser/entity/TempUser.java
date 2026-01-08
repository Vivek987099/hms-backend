package com.example.tempUser.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "tampUser")
public class TempUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private String username;
	@Column
	private String password;
	
	@Column
	private String role;
	@Column
	private String otp;
	@Column
	private LocalDateTime expiredAt;
	public TempUser() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TempUser(int id, String username, String password, String role, String otp,
			LocalDateTime expiredAt) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
		this.otp = otp;
		this.expiredAt = expiredAt;
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
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public LocalDateTime getExpiredAt() {
		return expiredAt;
	}
	public void setExpiredAt(LocalDateTime expiredAt) {
		this.expiredAt = expiredAt;
	}
	
	
}
