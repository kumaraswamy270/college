package com.student.detail.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "colleges")
public class College {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "collegeId")
	private Long collegeId;

	@Column(name = "collegeName", nullable = false)
	private String collegeName;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "zipcode")
	private int zipcode;

	@Column(name = "phoneNumber")
	private String phoneNumber;

	// One-to-Many relationship with Student
	@OneToMany(mappedBy = "collegeEntity", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> students = new ArrayList<>();

	// Many-to-Many relationship with Course
	@ManyToMany
	@JoinTable(name = "course_college", joinColumns = @JoinColumn(name = "collegeId"), inverseJoinColumns = @JoinColumn(name = "courseId"))
	private List<Course> courses = new ArrayList<>();

	public College() {
	}

	public College(long collegeId, String collegeName, String address, String city, String state, int zipcode,
			String phoneNumber) {
		this.collegeId = collegeId;
		this.collegeName = collegeName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.phoneNumber = phoneNumber;
	}

	// Getters and Setters
	public Long getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Long collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipcode() {
		return zipcode;
	}

	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	// Utility methods for bi-directional relationships
	public void addStudent(Student student) {
		students.add(student);
		student.setCollegeEntity(this);
	}

	public void removeStudent(Student student) {
		students.remove(student);
		student.setCollegeEntity(null);
	}

	public void addCourse(Course course) {
		courses.add(course);
	}

	public void removeCourse(Course course) {
		courses.remove(course);
	}

	// toString() Method
	@Override
	public String toString() {
		return "College{" + "collegeId=" + collegeId + ", collegeName='" + collegeName + '\'' + ", address='" + address
				+ '\'' + ", city='" + city + '\'' + ", state='" + state + '\'' + ", zipcode='" + zipcode + '\''
				+ ", phoneNumber='" + phoneNumber + '\'' + ", students=" + students.size() +

				", courses=" + courses.size() + '}';
	}
}
