package com.student.detail.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class StudentCourseId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long rollnumber;
	private Long courseId;

	// Default constructor
	public StudentCourseId() {
	}

	// Constructor with fields
	public StudentCourseId(Long rollnumber, Long courseId) {
		this.rollnumber = rollnumber;
		this.courseId = courseId;
	}

	// Getters and setters
	public Long getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(Long rollnumber) {
		this.rollnumber = rollnumber;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	// Override equals and hashCode methods
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		StudentCourseId that = (StudentCourseId) o;
		return Objects.equals(rollnumber, that.rollnumber) && Objects.equals(courseId, that.courseId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(rollnumber, courseId);
	}
}
