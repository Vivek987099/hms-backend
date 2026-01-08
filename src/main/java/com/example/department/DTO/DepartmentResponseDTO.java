package com.example.department.DTO;

public class DepartmentResponseDTO {

	private int departId;
	private String departmentName;
	private String departmentDescription;

	public DepartmentResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DepartmentResponseDTO(int departId, String departmentName, String departmentDescription) {
		super();
		this.departId = departId;
		this.departmentName = departmentName;
		this.departmentDescription = departmentDescription;
	}

	public int getDepartId() {
		return departId;
	}

	public void setDepartId(int departId) {
		this.departId = departId;
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

}
