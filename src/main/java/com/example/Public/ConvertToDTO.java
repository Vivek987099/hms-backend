package com.example.Public;

import org.springframework.stereotype.Component;

import com.example.appointment.dto.AppointmentResponseDTO;
import com.example.appointment.entity.Appointments;
import com.example.department.DTO.DepartmentResponseDTO;
import com.example.department.entity.Department;
import com.example.doctor.DTO.DoctorRequestDTO;
import com.example.doctor.DTO.DoctorResponseDTO;
import com.example.doctor.DTO.DoctorScheduleResponseDTO;
import com.example.doctor.entity.Doctor;
import com.example.doctor.entity.DoctorSchedule;
import com.example.patient.DTO.PatientResponseDTO;
import com.example.patient.entity.Patient;
import com.example.user.DTO.UserResponseDTO;
import com.example.user.entity.User;

@Component
public class ConvertToDTO {

	public DoctorResponseDTO convertToDoctorResponseDTO(Doctor doctor) {
		return new DoctorResponseDTO(doctor.getDoctorId(), doctor.getDoctorName(), doctor.getSpecialization(),
				doctor.getFee(), doctor.getProfilePhotoUrl(), doctor.getCreatedAt(),
				convertTDepartmentResponseDTO(doctor.getDepartment()));
	}

	public PatientResponseDTO convetToPatientResponseDTO(Patient patient) {
		return new PatientResponseDTO(patient.getPatientId(), patient.getPatientName(), patient.getGender(),
				patient.getAge(), patient.getPhone(), patient.getAdderes(), patient.getCreateAt());
	}

	public AppointmentResponseDTO convertToAppointmentResponseDTO(Appointments appointments) {
		DoctorResponseDTO doctorResponseDTO = convertToDoctorResponseDTO(appointments.getDoctor());
		PatientResponseDTO patientResponseDTO = convetToPatientResponseDTO(appointments.getPatient());
		return new AppointmentResponseDTO(appointments.getAppointmentId(), appointments.getDate(),
				appointments.getTime(), doctorResponseDTO, patientResponseDTO, appointments.getStatus());
	}

	public UserResponseDTO convertToUserResponseDTO(User user) {
		return new UserResponseDTO(user.getId(), user.getUsername(), user.getRole(), user.isStatus(),
				user.getCreatedAt());

	}

	public DoctorScheduleResponseDTO convertDoctorScheduleResponseDTO(DoctorSchedule doctorSchedule) {
		return new DoctorScheduleResponseDTO(doctorSchedule.getScheduleId(), doctorSchedule.getDay(),
				doctorSchedule.getStartTime(), doctorSchedule.getEndTime(),
				convertToDoctorResponseDTO(doctorSchedule.getDoctor()));
	}

	public DepartmentResponseDTO convertTDepartmentResponseDTO(Department department) {
		return new DepartmentResponseDTO(department.getId(), department.getDepartmentName(),
				department.getDepartmentDescription());
	}

	public boolean isDoctorProvided(DoctorRequestDTO doctorRequestDTO) {
		return doctorRequestDTO != null && !doctorRequestDTO.getDoctorName().isBlank()
				&& doctorRequestDTO.getDoctorName() != null && !doctorRequestDTO.getSpecialization().isBlank()
				&& doctorRequestDTO.getSpecialization() != null && doctorRequestDTO.getFee() != null
				&& doctorRequestDTO.getDepartmentId() != null;
	}

}
