package com.example.patient.DTO;



public class PatientRequestDTO {

	private int patientId;
	private String patientName;
	private String gender;
	private Integer age;
	private String phone;
	private String adderes;
	
	public PatientRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PatientRequestDTO(int patientId, String patientName, String gender, Integer age, String phone, String adderes) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.gender = gender;
		this.age = age;
		this.phone = phone;
		this.adderes = adderes;
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
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	

}
