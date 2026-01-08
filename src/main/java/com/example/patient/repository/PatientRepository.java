package com.example.patient.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.patient.entity.Patient;
import com.example.patient.service.PatientMonthWise;

public interface PatientRepository extends JpaRepository<Patient, Integer>{
	@Query(value = "select monthname(create_at) as month , count(*) as totalPatients from patient group by monthname(create_at);",nativeQuery = true)
	public List<PatientMonthWise> getPatientMonthWise();
	
	public Page<Patient> findByIsDeletedFalse(Pageable pageable);
	


}
