package com.student.detail.util;

import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.student.detail.model.Student;

class StudentCsvTest {

	@Test
	void testLoadStudentData() {
		String filePath = "D:\\sample\\Studentdata.csv";
		List<Student> students = CsvFileLoader.loadStudentData(filePath);

		// Assert that the student list is not null and not empty
		assertNotNull(students, "Student list should not be null");
		assertFalse(students.isEmpty(), "Student list should not be empty");

		System.out.println("Students (Unsorted):");
		for (Student student : students) {
			System.out.println(student);
		}
	}
}
