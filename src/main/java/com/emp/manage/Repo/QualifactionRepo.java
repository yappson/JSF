package com.emp.manage.Repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emp.manage.enitity.Qualification;

@Repository
public interface QualifactionRepo extends JpaRepository<Qualification,Long>{
	
	List<Qualification> findQualificationsByEmployee_employeeId(Long EMPLOYEE_CODE);
	
	 @Transactional
	    @Modifying
	    @Query("DELETE FROM Qualification q WHERE q.id = :qualificationId")
	    void deleteQualificationById(@Param("qualificationId") Long qualificationId);
	 }



