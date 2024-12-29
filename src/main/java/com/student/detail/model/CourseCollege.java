package com.student.detail.model;

import javax.persistence.*;

@Entity
@Table(name = "course_college")
public class CourseCollege {

	@EmbeddedId
	private CourseCollegeId id;

	@ManyToOne
	@MapsId("courseId") // Mapping the courseId part of the composite key
	@JoinColumn(name = "courseId", referencedColumnName = "courseId")
	private Course course;

	@ManyToOne
	@MapsId("collegeId") // Mapping the collegeId part of the composite key
	@JoinColumn(name = "collegeId", referencedColumnName = "collegeId")
	private College college;

	// Constructors, getters, setters
	public CourseCollege() {
	}

	public CourseCollege(CourseCollegeId id, Course course, College college) {
		this.id = id;
		this.course = course;
		this.college = college;
	}

	public CourseCollegeId getId() {
		return id;
	}

	public void setId(CourseCollegeId id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

}