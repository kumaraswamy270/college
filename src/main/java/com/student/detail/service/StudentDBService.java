package com.student.detail.service;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import com.student.detail.exception.StudentNotFoundException1;
import com.student.detail.model.Student;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

@Service
public class StudentDBService implements StudentService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Student> studentRowMapper = (rs, rowNum) -> new Student(rs.getInt("studentcode"),
			rs.getInt("rollnumber"), rs.getInt("marks"), rs.getString("branch"), rs.getString("college"),
			rs.getString("firstname"), rs.getString("lastname"), rs.getString("fathername"), rs.getString("mobileno"),
			rs.getDate("dateofbirth").toLocalDate(), rs.getString("address"), rs.getBoolean("status"),
			rs.getBytes("image"));

	@Override
	public Student addStudent(Student student) {
		String checkSql = "SELECT COUNT(*) FROM students WHERE rollnumber = ?";
		String insertSql = "INSERT INTO students (rollnumber, marks, branch, college, firstname, lastname, fathername, mobileno, dateofbirth, address, status, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String updateSql = "UPDATE students SET marks = ?, branch = ?, college = ?, firstname = ?, lastname = ?, fathername = ?, mobileno = ?, dateofbirth = ?, address = ?, status = ?, image = ? WHERE rollnumber = ?";

		int count = jdbcTemplate.queryForObject(checkSql, Integer.class, student.getRollnumber());

		if (count > 0) {
			// Update existing student
			jdbcTemplate.update(updateSql, student.getMarks(), student.getBranch(), student.getCollege(),
					student.getFirstname(), student.getLastname(), student.getFathername(), student.getMobileno(),
					Date.valueOf(student.getDateofbirth()), student.getAddress(), student.isStatus(),
					student.getimage(), student.getRollnumber());
			return student; // Successfully updated
		} else {
			// Insert new student
			jdbcTemplate.update(insertSql, student.getRollnumber(), student.getMarks(), student.getBranch(),
					student.getCollege(), student.getFirstname(), student.getLastname(), student.getFathername(),
					student.getMobileno(), Date.valueOf(student.getDateofbirth()), student.getAddress(),
					student.isStatus(), student.getimage());
			return student; // Successfully inserted
		}
	}

	@Override
	public Student updateStudent(Student student) throws StudentNotFoundException1 {
		return addStudent(student); // Uses the same method to add or update student
	}

	@Override
	public boolean deleteStudent(Student student) {
		String sql = "DELETE FROM students WHERE rollnumber = ?";
		int rowsAffected = jdbcTemplate.update(sql, student.getRollnumber());
		return rowsAffected > 0;
	}

	@Override
	public List<Student> getAllStudent() {
		String sql = "SELECT * FROM students";
		return jdbcTemplate.query(sql, studentRowMapper);
	}

	@Override
	public Student findStudentByName(String name) throws StudentNotFoundException1 {
		String sql = "SELECT * FROM students WHERE firstname = ? OR lastname = ?";
		try {
			return jdbcTemplate.queryForObject(sql, studentRowMapper, name, name);
		} catch (EmptyResultDataAccessException e) {
			throw new StudentNotFoundException1("Student not found with name: " + name);
		}
	}

	@Override
	public Student findStudentByRollnumber(int rollno) throws StudentNotFoundException1 {
		String sql = "SELECT * FROM students WHERE rollnumber = ?";
		try {
			return jdbcTemplate.queryForObject(sql, studentRowMapper, rollno);
		} catch (EmptyResultDataAccessException e) {
			throw new StudentNotFoundException1("Student not found with rollnumber: " + rollno);
		}
	}

	@Override
	public List<Student> findStudentsByCollege(String college) {
		String sql = "SELECT * FROM students WHERE college = ?";
		return jdbcTemplate.query(sql, studentRowMapper, college);
	}

	@Override
	public Student findStudentByMobileNumber(String mobile) throws StudentNotFoundException1 {
		String sql = "SELECT * FROM students WHERE mobileno = ?";
		try {
			return jdbcTemplate.queryForObject(sql, studentRowMapper, mobile);
		} catch (EmptyResultDataAccessException e) {
			throw new StudentNotFoundException1("Student not found with mobile number: " + mobile);
		}
	}

	@Override
	public List<Student> findStudentByDateofbirthRange(LocalDate startDate, LocalDate endDate) {
		String sql = "SELECT * FROM students WHERE dateofbirth BETWEEN ? AND ?";
		return jdbcTemplate.query(sql, studentRowMapper, Date.valueOf(startDate), Date.valueOf(endDate));
	}

	@Override
	public int getCountofStudents() {
		String sql = "SELECT COUNT(*) FROM students";
		return jdbcTemplate.queryForObject(sql, Integer.class);
	}
}
