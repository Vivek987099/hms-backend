package com.example.doctor.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.doctor.Enum.DoctorDayEnum.DoctorDay;

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
public class DoctorSchedule {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Integer scheduleId;
	
	@Column
	@Enumerated(EnumType.STRING)
	 private DoctorDay day;
	@Column
	 private LocalTime startTime;
	@Column
	 private LocalTime endTime;
	@Column
	 private LocalDate createdAt;
	@ManyToOne
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
	public DoctorSchedule() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public DoctorSchedule(Integer scheduleId, DoctorDay day, LocalTime startTime, LocalTime endTime,
			LocalDate createdAt, Doctor doctor) {
		super();
		this.scheduleId = scheduleId;
		this.day = day;
		this.startTime = startTime;
		this.endTime = endTime;
		this.createdAt = createdAt;
		this.doctor = doctor;
	}

	public Integer getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
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
	
	

}
