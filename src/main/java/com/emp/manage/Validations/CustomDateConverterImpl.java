package com.emp.manage.Validations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.faces.application.FacesMessage;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "customDateConverterImpl")
public class CustomDateConverterImpl implements Converter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            if (value == null || value.isEmpty()) {
                handleRequiredMessage(context, component);
                return null;
            }
            LocalDate date = LocalDate.parse(value, DATE_FORMATTER);
            handleValidationMessage(context, component, date);
            return date;
        } catch (DateTimeParseException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "Invalid date format. Please use yyyy-MM-dd.", null);
            context.addMessage(component.getClientId(context), message);
            throw new ConverterException(message, e);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof LocalDate) {
            return ((LocalDate) value).format(DATE_FORMATTER);
        }
        return null;
    }

    private void handleRequiredMessage(FacesContext context, UIComponent component) {
        if (component instanceof EditableValueHolder) {
            EditableValueHolder input = (EditableValueHolder) component;
            if (input.isRequired()) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "This field is required", null);
                context.addMessage(component.getClientId(context), message);
            }
        }
    }

    private void handleValidationMessage(FacesContext context, UIComponent component, LocalDate date) {
        if (component instanceof UIInput) {
            UIInput input = (UIInput) component;

            Object minValue = input.getAttributes().get("min");
            if (minValue != null && date.isBefore((LocalDate) minValue)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Date should be equal or later than " + minValue, null);
                context.addMessage(component.getClientId(context), message);
                throw new ConverterException(message);
            }

            Object maxValue = input.getAttributes().get("max");
            if (maxValue != null && date.isAfter((LocalDate) maxValue)) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Date should be equal or earlier than " + maxValue, null);
                context.addMessage(component.getClientId(context), message);
                throw new ConverterException(message);
            }

            // Check if toDate is before fromDate
            if (input.getId().equals("toPeriod")) {
                Object fromDateValue = input.getAttributes().get("fromDate");
                if (fromDateValue != null) {
                    LocalDate fromDate = (LocalDate) fromDateValue;
                    if (date.isBefore(fromDate) || date.isEqual(fromDate)) {
                        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                                "To Date should be later than From Date", null);
                        context.addMessage(component.getClientId(context), message);
                        throw new ConverterException(message);
                    }
                }
            }
        }
    }
}