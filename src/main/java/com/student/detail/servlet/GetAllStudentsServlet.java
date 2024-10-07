package com.student.detail.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.detail.model.Student;
import com.student.detail.service.StudentDBService;
import com.student.detail.service.StudentService;

// Servlet mapping
@WebServlet("/GetAllStudentsServlet")
public class GetAllStudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Inject the StudentService to interact with the database
	private StudentService studentService = new StudentDBService();

	/**
	 * Default constructor.
	 */
	public GetAllStudentsServlet() {
		super();
	}

	/**
	 * Handles GET requests to retrieve all students from the database.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (!SessionUtils.isSessionValid(request, response)) {
	        // If session is invalid, return to prevent further processing
	        return;
	    }

		try {
			// Get all students from the database
			List<Student> allStudents = studentService.getAllStudent();
			request.setAttribute("studentlist", allStudents);

			// Retrieve the success message from the session
			HttpSession session = request.getSession(false); // Get session but do not create a new one if it doesn't
																// exist
			if (session != null) {
				String successMessage = (String) session.getAttribute("successMessage");

				if (successMessage != null) {
					request.setAttribute("successMessage", successMessage); // Pass success message to request
					session.removeAttribute("successMessage"); // Clear the message from the session
				}
			}

			RequestDispatcher requestDispatcher = request.getRequestDispatcher("listofstudents.jsp");
			requestDispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error retrieving students: " + e.getMessage());
		}
	}

	/**
	 * Handles POST requests by redirecting to doGet.
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
