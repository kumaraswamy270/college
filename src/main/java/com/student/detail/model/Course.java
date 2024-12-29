package com.student.detail.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
	@Column(name = "courseId")
	private Long courseId;

	@Column(name = "courseName")
	private String courseName;

	@Column(name = "credits")
	private int credits;

	@Column(name = "department")
	private String department;

	@Column(name = "duration")
	private int duration; // Duration in weeks or months, depending on your requirement

	@Column(name = "feeStructure")
	private String feeStructure;

	@Column(name = "lengthOfStudents")
	private int lengthOfStudents; // Number of students in the course

	@Column(name = "startDate")
	private LocalDate startDate;

	@Column(name = "endDate")
	private LocalDate endDate;

	// Many-to-many relationship with College (assuming the 'College' entity exists)
	@ManyToMany
	@JoinTable(name = "course_college", joinColumns = @JoinColumn(name = "courseId"), inverseJoinColumns = @JoinColumn(name = "collegeId"))
	private List<College> colleges;

	// Default constructor
	public Course() {
	}

	// Constructor with all fields including colleges
	public Course(Long courseId, String courseName, int credits, String department, int duration, String feeStructure,
			int lengthOfStudents, LocalDate startDate, LocalDate endDate) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.credits = credits;
		this.department = department;
		this.duration = duration;
		this.feeStructure = feeStructure;
		this.lengthOfStudents = lengthOfStudents;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	// Getters and Setters
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getFeeStructure() {
		return feeStructure;
	}

	public void setFeeStructure(String feeStructure) {
		this.feeStructure = feeStructure;
	}

	public int getLengthOfStudents() {
		return lengthOfStudents;
	}

	public void setLengthOfStudents(int lengthOfStudents) {
		this.lengthOfStudents = lengthOfStudents;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public List<College> getColleges() {
		return colleges;
	}

	public void setColleges(List<College> colleges) {
		this.colleges = colleges;
	}

	@Override
	public String toString() {
		return "Course{" + "courseId=" + courseId + ", courseName='" + courseName + '\'' + ", credits=" + credits
				+ ", department='" + department + '\'' + ", duration=" + duration + ", feeStructure='" + feeStructure
				+ '\'' + ", lengthOfStudents=" + lengthOfStudents + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", colleges=" + colleges + '}';
	}
}
