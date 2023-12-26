package com.emp.manage.Repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.emp.manage.enitity.Employee;



@Repository
public interface EmployeRepository extends  JpaRepository<Employee, Long>{

	public List<Employee> findByIsActive(Boolean b);

	public boolean existsByEmployeeCode(String employeeCode);


    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department = :department")
    Long getCountByDepartment(@Param("department") String department);

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department = :department AND e.isActive = true")
    Long getCountByDepartmentIsActive(@Param("department") String department);

    @Query("SELECT COUNT(e) FROM Employee e WHERE e.department = :department AND e.designation = :designation")
    Long getCountByDepartmentAndDesignation(
            @Param("department") String department,
            @Param("designation") String designation
    );
//	default List<Employee> findWithPagination(int pageIndex, int pageSize) {
//        PageRequest pageRequest = PageRequest.of(pageIndex, pageSize);
//        Page<Employee> page = findAll(pageRequest);
//        return page.getContent();
//    }
	
    @Query("SELECT e FROM Employee e")
    List<Employee> findWithPagination(Pageable pageable);
	
}
