package com.emp.manage.controller;



import org.springframework.http.ResponseEntity;

import com.emp.manage.Utils.ApiResponce;
import com.emp.manage.enitity.Department;
import com.emp.manage.enitity.Designation;
import com.emp.manage.service.DepartmentRequest;
import com.emp.manage.service.DepartmentService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	 @Autowired
	    private DepartmentService departmentService;

	 @PostMapping("/withDesignations")
	 public ResponseEntity<ApiResponce> createDepartmentWithDesignations(@RequestBody Department department) {
	     try {
	         Department savedDepartment = departmentService.saveDepartment(department);
	         return ResponseEntity.ok(new ApiResponce(savedDepartment, "Department created successfully"));
	     } catch (Exception e) {
	         // Log the exception for debugging purposes
	         e.printStackTrace();
	         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                              .body(ApiResponce.error("Error creating department"));
	     }
	 }
}