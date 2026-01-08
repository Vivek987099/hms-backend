package com.example.patient.DTO;

import java.time.LocalDate;

public class PatientResponseDTO {
	private int patientId;
	private String patientName;
	private String gender;
	private int age;
	private String phone;
	private String adderes;
	private LocalDate createdAt;;

	public PatientResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	




	public PatientResponseDTO(int patientId, String patientName, String gender, int age, String phone, String adderes,
			LocalDate createdAt) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.adderes = adderes;
		this.createdAt = createdAt;
	}






	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
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



	public LocalDate getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}






	public int getAge() {
		return age;
	}






	public void setAge(int age) {
		this.age = age;
	}
	
	

}
