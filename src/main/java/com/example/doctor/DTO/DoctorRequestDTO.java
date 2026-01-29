package com.example.doctor.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class DoctorRequestDTO {
	@NotBlank(message = "Name required")
	@Pattern(regexp = "^[A-Za-z ]+$",message = "Digits,Special symbols are not allowed")
	@Size(min = 3,max = 30,message = "Name can be 3 to 30 chars")
	private String doctorName;
	@NotBlank(message = "This field is mandatory")
	private String specialization;
	@NotNull(message = "This field is mandatory")
	private Float fee;
	private String profilePhotoUrl;
	@NotNull(message = "Select Department")
	private Integer departmentId;

	public DoctorRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public DoctorRequestDTO(String doctorName, String specialization, Float fee, String profilePhotoUrl,
			Integer departmentId) {
		super();
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.fee = fee;
		this.profilePhotoUrl = profilePhotoUrl;
		this.departmentId = departmentId;
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

	public Float getFee() {
		return fee;
	}

	public void setFee(Float fee) {
		this.fee = fee;
	}

	public String getProfilePhotoUrl() {
		return profilePhotoUrl;
	}

	public void setProfilePhotoUrl(String profilePhotoUrl) {
		this.profilePhotoUrl = profilePhotoUrl;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}



	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	

}
