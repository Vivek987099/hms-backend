package com.example.user.service;



import java.util.List;

import org.springframework.data.domain.Page;

import com.example.user.DTO.OTPVerificationRequest;
import com.example.user.DTO.UserResponseDTO;
import com.example.user.entity.User;

public interface UserService {
	public String createNewUser(User user);
	public int otpVerification(OTPVerificationRequest otpVerificationRequest);
	public long totalUser();
	public Page<UserResponseDTO> getAllUser(int pageSize,int pageNo);
	public String deleteUser(int id);
	public List<UserResponseDTO> getUserByRole(String role);

}
