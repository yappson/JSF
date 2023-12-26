package com.emp.manage.Validations;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.emp.manage.service.EmployeService;

@FacesValidator("uniqueEmployeeCodeValidator")
@Component
public class UniqueEmployeeCodeValidator implements Validator {
	
	
	@Autowired
	private EmployeService employeService;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String employeeCode = (String) value;
		
		if (employeService.employeeExists(employeeCode)) {
            FacesMessage msg = new FacesMessage("Employee code must be unique.");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }

		
	}
}