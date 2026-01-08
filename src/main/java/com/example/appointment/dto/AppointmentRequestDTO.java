package com.example.appointment.dto;

import java.time.LocalDate;
import java.time.LocalTime;


public class AppointmentRequestDTO {

	private LocalDate date;
	private LocalTime time;
	private int doctorId;
	private int patientId;

	public AppointmentRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppointmentRequestDTO(LocalDate date, LocalTime time, int doctorId, int patientId) {
		super();
		this.date = date;
		this.time = time;
		this.doctorId = doctorId;
		this.patientId = patientId;
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

	public int getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}


}
