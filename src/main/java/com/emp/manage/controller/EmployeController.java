package com.emp.manage.controller;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.emp.manage.enitity.Employee;
import com.emp.manage.service.EmployeService;


@Controller
@SessionScoped
@Component
public class EmployeController {

	@Autowired
	private EmployeService employeService;

	public void processForDelete(Employee employee) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("selectedEmployee", employee);
	}

	public void processForUpdate(Employee employee) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
		session.setAttribute("selectedEmployee", employee);
		System.out.println("processForUpdate called with employee: " + employee);
	}

	@PostMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Long employeeId) {
		System.out.println("Deleting employee with ID: " + employeeId);
		if (employeeId != null) {
			employeService.deleteEmploye(employeeId);
			List<Employee> allEmploye = employeService.getAllEmploye();
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpSession session = (HttpSession) externalContext.getSession(true);
			session.setAttribute("allEmploye", allEmploye);
			return "redirect:/dashboard.xhtml";
		}
		return null;
	}

		@PostMapping("/updateEmployee")
		public String updateEmployee() {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpSession session = (HttpSession) externalContext.getSession(true);
			Employee employee = (Employee) session.getAttribute("selectedEmployee");
	
			if (employee != null) {
				employeService.updateEmployee(employee);
				List<Employee> allEmploye = employeService.getAllEmploye();
				session.setAttribute("allEmploye", allEmploye);
				FacesContext.getCurrentInstance().getExternalContext().getFlash().put("updateSuccessful", true);
				return "redirect:/admin.xhtml";
			}
			return null;
		}
			
		 public void validateEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		        String email = (String) value;
		        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$";

		        if (!email.matches(emailRegex)) {
		            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid email format", null);
		            throw new ValidatorException(message);
		        }
		    }
		 
		 @PostMapping("/createEmployee")
		 public String createEmployee(@RequestParam String name,
		                              @RequestParam String email,
		                              @RequestParam String contactNumber,
		                              @RequestParam String designation,
		                              @RequestParam String department,
		                              @RequestParam String employeeCode) {

		     if (employeService.employeeExists(employeeCode)) {
		         System.out.println("Employee exists");
		         FacesMessage createNewMessage = new FacesMessage("Employee with code " + employeeCode + " already exists.");
		         FacesContext.getCurrentInstance().addMessage(null, createNewMessage);
		         return "createNew";
		     }

		     employeService.createEmployee(name, email, contactNumber, designation, department, employeeCode);
		     return "dashboard?faces-redirect=true";
		 }

}


