package com.example.doctor.DTO;

import java.time.LocalTime;

import com.example.doctor.Enum.DoctorDayEnum.DoctorDay;

public class DoctorScheduleRequestDTO {

	private DoctorDay day;
	private LocalTime startTime;
	private LocalTime endTime;
	private Integer doctorId;

	public DoctorScheduleRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DoctorScheduleRequestDTO(DoctorDay day, LocalTime startTime, LocalTime endTime, Integer doctorId) {
		super();
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.doctorId = doctorId;
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

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

}
