package com.emp.manage.managedbean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@ManagedBean
@RequestScoped
@Component
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
    	
    	return "createNew?faces-redirect=true";
       // Outcome or navigation rule
    }

    public String admin() {
    	
    	setCurrentPage("admin");
    	return "admin?faces-redirect=true";
         // Outcome or navigation rule
    }

    public String pieChart() {
    	 setCurrentPage("pieChart");
    	 return "pieChart?faces-redirect=true";
       // Outcome or navigation rule
    }
    private String currentPage;

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }
}
