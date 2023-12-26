package com.emp.manage.service;

import org.springframework.stereotype.Service;


import com.emp.manage.Repo.DepartmentRepository;
import com.emp.manage.Repo.DesignationRepository;
import com.emp.manage.enitity.Department;
import com.emp.manage.enitity.Designation;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.transaction.support.TransactionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    
    @Autowired
    private DesignationRepository designationRepository;
   
    @Transactional
    public Department saveDepartment(Department department) {
    	for (Designation designation : department.getDesignations()) {
    		designation.setDepartment(department);
        }
    	
    	return departmentRepository.save(department);
    
    }

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }
        
}
