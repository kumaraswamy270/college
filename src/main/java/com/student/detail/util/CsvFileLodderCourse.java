package com.student.detail.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import com.student.detail.model.Course;

public class CsvFileLodderCourse {

	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public static Map<Long, Course> loadCourseData(String filePath) {

		Map<Long, Course> courses = new HashMap<>();
		BufferedReader br = null;

		try {
			File file = new File(filePath);
			br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				Course course = parseCourse(line);
				if (course != null) {
					courses.put(course.getCourseId(), course);
				}
			}
		} catch (IOException e) {
			System.err.println("Error reading file: " + e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					System.err.println("Error closing BufferedReader: " + e.getMessage());
				}
			}
		}

		return courses;
	}

	private static Course parseCourse(String line) {
		String[] data = line.split(",");

		if (data.length != 9) { // Correct number of fields should be 9
			System.err.println("Invalid data format in line: " + line);
			return null;
		}

		String courseName = data[1].trim();
		String startDateStr = data[7].trim();
		String endDateStr = data[8].trim();

		boolean isValidRecord = true;

		if (courseName.isEmpty()) {
			System.err.println("Missing required field: course name in line: " + line);
			isValidRecord = false;
		}

		if (startDateStr.isEmpty()) {
			System.err.println("Missing required field: start date in line: " + line);
			isValidRecord = false;
		}

		if (endDateStr.isEmpty()) {
			System.err.println("Missing required field: end date in line: " + line);
			isValidRecord = false;
		}
		if (!isValidRecord) {
			return null;
		}

		try {
			Long courseId = Long.parseLong(data[0].trim());
			int credits = Integer.parseInt(data[2].trim());
			String department = data[3].trim();
			int duration = Integer.parseInt(data[4].trim());
			String feeStructure = data[5].trim();
			int lengthOfStudents = Integer.parseInt(data[6].trim());
			LocalDate startDate = parseLocalDate(startDateStr);
			LocalDate endDate = parseLocalDate(endDateStr);

			return new Course(courseId, courseName, credits, department, duration, feeStructure, lengthOfStudents,
					startDate, endDate);

		} catch (NumberFormatException e) {
			System.err.println("Error parsing numeric data in line: " + line);
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
}
