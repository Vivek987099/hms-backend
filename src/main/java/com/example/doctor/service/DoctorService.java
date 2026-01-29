package com.example.doctor.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import com.example.doctor.DTO.DoctorRequestDTO;
import com.example.doctor.DTO.DoctorResponseDTO;

public interface DoctorService {
	
	public String saveDoctor(int id,DoctorRequestDTO doctorRequestDTO,MultipartFile file) throws IOException;
	public Page<DoctorResponseDTO> getAllDoctors(int pageSize,int pageNo);
	public long totalDoctors();
	public String deleteDoctorWithId(int id);
	
	public List<DoctorResponseDTO> allDoctorsWithoutPageable();
	
	public String updateDoctor(int id,DoctorRequestDTO doctorRequestDTO);
	
	public List<DoctorResponseDTO> getDoctorByDepartment(int departmentId);
	
	public DoctorResponseDTO doctorProfile();
	
	

}
