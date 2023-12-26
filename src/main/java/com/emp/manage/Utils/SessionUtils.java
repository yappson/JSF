package com.emp.manage.Utils;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static String getUserName() {
        HttpSession session = getSession();
        return session != null ? session.getAttribute("username").toString() : null;
    }

    public static String getUserId() {
        HttpSession session = getSession();
        return session != null ? (String) session.getAttribute("userid") : null;
    }
}
