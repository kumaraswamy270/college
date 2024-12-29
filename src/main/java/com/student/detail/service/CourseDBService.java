package com.student.detail.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.student.detail.exception.CourseNotFoundException;
import com.student.detail.model.Course;
import com.student.detail.repository.CourseRepository;
import com.student.detail.service.CourseService;

@Service
public class CourseDBService implements CourseService {

	private static final Logger logger = LoggerFactory.getLogger(CourseDBService.class);

	@Autowired
	private CourseRepository courseRepository;

	// Method to add a course
	@Override
	public Course addCourse(Course course) {
		try {
			Course savedCourse = courseRepository.save(course);
			logger.info("Course {} added successfully.", course.getCourseName());
			return savedCourse;
		} catch (Exception e) {
			logger.error("Error adding course: {}", e.getMessage());
			throw e;
		}
	}

	// Method to update a course
	@Override
	public Course updateCourse(Course course) throws CourseNotFoundException {
		if (!courseRepository.existsById(course.getCourseId())) {
			logger.error("Course not found with ID: {}", course.getCourseId());
			throw new CourseNotFoundException("Course not found with ID: " + course.getCourseId());
		}

		try {
			// Save the updated course
			Course updatedCourse = courseRepository.save(course);
			logger.info("Course {} updated successfully.", course.getCourseName());
			return updatedCourse;
		} catch (DataAccessException dae) {
			// Catching specific exception for database issues
			logger.error("Database error while updating course: {}", dae.getMessage(), dae);
			throw new RuntimeException("Error updating course due to database issue", dae); // Optional: Throwing a more
																							// specific exception
		} catch (Exception e) {
			// Catching other unexpected exceptions
			logger.error("Unexpected error updating course: {}", e.getMessage(), e);
			throw e;
		}
	}

	@Override
	public boolean deleteCourse(Course course) throws CourseNotFoundException {
		// Find the course by ID
		Course existingCourse = courseRepository.findById(course.getCourseId())
				.orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + course.getCourseId()));

		try {
			// Delete the course
			courseRepository.delete(existingCourse);
			logger.info("Course with ID {} deleted successfully.", course.getCourseId());
			return true; // Return true if deletion was successful
		} catch (Exception e) {
			// Log the error and rethrow the exception
			logger.error("Error deleting course with ID {}: {}", course.getCourseId(), e.getMessage());
			throw new RuntimeException("Failed to delete course with ID " + course.getCourseId(), e); // Rethrow with a
																										// more specific
																										// exception
		}
	}

	// Method to find a course by ID
	@Override
	public Course findCourseBycourseId(Long id) throws CourseNotFoundException {
		return courseRepository.findById(id)
				.orElseThrow(() -> new CourseNotFoundException("Course not found with ID: " + id));
	}

	// Method to find a course by name
	@Override
	public Course findCourseBycourseName(String courseName) throws CourseNotFoundException {
		try {
			Course course = courseRepository.findCourseBycourseName(courseName); // Assuming you have a custom query
																					// here
			if (course == null) {
				logger.error("Course not found with name: {}", courseName);
				throw new CourseNotFoundException("Course not found with name: " + courseName);
			}
			return course;
		} catch (Exception e) {
			logger.error("Error finding course by name: {}", e.getMessage());
			throw e;
		}
	}

	// Method to get all courses
	@Override
	public List<Course> getAllCourses() {
		try {
			List<Course> courses = courseRepository.findAll();
			if (courses.isEmpty()) {
				logger.info("No courses found.");
			} else {
				logger.info("Found {} courses.", courses.size());
			}
			return courses;
		} catch (Exception e) {
			logger.error("Error fetching courses: {}", e.getMessage());
			throw e;
		}
	}

	// Method to find courses by duration
	@Override
	public List<Course> findCoursesByDuration(int duration) {
		try {
			List<Course> courses = courseRepository.findCoursesByDuration(duration); // Assuming you have a custom query
																						// here
			return courses;
		} catch (Exception e) {
			logger.error("Error fetching courses by duration: {}", e.getMessage());
			throw e;
		}
	}

	// Method to get the count of courses
	@Override
	public int getCountOfCourses() {
		try {
			return (int) courseRepository.count();
		} catch (Exception e) {
			logger.error("Error getting count of courses: {}", e.getMessage());
			throw e;
		}
	}
}
