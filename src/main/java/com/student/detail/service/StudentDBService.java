package com.student.detail.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.student.detail.exception.StudentNotFoundException1;
import com.student.detail.model.Student;
import com.student.detail.util.DBUtil;

public class StudentDBService implements StudentService {

	private Student addSafeStudent(Student student) throws IllegalArgumentException, StudentNotFoundException1 {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String checkSql = "SELECT COUNT(*) FROM students WHERE rollnumber = ?";
		String insertSql = "INSERT INTO students (rollnumber, marks, branch, college, firstname, lastname, fathername, mobileno, dateofbirth, address, status, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String updateSql = "UPDATE students SET marks = ?, branch = ?, college = ?, firstname = ?, lastname = ?, fathername = ?, mobileno = ?, dateofbirth = ?, address = ?, status = ?, image = ? WHERE rollnumber = ?";

		try {
			con = DBUtil.getConnection(); // Updated connection string
			ps = con.prepareStatement(checkSql);

			ps.setInt(1, student.getRollnumber());
			rs = ps.executeQuery();
			if (rs.next() && rs.getInt(1) > 0) {
				ps = con.prepareStatement(updateSql);
				// Update existing student
				ps.setInt(1, student.getMarks());
				ps.setString(2, student.getBranch());
				ps.setString(3, student.getCollege());
				ps.setString(4, student.getFirstname());
				ps.setString(5, student.getLastname());
				ps.setString(6, student.getFathername());
				ps.setString(7, student.getMobileno());
				ps.setDate(8, Date.valueOf(student.getDateofbirth()));
				ps.setString(9, student.getAddress());
				ps.setBoolean(10, student.isStatus());
				ps.setBytes(11, student.getimage());
				ps.setInt(12, student.getRollnumber());

				int rowsAffected = ps.executeUpdate();
				if (rowsAffected > 0) {
					return student; // Successfully updated
				} else {
					throw new SQLException("Failed to update student.");
				}
			} else {
				ps = con.prepareStatement(insertSql);
				// Insert new student
				ps.setInt(1, student.getRollnumber());
				ps.setInt(2, student.getMarks());
				ps.setString(3, student.getBranch());
				ps.setString(4, student.getCollege());
				ps.setString(5, student.getFirstname());
				ps.setString(6, student.getLastname());
				ps.setString(7, student.getFathername());
				ps.setString(8, student.getMobileno());
				ps.setDate(9, Date.valueOf(student.getDateofbirth()));
				ps.setString(10, student.getAddress());
				ps.setBoolean(11, student.isStatus());
				ps.setBytes(12, student.getimage());

				int rowsAffected = ps.executeUpdate();
				if (rowsAffected > 0) {
					return student; // Successfully inserted
				} else {
					throw new SQLException("Failed to add student.");
				}
			}
		} catch (SQLException e) {
			System.err.println("Error adding or updating student: " + e.getMessage());
			return null;
		} finally {
			DBUtil.closeConnection(rs, ps, con);
		}
	}

	@Override
	public Student addStudent(Student student) {
		try {
			return addSafeStudent(student);
		} catch (IllegalArgumentException | StudentNotFoundException1 e) {
			throw new UnsupportedOperationException("Error adding student: " + e.getMessage(), e);
		}
	}

	@Override
	public Student updateStudent(Student student) throws StudentNotFoundException1 {
		try {
			return addSafeStudent(student);
		} catch (IllegalArgumentException e) {
			throw new UnsupportedOperationException("Error updating student: " + e.getMessage(), e);
		}
	}

	@Override
	public boolean deleteStudent(Student student) {
		Connection con = null;
		PreparedStatement ps = null;
		String sql = "DELETE FROM students WHERE rollnumber = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, student.getRollnumber());
			int rowsAffected = ps.executeUpdate();
			return rowsAffected > 0;
		} catch (SQLException e) {
			System.err.println("Error deleting student: " + e.getMessage());
			return false;
		} finally {
			DBUtil.closeConnection(null, ps, con);
		}
	}

	@Override
	public List<Student> getAllStudent() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM students";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student(rs.getInt("studentcode"), rs.getInt("rollnumber"), rs.getInt("marks"),
						rs.getString("branch"), rs.getString("college"), rs.getString("firstname"),
						rs.getString("lastname"), rs.getString("fathername"), rs.getString("mobileno"),
						rs.getDate("dateofbirth").toLocalDate(), rs.getString("address"), rs.getBoolean("status"),
						rs.getBytes("image"));

				students.add(student);
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving students: " + e.getMessage());
		} finally {
			DBUtil.closeConnection(rs, ps, con);
		}
		return students;
	}

	@Override
	public Student findStudentByName(String name) throws StudentNotFoundException1 {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM students WHERE firstname = ? OR lastname = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, name);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Student(rs.getInt("studentcode"), rs.getInt("rollnumber"), rs.getInt("marks"),
						rs.getString("branch"), rs.getString("college"), rs.getString("firstname"),
						rs.getString("lastname"), rs.getString("fathername"), rs.getString("mobileno"),
						rs.getDate("dateofbirth").toLocalDate(), rs.getString("address"), rs.getBoolean("status"),
						rs.getBytes("image"));
			} else {
				throw new StudentNotFoundException1("Student not found with name: " + name);
			}
		} catch (SQLException e) {
			System.err.println("Error finding student by name: " + e.getMessage());
			throw new StudentNotFoundException1("Error finding student by name: " + name);
		} finally {
			DBUtil.closeConnection(rs, ps, con);
		}
	}

	@Override
	public Student findStudentByRollnumber(int rollno) throws StudentNotFoundException1 {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM students WHERE rollnumber = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, rollno);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Student(rs.getInt("studentcode"), rs.getInt("rollnumber"), rs.getInt("marks"),
						rs.getString("branch"), rs.getString("college"), rs.getString("firstname"),
						rs.getString("lastname"), rs.getString("fathername"), rs.getString("mobileno"),
						rs.getDate("dateofbirth").toLocalDate(), rs.getString("address"), rs.getBoolean("status"),
						rs.getBytes("image"));
			} else {
				throw new StudentNotFoundException1("Student not found with rollnumber: " + rollno);
			}
		} catch (SQLException e) {
			System.err.println("Error finding student by rollnumber: " + e.getMessage());
			throw new StudentNotFoundException1("Error finding student by rollnumber: " + rollno);
		} finally {
			DBUtil.closeConnection(rs, ps, con);
		}
	}

	@Override
	public List<Student> findStudentsByCollege(String college) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM students WHERE college = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, college);
			rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student(rs.getInt("studentcode"), rs.getInt("rollnumber"), rs.getInt("marks"),
						rs.getString("branch"), rs.getString("college"), rs.getString("firstname"),
						rs.getString("lastname"), rs.getString("fathername"), rs.getString("mobileno"),
						rs.getDate("dateofbirth").toLocalDate(), rs.getString("address"), rs.getBoolean("status"),
						rs.getBytes("image"));

				students.add(student);
			}
		} catch (SQLException e) {
			System.err.println("Error finding students by college: " + e.getMessage());
		} finally {
			DBUtil.closeConnection(rs, ps, con);
		}
		return students;
	}

	@Override
	public Student findStudentByMobileNumber(String mobile) throws StudentNotFoundException1 {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM students WHERE mobileno = ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, mobile);
			rs = ps.executeQuery();
			if (rs.next()) {
				return new Student(rs.getInt("studentcode"), rs.getInt("rollnumber"), rs.getInt("marks"),
						rs.getString("branch"), rs.getString("college"), rs.getString("firstname"),
						rs.getString("lastname"), rs.getString("fathername"), rs.getString("mobileno"),
						rs.getDate("dateofbirth").toLocalDate(), rs.getString("address"), rs.getBoolean("status"),
						rs.getBytes("image")); // Fetch
				// status

			} else {
				throw new StudentNotFoundException1("Student not found with mobile number: " + mobile);

			}
		} catch (SQLException e) {
			System.err.println("Error finding student by mobile number: " + e.getMessage());
			throw new StudentNotFoundException1("Error finding student by mobile number: " + mobile);
		} finally {
			// Close resources
			DBUtil.closeConnection(rs, ps, con);
		}
	}

	@Override
	public List<Student> findStudentByDateofbirthRange(LocalDate startDate, LocalDate endDate) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM students WHERE dateofbirth BETWEEN ? AND ?";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			ps.setDate(1, Date.valueOf(startDate));
			ps.setDate(2, Date.valueOf(endDate));
			rs = ps.executeQuery();
			while (rs.next()) {
				Student student = new Student(rs.getInt("studentcode"), rs.getInt("rollnumber"), rs.getInt("marks"),
						rs.getString("branch"), rs.getString("college"), rs.getString("firstname"),
						rs.getString("lastname"), rs.getString("fathername"), rs.getString("mobileno"),
						rs.getDate("dateofbirth").toLocalDate(), rs.getString("address"), rs.getBoolean("status"),
						rs.getBytes("image")); // Fetch
				// status

				students.add(student);
			}
		} catch (SQLException e) {
			System.err.println("Error finding students by date of birth range: " + e.getMessage());
		} finally {
			// Close resources
			DBUtil.closeConnection(rs, ps, con);
		}
		return students;
	}

	@Override
	public int getCountofStudents() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM students";
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			System.err.println("Error counting students: " + e.getMessage());
		} finally {
			// Close resources
			DBUtil.closeConnection(rs, ps, con);
		}
		return 0;
	}

	@Override
	public int batchProcessStudents(List<Student> students) {
		Connection con = null;
		PreparedStatement ps = null;
		int[] updateCounts;
		int processedCount = 0;

		String insertSql = "INSERT INTO students (rollnumber, marks, branch, college, firstname, lastname, fathername, mobileno, dateofbirth, address, status, image) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
				+ "ON DUPLICATE KEY UPDATE marks = VALUES(marks), branch = VALUES(branch), college = VALUES(college), "
				+ "firstname = VALUES(firstname), lastname = VALUES(lastname), fathername = VALUES(fathername), "
				+ "mobileno = VALUES(mobileno), dateofbirth = VALUES(dateofbirth), address = VALUES(address), status = VALUES(status)";

		try {
			con = DBUtil.getConnection();
			con.setAutoCommit(false); // Disable auto-commit for batch processing
			ps = con.prepareStatement(insertSql);

			for (Student student : students) {
				ps.setInt(1, student.getRollnumber());
				ps.setInt(2, student.getMarks());
				ps.setString(3, student.getBranch());
				ps.setString(4, student.getCollege());
				ps.setString(5, student.getFirstname());
				ps.setString(6, student.getLastname());
				ps.setString(7, student.getFathername());
				ps.setString(8, student.getMobileno());
				ps.setDate(9, Date.valueOf(student.getDateofbirth()));
				ps.setString(10, student.getAddress());
				ps.setBoolean(11, student.isStatus());
				ps.setBytes(12, student.getimage());
				ps.addBatch();
			}

			updateCounts = ps.executeBatch();
			con.commit(); // Commit the transaction

			for (int count : updateCounts) {
				if (count != PreparedStatement.EXECUTE_FAILED) {
					processedCount++;
				}
			}

		} catch (SQLException e) {
			System.err.println("Error during batch processing: " + e.getMessage());
			try {
				if (con != null) {
					con.rollback(); // Rollback transaction in case of error
				}
			} catch (SQLException rollbackEx) {
				System.err.println("Error during rollback: " + rollbackEx.getMessage());
			}
		} finally {
			// Close resources
			DBUtil.closeConnection(null, ps, con);
		}

		return processedCount;
	}
}
