package com.example.department.service;

import java.util.List;

import com.example.department.DTO.DepartmentResponseDTO;
import com.example.department.entity.Department;

public interface DepartmentService {
	
	public String createDepartment(Department department);
	public List<DepartmentResponseDTO> getAllDepartments();
	
	 public String deleteDepartment(int id);
	 public String updateDepartment(int id,Department department);

}
