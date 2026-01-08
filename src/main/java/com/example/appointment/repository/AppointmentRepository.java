package com.example.appointment.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.appointment.entity.Appointments;
import com.example.appointment.service.AppointmentStatusCount;
import com.example.appointment.Enum.AppointmentEnum.AppointmentStatus;

public interface AppointmentRepository extends JpaRepository<Appointments, Integer> {

	@Query(value = "SELECT appointments.status as status,count(*) as count from appointments group by status;", nativeQuery = true)
	List<AppointmentStatusCount> getAppointmentStatusCount();

	public Page<Appointments> findByStatus(AppointmentStatus status,Pageable pageable);

}
