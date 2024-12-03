package com.student.detail.model;

import java.time.LocalDate;

public class Course {
	private int courseId;
	private String courseName;
	private int credits;
	private String department;
	private int duration;
	private String feeStructure;
	private int lengthOfStudents;
	private LocalDate startDate;
	private LocalDate endDate;

	// Constructor, getters, and setters

	public Course(int courseId, String courseName, int credits, String department, int duration, String feeStructure,
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

	public Course() {

	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
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

	@Override
	public String toString() {
		return "Course{" + "courseId='" + courseId + '\'' + ", courseName='" + courseName + '\'' + ", credits="
				+ credits + ", department='" + department + '\'' + ", duration=" + duration + ", feeStructure='"
				+ feeStructure + '\'' + ", lengthOfStudents=" + lengthOfStudents + ", startDate=" + startDate
				+ ", endDate=" + endDate + '}';
	}
}
