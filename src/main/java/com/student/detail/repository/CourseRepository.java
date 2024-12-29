package com.student.detail.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.student.detail.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

	Course findCourseBycourseName(String courseName);

	List<Course> findCoursesByDuration(int duration);

	Optional<Course> findBycourseId(Long courseId);
}
