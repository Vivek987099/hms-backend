package com.example.tempUser.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class TempUserController {
	
	@GetMapping
	public ResponseEntity<?> welcome(){
		return ResponseEntity.ok("HMS backend is runnig");
	}
	
	

}
