package com.emp.manage.Utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ApiResponce {
    private Object data;
    private String message;

    
    public static ApiResponce error(String message) {
        return new ApiResponce(null, message);
    }
    // Constructors, getters, setters

    public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ApiResponce(Object data, String message) {
        this.data = data;
        this.message = message;
    }
}
