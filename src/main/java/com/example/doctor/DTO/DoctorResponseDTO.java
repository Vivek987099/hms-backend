package com.example.doctor.DTO;

import java.time.LocalDate;

import com.example.department.DTO.DepartmentResponseDTO;


public class DoctorResponseDTO {
	private int doctorId;
	private String doctorName;
	private String specialization;
	private float fee;
	private String profilePhotoUrl;
	private LocalDate created_at;
	private DepartmentResponseDTO departmentResponseDTO;
	
	public DoctorResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public DoctorResponseDTO(int doctorId, String doctorName, String specialization, float fee, String profilePhotoUrl,
			LocalDate created_at,  DepartmentResponseDTO departmentResponseDTO) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.fee = fee;
		this.profilePhotoUrl = profilePhotoUrl;
		this.created_at = created_at;
		this.departmentResponseDTO = departmentResponseDTO;
	}



	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public float getFee() {
		return fee;
	}
	public void setFee(float fee) {
		this.fee = fee;
	}
	public String getProfilePhotoUrl() {
		return profilePhotoUrl;
	}
	public void setProfilePhotoUrl(String profilePhotoUrl) {
		this.profilePhotoUrl = profilePhotoUrl;
	}
	public LocalDate getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDate created_at) {
		this.created_at = created_at;
	}



	public DepartmentResponseDTO getDepartmentResponseDTO() {
		return departmentResponseDTO;
	}



	public void setDepartmentResponseDTO(DepartmentResponseDTO departmentResponseDTO) {
		this.departmentResponseDTO = departmentResponseDTO;
	}
	
	
	
	

}
