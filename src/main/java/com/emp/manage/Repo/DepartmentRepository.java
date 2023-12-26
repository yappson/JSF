package com.emp.manage.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.manage.enitity.Department;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
	
	
}
