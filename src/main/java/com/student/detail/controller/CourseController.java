package com.student.detail.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.student.detail.exception.CourseNotFoundException;
import com.student.detail.model.Course;
import com.student.detail.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	// Display the course form
	@GetMapping("/form")
	public String showCourseForm(HttpSession session) {
		// Session validation
		if (session.getAttribute("username") == null) {
			return "redirect:/login?sessionexpired=true";
		}
		return "courseform"; // JSP view for the form
	}

	// Submit the course form
	@PostMapping("/submit")
	public ModelAndView submitCourse(@RequestParam("courseId") String courseIdStr,
			@RequestParam("courseName") String courseName, @RequestParam("credits") String creditsStr,
			@RequestParam("department") String department, @RequestParam("duration") String durationStr,
			@RequestParam("feeStructure") String feeStructure,
			@RequestParam("lengthOfStudents") String lengthOfStudentsStr,
			@RequestParam("startDate") String startDateStr, @RequestParam("endDate") String endDateStr, Model model,
			RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();

		try {
			// Parse form fields
			int courseId = Integer.parseInt(courseIdStr);
			int credits = Integer.parseInt(creditsStr);
			int duration = Integer.parseInt(durationStr);
			int lengthOfStudents = Integer.parseInt(lengthOfStudentsStr);
			LocalDate startDate = LocalDate.parse(startDateStr);
			LocalDate endDate = LocalDate.parse(endDateStr);

			// Check for duplicate course codes
			try {
				if (courseService.findCourseById(courseId) != null) {
					model.addAttribute("databaseError", "Course ID already exists.");
					modelAndView.setViewName("courseform");
					return modelAndView;
				}
			} catch (CourseNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			// Create a new Course object
			Course course = new Course(courseId, courseName, credits, department, duration, feeStructure,
					lengthOfStudents, startDate, endDate);

			// Business validation: Credits should be positive
			if (credits <= 0) {
				model.addAttribute("invalidCreditsError", "Credits should be a positive number.");
				modelAndView.setViewName("courseform");
				return modelAndView;
			}

			// Add the course
			if (courseService.addCourse(course) != null) {
				redirectAttributes.addFlashAttribute("sessionMessage", "Course submitted successfully!");
				modelAndView.setViewName("redirect:/course/list");
			} else {
				model.addAttribute("errorMessage", "Error adding course.");
				modelAndView.setViewName("courseform");
			}

		} catch (NumberFormatException e) {
			model.addAttribute("invalidNumberError", "Invalid number format. Please check your input.");
			modelAndView.setViewName("courseform");
		} catch (DateTimeParseException e) {
			model.addAttribute("invalidDateError", "Invalid date format. Please check your input.");
			modelAndView.setViewName("courseform");
		}

		return modelAndView;
	}

	// Display the list of all courses
	@GetMapping("/list")
	public String getAllCourse(HttpSession session, Model model) {
		// Session validation
		if (session.getAttribute("username") == null) {
			return "redirect:/login?sessionexpired=true"; // Redirect to login page if session is invalid
		}
		model.addAttribute("courses", courseService.getAllCourses());
		return "listofcourses"; // JSP view for displaying the list
	}

	// Show course details for editing (based on course ID)
	@GetMapping("/edit")
	public String editCourse(@RequestParam("courseId") int courseId, Model model) {
		Course course = null; // Initialize the variable here to avoid compilation error

		try {
			// Fetch the course by course ID
			course = courseService.findCourseById(courseId);

			// Add course details to the model
			model.addAttribute("course", course);

			// Return the JSP view to edit the course
			return "editcourse"; // Replace with your actual view name for editing the course
		} catch (CourseNotFoundException e) {
			// Handle exception if course not found
			model.addAttribute("errorMessage", "Course with ID " + courseId + " not found.");

			// Redirect to the course list if not found
			return "redirect:/course/list"; // Make sure your course list URL is correct
		}
	}

	// Update course details
	@PostMapping("/update")
	public ModelAndView updateCourse(@RequestParam("courseId") String courseIdStr,
			@RequestParam("courseName") String courseName, @RequestParam("credits") String creditsStr,
			@RequestParam("department") String department, @RequestParam("duration") String durationStr,
			@RequestParam("feeStructure") String feeStructure,
			@RequestParam("lengthOfStudents") String lengthOfStudentsStr,
			@RequestParam("startDate") String startDateStr, @RequestParam("endDate") String endDateStr, Model model,
			RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();
		StringBuilder errorMessage = new StringBuilder();

		// Trim and validate courseId
		if (courseIdStr == null || courseIdStr.trim().isEmpty()) {
			errorMessage.append("Course ID is required.<br>");
		} else {
			try {
				// Trim any spaces before parsing
				int courseId = Integer.parseInt(courseIdStr.trim()); // Throws exception if not a valid integer
			} catch (NumberFormatException e) {
				errorMessage.append("Course ID must be a number.<br>");
			}
		}
		if (courseName == null || courseName.isEmpty()) {
			errorMessage.append("Course name is required.<br>");
		}

		if (creditsStr == null || creditsStr.isEmpty()) {
			errorMessage.append("Credits are required.<br>");
		} else if (!creditsStr.matches("\\d+")) {
			errorMessage.append("Credits must be a number.<br>");
		}

		if (department == null || department.isEmpty()) {
			errorMessage.append("Department is required.<br>");
		}

		if (durationStr == null || durationStr.isEmpty()) {
			errorMessage.append("Duration is required.<br>");
		} else if (!durationStr.matches("\\d+")) {
			errorMessage.append("Duration must be a valid number of weeks.<br>");
		}

		if (feeStructure == null || feeStructure.isEmpty()) {
			errorMessage.append("Fee structure is required.<br>");
		}

		if (lengthOfStudentsStr == null || lengthOfStudentsStr.isEmpty()) {
			errorMessage.append("Number of students is required.<br>");
		} else if (!lengthOfStudentsStr.matches("\\d+")) {
			errorMessage.append("Number of students must be a number.<br>");
		}

		if (startDateStr == null || startDateStr.isEmpty()) {
			errorMessage.append("Start date is required.<br>");
		} else {
			try {
				LocalDate.parse(startDateStr);
			} catch (DateTimeParseException e) {
				errorMessage.append("Invalid date format for start date.<br>");
			}
		}

		if (endDateStr == null || endDateStr.isEmpty()) {
			errorMessage.append("End date is required.<br>");
		} else {
			try {
				LocalDate.parse(endDateStr);
			} catch (DateTimeParseException e) {
				errorMessage.append("Invalid date format for end date.<br>");
			}
		}

// If there are errors, return to the form with error messages
		if (errorMessage.length() > 0) {
			model.addAttribute("errorMessage", errorMessage.toString());
			model.addAttribute("courseId", courseIdStr);
			model.addAttribute("courseName", courseName);
			model.addAttribute("credits", creditsStr);
			model.addAttribute("department", department);
			model.addAttribute("duration", durationStr);
			model.addAttribute("feeStructure", feeStructure);
			model.addAttribute("lengthOfStudents", lengthOfStudentsStr);
			model.addAttribute("startDate", startDateStr);
			model.addAttribute("endDate", endDateStr);

			modelAndView.setViewName("editcourse");
			return modelAndView;
		}

		try {
// Parse form fields
			int courseid = Integer.parseInt(courseIdStr);
			int credits = Integer.parseInt(creditsStr);
			int duration = Integer.parseInt(durationStr);
			int lengthOfStudents = Integer.parseInt(lengthOfStudentsStr);
			LocalDate startDate = LocalDate.parse(startDateStr);
			LocalDate endDate = LocalDate.parse(endDateStr);

			Course existingStudent = null;
			try {
				existingStudent = courseService.findCourseById(courseid);
			} catch (CourseNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (existingStudent == null) {
				model.addAttribute("errorMessage", "Student not found with roll number: " + courseid);
				modelAndView.setViewName("courseform");
				return modelAndView;
			}

// Create updated course object
			Course updatedCourse = new Course(courseid, courseName, credits, department, duration, feeStructure,
					lengthOfStudents, startDate, endDate);

// Update the course
			try {
				if (courseService.updateCourse(updatedCourse) != null) {
					redirectAttributes.addFlashAttribute("successMessage", "Course updated successfully!");
					modelAndView.setViewName("redirect:/course/list");
				} else {
					model.addAttribute("errorMessage", "Error updating course.");
					modelAndView.setViewName("courseform");
				}
			} catch (CourseNotFoundException e) {
				e.printStackTrace();
			}

		} catch (NumberFormatException e) {
			model.addAttribute("invalidNumberError", "Invalid number format. Please check your input.");
			modelAndView.setViewName("courseform");
		}

		return modelAndView;
	}

	// Delete a course by course ID
	@GetMapping("/deleteCourse")
	public String deleteCourse(@RequestParam("courseId") int courseId, HttpSession session,
			RedirectAttributes redirectAttributes) {
		try {
			Course courseToDelete = courseService.findCourseById(courseId);

			if (courseToDelete != null) {
				boolean isDeleted = courseService.deleteCourse(courseToDelete);

				if (isDeleted) {
					// Add success message to session attributes
					redirectAttributes.addFlashAttribute("successMessage", "Course deleted successfully.");
				} else {
					// Add failure message to session attributes
					redirectAttributes.addFlashAttribute("errorMessage", "Error deleting course.");
				}
			} else {
				redirectAttributes.addFlashAttribute("errorMessage", "Course not found.");
			}
		} catch (CourseNotFoundException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "Course not found.");
		}

		return "redirect:/course/list";
	}
}
