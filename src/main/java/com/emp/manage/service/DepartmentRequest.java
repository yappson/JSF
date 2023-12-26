package com.emp.manage.service;

import java.util.List;

public class DepartmentRequest {

    private String departmentName;
    private List<String> designationNames;
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public List<String> getDesignationNames() {
		return designationNames;
	}
	public void setDesignationNames(List<String> designationNames) {
		this.designationNames = designationNames;
	}

  
}

