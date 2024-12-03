package com.student.detail.service;

import java.util.List;

import com.student.detail.model.College;
import com.student.detail.exception.CollegeNotFoundException;

public interface CollegeService {

	College addCollege(College college);

	College updateCollege(College college) throws CollegeNotFoundException;

	boolean deleteCollege(College college) throws CollegeNotFoundException;

	List<College> getAllColleges();

	College findCollegeByName(String name) throws CollegeNotFoundException;

	College findCollegeById(int id) throws CollegeNotFoundException;

	List<College> findCollegesByCity(String city);

	int getCountOfColleges();
}
