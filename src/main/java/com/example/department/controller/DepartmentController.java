package com.example.department.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.department.DTO.DepartmentResponseDTO;
import com.example.department.entity.Department;
import com.example.department.serviceImple.DepartmentServiceImple;

@RestController
@RequestMapping("/department")
public class DepartmentController {
	
	@Autowired
	private DepartmentServiceImple departmentServiceImple;

	
	@PostMapping("/create-department")
	public ResponseEntity<?> createNewDepartment(@RequestBody Department department){
		String message= departmentServiceImple.createDepartment(department);
		return ResponseEntity.ok(Map.of("message",message));
		
	}
	
	@GetMapping("/all-departments")
	public ResponseEntity<List<DepartmentResponseDTO>>  allDepartments(){
		return ResponseEntity.ok(departmentServiceImple.getAllDepartments());
	}
	
	@DeleteMapping("/delete-department/{id}")
	public ResponseEntity<?> deleteDepartment(@PathVariable int id){
		String message=  departmentServiceImple.deleteDepartment(id);
		return ResponseEntity.ok(Map.of("message",message));
	}
	@PutMapping("/update-department/{id}")
	public ResponseEntity<?> updateDepartment(@PathVariable int id,@RequestBody Department department){
		String message= departmentServiceImple.updateDepartment(id, department);
		return ResponseEntity.ok(Map.of("message",message));
	}

}
