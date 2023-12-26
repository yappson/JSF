package com.emp.manage.enitity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "EMPLOYEE")
public class Employee {

    @Id
    @Column(name = "EMPLOYEE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "CONTACT_NUMBER")
    private String contactNumber;

    @Column(name = "DESIGNATION")
    private String designation;

    @Column(name = "DEPARTMENT")
    private String department;

    @Column(name = "EMPLOYEE_CODE", nullable = false)
    private String employeeCode;

    @Column(name = "VERSION_NO", nullable = false, length = 50)
    private String versionNo = "1";

    @Column(name = "CREATED_BY", length = 200)
    private String createdBy = "Tspl";

    @Column(name = "CREATED_ON")
    private Date createdOn = new Timestamp(System.currentTimeMillis());

    @Column(name = "IS_ACTIVE")
    private Boolean isActive;
   
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Qualification> qualifications = new ArrayList<>();
   

}