package com.student.detail.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.customer.detail.model.College;
import com.student.detail.util.CsvFileLoaderCollege;

import java.util.List;

class CollegeCsvTest {

	@Test
	void testLoadCollegeData() {
		String filePath = "D:\\sample\\CollegeData.csv"; // Adjust the file path as necessary

		// Load the college data from the CSV file
		CsvFileLoaderCollege loader = new CsvFileLoaderCollege();
		List<College> colleges = loader.loadCollegesFromCsv(filePath);

		// Assert that the colleges list is not null and not empty
		assertNotNull(colleges, "Colleges list should not be null.");
		assertFalse(colleges.isEmpty(), "Colleges list should not be empty.");

		// Validate the data of a sample college (replace with actual expected data if
		// available)
		College sampleCollege = colleges.get(0);
		assertNotNull(sampleCollege.getCollegeId(), "College ID should not be null");
		assertNotNull(sampleCollege.getCollegeName(), "College name should not be null");
		assertNotNull(sampleCollege.getAddress(), "College address should not be null");
		assertNotNull(sampleCollege.getCity(), "College city should not be null");
		assertNotNull(sampleCollege.getState(), "College state should not be null");
		assertNotNull(sampleCollege.getZipcode(), "College zipcode should not be null");
		assertNotNull(sampleCollege.getPhoneNumber(), "College phone number should not be null");
	}
}
