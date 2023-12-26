package com.emp.manage.managedbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;

import com.emp.manage.Repo.DepartmentRepository;
import com.emp.manage.Repo.DesignationRepository;
import com.emp.manage.enitity.Department;
import com.emp.manage.enitity.Designation;
import com.emp.manage.service.Department_and_deg_service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ManagedBean
@ViewScoped
public class DropdownBean {

	@Autowired
	private Department_and_deg_service and_deg_service;
	 private Long selecteddepartment;
	 private List<Department> dep;
	 private List<Designation> dis;
	 
	 
	 
	 @PostConstruct
	 public void init() {
		 dep = and_deg_service.getAllDepartment();
	 }
	 
	 
	 public void onDepartmentChange() {
		 
		 if (selecteddepartment!= null) {
	            
	            dis=and_deg_service.getDesignationsByDepartment(selecteddepartment);
	        } else {
	            dis = new ArrayList<>();
	        }
	    }


	public Long getSelecteddepartment() {
		return selecteddepartment;
	}


	public void setSelecteddepartment(Long selecteddepartment) {
		this.selecteddepartment = selecteddepartment;
	}


	public List<Department> getDep() {
		return dep;
	}


	public void setDep(List<Department> dep) {
		this.dep = dep;
	}


	public List<Designation> getDis() {
		return dis;
	}


	public void setDis(List<Designation> dis) {
		this.dis = dis;
	}
	 
	 

	 }

