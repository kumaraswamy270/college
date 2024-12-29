package com.student.detail.service;

import java.util.List;

import com.student.detail.exception.CourseNotFoundException;
import com.student.detail.model.Course;

public interface CourseService {

	Course addCourse(Course course);

	Course updateCourse(Course course) throws CourseNotFoundException;

	boolean deleteCourse(Course course) throws CourseNotFoundException;

	List<Course> getAllCourses();

	Course findCourseBycourseName(String courseName) throws CourseNotFoundException;

	Course findCourseBycourseId(Long courseId) throws CourseNotFoundException;

	List<Course> findCoursesByDuration(int duration);

	int getCountOfCourses();

}
