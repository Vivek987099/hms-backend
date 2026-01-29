package com.example.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthServiceImple authServiceImple;

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
		return authServiceImple.login(loginRequest);
	}

	@GetMapping("/checkAuth")
	public ResponseEntity<?> checkAuthentication() {
		return authServiceImple.checkAuth();
	}

	@GetMapping("/profile")
	public ResponseEntity<?> profile(){
		
		return ResponseEntity.ok(this.authServiceImple.getProfile());
	}
	
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletResponse response) {
		Cookie jwtCookie = new Cookie("token", null);
		jwtCookie.setHttpOnly(true);
		jwtCookie.setSecure(false); // production me true
		jwtCookie.setPath("/");
		jwtCookie.setMaxAge(0); // delete cookie

		response.addCookie(jwtCookie);
		return ResponseEntity.ok(Map.of("message", "Logout successfully"));
	}

}
