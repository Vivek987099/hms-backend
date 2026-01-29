package com.example.doctor.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.doctor.DTO.DoctorScheduleRequestDTO;
import com.example.doctor.Enum.DoctorDayEnum.DoctorDay;
import com.example.doctor.serviceImple.DoctorScheduleServiceImple;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/doctor-schedule")
public class DoctorScheduleController {

	@Autowired
	private DoctorScheduleServiceImple doctorScheduleServiceImple;

	
	@PostMapping("/make-schedule")
	public ResponseEntity<?> createSchedule(@Valid @RequestBody DoctorScheduleRequestDTO doctorScheduleRequestDTO) {
		String message = doctorScheduleServiceImple.makeSchedule(doctorScheduleRequestDTO);
		return ResponseEntity.ok(Map.of("message", message));
	}

	@GetMapping("/all-schedule")
	public ResponseEntity<?> doctorSchedule(@RequestParam(name = "filterBy", required = false) DoctorDay doctorDay,
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "pageNo", defaultValue = "0") int pageNo) {
		return ResponseEntity.ok(doctorScheduleServiceImple.getDoctorSchedule(doctorDay, pageSize, pageNo));
	}
	@DeleteMapping("/delete-schedule/{id}")
	public ResponseEntity<?> deleteSchedule(@PathVariable int id){
		String message = doctorScheduleServiceImple.deleteById(id);
		return ResponseEntity.ok(Map.of("message",message));
		
	}
	@PutMapping("/update-schedule/{id}")
	public ResponseEntity<?> updateSchedule(@PathVariable int id ,@RequestBody DoctorScheduleRequestDTO doctorScheduleRequestDTO){
		String message = doctorScheduleServiceImple.updateById(id, doctorScheduleRequestDTO);
		return ResponseEntity.ok(Map.of("message",message));
	}
	

}
