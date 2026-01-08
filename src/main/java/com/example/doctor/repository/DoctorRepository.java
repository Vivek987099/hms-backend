package com.example.doctor.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.doctor.entity.Doctor;
import com.example.department.entity.Department;


public interface DoctorRepository extends JpaRepository<Doctor,Integer>{
	
	List<Doctor> findByDepartment(Department department);

}
