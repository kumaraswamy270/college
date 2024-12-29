package com.student.detail.model;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CourseCollegeId implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long courseId;
	private Long collegeId;

	// Default constructor
	public CourseCollegeId() {
	}

	// Constructor with fields
	public CourseCollegeId(Long courseId, Long collegeId) {
		this.courseId = courseId;
		this.collegeId = collegeId;
	}

	// Getters and setters
	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	// Override equals and hashCode methods
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CourseCollegeId that = (CourseCollegeId) o;
		return Objects.equals(courseId, that.courseId) && Objects.equals(collegeId, that.collegeId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(courseId, collegeId);
	}
}
