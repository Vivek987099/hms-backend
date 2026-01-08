package com.example.user.serviceImple;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Public.ConvertToDTO;
import com.example.mail.service.MailService;
import com.example.tempUser.entity.TempUser;
import com.example.tempUser.repository.TempUserRepository;
import com.example.user.DTO.CustomUserDetails;
import com.example.user.DTO.OTPVerificationRequest;
import com.example.user.DTO.UserResponseDTO;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import com.example.user.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImple implements UserService {

	private final PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MailService mailService;

	@Autowired
	private TempUserRepository tempUserRepository;
	@Autowired
	private ConvertToDTO convertToDTO;

	UserServiceImple(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String createNewUser(User user) {
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
		String generatedOtp = String.valueOf(100000 + new Random().nextInt(900000));
		TempUser tempUser = new TempUser();
		tempUser.setUsername(user.getUsername());
		tempUser.setPassword(user.getPassword());
		tempUser.setRole(user.getRole());
		tempUser.setExpiredAt(LocalDateTime.now().plusMinutes(10));
		tempUser.setOtp(generatedOtp);
		boolean status = mailService.sendMail("verification", user.getUsername(),
				"Your One Time Password (OTP)  is " + generatedOtp + " valid for only 10 minutes.");

		if (status) {
			tempUserRepository.save(tempUser);
			return "We have sent an OTP to your email";
		} else {
			return "Try again to sent OTP";
		}

	}

	@Override
	public User getUserProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		User user = customUserDetails.getUser();
		return user;
	}

	@Override
	public long totalUser() {
		return userRepository.count();

	}

	@Transactional
	@Override
	public String otpVerification(OTPVerificationRequest otpVerificationRequest) {
		String username = otpVerificationRequest.getUsername();
		String otp = otpVerificationRequest.getOtp();
		TempUser tempUser = tempUserRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("not found "));
		if (!tempUser.getOtp().equals(otp)) {
			throw new RuntimeException("OTP Invalid");
		}
		User user = new User();

		user.setUsername(tempUser.getUsername());
		user.setPassword(passwordEncoder.encode(tempUser.getPassword()));
		user.setCreatedAt(LocalDate.now());
		user.setRole(tempUser.getRole());
		user.setStatus(true);
		userRepository.save(user);

		mailService.sendMail("Information", otpVerificationRequest.getUsername(),
				"Hey! you are now admin of Hospital Management System.");
		tempUserRepository.deleteByUsername(otpVerificationRequest.getUsername());

		return "Registered Successfuly";
	}

	@Override
	public Page<UserResponseDTO> getAllUser(int pageSize, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
		Page<User> userPage = userRepository.findAll(pageable);
		return userPage.map(user -> convertToDTO.convertToUserResponseDTO(user));
	}
	
	@Override
	public String deleteUser(int id) {
			 User user=  userRepository.findById(id).orElseThrow(()-> new RuntimeException("User not found with this id"));
			 userRepository.delete(user);
			 
			 return "User delete successfully";
	}
}
