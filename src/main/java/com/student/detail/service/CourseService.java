package com.student.detail.service;

import java.util.List;

import com.student.detail.exception.CourseNotFoundException;
import com.student.detail.model.Course;

public interface CourseService {

	Course addCourse(Course course);

	Course updateCourse(Course course) throws CourseNotFoundException;

	boolean deleteCourse(Course course);

	List<Course> getAllCourses();

	Course findCourseByName(String name) throws CourseNotFoundException;

	Course findCourseById(int courseId) throws CourseNotFoundException;

	List<Course> findCoursesByDuration(int duration);

	int getCountOfCourses();
}
