package com.example.tempUser.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp-user")
public class TempUserController {
	
	@GetMapping("/welcome")
	public ResponseEntity<?> welcome(){
		return ResponseEntity.ok("controller actice now");
	}
	
	

}
