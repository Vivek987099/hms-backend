package com.example.user.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.doctor.DTO.DoctorRequestDTO;
import com.example.user.DTO.OTPVerificationRequest;
import com.example.user.DTO.UserRequestDTO;
import com.example.user.entity.User;
import com.example.user.serviceImple.UserServiceImple;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserServiceImple userServiceImple;



	@PostMapping("/create-user")
	public ResponseEntity<?> creatUser(@RequestPart(value = "user", required = true) UserRequestDTO userRequestDTO,
			@Valid @RequestPart(value = "doctor", required = false) DoctorRequestDTO doctorRequestDTO,
			@RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
		String message = userServiceImple.createNewUser(userRequestDTO, doctorRequestDTO, file);
		return ResponseEntity.ok(Map.of("message",message));
	}

//	@PostMapping("/verify-otp")
//	public ResponseEntity<?> verifyOtp(@RequestBody OTPVerificationRequest otpVerificationRequest) {
//
//		int useId = userServiceImple.otpVerification(otpVerificationRequest);
//		return ResponseEntity.ok(Map.of("userId", useId));
//	}

	@GetMapping("/total-users")
	public ResponseEntity<?> getTotalUser() {
		return ResponseEntity.ok(userServiceImple.totalUser());
	}

	@GetMapping("/all-users")
	public ResponseEntity<?> allUsers(@RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
			@RequestParam(name = "pageNo", defaultValue = "0") int pageNo) {
		return ResponseEntity.ok(userServiceImple.getAllUser(pageSize, pageNo));

	}

	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<?> deleteUserWithId(@PathVariable int id) {
		String message = userServiceImple.deleteUser(id);
		return ResponseEntity.ok(Map.of("message", message));
	}

	@GetMapping("/users/{role}")
	public ResponseEntity<?> usersByRole(@PathVariable String role) {
		return ResponseEntity.ok(this.userServiceImple.getUserByRole(role));

	}
}
