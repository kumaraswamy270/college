package com.student.detail.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.student.detail.exception.CollegeNotFoundException;
import com.student.detail.model.College;
import com.student.detail.service.CollegeDBService;
import com.student.detail.service.CollegeService;
import com.student.detail.util.CsvFileLoaderCollege;

import java.util.List;

class CollegeDBTest {

	@Test
	void testCollegeOperations() {
		// 1. Load data from CSV
		String filePath = "D:\\sample\\CollegeData.csv"; // Update this path as necessary
		List<College> colleges = CsvFileLoaderCollege.loadCollegesFromCsv(filePath);

		// 2. Initialize the CollegeService implementation
		CollegeService collegeService = new CollegeDBService();

		// Add colleges from the CSV to the service
		for (College college : colleges) {
			try {
				collegeService.addCollege(college);
			} catch (Exception e) {
				System.err.println("Error adding college: " + e.getMessage());
			}
		}

		// Update a college's details
		try {
			College collegeToUpdate = collegeService.findCollegeById(1);
			collegeToUpdate.setCollegeName("JNTU");
			collegeToUpdate.setCity("Kakinada");

			// Update college details
			College updatedCollege = collegeService.updateCollege(collegeToUpdate);
			System.out.println("Updated college: " + updatedCollege);
			assertEquals("JNTU", updatedCollege.getCollegeName());
			assertEquals("Kakinada", updatedCollege.getCity());

		} catch (CollegeNotFoundException e) {
			System.err.println("Error updating college: " + e.getMessage());
		}

		// Delete a college
		try {
			College collegeToDelete = collegeService.findCollegeById(2);
			boolean isDeleted = collegeService.deleteCollege(collegeToDelete);
			System.out.println("College deleted: " + isDeleted);
			assertTrue(isDeleted);

		} catch (CollegeNotFoundException e) {
			System.err.println("Error deleting college: " + e.getMessage());
		}

		// Find a college by name
		try {
			College foundCollegeByName = collegeService.findCollegeByName("B.V Raju");
			System.out.println("Found college by name: " + foundCollegeByName);
			assertNotNull(foundCollegeByName);

		} catch (CollegeNotFoundException e) {
			System.err.println("Error finding college by name: " + e.getMessage());
		}

		// Find colleges by city
		List<College> collegesByCity = collegeService.findCollegesByCity("Bhimavaram");
		System.out.println("Colleges in Bhimavaram: " + collegesByCity);
		assertFalse(collegesByCity.isEmpty());

		// Get the count of all colleges
		int collegeCount = collegeService.getCountOfColleges();
		System.out.println("Total number of colleges: " + collegeCount);
		assertTrue(collegeCount > 0);

		// Fetch and print all colleges
		List<College> allColleges = collegeService.getAllColleges();
		System.out.println("All colleges: " + allColleges);
		assertFalse(allColleges.isEmpty());
	}
}
