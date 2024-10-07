package com.student.detail.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.student.detail.model.Student;
import com.student.detail.service.StudentDBService;
import com.student.detail.service.StudentService;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService studentService = new StudentDBService(); // Make sure this is the correct implementation

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Get rollnumber from the request (from URL parameter)
		String rollnumber = request.getParameter("rollnumber");

		// Check if rollnumber is valid
		if (rollnumber == null || rollnumber.isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid roll number.");
			return;
		}

		try {
			// Fetch the student details from the database using the service
			Student student = studentService.findStudentByRollnumber(Integer.parseInt(rollnumber));

			// Check if student exists
			if (student == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Student not found.");
				return;
			}

			// Set the student object as a request attribute to be accessed in the JSP
			request.setAttribute("student", student);

			// Forward the request to the edit form JSP
			RequestDispatcher dispatcher = request.getRequestDispatcher("editstudent.jsp");
			dispatcher.forward(request, response);

		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid roll number format.");
		} catch (Exception e) {
			// Log the exception and send a server error response
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
					"An error occurred while processing the request.");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Forward POST requests to doGet
		doGet(request, response);
	}
}
