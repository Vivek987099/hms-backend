package com.example.auth;

import org.springframework.http.ResponseEntity;

import com.example.user.DTO.UserResponseDTO;

public interface AuthService {

	public ResponseEntity<?> login(LoginRequest loginRequest);
	
	public ResponseEntity<?>  checkAuth();
	
	public UserResponseDTO getProfile();

}
