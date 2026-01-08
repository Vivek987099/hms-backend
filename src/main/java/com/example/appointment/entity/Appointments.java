package com.example.appointment.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.appointment.Enum.AppointmentEnum.AppointmentStatus;
import com.example.doctor.entity.Doctor;
import com.example.patient.entity.Patient;
import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table
public class Appointments {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointmentId;
	@Column
	private LocalDate date;
	@Column
	private LocalTime time;
	@Enumerated(EnumType.STRING)
	@Column
	private AppointmentStatus status;
	@Column
	private LocalDate createdAt;
	@Column
	private LocalDate updatedAt;
	@ManyToOne
	@JoinColumn(name = "patient_id")
	@JsonBackReference(value = "patientAppointment")
	private Patient patient;
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	@JsonBackReference(value = "docoterAppointment")
	private Doctor doctor;
	public Appointments() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Appointments(int appointmentId, LocalDate date, LocalTime time, AppointmentStatus status,
			LocalDate createdAt, LocalDate updatedAt, Patient patient, Doctor doctor) {
		super();
		this.appointmentId = appointmentId;
		this.date = date;
		this.time = time;
		this.status = status;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.patient = patient;
		this.doctor = doctor;
	}

	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public AppointmentStatus getStatus() {
		return status;
	}
	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDate getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDate updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	

}
