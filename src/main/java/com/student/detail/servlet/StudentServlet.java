package com.student.detail.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.detail.exception.StudentNotFoundException1;
import com.student.detail.model.Student;
import com.student.detail.service.StudentDBService;
import com.student.detail.service.StudentService;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentService studentService = new StudentDBService();

	/**
	 * Default constructor.
	 */
	public StudentServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Check if the user is logged in
		if (!SessionUtils.isSessionValid(request, response)) {
			// If session is invalid, return to prevent further processing
			return;
		}

		// Retrieve parameters from request
		String studentcodeStr = request.getParameter("studentCode");
		String rollnumberStr = request.getParameter("rollNumber");
		String marksStr = request.getParameter("marks");
		String branch = request.getParameter("branch");
		String college = request.getParameter("college");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String fathername = request.getParameter("fathername");
		String mobileno = request.getParameter("mobileno");
		String dateOfBirthStr = request.getParameter("dateOfBirth");
		String address = request.getParameter("address");
		String statusStr = request.getParameter("status");

		try {
			// Convert parameters to appropriate types
			int studentcode = Integer.parseInt(studentcodeStr);
			int rollnumber = Integer.parseInt(rollnumberStr);
			int marks = Integer.parseInt(marksStr);
			LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
			boolean status = Boolean.parseBoolean(statusStr);

			// Check for duplicate roll number
			try {
				if (studentService.findStudentByRollnumber(rollnumber) != null) {
					request.setAttribute("databaseError",
							"Roll number already exists. Please use a different roll number.");
					request.getRequestDispatcher("studentform.jsp").forward(request, response);
					return; // Exit to prevent adding the student
				}
			} catch (StudentNotFoundException1 e) {
				// If no exception is thrown, it means there's no duplicate
				// Continue with adding the student
			}

			// Create Student object and set properties
			Student student = new Student();
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
			student.setimage(null);

			// Add the student record
			studentService.addStudent(student);
			response.sendRedirect("GetAllStudentsServlet"); // Redirect to the success page

		} catch (NumberFormatException e) {
			// Handle number format exception
			request.setAttribute("databaseError", "Invalid number format. Please check your input.");
			request.getRequestDispatcher("studentform.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
