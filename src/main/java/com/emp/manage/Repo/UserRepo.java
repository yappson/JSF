package com.emp.manage.Repo;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.emp.manage.enitity.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{

	 Users findByUsername(String username);
	
	
}
