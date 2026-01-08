package com.example.patient.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.patient.DTO.PatientRequestDTO;
import com.example.patient.DTO.PatientResponseDTO;
import com.example.patient.entity.Patient;
import com.example.patient.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {

	@Autowired
	private PatientService patientService;

	@PostMapping("/register-patient")
	public ResponseEntity<?> registerPatient(@RequestBody PatientRequestDTO patientRequestDTO) {
		try {
		String message=	patientService.savePatient(patientRequestDTO);
			return ResponseEntity.ok(Map.of("message",message));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("There are some error");
		}
	}

	@GetMapping("/all-patients")
	public ResponseEntity<?> getAllPatients(@RequestParam(name = "pageSize",defaultValue = "5") int pageSize,@RequestParam(name = "pageNo",defaultValue = "0") int pageNo) {
		return ResponseEntity.ok(patientService.getAllPatients(pageSize,pageNo));
	}

	@PostMapping("/registerPatientWithAppointment")
	public ResponseEntity<?> registerPWithAppointment(@RequestBody Patient patient) {
		return ResponseEntity.ok(patientService.regsiterPtWithAp(patient));

	}

	@GetMapping("/total-patient-no")
	public ResponseEntity<?> getTotalPatientsNo() {
		return ResponseEntity.ok(patientService.totalPatientNo());
	}

	@GetMapping("/monthwise-patients")
	public ResponseEntity<?> getPatientMonthCount() {
		return ResponseEntity.ok(patientService.patientMonthWises());
	}

	@GetMapping("/recent-patients")
	public ResponseEntity<List<PatientResponseDTO>> recentPatients(
			@RequestParam(name = "pageSize", defaultValue = "5") int pageSize) {
		return ResponseEntity.ok(patientService.getRecentPatients(pageSize));
	}
	@DeleteMapping("/deletePatient/{id}")
	public ResponseEntity<?>  softDeleteById(@PathVariable int id){
	String message =	patientService.softDeletePatient(id);
	return ResponseEntity.ok(Map.of("message",message));
	}
	@PutMapping("/update-patient/{id}")
	public ResponseEntity<?> updatePatient(@PathVariable int id ,@RequestBody PatientRequestDTO patientRequestDTO){
		String message = patientService.updatePatient(id, patientRequestDTO);
		return ResponseEntity.ok(Map.of("message",message));
	}
	

}
