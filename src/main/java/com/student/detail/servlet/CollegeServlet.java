package com.student.detail.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.student.detail.model.College;
import com.student.detail.service.CollegeDBService;
import com.student.detail.service.CollegeService;

/**
 * Servlet implementation class CollegeServlet
 */
@WebServlet("/CollegeServlet")
public class CollegeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CollegeService collegeService = new CollegeDBService(); // College service instance

	/**
	 * Default constructor.
	 */
	public CollegeServlet() {
		// Default constructor
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Retrieve parameters from request
		String collegeIdStr = request.getParameter("collegeId");
		String collegeName = request.getParameter("collegeName");
		String address = request.getParameter("address");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zipcodeStr = request.getParameter("zipcode");
		String phoneNumber = request.getParameter("phoneNumber");

		try {
			// Convert parameters to appropriate types
			int collegeId = Integer.parseInt(collegeIdStr); // College ID is still an integer

			// Create College object and set properties
			College college = new College();
			college.setCollegeId(collegeId);
			college.setCollegeName(collegeName);
			college.setAddress(address);
			college.setCity(city);
			college.setState(state);
			college.setZipcode(zipcodeStr); // No need to parse zipcode as it is now a string
			college.setPhoneNumber(phoneNumber);

			// Add college to the database
			collegeService.addCollege(college);
		} catch (NumberFormatException e) {
			System.err.println("Error: Invalid number format. Check the input values.");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Error adding college: " + e.getMessage());
		}

		System.out.println("collegeName = " + collegeName + ", collegeId = " + collegeIdStr);

		// Redirect to the list of colleges after adding the record
		response.getWriter()
				.append("collegeId = " + collegeIdStr + ", collegeName = " + collegeName + ", address = " + address
						+ ", city = " + city + ", state = " + state + ", zipcode = " + zipcodeStr + ", phoneNumber = "
						+ phoneNumber);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// For POST requests, forward to the doGet method
		doGet(request, response);
	}

}
