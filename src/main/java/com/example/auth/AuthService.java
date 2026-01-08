package com.example.auth;

import org.springframework.http.ResponseEntity;

public interface AuthService {

	public ResponseEntity<?> login(LoginRequest loginRequest);
	
	public ResponseEntity<?>  checkAuth();

}
