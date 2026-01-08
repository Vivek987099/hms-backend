package com.example.appointment.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.appointment.Enum.AppointmentEnum.AppointmentStatus;
import com.example.doctor.DTO.DoctorResponseDTO;
import com.example.patient.DTO.PatientResponseDTO;

public class AppointmentResponseDTO {
	private int appointmentId;
	private LocalDate date;
	private LocalTime time;
	private DoctorResponseDTO doctorResponseDTO;
	private PatientResponseDTO patientResponseDTO;
	private AppointmentStatus status;
	public AppointmentResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public AppointmentResponseDTO(int appointmentId, LocalDate date, LocalTime time,
			DoctorResponseDTO doctorResponseDTO, PatientResponseDTO patientResponseDTO, AppointmentStatus status) {
		super();
		this.appointmentId = appointmentId;
		this.date = date;
		this.time = time;
		this.doctorResponseDTO = doctorResponseDTO;
		this.patientResponseDTO = patientResponseDTO;
		this.status = status;
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


	public DoctorResponseDTO getDoctorResponseDTO() {
		return doctorResponseDTO;
	}


	public void setDoctorResponseDTO(DoctorResponseDTO doctorResponseDTO) {
		this.doctorResponseDTO = doctorResponseDTO;
	}


	public PatientResponseDTO getPatientResponseDTO() {
		return patientResponseDTO;
	}


	public void setPatientResponseDTO(PatientResponseDTO patientResponseDTO) {
		this.patientResponseDTO = patientResponseDTO;
	}

	
	
	

}
