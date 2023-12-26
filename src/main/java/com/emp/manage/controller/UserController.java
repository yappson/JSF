package com.emp.manage.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emp.manage.Utils.SessionUtils;
import com.emp.manage.enitity.Employee;
import com.emp.manage.service.EmployeService;
import com.emp.manage.service.UserService;





@Controller
@SessionScoped
@Component
public class UserController {
	
	
    private final UserService userService;
    private final EmployeService employeService;

    @Autowired
    public UserController(UserService userService, EmployeService employeService) {
        this.userService = userService;
        this.employeService = employeService;
    }

	
	

	SessionUtils sessionUtils() {
		 
		return new SessionUtils();
	}
	
	 @GetMapping("/")
	    public String redirectToLogin() {
	        return "redirect:/login.xhtml";
	    }
	
	 @PostMapping("/login")
	    public String login(@RequestParam String username, @RequestParam String password) {
	        if (userService.isValidUser(username, password)) {
	            HttpSession session = SessionUtils.getSession();
	            List<Employee> allEmploye = employeService.getAllEmploye();
	            session.setAttribute("allEmploye", allEmploye);
	            session.setAttribute("username", username); // Set the username in the session
	            return "admin?faces-redirect=true"; // Assuming "admin.xhtml" is a valid mapping
	        } else {
	            FacesContext.getCurrentInstance().addMessage(
	                    null,
	                    new FacesMessage(FacesMessage.SEVERITY_WARN,
	                            "Incorrect Username and Password",
	                            "Please enter correct username and password"));
	            return null;
	        }
	    }
	 
	 
	 @RequestMapping("/logout")
	   public String logout() {
			HttpSession session = sessionUtils().getSession();
			session.invalidate();
			return "login?faces-redirect=true";
		}
	
}
