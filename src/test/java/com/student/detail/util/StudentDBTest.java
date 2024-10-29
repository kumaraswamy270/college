package com.student.detail.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.student.detail.exception.StudentNotFoundException1;
import com.student.detail.model.Student;
import com.student.detail.service.StudentDBService;
import com.student.detail.service.StudentService;

class StudentDBTest {

	private static StudentService studentService;
	private static List<Student> students;

	@BeforeAll
	static void setup() {
		String filePath = "D:\\sample\\Studentdata.csv"; // Update this path as necessary
		students = CsvFileLoader.loadStudentData(filePath);
		studentService = new StudentDBService();
		for (Student student : students) {
			try {
				studentService.addStudent(student);
			} catch (Exception e) {
				System.err.println("Error adding student: " + e.getMessage());
			}
		}
	}

	@Test
	void testUpdateStudentDetails() {
		try {
			Student studentToUpdate = studentService.findStudentByRollnumber(92);
			assertNotNull(studentToUpdate, "Student with roll number 92 should exist");

			studentToUpdate.setFirstname("Kumar");
			studentToUpdate.setLastname("Damisetti");
			studentToUpdate.setMarks(90);

			Student updatedStudent = studentService.updateStudent(studentToUpdate);
			assertEquals("Kumar", updatedStudent.getFirstname());
			assertEquals("Damisetti", updatedStudent.getLastname());
			assertEquals(90, updatedStudent.getMarks());

			byte[] imageBytes = updatedStudent.getimage();
			if (imageBytes != null) {
				try (FileOutputStream fos = new FileOutputStream("D:\\sample\\Universe.jpg")) {
					fos.write(imageBytes);
				}
				System.out.println("Image saved as Universe.jpg");
			} else {
				System.out.println("No image found for this student.");
			}

		} catch (StudentNotFoundException1 | IOException e) {
			fail("Exception occurred: " + e.getMessage());
		}
	}

	@Test
	void testDeleteStudent() {
		try {
			Student studentToDelete = studentService.findStudentByRollnumber(81);
			assertNotNull(studentToDelete, "Student with roll number 91 should exist before deletion");

			boolean isDeleted = studentService.deleteStudent(studentToDelete);
			assertTrue(isDeleted, "Student should be deleted successfully");

			// Verify deletion by attempting to find the student again
			assertThrows(StudentNotFoundException1.class, () -> studentService.findStudentByRollnumber(91),
					"Deleted student should not be found");

		} catch (StudentNotFoundException1 e) {
			fail("Exception occurred while deleting student: " + e.getMessage());
		}
	}

	@Test
	void testFindStudentByRollnumber() {
		try {
			Student foundStudent = studentService.findStudentByRollnumber(94);
			assertNotNull(foundStudent, "Student with roll number 91 should be found");

		} catch (StudentNotFoundException1 e) {
			fail("Exception occurred while finding student by roll number: " + e.getMessage());
		}
	}

	@Test
	void testFindStudentByName() {
		try {
			String name = "Rhyloo";
			Student foundStudent = studentService.findStudentByName(name);
			assertNotNull(foundStudent, "Student with name 'xxxx' should be found");
			assertEquals(name, foundStudent.getFirstname(), "Student's first name should match");

		} catch (StudentNotFoundException1 e) {
			fail("Exception occurred while finding student by name: " + e.getMessage());
		}
	}

	@Test
	void testFindStudentsByCollege() {
		List<Student> studentsByCollege = studentService.findStudentsByCollege("B.v raju");
		assertFalse(studentsByCollege.isEmpty(), "Students should be found by college");
	}

	@Test
	void testFindStudentByDateofbirthRange() {
		List<Student> studentsByDOBRange = studentService.findStudentByDateofbirthRange(LocalDate.of(2000, 6, 30),
				LocalDate.of(2003, 12, 21));
		assertFalse(studentsByDOBRange.isEmpty(), "Students should be found within the date of birth range");
	}

	@Test
	void testGetCountOfStudents() {
		int studentCount = studentService.getCountofStudents();
		assertTrue(studentCount > 0, "Student count should be greater than zero");
	}

	@Test
	void testGetAllStudents() {
		List<Student> allStudents = studentService.getAllStudent();
		assertNotNull(allStudents, "All students list should not be null");
		assertFalse(allStudents.isEmpty(), "All students list should not be empty");
	}

	@Test
	void testFindStudentByMobileNumber() {
		try {
			String mobileNumber = "9704005249";
			Student foundStudent = studentService.findStudentByMobileNumber(mobileNumber);
			assertNotNull(foundStudent, "Student with mobile number '1234567890' should be found");
			assertEquals(mobileNumber, foundStudent.getMobileno(), "Student's mobile number should match");

		} catch (StudentNotFoundException1 e) {
			fail("Exception occurred while finding student by mobile number: " + e.getMessage());
		}
	}
}
