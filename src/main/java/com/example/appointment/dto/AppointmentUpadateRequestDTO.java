package com.example.appointment.dto;


import com.example.appointment.Enum.AppointmentEnum.AppointmentStatus;


public class AppointmentUpadateRequestDTO {
	
	private AppointmentStatus status;
	public AppointmentUpadateRequestDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AppointmentUpadateRequestDTO(AppointmentStatus status) {
		super();
		this.status = status;
	}


	public AppointmentStatus getStatus() {
		return status;
	}
	public void setStatus(AppointmentStatus status) {
		this.status = status;
	}
	
	
	
	
	
	
	

}
