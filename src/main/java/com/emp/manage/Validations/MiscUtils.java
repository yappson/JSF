package com.emp.manage.Validations;

public class MiscUtils {

	
	public static void checkForNull(Object object, String message) {
		if (object == null) {
			throw new NullPointerException(message);
		}
	}
}
