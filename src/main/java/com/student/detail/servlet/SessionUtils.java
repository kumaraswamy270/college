package com.student.detail.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionUtils {

	// Method to check session validity
	public static boolean isSessionValid(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false); // Don't create a new session if none exists

		if (session == null || session.getAttribute("username") == null) {
			// Session is invalid or user is not logged in
			response.sendRedirect("login.jsp?sessionexpired=true");
			return false; // Indicate that the session is not valid
		}

		return true; // Session is valid
	}
}
