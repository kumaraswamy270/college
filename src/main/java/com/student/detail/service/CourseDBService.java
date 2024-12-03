package com.student.detail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.student.detail.exception.CourseNotFoundException;
import com.student.detail.model.Course;

import java.util.List;

@Service
public class CourseDBService implements CourseService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final RowMapper<Course> courseRowMapper = (rs, rowNum) -> new Course(rs.getInt("courseId"),
			rs.getString("courseName"), rs.getInt("credits"), rs.getString("department"), rs.getInt("duration"),
			rs.getString("feeStructure"), rs.getInt("lengthOfStudents"), rs.getDate("startDate").toLocalDate(),
			rs.getDate("endDate").toLocalDate());

	@Override
	public Course addCourse(Course course) {
		String checkSql = "SELECT COUNT(*) FROM courses WHERE courseId = ?";
		String insertSql = "INSERT INTO courses (courseId, courseName, credits, department, duration, feeStructure, lengthOfStudents, startDate, endDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String updateSql = "UPDATE courses SET courseName = ?, credits = ?, department = ?, duration = ?, feeStructure = ?, lengthOfStudents = ?, startDate = ?, endDate = ? WHERE courseId = ?";

		// Check if course already exists
		int count = jdbcTemplate.queryForObject(checkSql, Integer.class, course.getCourseId());

		if (count > 0) {
			// Update existing course
			jdbcTemplate.update(updateSql, course.getCourseName(), course.getCredits(), course.getDepartment(),
					course.getDuration(), course.getFeeStructure(), course.getLengthOfStudents(),
					java.sql.Date.valueOf(course.getStartDate()), java.sql.Date.valueOf(course.getEndDate()),
					course.getCourseId());
			return course; // Successfully updated
		} else {
			// Insert new course
			jdbcTemplate.update(insertSql, course.getCourseId(), course.getCourseName(), course.getCredits(),
					course.getDepartment(), course.getDuration(), course.getFeeStructure(),
					course.getLengthOfStudents(), java.sql.Date.valueOf(course.getStartDate()),
					java.sql.Date.valueOf(course.getEndDate()));
			return course; // Successfully inserted
		}
	}

	@Override
	public Course updateCourse(Course course) throws CourseNotFoundException {
		return addCourse(course); // Uses the same method to add or update course
	}

	@Override
	public boolean deleteCourse(Course course) {
		String sql = "DELETE FROM courses WHERE courseId = ?";
		int rowsAffected = jdbcTemplate.update(sql, course.getCourseId());
		return rowsAffected > 0;
	}

	@Override
	public List<Course> getAllCourses() {
		String sql = "SELECT * FROM courses";
		return jdbcTemplate.query(sql, courseRowMapper);
	}

	@Override
	public Course findCourseByName(String name) throws CourseNotFoundException {
		String sql = "SELECT * FROM courses WHERE courseName = ?";
		try {
			return jdbcTemplate.queryForObject(sql, courseRowMapper, name);
		} catch (EmptyResultDataAccessException e) {
			throw new CourseNotFoundException("Course not found with name: " + name);
		}
	}

	@Override
	public Course findCourseById(int id) throws CourseNotFoundException {
		String sql = "SELECT * FROM courses WHERE courseId = ?";
		try {
			return jdbcTemplate.queryForObject(sql, courseRowMapper, id);
		} catch (EmptyResultDataAccessException e) {
			throw new CourseNotFoundException("Course not found with ID: " + id);
		}
	}

	@Override
	public List<Course> findCoursesByDuration(int duration) {
		String sql = "SELECT * FROM courses WHERE duration = ?";
		return jdbcTemplate.query(sql, courseRowMapper, duration);
	}

	@Override
	public int getCountOfCourses() {
		String sql = "SELECT COUNT(*) FROM courses";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
