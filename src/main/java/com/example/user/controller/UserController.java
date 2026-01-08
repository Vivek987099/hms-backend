package com.example.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.user.DTO.OTPVerificationRequest;
import com.example.user.entity.User;
import com.example.user.serviceImple.UserServiceImple;

@RestController
@RequestMapping("/api/user")
public class UserController {
	@Autowired
	private UserServiceImple userServiceImple;
	

	@PostMapping("/create-user")
	public ResponseEntity<?> creatUser(@RequestBody User user) {

		String message = userServiceImple.createNewUser(user);
		return ResponseEntity.ok(Map.of("message", message));
	}
	@PostMapping("/verify-otp")
	public ResponseEntity<?> verifyOtp(@RequestBody OTPVerificationRequest otpVerificationRequest){
		
		
		String message =  userServiceImple.otpVerification(otpVerificationRequest);
		return ResponseEntity.ok(Map.of("message",message));
	}

	@GetMapping("/profile")
	public ResponseEntity<?> getProfile() {
		return ResponseEntity.ok(userServiceImple.getUserProfile());
	}

	@GetMapping("/total-users")
	public ResponseEntity<?> getTotalUser() {
		return ResponseEntity.ok(userServiceImple.totalUser());
	}
	@GetMapping("/all-users")
	public ResponseEntity<?> allUsers(@RequestParam(name = "pageSize",defaultValue = "2") int pageSize,@RequestParam(name = "pageNo",defaultValue = "0") int pageNo){
		return ResponseEntity.ok(userServiceImple.getAllUser(pageSize, pageNo));
		
	}
	
	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<?> deleteUserWithId(@PathVariable int id){
		String message=  userServiceImple.deleteUser(id);
		return ResponseEntity.ok(Map.of("message",message));
	}
}
