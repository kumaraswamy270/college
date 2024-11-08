package com.student.detail.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.detail.exception.CourseNotFoundException;
import com.student.detail.model.Course;
import com.student.detail.service.CourseDBService;
import com.student.detail.service.CourseService;

@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CourseService courseService = new CourseDBService();

	public CourseServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Check if the user is logged in
		if (!SessionUtils.isSessionValid(request, response)) {
			return;
		}

		// Retrieve parameters from request
		String courseIdStr = request.getParameter("courseId");
		String courseName = request.getParameter("courseName");
		String creditsStr = request.getParameter("credits");
		String department = request.getParameter("department");
		String durationStr = request.getParameter("duration");
		String feeStructure = request.getParameter("feeStructure");
		String lengthOfStudentsStr = request.getParameter("lengthOfStudents");
		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");

		try {
			// Convert parameters to appropriate types

			int credits = Integer.parseInt(creditsStr);
			int duration = Integer.parseInt(durationStr);
			int lengthOfStudents = Integer.parseInt(lengthOfStudentsStr);
			LocalDate startDate = LocalDate.parse(startDateStr);
			LocalDate endDate = LocalDate.parse(endDateStr);

			// Check for duplicate course ID
			try {
				if (courseService.findCourseByCode(courseIdStr) != null) {
					request.setAttribute("databaseError", "Course ID already exists. Please use a different ID.");
					request.getRequestDispatcher("courseform.jsp").forward(request, response);
					return;
				}
			} catch (CourseNotFoundException e) {
				System.out.println("No duplicate course ID found. Proceeding with adding the course.");
			}

			// Create Course object and set properties
			Course course = new Course();
			course.setCourseId(courseIdStr);
			course.setCourseName(courseName);
			course.setCredits(credits);
			course.setDepartment(department);
			course.setDuration(duration);
			course.setFeeStructure(feeStructure);
			course.setLengthOfStudents(lengthOfStudents);
			course.setStartDate(startDate);
			course.setEndDate(endDate);

			// Add the course record
			if (courseService.addCourse(course) != null) {
				HttpSession session = request.getSession();
				session.setAttribute("sessionMessage", "Course submitted successfully!");
				response.sendRedirect("GetAllCoursesServlet");
			} else {
				request.setAttribute("errorMessage", "Error adding course.");
				request.getRequestDispatcher("courseform.jsp").forward(request, response);
			}

		} catch (NumberFormatException e) {
			request.setAttribute("invalidNumberError", "Invalid number format. Please check your input.");
			request.getRequestDispatcher("courseform.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
