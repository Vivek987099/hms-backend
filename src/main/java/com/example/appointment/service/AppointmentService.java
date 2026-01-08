package com.example.appointment.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.appointment.Enum.AppointmentEnum.AppointmentStatus;
import com.example.appointment.dto.AppointmentRequestDTO;
import com.example.appointment.dto.AppointmentResponseDTO;
import com.example.appointment.dto.AppointmentUpadateRequestDTO;

public interface AppointmentService {
	
	public Page<AppointmentResponseDTO> getAllAppointment(AppointmentStatus filterBy,int pageSize,int pageNo);
	public Long totalNoOfAppointments();
	public String createAppointment(AppointmentRequestDTO appointmentRequestDTO);
	
	public List<AppointmentStatusCount> appointmentStatusCount();
	public List<AppointmentResponseDTO> getRecentAppointment(int pageSize);
	
	public String updateAppointment(int id ,AppointmentUpadateRequestDTO appointmentUpadateRequestDTO);
	
	

}
