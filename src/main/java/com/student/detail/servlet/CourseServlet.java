package com.student.detail.servlet;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.detail.model.Course;
import com.student.detail.service.CourseDBService;
import com.student.detail.service.CourseService;

/**
 * Servlet implementation class CourseServlet
 */
@WebServlet("/CourseServlet")
public class CourseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CourseService courseService = new CourseDBService();

	/**
	 * Default constructor.
	 */
	public CourseServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve parameters from the request
		String courseId = request.getParameter("courseId");
		String courseName = request.getParameter("courseName");
		String creditsStr = request.getParameter("credits");
		String department = request.getParameter("department");
		String durationStr = request.getParameter("duration");
		String feeStructure = request.getParameter("feeStructure");
		String lengthOfStudentsStr = request.getParameter("lengthOfStudents");
		String startDateStr = request.getParameter("startDate");
		String endDateStr = request.getParameter("endDate");

		try {
			int credits = Integer.parseInt(creditsStr);
			int duration = Integer.parseInt(durationStr);
			int lengthOfStudents = Integer.parseInt(lengthOfStudentsStr);
			LocalDate startDate = LocalDate.parse(startDateStr);
			LocalDate endDate = LocalDate.parse(endDateStr);

			Course course = new Course();
			course.setCourseId(courseId);
			course.setCourseName(courseName);
			course.setCredits(credits);
			course.setDepartment(department);
			course.setDuration(duration);
			course.setFeeStructure(feeStructure);
			course.setLengthOfStudents(lengthOfStudents);
			course.setStartDate(startDate);
			course.setEndDate(endDate);

			courseService.addCourse(course);
		} catch (NumberFormatException e) {
			System.err.println("Error: Invalid number format. Check the input values.");
		} catch (Exception e) {
			System.err.println("Error adding student: " + e.getMessage());
		}
		response.getWriter()
				.append("CourseId = " + courseId + ", CourseName = " + courseName + ", Credits = " + creditsStr
						+ ", Department = " + department + ", Duration = " + durationStr + ", FeeStructure = "
						+ feeStructure + ", NumberOfStudents = " + lengthOfStudentsStr + ", StartDate = " + startDateStr
						+ ", EndDate = " + endDateStr);

		// response.sendRedirect("courselist.html");
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
