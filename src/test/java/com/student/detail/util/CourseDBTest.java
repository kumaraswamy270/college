package com.student.detail.util;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.student.detail.exception.CourseNotFoundException;
import com.student.detail.model.Course;
import com.student.detail.service.CourseDBService;
import com.student.detail.service.CourseService;

class CourseDBTest {

	@Test
	void testCourseOperations() {
		// 1. Load data from CSV
		String filePath = "D:\\sample\\Coursedata.csv"; // Update this path as necessary
		Map<String, Course> courses = CsvFileLodderCourse.loadCourseData(filePath);

		// 2. Initialize the CourseService implementation
		CourseService courseService = new CourseDBService();

		// Add courses from the CSV to the service
		for (Course course : courses.values()) {
			try {
				courseService.addCourse(course);
			} catch (Exception e) {
				System.err.println("Error adding course: " + e.getMessage());
			}
		}

		// Update a course's details
		try {
			Course courseToUpdate = courseService.findCourseByCode("C119");
			courseToUpdate.setCourseName("Advanced Java Programming");
			courseToUpdate.setCredits(15);

			// Update course details
			Course updatedCourse = courseService.updateCourse(courseToUpdate);
			System.out.println("Updated course: " + updatedCourse);

		} catch (CourseNotFoundException e) {
			System.err.println("Error finding course to update: " + e.getMessage());
		}

		// Delete a course
		try {
			Course courseToDelete = courseService.findCourseByCode("C113");
			boolean isDeleted = courseService.deleteCourse(courseToDelete);
			System.out.println("Course deleted: " + isDeleted);

		} catch (CourseNotFoundException e) {
			System.err.println("Error deleting course: " + e.getMessage());
		}

		// Find a course by code
		try {
			Course foundCourseByCode = courseService.findCourseByCode("C112");
			System.out.println("Found course by code: " + foundCourseByCode);

		} catch (CourseNotFoundException e) {
			System.err.println("Error finding course by code: " + e.getMessage());
		}

		// Find a course by name
		try {
			Course foundCourseByName = courseService.findCourseByName("mpc");
			System.out.println("Found course by name: " + foundCourseByName);

		} catch (CourseNotFoundException e) {
			System.err.println("Error finding course by name: " + e.getMessage());
		}

		// Find courses by duration
		List<Course> coursesByDuration = courseService.findCoursesByDuration(3);
		System.out.println("Courses with 3 months duration: " + coursesByDuration);

		// Get the count of all courses
		int courseCount = courseService.getCountOfCourses();
		System.out.println("Total number of courses: " + courseCount);

		// Fetch and print all courses
		List<Course> allCourses = courseService.getAllCourses();
		System.out.println("All courses: " + allCourses);
	}
}
