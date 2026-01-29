package com.example.appointment.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.appointment.Enum.AppointmentEnum.AppointmentStatus;
import com.example.appointment.dto.AppointmentRequestDTO;
import com.example.appointment.dto.AppointmentResponseDTO;
import com.example.appointment.dto.AppointmentUpadateRequestDTO;
import com.example.appointment.service.AppointmentStatusCount;
import com.example.appointment.serviceImple.AppointmentServiceImple;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {
	@Autowired
	private AppointmentServiceImple appointmentServiceImple;

	@GetMapping("/all-appointments")
	public ResponseEntity<?> getAppointments(@RequestParam(name = "filterBy",required = false) AppointmentStatus filterBy,@RequestParam(name = "pageSize", defaultValue = "2") int pageSize,
			@RequestParam(name = "pageNo", defaultValue = "0") int pageNo) {
		return ResponseEntity.ok(appointmentServiceImple.getAllAppointment(filterBy,pageSize, pageNo));
	}

	@GetMapping("/total-appointment")
	public ResponseEntity<?> totalAppointments() {
		return ResponseEntity.ok(appointmentServiceImple.totalNoOfAppointments());
	}

	@PostMapping("/create-appointment")
	public ResponseEntity<?> makeAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO) {
	
		String message= appointmentServiceImple.createAppointment(appointmentRequestDTO);
		return ResponseEntity.ok(Map.of("message",message));
	}

	@GetMapping("/status-count")
	public ResponseEntity<List<AppointmentStatusCount>> getApntmntStatusCount() {
		return ResponseEntity.ok(appointmentServiceImple.appointmentStatusCount());
	}

	@GetMapping("/recent-appointments")
	public ResponseEntity<List<AppointmentResponseDTO>> recentAppointment(
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
		return ResponseEntity.ok(appointmentServiceImple.getRecentAppointment(pageSize));
	}
	
	@PutMapping("/update-status/{id}")
	public ResponseEntity<?> updateAppointmentById(@PathVariable int id,@RequestBody AppointmentUpadateRequestDTO appointmentUpadateRequestDTO){
	 String message= appointmentServiceImple.updateAppointment(id, appointmentUpadateRequestDTO);
	 return ResponseEntity.ok(Map.of("message",message));
		
	}
	
	@GetMapping("/appointment-by-doctor")
	public ResponseEntity<?> getAppointmentByDoctor(){
		return ResponseEntity.ok(this.appointmentServiceImple.appointmentByDoctor());
	}

}
