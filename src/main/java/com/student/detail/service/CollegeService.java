package com.student.detail.service;

import java.util.List;

import com.student.detail.model.College;
import com.student.detail.exception.CollegeNotFoundException;

public interface CollegeService {

	College addCollege(College college);

	College updateCollege(College college) throws CollegeNotFoundException;

	boolean deleteCollege(College college) throws CollegeNotFoundException;

	List<College> getAllColleges();

	College findCollegeBycollegeName(String collegename) throws CollegeNotFoundException;

	College findCollegeBycollegeId(Long collegeid) throws CollegeNotFoundException;

	List<College> findCollegesByCity(String city);

	int getCountOfColleges();
}
