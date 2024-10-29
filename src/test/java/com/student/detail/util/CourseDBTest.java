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
	private static Map<String, Course> courses;

	@BeforeAll
	static void setup() {
		String filePath = "D:\\sample\\Coursedata.csv"; // Update this path as necessary
		courses = CsvFileLodderCourse.loadCourseData(filePath);
		courseService = new CourseDBService();

		for (Course course : courses.values()) {
			try {
				courseService.addCourse(course);
			} catch (Exception e) {
				System.err.println("Error adding course: " + e.getMessage());
			}
		}
	}

	@Test
	void testUpdateCourseDetails() {
		try {
			Course courseToUpdate = courseService.findCourseByCode("C119");
			assertNotNull(courseToUpdate, "Course with code C119 should exist");

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
			Course courseToDelete = courseService.findCourseByCode("C113");
			assertNotNull(courseToDelete, "Course with code C113 should exist before deletion");

			boolean isDeleted = courseService.deleteCourse(courseToDelete);
			assertTrue(isDeleted, "Course should be deleted successfully");

			// Verify deletion by attempting to find the course again
			assertThrows(CourseNotFoundException.class, () -> courseService.findCourseByCode("C113"),
					"Deleted course should not be found");

		} catch (CourseNotFoundException e) {
			fail("Exception occurred while deleting course: " + e.getMessage());
		}
	}

	@Test
	void testFindCourseByCode() {
		try {
			Course foundCourse = courseService.findCourseByCode("C112");
			assertNotNull(foundCourse, "Course with code C112 should be found");

		} catch (CourseNotFoundException e) {
			fail("Exception occurred while finding course by code: " + e.getMessage());
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
