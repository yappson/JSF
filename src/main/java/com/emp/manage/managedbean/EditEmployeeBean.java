package com.emp.manage.managedbean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.emp.manage.Repo.EmployeRepository;
import com.emp.manage.Utils.SessionUtils;
import com.emp.manage.enitity.Employee;
import com.emp.manage.service.EmployeService;

@ManagedBean
@SessionScoped
@Component
public class EditEmployeeBean {
	@Autowired
    private EmployeRepository emprepo;
	@Autowired
	private EmployeService employeService;
    
	private Employee selectedEmployee;
	private List<Employee> employees;	
	
	private EmployeeBean bean;
	
	
	@Autowired
	public EditEmployeeBean(EmployeeBean bean) {
		this.bean=bean;
	}
	
	@PostConstruct
    public void init() {
		employees = emprepo.findAll();
		
	}

	public Employee getSelectedEmployee() {
		return selectedEmployee;
	}


	public void setSelectedEmployee(Employee selectedEmployee) {
		this.selectedEmployee = selectedEmployee;
	}

	public String editEmployee() {
		  if (selectedEmployee != null) {
			 
			 
	            return "editEmployee?faces-redirect=true";
	        }

	        return "index";
     
    }

    public String saveEmployee() {
    	
    	if (selectedEmployee != null) {
    		employeService.updateEmployee(selectedEmployee);
    		
         	bean.init();
    		
    		 return "admin?faces-redirect=true";
    	}
    	
    	FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Incorrect Username and Passowrd",
						"Please enter correct username and Password"));
    return null;
    }
	
    

}
