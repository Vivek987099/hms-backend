package com.example.patient.serviceImple;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Public.ConvertToDTO;
import com.example.appointment.entity.Appointments;
import com.example.doctor.entity.Doctor;
import com.example.doctor.repository.DoctorRepository;
import com.example.patient.DTO.PatientRequestDTO;
import com.example.patient.DTO.PatientResponseDTO;
import com.example.patient.entity.Patient;
import com.example.patient.repository.PatientRepository;
import com.example.patient.service.PatientMonthWise;
import com.example.patient.service.PatientService;

@Service
public class PatientServiceImple implements PatientService {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private ConvertToDTO convertToDTO;

//	METHOD FOR CONVERT PATIENT INTO PATIENT RESPONSE DTO

	@Override
	public String savePatient(PatientRequestDTO patientRequestDTO) {

		Patient patient = new Patient();
		patient.setPatientName(patientRequestDTO.getPatientName());
		patient.setAge(patientRequestDTO.getAge());
		patient.setAdderes(patientRequestDTO.getAdderes());

		patient.setGender(patientRequestDTO.getGender());
		patient.setPhone(patientRequestDTO.getPhone());
		patient.setCreateAt(LocalDate.now());
		patient.setDeleted(false);

		 patientRepository.save(patient);
		 
		 return "Registered new patient";
		

	}

	@Override
	public Page<PatientResponseDTO> getAllPatients(int pageSize, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createAt").descending());
		Page<Patient> patientPages = patientRepository.findByIsDeletedFalse(pageable);
		Page<PatientResponseDTO> patientResPage = patientPages
				.map(patient -> convertToDTO.convetToPatientResponseDTO(patient));

		return patientResPage;
	}

	@Override
	public Patient regsiterPtWithAp(Patient patient) {

		for (Appointments a : patient.getAppointments()) {
			a.setPatient(patient);

			Doctor appointedDoctor = doctorRepository.findById(a.getDoctor().getDoctorId())
					.orElseThrow(() -> new RuntimeException("doctor is not found with this id "));
			a.setDoctor(appointedDoctor);

		}

		return patientRepository.save(patient);
	}

	@Override
	public long totalPatientNo() {
		return patientRepository.count();
	}

	@Override
	public List<PatientMonthWise> patientMonthWises() {

		return patientRepository.getPatientMonthWise();
	}

	@Override
	public List<PatientResponseDTO> getRecentPatients(int pageSize) {
		Pageable pageable = PageRequest.ofSize(pageSize);
		List<PatientResponseDTO> patientResponseDTOsList = new ArrayList<>();
		List<Patient> patientsList = patientRepository.findAll(pageable).getContent();

		for (Patient patient : patientsList) {
			PatientResponseDTO patientResponseDTO = convertToDTO.convetToPatientResponseDTO(patient);
			patientResponseDTOsList.add(patientResponseDTO);

		}
		return patientResponseDTOsList;
	}
		@Override
		public String softDeletePatient(int id) {
			Patient patient= patientRepository.findById(id).orElseThrow(()-> new RuntimeException("Patient is not found with this id "+id));
			
			patient.setDeleted(true);
			patientRepository.save(patient);			
			return "Patient deleted";
		}
		
		
		@Override
		public String updatePatient(int id, PatientRequestDTO patientRequestDTO) {
			
			Patient patient= patientRepository.findById(id).orElseThrow(()-> new RuntimeException("patient is not found with this id "+id));
			if(patientRequestDTO.getPatientName() != null || !patientRequestDTO.getPatientName().trim().isEmpty()) {
				patient.setPatientName(patientRequestDTO.getPatientName());
			}
			if(patientRequestDTO.getGender() != null || !patientRequestDTO.getGender().trim().isEmpty()) {
				patient.setGender(patientRequestDTO.getGender());
			}
			if(patientRequestDTO.getAdderes() != null || !patientRequestDTO.getAdderes().trim().isEmpty()) {
				patient.setAdderes(patientRequestDTO.getAdderes());
			}
			if(patientRequestDTO.getPhone() != null || !patientRequestDTO.getPhone().trim().isEmpty()) {
				patient.setPhone(patientRequestDTO.getPhone());
			}
			if(patientRequestDTO.getAge() != null) {
				patient.setAge(patientRequestDTO.getAge());
			}
			
			patientRepository.save(patient);
			return "Patient updated";
		}
}
