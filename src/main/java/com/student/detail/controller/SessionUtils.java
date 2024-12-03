package com.student.detail.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionUtils {

	public static boolean isSessionValid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false); // Don't create a new session if none exists
		if (session == null || session.getAttribute("username") == null) {
			// Redirect to login endpoint (controller will handle the view resolution)
			response.sendRedirect(request.getContextPath() + "/login?sessionexpired=true");
			return false; // Stop further processing
		}
		return true; // Session is valid
	}
}
