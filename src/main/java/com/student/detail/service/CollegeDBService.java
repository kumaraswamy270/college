package com.student.detail.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.student.detail.model.College;
import com.student.detail.exception.CollegeNotFoundException;
import com.student.detail.repository.CollegeRepository;
import com.student.detail.service.CollegeService;

@Service
public class CollegeDBService implements CollegeService {

	private static final Logger logger = LoggerFactory.getLogger(CollegeDBService.class);

	@Autowired
	private CollegeRepository collegeRepository;

	// Method to add a college
	@Override
	public College addCollege(College college) {
		try {
			College savedCollege = collegeRepository.save(college);
			logger.info("College {} added successfully.", college.getCollegeName());
			return savedCollege;
		} catch (Exception e) {
			logger.error("Error adding college: {}", e.getMessage());
			throw e;
		}
	}

	// Method to update a college
	@Override
	public College updateCollege(College college) throws CollegeNotFoundException {
		if (!collegeRepository.existsById(college.getCollegeId())) {
			logger.error("College not found with ID: {}", college.getCollegeId());
			throw new CollegeNotFoundException("College not found with ID: " + college.getCollegeId());
		}
		try {
			College updatedCollege = collegeRepository.save(college);
			logger.info("College {} updated successfully.", college.getCollegeName());
			return updatedCollege;
		} catch (Exception e) {
			logger.error("Error updating college: {}", e.getMessage());
			throw e;
		}
	}

	// Method to delete a college by ID
	@Override
	public boolean deleteCollege(College college) throws CollegeNotFoundException {
		// Find the college by ID
		College existingCollege = collegeRepository.findById(college.getCollegeId()).orElseThrow(
				() -> new CollegeNotFoundException("College not found with ID: " + college.getCollegeId()));

		try {
			// Delete the college
			collegeRepository.delete(existingCollege);
			logger.info("College with ID {} deleted successfully.", college.getCollegeId());
			return true; // Return true if deletion was successful
		} catch (Exception e) {
			logger.error("Error deleting college: {}", e.getMessage());
			return false; // Return false if an error occurs
		}
	}

	@Override
	public College findCollegeBycollegeId(Long id) throws CollegeNotFoundException {
		return collegeRepository.findById(id)
				.orElseThrow(() -> new CollegeNotFoundException("College not found with ID: " + id));
	}

	// Method to find a college by name
	@Override
	public College findCollegeBycollegeName(String collegeName) throws CollegeNotFoundException {
		try {
			College college = collegeRepository.findCollegeBycollegeName(collegeName); // Assuming you have a custom
																						// query here
			if (college == null) {
				logger.error("College not found with name: {}", collegeName);
				throw new CollegeNotFoundException("College not found with name: " + collegeName);
			}
			return college;
		} catch (Exception e) {
			logger.error("Error finding college by name: {}", e.getMessage());
			throw e;
		}
	}

	// Method to get all colleges
	@Override
	public List<College> getAllColleges() {
		try {
			List<College> colleges = collegeRepository.findAll();
			if (colleges.isEmpty()) {
				logger.info("No colleges found.");
			} else {
				logger.info("Found {} colleges.", colleges.size());
			}
			return colleges;
		} catch (Exception e) {
			logger.error("Error fetching colleges: {}", e.getMessage());
			throw e;
		}
	}

	// Method to find colleges by city
	@Override
	public List<College> findCollegesByCity(String city) {
		try {
			List<College> colleges = collegeRepository.findCollegesByCity(city); // Assuming you have a custom query
																					// here
			return colleges;
		} catch (Exception e) {
			logger.error("Error fetching colleges by city: {}", e.getMessage());
			throw e;
		}
	}

	// Method to get the count of colleges
	@Override
	public int getCountOfColleges() {
		try {
			return (int) collegeRepository.count();
		} catch (Exception e) {
			logger.error("Error getting count of colleges: {}", e.getMessage());
			throw e;
		}
	}

}
