package com.student.detail.servlet;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService studentService = new StudentDBService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!SessionUtils.isSessionValid(request, response)) {
	        // If session is invalid, return to prevent further processing
	        return;
	    }

		// Retrieve and trim parameters from request
		String studentcodeStr = request.getParameter("studentCode").trim();
		String rollnumberStr = request.getParameter("rollNumber").trim();
		String marksStr = request.getParameter("marks").trim();
		String branch = request.getParameter("branch").trim();
		String college = request.getParameter("college").trim();
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		String fathername = request.getParameter("fathername").trim();
		String mobileno = request.getParameter("mobileno").trim();
		String dateOfBirthStr = request.getParameter("dateOfBirth").trim();
		String address = request.getParameter("address").trim(); // New field for address
		String statusStr = request.getParameter("status").trim();

		// Initialize error messages
		StringBuilder errorMessage = new StringBuilder();

		// Validation for each field
		if (studentcodeStr.isEmpty()) {
			errorMessage.append("Student code is required.<br>");
		} else if (!studentcodeStr.matches("\\d+")) {
			errorMessage.append("Student code must be a number.<br>");
		}

		if (rollnumberStr.isEmpty()) {
			errorMessage.append("Roll number is required.<br>");
		} else if (!rollnumberStr.matches("\\d+")) {
			errorMessage.append("Roll number must be a number.<br>");
		}

		if (marksStr.isEmpty()) {
			errorMessage.append("Marks are required.<br>");
		} else if (!marksStr.matches("\\d+") || Integer.parseInt(marksStr) < 0 || Integer.parseInt(marksStr) > 100) {
			errorMessage.append("Marks must be a number between 0 and 100.<br>");
		}

		if (branch.isEmpty()) {
			errorMessage.append("Branch is required.<br>");
		}

		if (college.isEmpty()) {
			errorMessage.append("College name is required.<br>");
		}

		if (firstname.isEmpty()) {
			errorMessage.append("First name is required.<br>");
		}

		if (lastname.isEmpty()) {
			errorMessage.append("Last name is required.<br>");
		}

		if (fathername.isEmpty()) {
			errorMessage.append("Father's name is required.<br>");
		}

		if (mobileno.isEmpty()) {
			errorMessage.append("Mobile number is required.<br>");
		} else if (!mobileno.matches("\\d{10}")) { // Assuming a 10-digit mobile number
			errorMessage.append("Mobile number must be 10 digits long.<br>");
		}

		if (dateOfBirthStr.isEmpty()) {
			errorMessage.append("Date of birth is required.<br>");
		} else {
			try {
				LocalDate.parse(dateOfBirthStr); // Check if the date format is valid
			} catch (DateTimeParseException e) {
				errorMessage.append("Invalid date format for date of birth.<br>");
			}
		}

		if (address.isEmpty()) {
			errorMessage.append("Address is required.<br>");
		}

		if (statusStr.isEmpty()) {
			errorMessage.append("Status is required.<br>");
		} else if (!statusStr.equalsIgnoreCase("true") && !statusStr.equalsIgnoreCase("false")) {
			errorMessage.append("Status must be true or false.<br>");
		}

		// If there are any error messages, send them back to the editstudent.jsp page
		if (errorMessage.length() > 0) {
			// Set error message
			request.setAttribute("errorMessage", errorMessage.toString());

			// Retain input values using Student class structure
			request.setAttribute("studentcode", studentcodeStr);
			request.setAttribute("rollnumber", rollnumberStr);
			request.setAttribute("marks", marksStr);
			request.setAttribute("branch", branch);
			request.setAttribute("college", college);
			request.setAttribute("firstname", firstname);
			request.setAttribute("lastname", lastname);
			request.setAttribute("fathername", fathername);
			request.setAttribute("mobileno", mobileno);
			request.setAttribute("dateofbirth", dateOfBirthStr);
			request.setAttribute("address", address);
			request.setAttribute("status", statusStr);

			// Forward back to editstudent.jsp
			RequestDispatcher dispatcher = request.getRequestDispatcher("editstudent.jsp");
			dispatcher.forward(request, response);
			return; // Exit to prevent further processing
		}

		try {
			// Convert parameters to appropriate types
			int studentcode = Integer.parseInt(studentcodeStr);
			int rollnumber = Integer.parseInt(rollnumberStr);
			int marks = Integer.parseInt(marksStr);
			LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr); // Assumes the date format is compatible
			boolean status = Boolean.parseBoolean(statusStr);

			// Create a student object and set the updated details
			Student student = studentService.findStudentByRollnumber(rollnumber);
			if (student != null) {
				student.setStudentcode(studentcode);
				student.setRollnumber(rollnumber);
				student.setMarks(marks);
				student.setBranch(branch);
				student.setCollege(college);
				student.setFirstname(firstname);
				student.setLastname(lastname);
				student.setFathername(fathername);
				student.setMobileno(mobileno);
				student.setDateofbirth(dateOfBirth);
				student.setAddress(address);
				student.setStatus(status);

				// Update student by rollnumber in the database
				boolean isUpdated = studentService.updateStudent(student) != null;

				if (isUpdated) {
					HttpSession session = request.getSession();
					session.setAttribute("successMessage", "Edit successfully");
					response.sendRedirect("GetAllStudentsServlet");
				} else {
					// On failure to update, redirect with an error message
					request.setAttribute("errorMessage", "Error updating student with roll number " + rollnumber + ".");
					RequestDispatcher dispatcher = request.getRequestDispatcher("editstudent.jsp");
					dispatcher.forward(request, response);
				}
			} else {
				// If student not found
				request.setAttribute("errorMessage", "Student with roll number " + rollnumber + " not found.");
				RequestDispatcher dispatcher = request.getRequestDispatcher("editstudent.jsp");
				dispatcher.forward(request, response);
			}
		} catch (NumberFormatException e) {
			// Handle invalid input format
			request.setAttribute("errorMessage", "Invalid input format. Please check the fields.");
			RequestDispatcher dispatcher = request.getRequestDispatcher("editstudent.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			// Handle other exceptions
			e.printStackTrace();
			request.setAttribute("errorMessage", "Error updating student: " + e.getMessage());
			RequestDispatcher dispatcher = request.getRequestDispatcher("editstudent.jsp");
			dispatcher.forward(request, response);
		}
	}
}