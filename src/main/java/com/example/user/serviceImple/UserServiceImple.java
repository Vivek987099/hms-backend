package com.example.user.serviceImple;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Public.ConvertToDTO;
import com.example.department.entity.Department;
import com.example.department.repository.DepartmentRepository;
import com.example.doctor.DTO.DoctorRequestDTO;
import com.example.doctor.entity.Doctor;
import com.example.doctor.repository.DoctorRepository;
import com.example.exception.NullFileException;
import com.example.helperClasses.FileUpload;
import com.example.mail.service.MailService;
import com.example.tempUser.entity.TempUser;
import com.example.tempUser.repository.TempUserRepository;
import com.example.user.DTO.OTPVerificationRequest;
import com.example.user.DTO.UserRequestDTO;
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
	private DoctorRepository doctorRepository;
	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private FileUpload fileUpload;

	@Autowired
	private TempUserRepository tempUserRepository;
	@Autowired
	private ConvertToDTO convertToDTO;

	UserServiceImple(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public String createNewUser(UserRequestDTO userRequestDTO, DoctorRequestDTO doctorRequestDTO, MultipartFile file) throws IOException {
		String generatedOtp = String.valueOf(100000 + new Random().nextInt(900000));
		
		
		
		TempUser tempUser = new TempUser();
		tempUser.setUsername(userRequestDTO.getUsername());
		tempUser.setPassword(userRequestDTO.getPassword());
		tempUser.setRole(userRequestDTO.getRole());
		tempUser.setExpiredAt(LocalDateTime.now().plusMinutes(10));
		tempUser.setOtp(generatedOtp);
		if(file == null) {
			throw new NullFileException("file can't be null or empty");
		}

		if (convertToDTO.isDoctorProvided(doctorRequestDTO) && file != null) {
			Path profileUrl = this.fileUpload.uploadFile(file);
			tempUser.setDoctorName(doctorRequestDTO.getDoctorName());
			tempUser.setFee(doctorRequestDTO.getFee());
			tempUser.setSpecialization(doctorRequestDTO.getSpecialization());
			tempUser.setDepartId(doctorRequestDTO.getDepartmentId());
			tempUser.setProfilePhotoUrl(profileUrl.toString());
		}

		
		
		if(userRequestDTO.getUsername() ==null || userRequestDTO.getUsername().isBlank()) {
			throw new IllegalArgumentException("Email can not be null or empty");
		
		}
		
		 boolean status =	mailService.sendMail("verification", userRequestDTO.getUsername(),
				"Your One Time Password (OTP)  is " + generatedOtp + " valid for only 10 minutes.");
		
		if (status) {
			tempUserRepository.save(tempUser);
			return "We have sent an OTP to your email";
		} else {
			return "Try again to sent OTP";
		}
	}

	@Override
	public long totalUser() {
		return userRepository.count();

	}

	@Transactional
	@Override
	public int otpVerification(OTPVerificationRequest otpVerificationRequest) {
		String username = otpVerificationRequest.getUsername();
		String otp = otpVerificationRequest.getOtp();
		TempUser tempUser = tempUserRepository.findByUsername(username)
				.orElseThrow(() -> new RuntimeException("not found "));
		if (!tempUser.getOtp().equals(otp)) {
			throw new RuntimeException("OTP Invalid");
		}
		if (tempUser.getExpiredAt().isBefore(LocalDateTime.now())) {
			throw new RuntimeException("OTP expired");
		}

		User user = new User();
		user.setUsername(tempUser.getUsername());
		user.setPassword(passwordEncoder.encode(tempUser.getPassword()));
		user.setCreatedAt(LocalDate.now());
		user.setRole(tempUser.getRole());
		user.setStatus(true);
		

		User savedUser = userRepository.save(user);
		if (tempUser.getRole().equals("DOCTOR")) {
			Department department = this.departmentRepository.findById(tempUser.getDepartId())
					.orElseThrow(() -> new RuntimeException("Department not found"));
			Doctor doctor = new Doctor();
			doctor.setDoctorName(tempUser.getDoctorName());
			doctor.setSpecialization(tempUser.getSpecialization());
			doctor.setFee(tempUser.getFee());
			doctor.setDepartment(department);
			doctor.setCreatedAt(LocalDate.now());
			doctor.setProfilePhotoUrl(tempUser.getProfilePhotoUrl());
			doctor.setUser(savedUser);

			this.doctorRepository.save(doctor);
		}

		mailService.sendMail("Information", otpVerificationRequest.getUsername(),
				"Hey! you are now admin of Hospital Management System.");
		tempUserRepository.deleteByUsername(otpVerificationRequest.getUsername());
		return savedUser.getId();
	}

	@Override
	public Page<UserResponseDTO> getAllUser(int pageSize, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
		Page<User> userPage = userRepository.findAll(pageable);
		return userPage.map(user -> convertToDTO.convertToUserResponseDTO(user));
	}

	@Override
	public String deleteUser(int id) {
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found with this id"));
		userRepository.delete(user);
		return "User delete successfully";
	}

	@Override
	public List<UserResponseDTO> getUserByRole(String role) {
		List<User> userList = this.userRepository.findByRole(role);
		List<UserResponseDTO> dtoList = userList.stream().map(user -> convertToDTO.convertToUserResponseDTO(user))
				.toList();
		return dtoList;
	}

	
}
