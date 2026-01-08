package com.example.doctor.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.doctor.entity.DoctorSchedule;
import com.example.doctor.Enum.DoctorDayEnum.DoctorDay;


public interface DoctorScheduleRepository extends JpaRepository<DoctorSchedule, Integer>{
	
	
	public Page<DoctorSchedule>  findByDay(DoctorDay day,Pageable pageable);

}
