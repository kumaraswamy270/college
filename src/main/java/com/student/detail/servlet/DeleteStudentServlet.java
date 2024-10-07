package com.student.detail.servlet;

import java.io.IOException;

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
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteStudentServlet")
public class DeleteStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentService studentService = new StudentDBService();// Initialize your student service

	@Override
	public void init() throws ServletException {
		super.init(); // Optional: can be omitted as it's automatically called.
		// Any additional initialization code can go here if needed.
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String rollnumberStr = request.getParameter("rollnumber");
			int rollnumber = Integer.parseInt(rollnumberStr);

			Student studentToDelete = studentService.findStudentByRollnumber(rollnumber);

			if (studentToDelete != null) {
				boolean isDeleted = studentService.deleteStudent(studentToDelete);

				if (isDeleted) {
					HttpSession session = request.getSession(true); // Create session if doesn't exist
					session.setAttribute("successMessage", "Student deleted successfully.");
					session.getAttribute("successMessage");

					// Redirect to GetAllStudentsServlet
					response.sendRedirect("GetAllStudentsServlet");
				} else {
					response.getWriter().println("Failed to delete student with roll number " + rollnumber);
				}
			} else {
				response.getWriter().println("No student found with roll number " + rollnumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().println("Error deleting student: " + e.getMessage());
		}
	}
}
