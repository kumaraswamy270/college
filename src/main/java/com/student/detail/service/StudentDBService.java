package com.student.detail.service;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.student.detail.exception.StudentNotFoundException1;
import com.student.detail.model.Student;
import com.student.detail.repository.StudentRepository;

@Service
public class StudentDBService implements StudentService {

	private static final Logger logger = LoggerFactory.getLogger(StudentDBService.class);

	@Autowired
	private StudentRepository studentRepository;

	// Method to add or update a student
	@Override
	public Student addStudent(Student student) {
		try {
			Student savedStudent = studentRepository.save(student);
			logger.info("Student with roll number {} added/updated successfully.", student.getRollnumber());
			return savedStudent;
		} catch (Exception e) {
			logger.error("Error adding/updating student: {}", e.getMessage());
			throw e;
		}
	}

	@Override
	public Student updateStudent(Student student) throws StudentNotFoundException1 {
		if (!studentRepository.existsById(student.getStudentcode())) {
			logger.error("Student not found with ID: {}", student.getStudentcode());
			throw new StudentNotFoundException1("Student not found with ID: " + student.getStudentcode());
		}
		try {
			Student updatedStudent = studentRepository.save(student);
			logger.info("Student with roll number {} updated successfully.", student.getRollnumber());
			return updatedStudent;
		} catch (Exception e) {
			logger.error("Error updating student: {}", e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean deleteStudent(Student student) {
		if (!studentRepository.existsById(student.getStudentcode())) {
			logger.warn("No student found with ID: {}", student.getStudentcode());
			return false;
		}
		try {
			studentRepository.deleteById(student.getStudentcode());
			logger.info("Student with ID {} deleted successfully.", student.getStudentcode());
			return true;
		} catch (Exception e) {
			logger.error("Error deleting student: {}", e.getMessage());
			return false;
		}
	}

	@Override
	public List<Student> getAllStudent() {
		try {
			List<Student> students = studentRepository.findAll();
			if (students.isEmpty()) {
				logger.info("No students found in the database.");
			} else {
				logger.info("Retrieved {} students from the database.", students.size());

				// Convert image byte[] to Base64 for each student
				for (Student student : students) {
					if (student.getImage() != null && student.getImage().length > 0) {
						String base64Image = Base64.getEncoder().encodeToString(student.getImage());
						student.setBase64Image(base64Image); // Temporarily set it in a transient field
					}
				}
			}
			return students;
		} catch (Exception e) {
			logger.error("Error fetching students: {}", e.getMessage());
			throw e;
		}
	}

	@Override
	public Student findStudentByRollnumber(int rollno) throws StudentNotFoundException1 {
		try {
			Optional<Student> student = studentRepository.findByRollnumber(rollno);
			if (student.isEmpty()) {
				logger.error("Student not found with roll number: {}", rollno);
				throw new StudentNotFoundException1("Student not found with roll number: " + rollno);
			}
			return student.get();
		} catch (Exception e) {
			logger.error("Error finding student by roll number: {}", e.getMessage());
			throw e;
		}
	}

	@Override
	public Student findStudentByName(String name) throws StudentNotFoundException1 {
		try {
			Optional<Student> student = studentRepository.findByFirstnameOrLastname(name, name);
			if (student.isEmpty()) {
				logger.error("Student not found with name: {}", name);
				throw new StudentNotFoundException1("Student not found with name: " + name);
			}
			return student.get();
		} catch (Exception e) {
			logger.error("Error finding student by name: {}", e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Student> findStudentsByCollege(String college) {
		try {
			List<Student> students = studentRepository.findByCollege(college);
			logger.info("Retrieved {} students from college: {}", students.size(), college);
			return students;
		} catch (Exception e) {
			logger.error("Error finding students by college: {}", e.getMessage());
			throw e;
		}
	}

	@Override
	public Student findStudentByMobileNumber(String mobile) throws StudentNotFoundException1 {
		try {
			Optional<Student> student = studentRepository.findByMobileno(mobile);
			if (student.isEmpty()) {
				logger.error("Student not found with mobile number: {}", mobile);
				throw new StudentNotFoundException1("Student not found with mobile number: " + mobile);
			}
			return student.get();
		} catch (Exception e) {
			logger.error("Error finding student by mobile number: {}", e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Student> findStudentByDateofbirthRange(LocalDate startDate, LocalDate endDate) {
		try {
			List<Student> students = studentRepository.findByDateofbirthBetween(startDate, endDate);
			logger.info("Retrieved {} students born between {} and {}.", students.size(), startDate, endDate);
			return students;
		} catch (Exception e) {
			logger.error("Error finding students by date of birth range: {}", e.getMessage());
			throw e;
		}
	}

	@Override
	public List<Student> getPaginatedStudents(int page, int rowsPerPage) {
		try {
			// Ensure the page number is non-negative, and rowsPerPage is greater than 0
			if (page <= 0) {
				page = 1; // Default to the first page if the page is less than or equal to 0
			}
			if (rowsPerPage <= 0) {
				rowsPerPage = 10; // Set a default value for rowsPerPage if invalid
			}
			// Page number starts from 0 in PageRequest, so subtract 1 from page to handle
			// 1-based input
			PageRequest pageable = PageRequest.of(page - 1, rowsPerPage);

			// Fetch the page of students
			Page<Student> studentPage = studentRepository.findAll(pageable);

			// Log the page and number of students fetched
			logger.info("Requested page {} (0-based offset: {}) with {} records per page.", page, page - 1,
					rowsPerPage);
			logger.info("Total pages: {}, Total students: {}, Number of students on current page: {}.",
					studentPage.getTotalPages(), studentPage.getTotalElements(), studentPage.getNumberOfElements());

			return studentPage.getContent(); // Returns the list of students in the current page
		} catch (Exception e) {
			logger.error("Error fetching paginated students: {}", e.getMessage(), e);
			throw new RuntimeException("Error fetching paginated students", e); // Throw a custom exception if necessary
		}
	}

	@Override
	public int getTotalStudents() {
		try {
			long totalStudents = studentRepository.count();
			logger.info("Total number of students: {}", totalStudents);
			return (int) totalStudents; // Return the count as an integer
		} catch (Exception e) {
			logger.error("Error getting total students: {}", e.getMessage());
			throw e;
		}
	}
}
