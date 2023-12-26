package com.emp.manage.Repo;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.emp.manage.enitity.Designation;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {

   List<Designation> findByDepartmentId(Long departementId);
}


