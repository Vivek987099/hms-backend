package com.example.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.Public.ApiResponse;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<?> handleBadCredentials(BadCredentialsException ex, WebRequest request) {
		ApiResponse apiErrorResponse = new ApiResponse("Username or password is incorrect",
				HttpStatus.UNAUTHORIZED.value(), LocalDateTime.now(), request.getDescription(false));
		return new ResponseEntity<ApiResponse>(apiErrorResponse, HttpStatus.UNAUTHORIZED);

	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<?> handleUserNotFound(UsernameNotFoundException ex) {
		ApiResponse apiErrorResponse = new ApiResponse();
		apiErrorResponse.setMessage("User not found try again");
		apiErrorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
		apiErrorResponse.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ApiResponse>(apiErrorResponse, HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> mathodArgumentNotValid(MethodArgumentNotValidException ex) {
		Map<String, String> errorMap = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errorMap.put(fieldName, errorMessage);
		});
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DoctorAlreadyException.class)
	public ResponseEntity<?> doctorAlreadyExistException(DoctorAlreadyException ex) {
				
		Map<String, String> errorMap= new HashMap<>();
		  String errorMassage= ex.getMessage();
		  errorMap.put("message", errorMassage);
	
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.CONFLICT);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<?> illegalArgumentException(IllegalArgumentException ex) {
				
		Map<String, String> errorMap= new HashMap<>();
		  String errorMassage= ex.getMessage();
		  errorMap.put("message", errorMassage);
		
	
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NullFileException.class)
	public ResponseEntity<?> handleNullFileException(NullFileException ex) {
				
		Map<String, String> errorMap= new HashMap<>();
		  String errorMassage= ex.getMessage();
		  errorMap.put("message", errorMassage);
		
	
		return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
	}
	
	

}
