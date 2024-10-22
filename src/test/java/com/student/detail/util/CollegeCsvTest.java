package com.student.detail.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.student.detail.model.College;
import com.student.detail.util.CsvFileLoaderCollege;

import java.util.List;

class CollegeCsvTest {

	@Test
	void testLoadCollegeData() {
		String filePath = "D:\\sample\\CollegeData.csv"; // Update this path as necessary

		// Load the college data from the CSV file
		CsvFileLoaderCollege loader = new CsvFileLoaderCollege();
		List<College> colleges = loader.loadCollegesFromCsv(filePath);

		// Assert that the colleges list is not null
		assertNotNull(colleges, "Colleges list should not be null.");

		// Assert that the colleges list is not empty
		assertFalse(colleges.isEmpty(), "Colleges list should not be empty.");

		// Print out the loaded colleges for verification
		System.out.println("Colleges (Unsorted):");
		for (College college : colleges) {
			System.out.println(college);
		}
	}
}
