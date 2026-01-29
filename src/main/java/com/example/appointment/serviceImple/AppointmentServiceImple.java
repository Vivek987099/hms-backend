package com.example.appointment.serviceImple;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.Public.ConvertToDTO;
import com.example.appointment.Enum.AppointmentEnum.AppointmentStatus;
import com.example.appointment.dto.AppointmentRequestDTO;
import com.example.appointment.dto.AppointmentResponseDTO;
import com.example.appointment.dto.AppointmentUpadateRequestDTO;
import com.example.appointment.entity.Appointments;
import com.example.appointment.repository.AppointmentRepository;
import com.example.appointment.service.AppointmentService;
import com.example.appointment.service.AppointmentStatusCount;
import com.example.doctor.entity.Doctor;
import com.example.doctor.repository.DoctorRepository;
import com.example.patient.entity.Patient;
import com.example.patient.repository.PatientRepository;
import com.example.user.DTO.CustomUserDetails;
import com.example.user.entity.User;

@Service
public class AppointmentServiceImple implements AppointmentService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private ConvertToDTO convertToDTO;

	@Override
	public Page<AppointmentResponseDTO> getAllAppointment(AppointmentStatus filterBy, int pageSize, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
		if (filterBy != null) {
			Page<Appointments> appointPage = appointmentRepository.findByStatus(filterBy, pageable);
			return appointPage.map(appointment -> convertToDTO.convertToAppointmentResponseDTO(appointment));
		}
		Page<Appointments> appPage = appointmentRepository.findAll(pageable);
		return appPage.map(appointment -> convertToDTO.convertToAppointmentResponseDTO(appointment));
	}

	@Override
	public Long totalNoOfAppointments() {
		return appointmentRepository.count();
	}

	@Override
	public String createAppointment(AppointmentRequestDTO appointmentRequestDTO) {
		Appointments appointments = new Appointments();
		Doctor doctor = doctorRepository.findById(appointmentRequestDTO.getDoctorId()).orElseThrow(
				() -> new RuntimeException("doctor not found with this id " + appointments.getDoctor().getDoctorId()));
		Patient patient = patientRepository.findById(appointmentRequestDTO.getPatientId())
				.orElseThrow(() -> new RuntimeException(
						"patient not found with this id " + appointments.getPatient().getPatientId()));
		appointments.setPatient(patient);
		appointments.setDoctor(doctor);
		appointments.setStatus(AppointmentStatus.BOOKED);
		appointments.setDate(appointmentRequestDTO.getDate());
		appointments.setTime(appointmentRequestDTO.getTime());
		appointments.setCreatedAt(LocalDate.now());
		appointmentRepository.save(appointments);
		return "Appointment created";
	}

	@Override
	public List<AppointmentStatusCount> appointmentStatusCount() {
		return appointmentRepository.getAppointmentStatusCount();
	}

	@Override
	public List<AppointmentResponseDTO> getRecentAppointment(int pageSize) {
		Pageable pageable = PageRequest.ofSize(pageSize);
		List<AppointmentResponseDTO> appointmentResponseDTOsList = new ArrayList<>();
		List<Appointments> appointmentsList = appointmentRepository.findAll(pageable).getContent();
		for (Appointments appointments : appointmentsList) {
			AppointmentResponseDTO appointmentResponseDTO = convertToDTO.convertToAppointmentResponseDTO(appointments);
			appointmentResponseDTOsList.add(appointmentResponseDTO);
		}
		return appointmentResponseDTOsList;
	}

	@Override
	public String updateAppointment(int id, AppointmentUpadateRequestDTO appointmentUpadateRequestDTO) {
		Appointments appointments = appointmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Appointment is not found with this id " + id));
		appointments.setStatus(appointmentUpadateRequestDTO.getStatus());
		appointments.setUpdatedAt(LocalDate.now());
		appointmentRepository.save(appointments);
		return "Status Update";
	}

	@Override
	public List<AppointmentResponseDTO> appointmentByDoctor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
		User user = customUserDetails.getUser();
		Doctor doctor = doctorRepository.findByUser(user).orElseThrow(() -> new RuntimeException("doctor not found"));
		List<Appointments> aptList = this.appointmentRepository.findByDoctor(doctor);
		List<AppointmentResponseDTO> responseDTOs = aptList.stream()
				.map(apt -> convertToDTO.convertToAppointmentResponseDTO(apt)).toList();
		return responseDTOs;
	}

}
