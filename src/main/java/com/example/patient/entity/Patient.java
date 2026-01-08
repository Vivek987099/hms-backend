package com.example.patient.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.appointment.entity.Appointments;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int patientId;
	@Column(nullable = false)
	private String patientName;
	@Column(nullable = false)
	private int age;
	@Column(nullable = false)
	private String gender;
	@Column(nullable = false)
	private String phone;
	@Column(nullable = false)
	private String adderes;
	@Column
	private boolean isDeleted = false;
	@Column
	private LocalDate createAt;
	@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "patientAppointment")
	private List<Appointments> appointments = new ArrayList<>();

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Patient(int patientId, String patientName, int age, String gender, String phone, String adderes,
			boolean isDeleted, LocalDate createAt, List<Appointments> appointments) {
		super();
		this.patientId = patientId;
		this.patientName = patientName;
		this.age = age;
		this.gender = gender;
		this.phone = phone;
		this.adderes = adderes;
		this.isDeleted = isDeleted;
		this.createAt = createAt;
		this.appointments = appointments;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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

	public List<Appointments> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointments> appointments) {
		this.appointments = appointments;
	}

	public LocalDate getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDate createAt) {
		this.createAt = createAt;
	}



	public boolean isDeleted() {
		return isDeleted;
	}



	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	

}
