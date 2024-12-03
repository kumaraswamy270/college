package com.student.detail.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.student.detail.model.College;

public class CsvFileLoaderCollege {

	public static List<College> loadCollegesFromCsv(String filePath) {
		List<College> colleges = new ArrayList<>();
		String line;
		String csvSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			// Skip the header if the CSV has one
			br.readLine();

			while ((line = br.readLine()) != null) {
				String[] data = line.split(csvSplitBy);

				// Assuming the order of fields in the CSV file matches the College fields
				int collegeId = Integer.parseInt(data[0].trim());
				String collegeName = data[1].trim();
				String address = data[2].trim();
				String city = data[3].trim();
				String state = data[4].trim();
				int zipcode = Integer.parseInt(data[5].trim());
				String phoneNumber = data[6].trim();

				College college = new College(collegeId, collegeName, address, city, state, zipcode, phoneNumber);
				colleges.add(college);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return colleges;
	}
}
