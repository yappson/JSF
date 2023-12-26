package com.emp.manage.service;



import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emp.manage.Repo.UserRepo;
import com.emp.manage.enitity.Users;





@Service
public class UserService {

	@Autowired
	private UserRepo userRepo; 


	public boolean isValidUser(String username, String password) {
        Users user = userRepo.findByUsername(username);
        return user != null && user.getPassword().equals(password);
      
        
        
    }
	
}