package com.example.doctor.serviceImple;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import com.example.user.DTO.CustomUserDetails;
import com.example.user.DTO.CustomUserDetailsService;
import com.example.user.entity.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Public.ConvertToDTO;
import com.example.department.entity.Department;
import com.example.department.repository.DepartmentRepository;
import com.example.doctor.DTO.DoctorRequestDTO;
import com.example.doctor.DTO.DoctorResponseDTO;
import com.example.doctor.entity.Doctor;
import com.example.doctor.repository.DoctorRepository;
import com.example.doctor.service.DoctorService;
import com.example.helperClasses.FileUpload;
import com.example.mail.service.MailService;

@Service
public class DoctorServiceImple implements DoctorService {

	private final UserRepository userRepository;

	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private FileUpload fileUpload;
	@Autowired
	private ConvertToDTO convertToDTO;
	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private MailService mailService;

	DoctorServiceImple(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public Page<DoctorResponseDTO> getAllDoctors(int pageSize, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
		Page<Doctor> doctorPage = doctorRepository.findAll(pageable);
		return doctorPage.map(doctor -> convertToDTO.convertToDoctorResponseDTO(doctor));

	}

	@Override
	public String saveDoctor(int id, DoctorRequestDTO doctorRequestDTO, MultipartFile file) throws IOException {
		
		Doctor doctor = new Doctor();
//
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("user not found"));
		Department department = departmentRepository.findById(doctorRequestDTO.getDepartmentId()).orElseThrow(
				() -> new RuntimeException("Department not found with this id" + doctorRequestDTO.getDepartmentId()+" department"));

		Path url = fileUpload.uploadFile(file);
		doctor.setDoctorName(doctorRequestDTO.getDoctorName());
		doctor.setSpecialization(doctorRequestDTO.getSpecialization());
		doctor.setFee(doctorRequestDTO.getFee());
		doctor.setProfilePhotoUrl(url.toString());
		doctor.setCreatedAt(LocalDate.now());
		doctor.setDepartment(department);
		doctor.setUser(user);
		 boolean status=  mailService.sendMail("Today's information", user.getUsername(), "Dear "
				+ doctorRequestDTO.getDoctorName() + " today you will work in " + department.getDepartmentName());
		
		 if(status) {
			 
			 doctorRepository.save(doctor);
			 return "Doctor created";
		 }else {
			return "Try again";
		}
	}

	@Override
	public long totalDoctors() {
		return doctorRepository.count();
	}

	@Override
	public String deleteDoctorWithId(int id) {
		Doctor doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Doctor not found with this id" + id));
		doctorRepository.delete(doctor);
		return "Doctore Deleted";
	}

	@Override
	public List<DoctorResponseDTO> allDoctorsWithoutPageable() {
		List<Doctor> doctorsList = doctorRepository.findAll();
		return doctorsList.stream().map(doctor -> convertToDTO.convertToDoctorResponseDTO(doctor)).toList();
	}

	@Override
	public String updateDoctor(int id, DoctorRequestDTO doctorRequestDTO) {
		Doctor doctor = doctorRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("doctor not found with this id " + id));
		Department department = departmentRepository.findById(doctorRequestDTO.getDepartmentId())
				.orElseThrow(() -> new RuntimeException(
						"Department is not found with this id" + doctorRequestDTO.getDepartmentId()));
		if (doctorRequestDTO.getDoctorName() != null || !doctorRequestDTO.getDoctorName().trim().isBlank()) {
			doctor.setDoctorName(doctorRequestDTO.getDoctorName());
		}

		if (doctorRequestDTO.getFee() != null) {
			doctor.setFee(doctorRequestDTO.getFee());
		}
		if (doctor.getProfilePhotoUrl() != null && !doctorRequestDTO.getProfilePhotoUrl().trim().isEmpty()) {
			doctor.setProfilePhotoUrl(doctorRequestDTO.getProfilePhotoUrl());
		}
		if (doctorRequestDTO.getDepartmentId() != null) {
			doctor.setDepartment(department);
		}
		if (doctorRequestDTO.getSpecialization() != null || !doctorRequestDTO.getSpecialization().trim().isEmpty()) {
			doctor.setSpecialization(doctorRequestDTO.getSpecialization());
		}
		doctorRepository.save(doctor);

		return "Doctor Updated Successfully";
	}

	@Override
	public List<DoctorResponseDTO> getDoctorByDepartment(int departmentId) {
		Department department = departmentRepository.findById(departmentId)
				.orElseThrow(() -> new RuntimeException("Department is not found with this id " + departmentId));
		List<Doctor> doctorList = doctorRepository.findByDepartment(department);
		return doctorList.stream().map(doctor -> convertToDTO.convertToDoctorResponseDTO(doctor)).toList();
	}
	
	@Override
	public DoctorResponseDTO doctorProfile() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	CustomUserDetails customUserDetails =(CustomUserDetails)	 authentication.getPrincipal();
		 User user =	customUserDetails.getUser();
		 Doctor doctor= doctorRepository.findByUser(user).orElseThrow(()-> new RuntimeException("not found"));
		return convertToDTO.convertToDoctorResponseDTO(doctor);
	}
}
