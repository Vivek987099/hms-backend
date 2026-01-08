package com.example.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
	
	   @ExceptionHandler(BadCredentialsException.class)
	    public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex) {
	        return ResponseEntity.status(org.springframework.http.HttpStatus.UNAUTHORIZED)
	                .body(Map.of("message", "Incorrect username or password"));
	    }
	   
	   
	   @ExceptionHandler(UsernameNotFoundException.class)
	    public ResponseEntity<?> handleUserNotFound(UsernameNotFoundException ex) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body(Map.of("message", "User not found"));
	    }

	   

}
