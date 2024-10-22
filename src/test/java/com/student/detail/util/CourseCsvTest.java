package com.student.detail.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.student.detail.model.Course;
import com.student.detail.util.CsvFileLodderCourse;

import java.util.Map;

class CourseCsvTest {

	@Test
	void testLoadCourseData() {
		String filePath = "D:\\sample\\Coursedata.csv"; // Update this path as necessary

		// Load the course data from the CSV file
		Map<String, Course> courses = CsvFileLodderCourse.loadCourseData(filePath);

		// Check if the courses map is not null and not empty
		if (courses != null && !courses.isEmpty()) {
			System.out.println("Courses (Unsorted):");
			for (Course course : courses.values()) {
				System.out.println(course);
			}
		} else {
			System.err.println("No course data loaded or file not found.");
		}

		// Assert that the courses map is not null and not empty
		assertNotNull(courses, "Courses map should not be null.");
		assertFalse(courses.isEmpty(), "Courses map should not be empty.");
	}
}
