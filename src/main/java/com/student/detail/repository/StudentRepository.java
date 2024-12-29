package com.student.detail.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.detail.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByDateofbirthBetween(LocalDate startDate, LocalDate endDate);

	Optional<Student> findByMobileno(String mobile);

	List<Student> findByCollege(String college);

	Optional<Student> findByFirstnameOrLastname(String name, String name2);

	Optional<Student> findByRollnumber(int rollno);

	Page<Student> findAll(Pageable pageable);

}
