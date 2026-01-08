package com.example.department.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.doctor.entity.Doctor;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "department")
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false)
	private String departmentName;
	@Column
	private String departmentDescription;
	
	@OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
	List<Doctor> doctors=new ArrayList<>();

	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Department(int id, String departmentName, String departmentDescription, List<Doctor> doctors) {
		super();
		this.id = id;
		this.departmentName = departmentName;
		this.departmentDescription = departmentDescription;
		this.doctors = doctors;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentDescription() {
		return departmentDescription;
	}

	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}


	public List<Doctor> getDoctors() {
		return doctors;
	}


	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	
	

}
