package com.student.detail.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.student.detail.exception.StudentNotFoundException1;
import com.student.detail.model.Student;
import com.student.detail.service.StudentDBService;
import com.student.detail.service.StudentService;

class StudentDBTest {

	@Test
	void testStudentOperations() {
		// 1. Load data from CSV
		String filePath = "D:\\sample\\Studentdata.csv"; // Update this path as necessary
		List<Student> students = CsvFileLoader.loadStudentData(filePath);

		// 2. Initialize the StudentService implementation
		StudentService studentService = new StudentDBService();

		// Add students from the CSV to the service
		for (Student student : students) {
			try {
				studentService.addStudent(student);
			} catch (Exception e) {
				System.err.println("Error adding student: " + e.getMessage());
			}
		}

		// Update a student's details and retrieve the image
		try {
			Student studentToUpdate = studentService.findStudentByRollnumber(92);
			studentToUpdate.setFirstname("Kumar");
			studentToUpdate.setLastname("Damisetti");
			studentToUpdate.setMarks(90);

			// Update student details
			Student updatedStudent = studentService.updateStudent(studentToUpdate);
			System.out.println("Updated student: " + updatedStudent);

			// Retrieve the image associated with the student
			byte[] imageBytes = studentToUpdate.getimage(); // Use getImage method
			if (imageBytes != null) {
				// Convert byte[] to an image file
				try (FileOutputStream fos = new FileOutputStream("D:\\sample\\Universe.jpg")) { // Changed file name
					fos.write(imageBytes);
				}
				System.out.println("Image retrieved and saved as Universe.jpg");
			} else {
				System.out.println("No image found for this student.");
			}

		} catch (StudentNotFoundException1 | IOException e) {
			System.err.println("Error: " + e.getMessage());
		}

		// Delete a student
		try {
			Student studentToDelete = studentService.findStudentByRollnumber(91);
			boolean isDeleted = studentService.deleteStudent(studentToDelete);
			System.out.println("Student deleted: " + isDeleted);

		} catch (StudentNotFoundException1 e) {
			System.err.println("Error deleting student: " + e.getMessage());
		}

		// Find a student by roll number
		try {
			Student findStudentByRoll = studentService.findStudentByRollnumber(91);
			System.out.println("Found student by roll number: " + findStudentByRoll);

		} catch (StudentNotFoundException1 e) {
			System.err.println("Error finding student by roll number: " + e.getMessage());
		}

		// Find a student by name
		try {
			Student findStudentByName = studentService.findStudentByName("xxxx");
			System.out.println("Found student by name: " + findStudentByName);

		} catch (StudentNotFoundException1 e) {
			System.err.println("Error finding student by name: " + e.getMessage());
		}

		// Find students by college name
		List<Student> studentsByCollege = studentService.findStudentsByCollege("B.v raju");
		System.out.println("Students from B.v raju College: " + studentsByCollege);

		// Find students by date of birth range
		List<Student> studentsByDOBRange = studentService.findStudentByDateofbirthRange(LocalDate.of(2000, 6, 30),
				LocalDate.of(2003, 12, 21));
		System.out.println("Students born between 2000 and 2003: " + studentsByDOBRange);

		// Get the count of all students
		int studentCount = studentService.getCountofStudents();
		System.out.println("Total number of students: " + studentCount);

		// Fetch and print all students
		List<Student> allStudents = studentService.getAllStudent();
		System.out.println("All students: " + allStudents);

		// Find a student by mobile number
		try {
			Student foundStudentByMobile = studentService.findStudentByMobileNumber("1234567890");
			System.out.println("Found student by mobile number: " + foundStudentByMobile);

		} catch (StudentNotFoundException1 e) {
			System.err.println("Error finding student by mobile number: " + e.getMessage());
		}
	}
}
