package com.student.detail.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.detail.service.LoginDBService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDBService loginDBService = new LoginDBService();

	/**
	 * Default constructor.
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Handles GET requests.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * Handles POST requests (login logic).
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get the username and password from the request
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Check credentials using the LoginDBService
		boolean isAuthenticated = loginDBService.validateUser(username, password);

		// Simple validation using if statement
		if (isAuthenticated) { // No need to check equals(username) and equals(password) again
			// Create a session if the user is authenticated
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			// Redirect to the success page (e.g., dashboard)
			response.sendRedirect("index.jsp?loginSuccess=true");
		} else {
			// Authentication failed, redirect to the login page with an error message
			response.sendRedirect("login.jsp?error=true");
		}
	}
}
