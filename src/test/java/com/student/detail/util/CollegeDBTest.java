package com.student.detail.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.customer.detail.model.College;
import com.student.detail.exception.CollegeNotFoundException;
import com.student.detail.service.CollegeDBService;
import com.student.detail.service.CollegeService;

class CollegeDBTest {

	private static CollegeService collegeService;
	private static List<College> colleges;

	@BeforeAll
	static void setup() {
		String filePath = "D:\\sample\\CollegeData.csv"; // Update this path as necessary
		colleges = CsvFileLoaderCollege.loadCollegesFromCsv(filePath);
		collegeService = new CollegeDBService();

		// Add colleges from the CSV to the service
		colleges.forEach(college -> {
			try {
				collegeService.addCollege(college);
			} catch (Exception e) {
				fail("Failed to add college: " + e.getMessage());
			}
		});
	}

	@Test
	void testUpdateCollegeDetails() {
		try {
			College collegeToUpdate = collegeService.findCollegeById(1);
			collegeToUpdate.setCollegeName("JNTU");
			collegeToUpdate.setCity("Kakinada");

			// Update college details
			College updatedCollege = collegeService.updateCollege(collegeToUpdate);
			assertEquals("JNTU", updatedCollege.getCollegeName());
			assertEquals("Kakinada", updatedCollege.getCity());

		} catch (CollegeNotFoundException e) {
			fail("Error updating college: " + e.getMessage());
		}
	}

	@Test
	void testDeleteCollege() {
		try {
			College collegeToDelete = collegeService.findCollegeById(2);
			boolean isDeleted = collegeService.deleteCollege(collegeToDelete);
			assertTrue(isDeleted, "College should be deleted successfully");

		} catch (CollegeNotFoundException e) {
			fail("Error deleting college: " + e.getMessage());
		}
	}

	@Test
	void testFindCollegeByName() {
		try {
			College foundCollegeByName = collegeService.findCollegeByName("JNTU");
			assertNotNull(foundCollegeByName, "College should be found by name");

		} catch (CollegeNotFoundException e) {
			fail("Error finding college by name: " + e.getMessage());
		}
	}

	@Test
	void testFindCollegesByCity() {
		List<College> collegesByCity = collegeService.findCollegesByCity("Bhimavaram");
		assertFalse(collegesByCity.isEmpty(), "Colleges in Bhimavaram should be found");
	}

	@Test
	void testGetCountOfColleges() {
		int collegeCount = collegeService.getCountOfColleges();
		assertTrue(collegeCount > 0, "College count should be greater than zero");
	}

	@Test
	void testGetAllColleges() {
		List<College> allColleges = collegeService.getAllColleges();
		assertNotNull(allColleges, "All colleges list should not be null");
		assertFalse(allColleges.isEmpty(), "All colleges list should not be empty");
	}

	@Test
	void testFindCollegeById() {
		try {
			// Test for an existing college
			College foundCollege = collegeService.findCollegeById(1); // Assuming ID 1 exists
			assertNotNull(foundCollege, "College with ID 1 should be found");
			assertEquals(1, foundCollege.getCollegeId(), "College ID should match");

			// Test for a non-existing college
			assertThrows(CollegeNotFoundException.class, () -> {
				collegeService.findCollegeById(999); // Assuming ID 999 does not exist
			}, "College with ID 999 should not be found");

		} catch (CollegeNotFoundException e) {
			fail("Unexpected exception while finding college by ID: " + e.getMessage());
		}
	}
}
