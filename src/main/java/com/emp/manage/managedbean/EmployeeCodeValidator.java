package com.emp.manage.managedbean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.emp.manage.service.EmployeService;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("employeeCodeValidator")
@Component
public class EmployeeCodeValidator implements Validator {

    private static EmployeService employeService;

    @Autowired
    public void setEmployeService(EmployeService employeService) {
        EmployeeCodeValidator.employeService = employeService;
    }

    public static void initialize() {
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(EmployeeCodeValidator.class);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        // Initialize the validator to ensure that the employeService is injected
        initialize();

        String employeeCode = (String) value;
        System.out.println(employeeCode);

        // Validate pattern (e.g., ABC-1234)
        if (!employeeCode.matches("^[A-Za-z]{3}-\\d{4}$")) {
            FacesMessage message = new FacesMessage("Invalid employee code format. It should be in the format ABC-1234.");
            throw new ValidatorException(message);
        }

        // Check if the employee code already exists
        if (employeService.employeeExists(employeeCode)) {
            System.out.println("Employee with code " + employeeCode + " already exists.");
            FacesMessage message = new FacesMessage("Employee with code " + employeeCode + " already exists.");
            throw new ValidatorException(message);
        }
    }
}
