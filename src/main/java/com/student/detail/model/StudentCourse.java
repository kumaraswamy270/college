package com.student.detail.model;

import javax.persistence.*;

@Entity
@Table(name = "student_course")
public class StudentCourse {

	@EmbeddedId
	private StudentCourseId id;

	@ManyToOne
	@MapsId("rollnumber") // Mapping the rollnumber part of the composite key
	@JoinColumn(name = "rollnumber", referencedColumnName = "rollnumber")
	private Student student;

	@ManyToOne
	@MapsId("courseId") // Mapping the courseId part of the composite key
	@JoinColumn(name = "courseId", referencedColumnName = "courseId")
	private Course course;

	@Column(name = "enrollmentDate")
	private String enrollmentDate;

	// Constructors, getters, setters
	public StudentCourse() {
	}

	public StudentCourse(StudentCourseId id, Student student, Course course, String enrollmentDate) {
		this.id = id;
		this.student = student;
		this.course = course;
		this.enrollmentDate = enrollmentDate;
	}

	public StudentCourseId getId() {
		return id;
	}

	public void setId(StudentCourseId id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public String getEnrollmentDate() {
		return enrollmentDate;
	}

	public void setEnrollmentDate(String enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
}
