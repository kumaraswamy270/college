package com.student.detail.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.student.detail.model.College;

@Repository
public interface CollegeRepository extends JpaRepository<College, Long> {

	// Custom query methods
	College findCollegeBycollegeName(String collegeName);

	College findCollegeBycollegeId(Long id);

	List<College> findCollegesByCity(String city);
}
