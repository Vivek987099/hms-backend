package com.example.user.service;



import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.doctor.DTO.DoctorRequestDTO;
import com.example.user.DTO.OTPVerificationRequest;
import com.example.user.DTO.UserRequestDTO;
import com.example.user.DTO.UserResponseDTO;

public interface UserService {
	public String createNewUser(UserRequestDTO userRequestDTO,DoctorRequestDTO doctorRequestDTO,MultipartFile file) throws IOException;
	public int otpVerification(OTPVerificationRequest otpVerificationRequest);
	public long totalUser();
	public Page<UserResponseDTO> getAllUser(int pageSize,int pageNo);
	public String deleteUser(int id);
	public List<UserResponseDTO> getUserByRole(String role);

}
