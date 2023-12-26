package com.emp.manage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.manage.Repo.DepartmentRepository;
import com.emp.manage.Repo.DesignationRepository;
import com.emp.manage.enitity.Department;
import com.emp.manage.enitity.Designation;

@Service
public class Department_and_deg_service {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	@Autowired
	private DesignationRepository designationRepository;
	
	
	public List<Department> getAllDepartment(){
		return departmentRepository.findAll();
		
	}
	
	public List<Designation> getDesignationsByDepartment(Long DepartmentId){
		
		return designationRepository.findByDepartmentId(DepartmentId);
	}

}
