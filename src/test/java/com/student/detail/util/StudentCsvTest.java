package com.student.detail.util;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.student.detail.model.Student;

class StudentCsvTest {

	@Test
	void testLoadStudentData() {
		String filePath = "D:\\sample\\Studentdata.csv";
		List<Student> students = CsvFileLoader.loadStudentData(filePath);

		if (students != null && !students.isEmpty()) {
			System.out.println("Students (Unsorted):");
			for (Student student : students) {
				System.out.println(student);
			}
		} else {
			System.err.println("No student data loaded or file not found.");
		}
	}
}