package com.student.detail.controller;

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

import com.student.detail.model.College;
import com.student.detail.exception.CollegeNotFoundException;
import com.student.detail.service.CollegeService;

@Controller
@RequestMapping("/college")
public class CollegeController {

	@Autowired
	private CollegeService collegeService;

	// Display the college form
	@GetMapping("/form")
	public String showCollegeForm(HttpSession session) {
		if (session.getAttribute("username") == null) {
			return "redirect:/login?sessionexpired=true";
		}
		return "collegeform"; // JSP view for the form
	}

	// Submit the college form
	@PostMapping("/submit")
	public ModelAndView submitCollege(@RequestParam("collegeId") String collegeIdStr,
			@RequestParam("collegeName") String collegeName, @RequestParam("address") String address,
			@RequestParam("city") String city, @RequestParam("state") String state,
			@RequestParam("zipcode") int zipcodeStr, @RequestParam("phoneNumber") String phoneNumber, Model model,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			// Parse form fields
			long collegeId = Long.parseLong(collegeIdStr);

			// Check for duplicate college IDs
			try {
				if (collegeService.findCollegeBycollegeId(collegeId) != null) {
					model.addAttribute("databaseError", "College ID already exists.");
					modelAndView.setViewName("collegeform");
					return modelAndView;
				}
			} catch (CollegeNotFoundException e) {
				// College ID does not exist, proceed
			}

			// Create a new College object
			College college = new College(collegeId, collegeName, address, city, state, zipcodeStr, phoneNumber);

			// Add the college
			if (collegeService.addCollege(college) != null) {
				redirectAttributes.addFlashAttribute("sessionMessage", "College submitted successfully!");
				modelAndView.setViewName("redirect:/college/list");
			} else {
				model.addAttribute("errorMessage", "Error adding college.");
				modelAndView.setViewName("collegeform");
			}

		} catch (NumberFormatException e) {
			model.addAttribute("invalidNumberError", "Invalid number format. Please check your input.");
			modelAndView.setViewName("collegeform");
		}

		return modelAndView;
	}

	// Display the list of all colleges
	@GetMapping("/list")
	public String getAllColleges(HttpSession session, Model model) {
		if (session.getAttribute("username") == null) {
			return "redirect:/login?sessionexpired=true";
		}
		model.addAttribute("colleges", collegeService.getAllColleges());
		return "listofcolleges"; // JSP view for displaying the list
	}

	// Show college details for editing (based on college ID)
	@GetMapping("/edit")
	public String editCollege(@RequestParam("collegeId") long collegeId, Model model) { // Changed to long to match
																						// CollegeService
		try {
			College college = collegeService.findCollegeBycollegeId(collegeId);
			model.addAttribute("college", college);
			return "editcollege"; // JSP view to edit the college
		} catch (CollegeNotFoundException e) {
			model.addAttribute("errorMessage", "College with ID " + collegeId + " not found.");
			return "redirect:/college/list";
		}
	}

	// Update college details
	@PostMapping("/update")
	public ModelAndView updateCollege(@RequestParam("collegeId") String collegeIdStr,
			@RequestParam("collegeName") String collegeName, @RequestParam("address") String address,
			@RequestParam("city") String city, @RequestParam("state") String state,
			@RequestParam("zipcode") String zipcodeStr, @RequestParam("phoneNumber") String phoneNumber, Model model,
			RedirectAttributes redirectAttributes) {

		ModelAndView modelAndView = new ModelAndView();
		StringBuilder errorMessage = new StringBuilder();

		// Trim and validate collegeId
		if (collegeIdStr == null || collegeIdStr.trim().isEmpty()) {
			errorMessage.append("College ID is required.<br>");
		} else {
			try {
				// Trim any spaces before parsing
				long collegeId = Long.parseLong(collegeIdStr.trim()); // Throws exception if not a valid integer
			} catch (NumberFormatException e) {
				errorMessage.append("College ID must be a number.<br>");
			}
		}

		// Validate collegeName
		if (collegeName == null || collegeName.isEmpty()) {
			errorMessage.append("College name is required.<br>");
		}

		// Validate address
		if (address == null || address.isEmpty()) {
			errorMessage.append("Address is required.<br>");
		}

		// Validate city
		if (city == null || city.isEmpty()) {
			errorMessage.append("City is required.<br>");
		}

		// Validate state
		if (state == null || state.isEmpty()) {
			errorMessage.append("State is required.<br>");
		}

		// Validate zipcode
		if (zipcodeStr == null || zipcodeStr.isEmpty()) {
			errorMessage.append("Zipcode is required.<br>");
		} else if (!zipcodeStr.matches("\\d+")) {
			errorMessage.append("Zipcode must be a valid 5-digit number.<br>");
		}

		// Validate phoneNumber
		if (phoneNumber == null || phoneNumber.isEmpty()) {
			errorMessage.append("Phone number is required.<br>");
		} else if (!phoneNumber.matches("\\d{10}")) {
			errorMessage.append("Phone number must be a valid 10-digit number.<br>");
		}

		// If there are errors, return to the form with error messages
		if (errorMessage.length() > 0) {
			model.addAttribute("errorMessage", errorMessage.toString());
			model.addAttribute("collegeId", collegeIdStr);
			model.addAttribute("collegeName", collegeName);
			model.addAttribute("address", address);
			model.addAttribute("city", city);
			model.addAttribute("state", state);
			model.addAttribute("zipcode", zipcodeStr);
			model.addAttribute("phoneNumber", phoneNumber);

			modelAndView.setViewName("editcollege");
			return modelAndView;
		}

		try {
			// Parse form fields
			long collegeId = Long.parseLong(collegeIdStr);
			int zipcode = Integer.parseInt(zipcodeStr);
			// Find the existing college
			College existingCollege = null;
			try {
				existingCollege = collegeService.findCollegeBycollegeId(collegeId);
			} catch (CollegeNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (existingCollege == null) {
				model.addAttribute("errorMessage", "College not found with ID: " + collegeId);
				modelAndView.setViewName("collegeform");
				return modelAndView;
			}

			// Create updated college object
			College updatedCollege = new College(collegeId, collegeName, address, city, state, zipcode, phoneNumber);

			// Update the college
			try {
				if (collegeService.updateCollege(updatedCollege) != null) {
					redirectAttributes.addFlashAttribute("successMessage", "College updated successfully!");
					modelAndView.setViewName("redirect:/college/list");
				} else {
					model.addAttribute("errorMessage", "Error updating college.");
					modelAndView.setViewName("collegeform");
				}
			} catch (CollegeNotFoundException e) {
				e.printStackTrace();
			}

		} catch (NumberFormatException e) {
			model.addAttribute("invalidNumberError", "Invalid number format. Please check your input.");
			modelAndView.setViewName("collegeform");
		}

		return modelAndView;
	}

	// Delete a college by college ID
	@GetMapping("/deleteCollege")
	public String deleteCollege(@RequestParam("collegeId") long collegeId, HttpSession session,
			RedirectAttributes redirectAttributes) { // Changed to long to match CollegeService
		try {
			College collegeToDelete = collegeService.findCollegeBycollegeId(collegeId);

			if (collegeToDelete != null) {
				boolean isDeleted = collegeService.deleteCollege(collegeToDelete);

				if (isDeleted) {
					redirectAttributes.addFlashAttribute("successMessage", "College deleted successfully.");
				} else {
					redirectAttributes.addFlashAttribute("errorMessage", "Error deleting college.");
				}
			} else {
				redirectAttributes.addFlashAttribute("errorMessage", "College not found.");
			}
		} catch (CollegeNotFoundException e) {
			redirectAttributes.addFlashAttribute("errorMessage", "College not found.");
		}

		return "redirect:/college/list";
	}
}
