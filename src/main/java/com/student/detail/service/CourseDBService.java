package com.student.detail.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.student.detail.exception.CourseNotFoundException;
import com.student.detail.model.Course;
import com.student.detail.util.DBUtil;

public class CourseDBService implements CourseService {

	public Course addCourse(Course course) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "INSERT INTO courses (courseId, courseName, credits, department, duration, feeStructure, lengthOfStudents, startDate, endDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, course.getCourseId());
			ps.setString(2, course.getCourseName());
			ps.setInt(3, course.getCredits());
			ps.setString(4, course.getDepartment());
			ps.setInt(5, course.getDuration());
			ps.setString(6, course.getFeeStructure());
			ps.setInt(7, course.getLengthOfStudents());
			ps.setDate(8, java.sql.Date.valueOf(course.getStartDate()));
			ps.setDate(9, java.sql.Date.valueOf(course.getEndDate()));

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				return course;
			} else {
				throw new SQLException("Failed to add course");
			}

		} catch (SQLException e) {
			System.err.println("Error adding course: " + e.getMessage());
			return null;
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
			}
		}
	}

	public Course updateCourse(Course course) throws CourseNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "UPDATE courses SET courseName = ?, credits = ?, department = ?, duration = ?, feeStructure = ?, lengthOfStudents = ?, startDate = ?, endDate = ? WHERE courseId = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, course.getCourseName());
			ps.setInt(2, course.getCredits());
			ps.setString(3, course.getDepartment());
			ps.setInt(4, course.getDuration());
			ps.setString(5, course.getFeeStructure());
			ps.setInt(6, course.getLengthOfStudents());
			ps.setDate(7, java.sql.Date.valueOf(course.getStartDate()));
			ps.setDate(8, java.sql.Date.valueOf(course.getEndDate()));
			ps.setString(9, course.getCourseId());

			int rowsAffected = ps.executeUpdate();
			if (rowsAffected > 0) {
				return course;
			} else {
				throw new CourseNotFoundException("Course with ID " + course.getCourseId() + " not found");
			}

		} catch (SQLException e) {
			System.err.println("Error updating course: " + e.getMessage());
			return null;
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
			}
		}
	}

	public boolean deleteCourse(Course course) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM courses WHERE courseId = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);

			ps.setString(1, course.getCourseId());

			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Error deleting course: " + e.getMessage());
			return false;
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
			}
		}
	}

	public List<Course> getAllCourses() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Course> courses = new ArrayList<>();
		String sql = "SELECT * FROM courses";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				Course course = new Course(rs.getString("courseId"), rs.getString("courseName"), rs.getInt("credits"),
						rs.getString("department"), rs.getInt("duration"), rs.getString("feeStructure"),
						rs.getInt("lengthOfStudents"), rs.getDate("startDate").toLocalDate(),
						rs.getDate("endDate").toLocalDate());
				courses.add(course);
			}
			return courses;

		} catch (SQLException e) {
			System.err.println("Error retrieving courses: " + e.getMessage());
			return courses;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
			}
		}
	}

	@Override
	public Course findCourseByName(String name) throws CourseNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM courses WHERE courseName = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Course(rs.getString("courseId"), rs.getString("courseName"), rs.getInt("credits"),
						rs.getString("department"), rs.getInt("duration"), rs.getString("feeStructure"),
						rs.getInt("lengthOfStudents"), rs.getDate("startDate").toLocalDate(),
						rs.getDate("endDate").toLocalDate());
			} else {
				throw new CourseNotFoundException("Course with name " + name + " not found");
			}

		} catch (SQLException e) {
			System.err.println("Error finding course by name: " + e.getMessage());
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
			}
		}
	}

	@Override
	public Course findCourseByCode(String code) throws CourseNotFoundException {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM courses WHERE courseId = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Course(rs.getString("courseId"), rs.getString("courseName"), rs.getInt("credits"),
						rs.getString("department"), rs.getInt("duration"), rs.getString("feeStructure"),
						rs.getInt("lengthOfStudents"), rs.getDate("startDate").toLocalDate(),
						rs.getDate("endDate").toLocalDate());
			} else {
				throw new CourseNotFoundException("Course with ID " + code + " not found");
			}

		} catch (SQLException e) {
			System.err.println("Error finding course by code: " + e.getMessage());
			return null;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
			}
		}
	}

	@Override
	public List<Course> findCoursesByDuration(int duration) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Course> courses = new ArrayList<>();
		String sql = "SELECT * FROM courses WHERE duration = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, duration);
			rs = ps.executeQuery();
			while (rs.next()) {
				Course course = new Course(rs.getString("courseId"), rs.getString("courseName"), rs.getInt("credits"),
						rs.getString("department"), rs.getInt("duration"), rs.getString("feeStructure"),
						rs.getInt("lengthOfStudents"), rs.getDate("startDate").toLocalDate(),
						rs.getDate("endDate").toLocalDate());
				courses.add(course);
			}
		} catch (SQLException e) {
			System.err.println("Error finding courses by duration: " + e.getMessage());
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
			}
		}
		return courses;
	}

	@Override
	public int getCountOfCourses() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM courses";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				throw new SQLException("Failed to count courses");
			}

		} catch (SQLException e) {
			System.err.println("Error counting courses: " + e.getMessage());
			return 0;
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				System.err.println("Error closing resources: " + e.getMessage());
			}
		}
	}
}
