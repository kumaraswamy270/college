package com.student.detail.service;

import java.time.LocalDate;
import java.util.List;

import com.student.detail.exception.StudentNotFoundException1;
import com.student.detail.model.Student;

public interface StudentService {

	Student addStudent(Student student);

	Student updateStudent(Student student) throws StudentNotFoundException1;

	boolean deleteStudent(Student studentToDelete) throws StudentNotFoundException1;

	List<Student> getAllStudent();

	Student findStudentByName(String name) throws StudentNotFoundException1;

	Student findStudentByRollnumber(int rollno) throws StudentNotFoundException1;

	List<Student> findStudentsByCollege(String college);

	Student findStudentByMobileNumber(String mobile) throws StudentNotFoundException1;

	List<Student> findStudentByDateofbirthRange(LocalDate startDate, LocalDate endDate);

	int getCountofStudents();

	int batchProcessStudents(List<Student> students);

	

}
