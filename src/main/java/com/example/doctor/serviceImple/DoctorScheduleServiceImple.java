package com.example.doctor.serviceImple;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Public.ConvertToDTO;
import com.example.doctor.DTO.DoctorScheduleRequestDTO;
import com.example.doctor.DTO.DoctorScheduleResponseDTO;
import com.example.doctor.Enum.DoctorDayEnum.DoctorDay;
import com.example.doctor.entity.Doctor;
import com.example.doctor.entity.DoctorSchedule;
import com.example.doctor.repository.DoctorRepository;
import com.example.doctor.repository.DoctorScheduleRepository;
import com.example.doctor.service.DoctorScheduleService;

@Service
public class DoctorScheduleServiceImple implements DoctorScheduleService {
	@Autowired
	private DoctorScheduleRepository doctorScheduleRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private ConvertToDTO convertToDTO;

	@Override
	public String makeSchedule(DoctorScheduleRequestDTO doctorScheduleRequestDTO) {
		Doctor doctor = doctorRepository.findById(doctorScheduleRequestDTO.getDoctorId()).orElseThrow(
				() -> new RuntimeException("Doctor not found with this id " + doctorScheduleRequestDTO.getDoctorId()));
		DoctorSchedule doctorSchedule = new DoctorSchedule();
		doctorSchedule.setDay(doctorScheduleRequestDTO.getDay());
		doctorSchedule.setStartTime(doctorScheduleRequestDTO.getStartTime());
		doctorSchedule.setEndTime(doctorScheduleRequestDTO.getEndTime());
		doctorSchedule.setCreatedAt(LocalDate.now());
		doctorSchedule.setDoctor(doctor);
		doctorScheduleRepository.save(doctorSchedule);
		return "Schedule created";
	}

	@Override
	public Page<DoctorScheduleResponseDTO> getDoctorSchedule(DoctorDay doctorDay, int pageSize, int pageNo) {
		Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createdAt").descending());
		if (doctorDay != null) {
			Page<DoctorSchedule> doctorschedulePage = doctorScheduleRepository.findByDay(doctorDay, pageable);
			return doctorschedulePage
					.map(doctorschedule -> convertToDTO.convertDoctorScheduleResponseDTO(doctorschedule));
		}
		Page<DoctorSchedule> doctorSchedulesPage = doctorScheduleRepository.findAll(pageable);
		return doctorSchedulesPage.map(doctorSchedule -> convertToDTO.convertDoctorScheduleResponseDTO(doctorSchedule));
	}

	@Override
	public String deleteById(int id) {
		DoctorSchedule doctorSchedule = doctorScheduleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Schedule is not found with this id " + id));
		doctorScheduleRepository.delete(doctorSchedule);
		return "Schedule Deleted";
	}

	@Override
	public String updateById(int id, DoctorScheduleRequestDTO doctorScheduleRequestDTO) {
		DoctorSchedule doctorSchedule = doctorScheduleRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Schedule not found with this id " + id));
		if (doctorScheduleRequestDTO.getStartTime() != null) {
			doctorSchedule.setStartTime(doctorScheduleRequestDTO.getStartTime());
		}
		if (doctorScheduleRequestDTO.getEndTime() != null) {
			doctorSchedule.setEndTime(doctorScheduleRequestDTO.getEndTime());
		}
		if (doctorScheduleRequestDTO.getDay() != null) {
			doctorSchedule.setDay(doctorScheduleRequestDTO.getDay());
		}
		if (doctorScheduleRequestDTO.getDoctorId() != null) {
			Doctor doctor = doctorRepository.findById(doctorScheduleRequestDTO.getDoctorId())
					.orElseThrow(() -> new RuntimeException(
							"Doctor not found with this id " + doctorScheduleRequestDTO.getDoctorId()));
			doctorSchedule.setDoctor(doctor);
		}
		
		doctorScheduleRepository.save(doctorSchedule);

		return "Schedule Updated";
	}

}
