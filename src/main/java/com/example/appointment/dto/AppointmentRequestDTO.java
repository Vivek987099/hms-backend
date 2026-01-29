package com.example.appointment.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;


public class AppointmentRequestDTO {
	@NotNull(message = "This field is required")
	private LocalDate date;
	@NotNull(message = "This field is required")
	private LocalTime time;
	@NotNull(message = "This field is required")
	private Integer doctorId;
	@NotNull(message = "This field is required")
	private Integer patientId;

	public AppointmentRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AppointmentRequestDTO(LocalDate date, LocalTime time, Integer doctorId, Integer patientId) {
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

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}


}
