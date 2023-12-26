package com.emp.manage.managedbean;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.emp.manage.Repo.EmployeRepository;
import com.emp.manage.enitity.Employee;
import com.emp.manage.service.EmployeService;

@ManagedBean
@RequestScoped
@Component
public class EmployeeBean {

	@Autowired
    private EmployeRepository emprepo;
	@Autowired
	private EmployeService employeService;
    private List<Employee> employeelist;
    private int rowsPerPage;
    private int rowIndex;
    private int totalRows;
    private int currentPage;
    private String name;
    private String email;
    private String department;
    private String designation;
    private String contactNumber;
    private String employeeCode;
    private String generatedEmployeeCode;


    // Getter method for employeeCode
    public String getEmployeeCode() {
        return employeeCode;
    }

    // Setter method for employeeCode
    public void setEmployeeCode(String employeeCode) {
        this.employeeCode = employeeCode;
    }
    @ManagedProperty(value = "#{menuBean}")
    private MenuBean menuBean;

    // your existing code...

    // Injecting the MenuBean
    public void setMenuBean(MenuBean menuBean) {
        this.menuBean = menuBean;
    }
    public String resetFormValues() {
        // Reset values for each property
        name = null;
        email = null;
        contactNumber = null;
        designation = null;
        department = null;
        employeeCode = null;

        // Return the outcome to navigate back to the same page or another if needed
        return "createNew";
    }

	@PostConstruct
    public void init() {
		  
		 employeelist = emprepo.findAll();
	        totalRows = employeelist.size();
	        rowIndex = 0;
	        rowsPerPage=6;
	        currentPage = 1;
	        generatedEmployeeCode = generateUniqueEmployeeCode();
	        updateDataModel();
    }

	public List<Employee> getEmployeelist() {
		return employeelist;
	}

	public void setEmployeelist(List<Employee> employeelist) {
		this.employeelist = employeelist;
	}
	
	
	// previous and next button
	public boolean isShowPreviousButton() {
	    return rowIndex >= rowsPerPage;
	}

	public boolean isShowNextButton() {
	    return (rowIndex + rowsPerPage) < totalRows;
	}

	
    
    // delete method
	public void deleteRow(Employee employee) {
			
		emprepo.delete(employee);
		employeelist = emprepo.findAll();
		totalRows = employeelist.size();
        updateDataModel();
	}
	
	public void refresh() {
		employeelist = emprepo.findAll();
	}
	
	
	// create method
	public String createEmployee(String name, String email,String contactNumber,String designation,String department){
		
		employeeCode = generateUniqueEmployeeCode();
		
		
		
		System.out.print(name+" "+email+" "+contactNumber+" "+department+" "+designation+" "+employeeCode);
		employeService.createEmployee1(name, email, contactNumber, designation, department, employeeCode);
	    
		System.out.println("Inside Bean");
	//	employeelist = emprepo.findAll();
		  totalRows = employeelist.size();
	      updateDataModel();
		return "admin?faces-redirect=true";
		
		
	}
	
	 private String generateUniqueEmployeeCode() {
	        String employeeCode;
	        do {
	            // Generate a random 4-digit number
	            String randomDigits = String.format("%04d", new Random().nextInt(10000));

	            // Combine with the fixed prefix
	            employeeCode = "TSD-" + randomDigits;
	        } while (employeeExists(employeeCode)); // Check if the code already exists in the database

	        return employeeCode;
	    }

	    // Method to check if an employee with the given code already exists in the database
	    private boolean employeeExists(String employeeCode) {
	        // Implement your logic to check if the employee code exists in the database
	        // You can use your employeService.employeeExists(employeeCode) or employeRepo.existsByEmployeeCode(employeeCode) method
	        return employeService.employeeExists(employeeCode);
	    }


	// next impl
	// next impl
	public void next() {
	    if (isShowNextButton()) {
	        rowIndex += rowsPerPage;
	        currentPage++;
	        if (rowIndex >= totalRows) {
	            rowIndex = 0;
	            currentPage = 1;
	        }
	        updateDataModel();
	    }
	}


	// prv inmpl
	// prv inmpl
	public void previous() {
	    if (isShowPreviousButton()) {
	        rowIndex -= rowsPerPage;
	        currentPage--;
	        if (rowIndex < 0) {
	            rowIndex = 0;
	            currentPage = 1;
	        }
	        updateDataModel();
	    }
	}

    
    // update data model
	private void updateDataModel() {
	    int fromIndex = Math.max(rowIndex, 0); // Ensure fromIndex is not less than zero
	    int toIndex = Math.min(rowIndex + rowsPerPage, totalRows);

	    Pageable pageable = PageRequest.of(fromIndex / rowsPerPage, rowsPerPage);
	    employeelist = emprepo.findWithPagination(pageable);
	}

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return (int) Math.ceil((double) totalRows / rowsPerPage);
    }

    public int getRowIndex() {
        return rowIndex;
    }

    // row per pages
	public int getRowsPerPage() {
		return rowsPerPage;
	}
	private void updatePageInfo() {
	    currentPage = (rowIndex / rowsPerPage) + 1;
	}
	
	public void setRowsPerPage(int rowsPerPage) {
		 if (this.rowsPerPage != rowsPerPage) {
	           
	            this.currentPage = 0;
	        }
	        this.rowsPerPage = rowsPerPage;
	        handleRowsPerPageChange();
	}

	public void handleRowsPerPageChange() {
	    rowIndex = (currentPage - 1) * rowsPerPage;
	    totalRows = employeelist.size();
	    updateDataModel();
	    updatePageInfo();
	}

	   public String getGeneratedEmployeeCode() {
	        return generatedEmployeeCode;
	    }
	public void setGeneratedEmployeeCode(String generatedEmployeeCode) {
		this.generatedEmployeeCode = generatedEmployeeCode;
	}
	
	

	   
    
    
}