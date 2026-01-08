package com.example.doctor.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.example.appointment.entity.Appointments;
import com.example.department.entity.Department;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int doctorId;
	@Column(nullable = false)
	private String doctorName;
	@Column(nullable = false)
	private String specialization;
	@Column(nullable = false)
	private float fee;
	@Column
	private String profilePhotoUrl;
	@Column
	private LocalDate createdAt;
	@Column
	private String email;

//	RELATION FOR APPOINTMENTS

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	@JsonManagedReference(value = "docoterAppointment")
	List<Appointments> appointments = new ArrayList<>();

//	RELATION FOR DOCTOR SCHEDULE
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
	List<DoctorSchedule> schedules = new ArrayList<>();

	@ManyToOne
	@JoinColumn(name = "departId")
	Department department = new Department();

	public Doctor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Doctor(int doctorId, String doctorName, String specialization, float fee, String profilePhotoUrl,
			LocalDate createdAt, String email, List<Appointments> appointments, List<DoctorSchedule> schedules,
			Department department) {
		super();
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.fee = fee;
		this.profilePhotoUrl = profilePhotoUrl;
		this.createdAt = createdAt;
		this.email = email;
		this.appointments = appointments;
		this.schedules = schedules;
		this.department = department;
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

	public List<DoctorSchedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<DoctorSchedule> schedules) {
		this.schedules = schedules;
	}

	public List<Appointments> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Appointments> appointments) {
		this.appointments = appointments;
	}

	public String getProfilePhotoUrl() {
		return profilePhotoUrl;
	}

	public void setProfilePhotoUrl(String profilePhotoUrl) {
		this.profilePhotoUrl = profilePhotoUrl;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
