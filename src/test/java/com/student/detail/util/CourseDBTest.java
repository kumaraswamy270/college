package com.student.detail.util;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import com.student.detail.exception.CourseNotFoundException;
import com.student.detail.model.Course;
import com.student.detail.service.CourseDBService;
import com.student.detail.service.CourseService;

class CourseDBTest {

	private static CourseService courseService;
	private static Map<Integer, Course> courses;

	@BeforeAll
	static void setup() {
		String filePath = "D:\\sample\\Coursedata.csv"; // Update this path as necessary
		courses = CsvFileLodderCourse.loadCourseData(filePath); // Ensure this method loads data correctly
		courseService = new CourseDBService();

		// Add courses to the service
		for (Course course : courses.values()) {
			try {
				courseService.addCourse(course); // Ensure addCourse method is implemented correctly
			} catch (Exception e) {
				System.err.println("Error adding course: " + e.getMessage());
			}
		}
	}

	@Test
	void testUpdateCourseDetails() {
		try {
			// Assuming courseId is Integer
			Course courseToUpdate = courseService.findCourseById(119); // Pass Integer, not String
			assertNotNull(courseToUpdate, "Course with ID 119 should exist");

			courseToUpdate.setCourseName("Advanced Java Programming");
			courseToUpdate.setCredits(15);

			Course updatedCourse = courseService.updateCourse(courseToUpdate);
			assertEquals("Advanced Java Programming", updatedCourse.getCourseName());
			assertEquals(15, updatedCourse.getCredits());

		} catch (CourseNotFoundException e) {
			fail("Exception occurred while updating course: " + e.getMessage());
		}
	}

	@Test
	void testDeleteCourse() {
		try {
			// Assuming courseId is Integer
			Course courseToDelete = courseService.findCourseById(1); // Pass Integer, not String
			assertNotNull(courseToDelete, "Course with ID 1 should exist before deletion");

			boolean isDeleted = courseService.deleteCourse(courseToDelete);
			assertTrue(isDeleted, "Course should be deleted successfully");

			// Verify deletion by attempting to find the course again
			assertThrows(CourseNotFoundException.class, () -> courseService.findCourseById(1),
					"Deleted course should not be found");

		} catch (CourseNotFoundException e) {
			fail("Exception occurred while deleting course: " + e.getMessage());
		}
	}

	@Test
	void testFindCourseById() {
		try {
			// Assuming courseId is Integer
			Course foundCourse = courseService.findCourseById(112); // Pass Integer, not String
			assertNotNull(foundCourse, "Course with ID 112 should be found");

		} catch (CourseNotFoundException e) {
			fail("Exception occurred while finding course by ID: " + e.getMessage());
		}
	}

	@Test
	void testFindCourseByName() {
		try {
			String name = "mpc";
			Course foundCourse = courseService.findCourseByName(name);
			assertNotNull(foundCourse, "Course with name 'mpc' should be found");
			assertEquals(name, foundCourse.getCourseName(), "Course name should match");

		} catch (CourseNotFoundException e) {
			fail("Exception occurred while finding course by name: " + e.getMessage());
		}
	}

	@Test
	void testFindCoursesByDuration() {
		List<Course> coursesByDuration = courseService.findCoursesByDuration(3);
		assertFalse(coursesByDuration.isEmpty(), "Courses with 3 months duration should be found");
	}

	@Test
	void testGetCountOfCourses() {
		int courseCount = courseService.getCountOfCourses();
		assertTrue(courseCount > 0, "Course count should be greater than zero");
	}

	@Test
	void testGetAllCourses() {
		List<Course> allCourses = courseService.getAllCourses();
		assertNotNull(allCourses, "All courses list should not be null");
		assertFalse(allCourses.isEmpty(), "All courses list should not be empty");
	}
}
