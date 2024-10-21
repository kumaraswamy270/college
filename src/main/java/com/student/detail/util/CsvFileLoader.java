package com.student.detail.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.student.detail.model.Student;

public class CsvFileLoader { // Class name updated to CsvFileLoader

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public static List<Student> loadStudentData(String filePath) {
		List<Student> students = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			String line;
			boolean isFirstLine = true; // Flag to skip the header line

			while ((line = br.readLine()) != null) {
				if (isFirstLine) {
					isFirstLine = false; // Skip the first line (header)
					continue;
				}
				Student student = parseStudent(line);
				if (student != null) {
					students.add(student);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		}

		return students;
	}

	private static Student parseStudent(String line) {
		String[] data = line.split(",");

		if (data.length != 13) { // Assuming the CSV has 13 fields
			System.err.println("Invalid data format in line: " + line);
			return null;
		}

		String firstname = data[5].trim();
		String dob = data[9].trim();
		String statusStr = data[11].trim();
		String imagePath = data[12].trim(); // This is the path to the image file

		// Check for required fields
		if (firstname.isEmpty() || dob.isEmpty() || statusStr.isEmpty()) {
			System.err.println("Missing required fields in line: " + line);
			return null;
		}

		try {
			int studentcode = Integer.parseInt(data[0].trim());
			int rollnumber = Integer.parseInt(data[1].trim());
			int marks = Integer.parseInt(data[2].trim());
			String branch = data[3].trim();
			String college = data[4].trim();
			String lastname = data[6].trim();
			String fathername = data[7].trim();
			String mobileno = data[8].trim();
			LocalDate dateOfBirth = parseLocalDate(dob);
			String address = data[10].trim();
			boolean status = Integer.parseInt(statusStr) == 1; // Parse status as boolean

			byte[] getImage = loadImage(imagePath); // Load the image bytes

			if (dateOfBirth == null) {
				System.err.println("Invalid date format for line: " + line);
				return null;
			}

			return new Student(studentcode, rollnumber, marks, branch, college, firstname, lastname, fathername,
					mobileno, dateOfBirth, address, status, getImage);

		} catch (NumberFormatException e) {
			System.err.println("Error parsing numeric data in line: " + line + ". Error: " + e.getMessage());
			return null;
		}
	}

	private static LocalDate parseLocalDate(String dateStr) {
		try {
			return LocalDate.parse(dateStr, dateFormatter);
		} catch (Exception e) {
			System.err.println("Error parsing date: " + dateStr + ". Correct format is dd-MM-yyyy.");
			return null;
		}
	}

	private static byte[] loadImage(String imagePath) {
		try {
			return Files.readAllBytes(Paths.get(imagePath));
		} catch (IOException e) {
			System.err.println("Error loading image from path: " + imagePath + ". Error: " + e.getMessage());
			return null;
		}
	}
}
