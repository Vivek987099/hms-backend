package com.example.doctor.service;


import org.springframework.data.domain.Page;

import com.example.doctor.DTO.DoctorScheduleRequestDTO;
import com.example.doctor.DTO.DoctorScheduleResponseDTO;
import com.example.doctor.Enum.DoctorDayEnum.DoctorDay;

public interface DoctorScheduleService {
	
	public String makeSchedule(DoctorScheduleRequestDTO doctorScheduleRequestDTO);
	
	public Page<DoctorScheduleResponseDTO> getDoctorSchedule(DoctorDay doctorDay,int pageSize,int pageNo);
	
	public String deleteById(int id);
	
	public String updateById(int id ,DoctorScheduleRequestDTO doctorScheduleRequestDTO);

}
