package com.example.patient.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.patient.DTO.PatientRequestDTO;
import com.example.patient.DTO.PatientResponseDTO;
import com.example.patient.entity.Patient;

public interface PatientService {
	

	// REGISTER NEW PATIENT 
	public String savePatient(PatientRequestDTO patientRequestDTO);
	
//	REGISTER PATIENT WITH APPOINTMENT
	public Patient regsiterPtWithAp(Patient patient);

	// GET ALL PATIENTS
	public Page<PatientResponseDTO> getAllPatients(int pageSize,int pageNo);
	
	public long totalPatientNo();
	
	public List<PatientMonthWise> patientMonthWises();
	
	public List<PatientResponseDTO> getRecentPatients(int pageSize);
	
	public String softDeletePatient(int id);
	
	public String updatePatient(int id,PatientRequestDTO patientRequestDTO);

	
	
	
	

}
