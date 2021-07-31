package polymart.xyz.ass_jv4.utils;


import polymart.xyz.ass_jv4.entity.EntityStaff;
import polymart.xyz.ass_jv4.entity.EntityVisit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SessionUtils {

    private static SessionUtils sessionUtils;

    public static SessionUtils getSessionUtils() {
        if (sessionUtils == null) {
            return sessionUtils = new SessionUtils();
        }
        return sessionUtils;
    }

    public void saveSession(String name, String value, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(name.toUpperCase(), value);
        session.setMaxInactiveInterval(60 * 60 * 24);
    }

    public String getSessionString(String name, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (String) session.getAttribute(name.toUpperCase());
    }

    public void removeSession(String name, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute(name.toUpperCase());
    }

    public void saveSessionVisit(String name, EntityVisit value, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(name.toUpperCase(), value);
        session.setMaxInactiveInterval(60 * 60 * 24);
    }

    public void saveSessionStaff(String name, EntityStaff value, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute(name.toUpperCase(), value);
        session.setMaxInactiveInterval(60 * 60 * 24);
    }

    public EntityStaff getSessionStaff(String name, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (EntityStaff) session.getAttribute(name.toUpperCase());
    }

    public EntityVisit getSessionVisit(String name, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (EntityVisit) session.getAttribute(name.toUpperCase());
    }
}
