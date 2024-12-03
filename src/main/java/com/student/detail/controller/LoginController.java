package com.student.detail.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.student.detail.service.LoginDBService;

@Controller
@SessionAttributes("username") // This makes 'username' a session attribute
public class LoginController {

	@Autowired
	private LoginDBService loginDBService;

	// Handles GET requests for the login page
	@GetMapping("/login")
	public String showLoginPage(HttpServletRequest request, Model model) {
		// Get 'sessionexpired' parameter from the request
		String sessionExpired = request.getParameter("sessionexpired");
		System.out.println("Session expired parameter: " + sessionExpired);

		// If session expired, add this flag to the model
		if (sessionExpired != null && sessionExpired.equals("true")) {
			model.addAttribute("sessionexpired", true);
		}

		return "login"; // Return the login page
	}

	// Handles POST requests for the login form submission
	@PostMapping("/login")
	public String handleLogin(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model) {

		// Validate credentials with the LoginDBService
		boolean isAuthenticated = loginDBService.validateUser(username, password);

		if (isAuthenticated) {
			// Add the username to the session
			model.addAttribute("username", username);
			return "redirect:/index?loginSuccess=true"; // Redirect to the index page with a success flag
		} else {
			// If authentication fails, add an error attribute to the model and return to
			// the login page
			return "redirect:/login?error=true";// Return to the login page with error message
		}
	}

	// Logout method to clear the session
	@GetMapping("/logout")
	public String logout(SessionStatus sessionStatus) {
		// Check if the session has a "username" attribute
		if (sessionStatus.isComplete()) {
			return "redirect:/login?logout=true"; // Already logged out, redirect to login page
		}

		// Clear session attributes (invalidate session)
		sessionStatus.setComplete(); // Removes all session attributes

		// Redirect to login page after logout
		return "redirect:/login?logoutSuccess=true"; // Redirect to login page with logout success flag
	}

}