package com.emp.manage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.emp.manage.Repo.EmployeRepository;
import com.emp.manage.enitity.Employee;
import com.emp.manage.enitity.Qualification;

@Service
public class EmployeService {

	@Autowired
	private EmployeRepository employeRepo;
	
	public List<Employee> getAllEmploye(){
		List<Employee> findAll = employeRepo.findByIsActive(true);
		return findAll;
	}
	
	public void deleteEmploye(Long id) {
		Optional<Employee> findById = employeRepo.findById(id);
		if(!findById.isEmpty()) {
			findById.get().setIsActive(false);
			employeRepo.save(findById.get());
		}
		
	}
	
	public void updateEmployee(Employee employee) {
		Optional<Employee> findById = employeRepo.findById(employee.getEmployeeId());
		Employee employee2 = findById.get();
		employee2.setName(employee.getName());
		employee2.setEmail(employee.getEmail());
		employee2.setDepartment(employee.getDepartment());
		employee2.setDesignation(employee.getDesignation());
		employee2.setContactNumber(employee.getContactNumber());
		employee2.setEmployeeCode(employee.getEmployeeCode());
		
		
		   List<Qualification> currentQualifications = new ArrayList<>(	employee2.getQualifications());
           List<Qualification> updatedQualifications = employee.getQualifications();
           
           List<Qualification> newQualifications = new ArrayList<>();

           for (Qualification updatedQualification : updatedQualifications) {
               if (updatedQualification.getId() != null) {
                   Optional<Qualification> existingQualificationOptional = currentQualifications.stream()
                           .filter(q -> q.getId().equals(updatedQualification.getId()))
                           .findFirst();

                   if (existingQualificationOptional.isPresent()) {
                       Qualification existingQualification = existingQualificationOptional.get();
                       
                       existingQualification.setInstitute(updatedQualification.getInstitute());
                       existingQualification.setDegree(updatedQualification.getDegree());
                       existingQualification.setSpecialization(updatedQualification.getSpecialization());
                       existingQualification.setFromPeriod(updatedQualification.getFromPeriod());
                       existingQualification.setToPeriod(updatedQualification.getToPeriod());
                       existingQualification.setPercentage(updatedQualification.getPercentage());
                   } else {
                       throw new IllegalArgumentException("Qualification with ID " +
                               updatedQualification.getId() + " not found");
                   }
               } else {
                   updatedQualification.setEmployee(employee2);
                   newQualifications.add(updatedQualification);
               }
           }

           currentQualifications.addAll(newQualifications);

           employee2.setQualifications(currentQualifications);

          
           employeRepo.save(employee2);
	}
	
	 public boolean employeeExists(String employeeCode) {
	        return employeRepo.existsByEmployeeCode(employeeCode);
	    }
	 	 
	
	public void createEmployee(String name, String email,String contactNumber,String designation,String department, String employeeCode) {
		 if (employeeExists(employeeCode)) {
	            throw new ArithmeticException("Employee with code " + employeeCode + " already exists.");
	        }
		Employee employe= new Employee();
        employe.setName(name);
        employe.setEmail(email);
        employe.setDepartment(department);
        employe.setDesignation(designation);
        employe.setContactNumber(contactNumber);
        employe.setEmployeeCode(employeeCode);
        employe.setIsActive(true);
        employeRepo.save(employe);
    }
	public void createEmployee1(String name, String email,String contactNumber,String designation,String department, String employeeCode) {
		 if (employeeExists(employeeCode)) {
	            throw new ArithmeticException("Employee with code " + employeeCode + " already exists.");
	        }
		 
		 System.out.println("inside Service");
		 System.out.print(name+" "+email+" "+contactNumber+" "+department+" "+designation+" "+employeeCode);
		Employee employe= new Employee();
       employe.setName(name);
       employe.setEmail(email);
       employe.setDepartment(department);
       employe.setDesignation(designation);
       employe.setContactNumber(contactNumber);
       employe.setEmployeeCode(employeeCode);
       employe.setIsActive(true);
       employeRepo.save(employe);
   }
	
	
	
	
	    
	    
	    
}
