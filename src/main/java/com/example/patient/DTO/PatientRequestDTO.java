package com.example.patient.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class PatientRequestDTO {
	@NotBlank(message = "Name required")
	@Pattern(regexp = "^[A-Za-z ]+$",message = "Digits,Special symbols are not allowed")
	@Size(min = 3,max = 30,message = "Name can be 3 to 30 chars")
	private String patientName;
	@NotBlank(message = "Gender required")
	private String gender;
	@NotNull(message = "Age can't be null")
	@PositiveOrZero
	private Integer age;
	@NotBlank(message = "Please enter mobile")
	@Pattern(regexp = "^[5-9][0-9]{9}$" , message = "Phone number should be exactly 10 digits and not start with 0-4")
	private String phone;
	private String adderes;

	public PatientRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PatientRequestDTO(String patientName, String gender, Integer age, String phone, String adderes) {
		super();

		this.patientName = patientName;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.adderes = adderes;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAdderes() {
		return adderes;
	}

	public void setAdderes(String adderes) {
		this.adderes = adderes;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

}
