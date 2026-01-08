package com.example.doctor.DTO;


public class DoctorRequestDTO {
	private String doctorName;
	private String specialization;
	private Float fee;
	private String profilePhotoUrl;
	private String email;
	private Integer departmentId;

	public DoctorRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public DoctorRequestDTO(String doctorName, String specialization, Float fee, String profilePhotoUrl, String email,
			Integer departmentId) {
		super();
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.fee = fee;
		this.profilePhotoUrl = profilePhotoUrl;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public Integer getDepartmentId() {
		return departmentId;
	}



	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	

}
