package com.emp.manage.QualifactionBeans;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emp.manage.Repo.EmployeRepository;
import com.emp.manage.Repo.QualifactionRepo;
import com.emp.manage.enitity.Employee;
import com.emp.manage.enitity.Qualification;
import com.emp.manage.managedbean.EmployeeBean;
import com.emp.manage.service.EmployeService;




@ManagedBean(name = "qualiBean")
@SessionScoped
@Component
public class QualiBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private Employee selectedEmployee;
    
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private QualifactionRepo qualifactionRepo;
    @Autowired
    private EmployeService employeService;
   
    private boolean validationError;
  
    
    private List<Qualification> gridItems;
    

    public QualiBean() {
        gridItems = new ArrayList<>();

        // Initialize the grid with at least one qualification
        gridItems.add(new Qualification());
    }
    
    

    
    
    //edit Employee to Redirect to Qualifaction page
 public String addQualifaction() {
  if (selectedEmployee != null) {
  List<Qualification> savedQualifications = qualifactionRepo.findQualificationsByEmployee_employeeId(selectedEmployee.getEmployeeId());
  gridItems.clear();
      gridItems.addAll(savedQualifications);
			 
	            return "Qualifaction?faces-redirect=true";
	        }

	        return "index";
   
  }
    
    //end of the Qualifaction edit page redirect....

    
    
    
// Getter Setter Started
    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }

    public List<Qualification> getGridItems() {
        return gridItems;
    }

    public void setGridItems(List<Qualification> gridItems) {
        this.gridItems = gridItems;
    }

    public void addGridItem() {
        gridItems.add(new Qualification());
    }

    public void removeGridItem(Qualification qualification) {
    	
        gridItems.remove(qualification);
        Long id = qualification.getId();
        
   qualifactionRepo.deleteQualificationById(id);
      
    }
    
    
    //getter setter End

    public String submit() {
        if (selectedEmployee != null) {
            if (gridItems.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Qualifications cannot be empty!", null));
                return null;
            }

            if (validateQualifications(gridItems)) {
                selectedEmployee.setQualifications(gridItems);
                employeService.updateEmployee(selectedEmployee);
                gridItems.clear(); // Clear qualifications after saving

                // Add success message
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Qualifications saved successfully!", null));

                // Redirect to admin page or another page
                try {
                    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                    externalContext.redirect("/admin.xhtml"); // Change the URL to the appropriate admin page
                } catch (IOException e) {
                    e.printStackTrace(); // Handle the IOException if necessary
                }

                return "success";
            } else {
            	                return null;
            }
        }
        return "index";
    }

    
    private boolean validateQualifications(List<Qualification> qualifications) {
        for (Qualification qualification : qualifications) {
            LocalDate fromDate = qualification.getFromPeriod();
            LocalDate toDate = qualification.getToPeriod();

            // Check for valid date format using the converter
            if (fromDate == null || toDate == null || toDate.isBefore(fromDate) || toDate.isEqual(fromDate)) {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "To Date can't be Equal or Before From Date", null));
                return false;
            }
        }
        return true;
    }






	public boolean isValidationError() {
		return validationError;
	}





	public void setValidationError(boolean validationError) {
		this.validationError = validationError;
	}

    
    
}
    
    
    
    
