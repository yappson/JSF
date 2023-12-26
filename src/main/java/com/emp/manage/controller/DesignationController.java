package com.emp.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emp.manage.enitity.Designation;
import com.emp.manage.service.DesignationService;

@RestController
@RequestMapping("/api/designations")
public class DesignationController {
    @Autowired
    private DesignationService designationService;

    @PostMapping
    public ResponseEntity<Designation> createDesignation(@RequestBody Designation designation) {
        Designation savedDesignation = designationService.saveDesignation(designation);
        return new ResponseEntity<>(savedDesignation, HttpStatus.CREATED);
    }
}
