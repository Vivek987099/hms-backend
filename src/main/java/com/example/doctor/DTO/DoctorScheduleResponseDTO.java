package com.example.doctor.DTO;

import java.time.LocalTime;

import com.example.doctor.Enum.DoctorDayEnum.DoctorDay;

public class DoctorScheduleResponseDTO {

	private Integer id;
	private DoctorDay day;
	private LocalTime startTime;
	private LocalTime endTime;
	private DoctorResponseDTO doctorResponseDTO;

	public DoctorScheduleResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorScheduleResponseDTO(Integer id, DoctorDay day, LocalTime startTime, LocalTime endTime,
			DoctorResponseDTO doctorResponseDTO) {
		super();
		this.id = id;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.doctorResponseDTO = doctorResponseDTO;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DoctorDay getDay() {
		return day;
	}

	public void setDay(DoctorDay day) {
		this.day = day;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	public DoctorResponseDTO getDoctorResponseDTO() {
		return doctorResponseDTO;
	}

	public void setDoctorResponseDTO(DoctorResponseDTO doctorResponseDTO) {
		this.doctorResponseDTO = doctorResponseDTO;
	}

}
