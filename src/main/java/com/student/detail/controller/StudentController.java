package com.student.detail.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

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

import com.student.detail.exception.StudentNotFoundException1;
import com.student.detail.model.Student;
import com.student.detail.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	// Display the student form
	@GetMapping("/form")
	public String showStudentForm(HttpSession session) {
		// Session validation
		if (session.getAttribute("username") == null) {
			return "redirect:/login?sessionexpired=true";
		}
		return "studentform"; // JSP view for the form
	}

	// Submit the student form
	@PostMapping("/submit")
	public ModelAndView submitStudent(@RequestParam("studentCode") String studentCodeStr,
			@RequestParam("rollNumber") String rollNumberStr, @RequestParam("marks") String marksStr,
			@RequestParam("branch") String branch, @RequestParam("college") String college,
			@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
			@RequestParam("fathername") String fathername, @RequestParam("mobileno") String mobileNo,
			@RequestParam("dateOfBirth") String dateOfBirthStr, @RequestParam("address") String address,
			@RequestParam("status") String statusStr, Model model, RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();

		try {
			// Parse form fields
			int studentCode = Integer.parseInt(studentCodeStr);
			int rollNumber = Integer.parseInt(rollNumberStr);
			int marks = Integer.parseInt(marksStr);
			LocalDate dateOfBirth = LocalDate.parse(dateOfBirthStr);
			boolean status = Boolean.parseBoolean(statusStr);

			// Check for duplicate roll numbers
			try {
				if (studentService.findStudentByRollnumber(rollNumber) != null) {
					model.addAttribute("databaseError", "Roll number already exists.");
					modelAndView.setViewName("studentform");
					return modelAndView;
				}
			} catch (StudentNotFoundException1 e) {
				// No duplicate roll number
			}

			// Create a new Student object
			Student student = new Student(studentCode, rollNumber, marks, branch, college, firstname, lastname,
					fathername, mobileNo, dateOfBirth, address, status, null);

			// Business validation: Marks should be between 0 and 100
			if (marks < 0 || marks > 100) {
				model.addAttribute("invalidMarksError", "Marks should be between 0 and 100.");
				modelAndView.setViewName("studentform");
				return modelAndView;
			}

			// Business validation: Valid mobile number length (10 digits)
			if (mobileNo.length() != 10) {
				model.addAttribute("invalidMobileError", "Mobile number should be 10 digits.");
				modelAndView.setViewName("studentform");
				return modelAndView;
			}

			// Add the student
			if (studentService.addStudent(student) != null) {
				redirectAttributes.addFlashAttribute("sessionMessage", "Student submitted successfully!");
				modelAndView.setViewName("redirect:/student/list");
			} else {
				model.addAttribute("errorMessage", "Error adding student.");
				modelAndView.setViewName("studentform");
			}

		} catch (NumberFormatException e) {
			model.addAttribute("invalidNumberError", "Invalid number format. Please check your input.");
			modelAndView.setViewName("studentform");
		}

		return modelAndView;
	}

	// Display the list of all students
	@GetMapping("/list")
	public String getAllStudents(HttpSession session, Model model) {
		// Session validation
		if (session.getAttribute("username") == null) {
			return "redirect:/login?sessionexpired=true"; // Redirect to login page if session is invalid
		}
		model.addAttribute("students", studentService.getAllStudent());
		return "listofstudents"; // JSP view for displaying the list
	}

	@GetMapping("/page1")
	public String getFirstPage(Model model) {
		List<Student> students = studentService.getPaginatedStudents(1, 3); // Pass 1 for the first page
		model.addAttribute("students", students);
		model.addAttribute("currentPage", 1);
		return "listofstudents";
	}

	@GetMapping("/page2")
	public String getSecondPage(Model model) {
		List<Student> students = studentService.getPaginatedStudents(2, 3); // Pass 2 for the second page
		model.addAttribute("students", students);
		model.addAttribute("currentPage", 2);
		return "listofstudents";
	}

	@GetMapping("/page3")
	public String getThirdPage(Model model) {
		List<Student> students = studentService.getPaginatedStudents(3, 3); // Pass 3 for the third page
		model.addAttribute("students", students);
		model.addAttribute("currentPage", 3);
		return "listofstudents";
	}

	// Show student details for editing (based on roll number)
	@GetMapping("/edit")
	public String editStudent(@RequestParam("rollnumber") int rollNumber, Model model) {
		Student student = null; // Initialize the variable here to avoid compilation error

		try {
			student = studentService.findStudentByRollnumber(rollNumber); // Fetch the student by roll number
			model.addAttribute("student", student); // Add student details to the model
			return "editstudent"; // Return the JSP view to edit the student
		} catch (StudentNotFoundException1 e) {
			// Handle exception if student not found
			model.addAttribute("errorMessage", "Student with roll number " + rollNumber + " not found.");
			return "redirect:/student/list"; // Redirect to the student list if not found
		}
	}

	// Update student details
	@PostMapping("/update")
	public ModelAndView updateStudent(@RequestParam("studentCode") String studentCodeStr,
			@RequestParam("rollNumber") String rollNumberStr, @RequestParam("marks") String marksStr,
			@RequestParam("branch") String branch, @RequestParam("college") String college,
			@RequestParam("firstname") String firstname, @RequestParam("lastname") String lastname,
			@RequestParam("fathername") String fathername, @RequestParam("mobileno") String mobileNo,
			@RequestParam("dateOfBirth") String dateOfBirthStr, @RequestParam("address") String address,
			@RequestParam("status") String statusStr, Model model, RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();
		// Initialize a StringBuilder for error messages
		StringBuilder errorMessage = new StringBuilder();

		// Validation for each field
		if (studentCodeStr == null || studentCodeStr.isEmpty()) {
			errorMessage.append("Student code is required.<br>");
		} else if (!studentCodeStr.matches("\\d+")) {
			errorMessage.append("Student code must be a number.<br>");
		}

		if (rollNumberStr == null || rollNumberStr.isEmpty()) {
			errorMessage.append("Roll number is required.<br>");
		} else if (!rollNumberStr.matches("\\d+")) {
			errorMessage.append("Roll number must be a number.<br>");
		}

		if (marksStr == null || marksStr.isEmpty()) {
			errorMessage.append("Marks are required.<br>");
		} else if (!marksStr.matches("\\d+") || Integer.parseInt(marksStr) < 0 || Integer.parseInt(marksStr) > 100) {
			errorMessage.append("Marks must be a number between 0 and 100.<br>");
		}

		if (branch == null || branch.isEmpty()) {
			errorMessage.append("Branch is required.<br>");
		}

		if (college == null || college.isEmpty()) {
			errorMessage.append("College name is required.<br>");
		}

		if (firstname == null || firstname.isEmpty()) {
			errorMessage.append("First name is required.<br>");
		}

		if (lastname == null || lastname.isEmpty()) {
			errorMessage.append("Last name is required.<br>");
		}

		if (fathername == null || fathername.isEmpty()) {
			errorMessage.append("Father's name is required.<br>");
		}

		if (mobileNo == null || mobileNo.isEmpty()) {
			errorMessage.append("Mobile number is required.<br>");
		} else if (!mobileNo.matches("\\d{10}")) {
			errorMessage.append("Mobile number must be 10 digits long.<br>");
		}

		if (dateOfBirthStr == null || dateOfBirthStr.isEmpty()) {
			errorMessage.append("Date of birth is required.<br>");
		} else {
			try {
				LocalDate.parse(dateOfBirthStr); // Check if the date format is valid
			} catch (DateTimeParseException e) {
				errorMessage.append("Invalid date format for date of birth.<br>");
			}
		}

		if (address == null || address.isEmpty()) {
			errorMessage.append("Address is required.<br>");
		}

		if (statusStr == null || statusStr.isEmpty()) {
			errorMessage.append("Status is required.<br>");
		} else if (!statusStr.equalsIgnoreCase("false") && !statusStr.equalsIgnoreCase("true")) {
			errorMessage.append("Status must be true or false.<br>");
		}

		// If there are errors, return to the form with error messages
		if (errorMessage.length() > 0) {
			model.addAttribute("errorMessage", errorMessage.toString());
			model.addAttribute("studentcode", studentCodeStr);
			model.addAttribute("rollnumber", rollNumberStr);
			model.addAttribute("marks", marksStr);
			model.addAttribute("branch", branch);
			model.addAttribute("college", college);
			model.addAttribute("firstname", firstname);
			model.addAttribute("lastname", lastname);
			model.addAttribute("fathername", fathername);
			model.addAttribute("mobileno", mobileNo);
			model.addAttribute("dateofbirth", dateOfBirthStr);
			model.addAttribute("address", address);
			model.addAttribute("status", statusStr);

			modelAndView.setViewName("editstudent");
			return modelAndView;
		}

		try {
			// Parse form fields
			int studentcode = Integer.parseInt(studentCodeStr);
			int rollnumber = Integer.parseInt(rollNumberStr);
			int marks = Integer.parseInt(marksStr);
			LocalDate dateofbirth = LocalDate.parse(dateOfBirthStr);
			boolean status = Boolean.parseBoolean(statusStr);

			// Business validation: Marks should be between 0 and 100
			if (marks < 0 || marks > 100) {
				model.addAttribute("invalidMarksError", "Marks should be between 0 and 100.");
				modelAndView.setViewName("studentform");
				return modelAndView;
			}

			// Business validation: Valid mobile number length (10 digits)
			if (mobileNo.length() != 10) {
				model.addAttribute("invalidMobileError", "Mobile number should be 10 digits.");
				modelAndView.setViewName("studentform");
				return modelAndView;
			}

			// Find the existing student
			Student existingStudent = null;
			try {
				existingStudent = studentService.findStudentByRollnumber(rollnumber);
			} catch (StudentNotFoundException1 e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (existingStudent == null) {
				model.addAttribute("errorMessage", "Student not found with roll number: " + rollnumber);
				modelAndView.setViewName("studentform");
				return modelAndView;
			}

			// Create updated student object
			Student updatedStudent = new Student(studentcode, rollnumber, marks, branch, college, firstname, lastname,
					fathername, mobileNo, dateofbirth, address, status, existingStudent.getImage());

			// Update the student
			try {
				if (studentService.updateStudent(updatedStudent) != null) {
					redirectAttributes.addFlashAttribute("successMessage", "Student Edit successfully!");
					modelAndView.setViewName("redirect:/student/list");
				} else {
					model.addAttribute("errorMessage", "Error updating student.");
					modelAndView.setViewName("studentform");
				}
			} catch (StudentNotFoundException1 e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (NumberFormatException e) {
			model.addAttribute("invalidNumberError", "Invalid number format. Please check your input.");
			modelAndView.setViewName("studentform");
		}

		return modelAndView;
	}

	@GetMapping("/deleteStudent")
	public String deleteStudent(@RequestParam("rollnumber") int rollnumber, HttpSession session,
			RedirectAttributes redirectAttributes) {
		try {
			Student studentToDelete = studentService.findStudentByRollnumber(rollnumber);

			if (studentToDelete != null) {
				boolean isDeleted = studentService.deleteStudent(studentToDelete);

				if (isDeleted) {
					// Add success message to session attributes
					redirectAttributes.addFlashAttribute("successMessage", "Student deleted successfully.");
				} else {
					// Add failure message to session attributes
					redirectAttributes.addFlashAttribute("errorMessage",
							"Failed to delete student with roll number " + rollnumber);
				}
			} else {
				// Add not found message to session attributes
				redirectAttributes.addFlashAttribute("errorMessage", "No student found with roll number " + rollnumber);
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Error deleting student: " + e.getMessage());
		}

		// Redirect to the list of all students
		return "redirect:/student/list";
	}
}