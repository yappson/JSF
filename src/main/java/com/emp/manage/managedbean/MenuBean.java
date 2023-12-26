package com.emp.manage.managedbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean
@RequestScoped
public class MenuBean {
	
	@Autowired
	private EmployeeBean bean;
	
	@PostConstruct
    public void init() {
		
	      bean.init();
    }

    public String createNew() {
        // Logic for handling "Create New" action
    	setCurrentPage("createNew");
        return "createNew"; // Outcome or navigation rule
    }

    public String admin() {
    	
    	setCurrentPage("admin");
  
        return "admin"; // Outcome or navigation rule
    }

    public String pieChart() {
    	 setCurrentPage("pieChart");
        return "pieChart"; // Outcome or navigation rule
    }
    private String currentPage;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
}
