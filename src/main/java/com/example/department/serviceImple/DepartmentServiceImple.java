package com.example.department.serviceImple;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Public.ConvertToDTO;
import com.example.department.DTO.DepartmentResponseDTO;
import com.example.department.entity.Department;
import com.example.department.repository.DepartmentRepository;
import com.example.department.service.DepartmentService;

@Service
public class DepartmentServiceImple implements DepartmentService{
	
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Autowired
	private ConvertToDTO convertToDTO;
	
	@Override
	public String createDepartment(Department department) {
		departmentRepository.save(department);
		return "Department created";
	}
	@Override
	public List<DepartmentResponseDTO> getAllDepartments() {
		
		 List<Department> departmentList= departmentRepository.findAll();
		 
		 return departmentList.stream().map(department -> convertToDTO.convertTDepartmentResponseDTO(department)).toList();
		 
		 
	}
	
	@Override
	public String deleteDepartment(int id) {
		Department department= departmentRepository.findById(id).orElseThrow(()-> new RuntimeException("Department is not found with this id "+id));
		
		departmentRepository.delete(department);
		return "Department deleted";
	}
	@Override
	public String updateDepartment(int id,Department department) {
		
		Department depart= departmentRepository.findById(id).orElseThrow(()->new RuntimeException("Department is not found with thid id : "+id));
		if(department.getDepartmentName() != null || !department.getDepartmentName().trim().isEmpty()) {
			depart.setDepartmentName(department.getDepartmentName());
		}
		if(department.getDepartmentDescription() != null  || !department.getDepartmentDescription().trim().isEmpty()) {
			depart.setDepartmentDescription(department.getDepartmentDescription());
		}
		departmentRepository.save(depart);
		return "Department Updated";
	}

}
