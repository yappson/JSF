package com.emp.manage.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.manage.Repo.DesignationRepository;
import com.emp.manage.enitity.Designation;

@Service
public class DesignationService {
    @Autowired
    private DesignationRepository designationRepository;

    public Designation saveDesignation(Designation designation) {
        return designationRepository.save(designation);
    }
    
    
    public List<Designation> getAllDesignations(){
    	
    	return designationRepository.findAll();
    }
    
  
  }
