package com.example.doctor.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.doctor.DTO.DoctorRequestDTO;
import com.example.doctor.DTO.DoctorResponseDTO;
import com.example.doctor.service.DoctorService;

@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "http://localhost:5173")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@PostMapping("/create-doctor")
	public ResponseEntity<?> saveDoctor(@RequestPart("doctor") DoctorRequestDTO doctorRequestDTO, @RequestPart("file") MultipartFile file)
			throws IOException {
		 String message= doctorService.saveDoctor(doctorRequestDTO, file);
		return ResponseEntity.ok(Map.of("message",message));
	}

	@GetMapping("/all-doctors")
	public ResponseEntity<?> getDoctors(@RequestParam(name = "pageSize", defaultValue = "5") int pageSize,
			@RequestParam(name = "pageNo", defaultValue = "0") int pageNo) {
		return ResponseEntity.ok(doctorService.getAllDoctors(pageSize, pageNo));
	}

	@GetMapping("/total-doctors")
	public ResponseEntity<?> getTotalDoctors() {
		return ResponseEntity.ok(doctorService.totalDoctors());
	}

	@DeleteMapping("/delete-doctor/{id}")
	public ResponseEntity<?> deleteDoctor(@PathVariable int id) {
		String message = doctorService.deleteDoctorWithId(id);
		return ResponseEntity.ok(Map.of("message", message));

	}

	@GetMapping("/allDoctors-without-pageable")
	public ResponseEntity<List<DoctorResponseDTO>> allDoctorsWithoutPageable() {
		return ResponseEntity.ok(doctorService.allDoctorsWithoutPageable());
	}
	@PutMapping("/update-doctor/{id}")
	public ResponseEntity<?> updateDoctorWithId(@PathVariable int id,@RequestBody DoctorRequestDTO doctorRequestDTO){
		String message= doctorService.updateDoctor(id, doctorRequestDTO);
		return ResponseEntity.ok(Map.of("message",message));
		
	}
	@GetMapping("/doctor-by-department/{id}")
	public ResponseEntity<List<DoctorResponseDTO>> doctorByDepartment(@PathVariable int id){
		return ResponseEntity.ok(doctorService.getDoctorByDepartment(id));
	}

}
