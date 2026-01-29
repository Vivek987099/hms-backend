package com.example.doctor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.doctor.entity.Doctor;
import com.example.department.entity.Department;
import com.example.user.entity.User;



public interface DoctorRepository extends JpaRepository<Doctor,Integer>{
	
	List<Doctor> findByDepartment(Department department);
	Optional<Doctor>  findByUser(User user);

}
