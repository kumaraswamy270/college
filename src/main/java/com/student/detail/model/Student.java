package com.student.detail.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "students")
public class Student implements Comparable<Student> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "studentcode")
	private int studentcode;

	@Column(name = "rollnumber", nullable = false, unique = true)
	private int rollnumber;

	@Column(name = "marks", nullable = false)
	private int marks;

	@Column(name = "branch", nullable = false, length = 50)
	private String branch;

	@Column(name = "college", nullable = false, length = 100)
	private String college;

	@Column(name = "firstname", nullable = false, length = 50)
	private String firstname;

	@Column(name = "lastname", nullable = false, length = 50)
	private String lastname;

	@Column(name = "fathername", length = 50)
	private String fathername;

	@Column(name = "mobileno", nullable = false, length = 15)
	private String mobileno;

	@Column(name = "dateofbirth")
	private LocalDate dateofbirth;

	@Column(name = "address", length = 255)
	private String address;

	@Column(name = "status", nullable = false)
	private boolean status;

	@Lob
	@Column(name = "image")
	private byte[] image;

	@ManyToOne
	@JoinColumn(name = "collegeId")
	private College collegeEntity;

	// Many-to-Many relationship with Course
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "rollnumber"), inverseJoinColumns = @JoinColumn(name = "courseId"))
	private List<Course> courses;

	// Constructors
	public Student() {
		// Default constructor
	}

	public Student(int studentcode, int rollnumber, int marks, String branch, String college, String firstname,
			String lastname, String fathername, String mobileno, LocalDate dateofbirth, String address, boolean status,
			byte[] image) {
		this.studentcode = studentcode;
		this.rollnumber = rollnumber;
		this.marks = marks;
		this.branch = branch;
		this.college = college;
		this.firstname = firstname;
		this.lastname = lastname;
		this.fathername = fathername;
		this.mobileno = mobileno;
		this.dateofbirth = dateofbirth;
		this.address = address;
		this.status = status;
		this.image = image;
	}

	// Getters and Setters
	public int getStudentcode() {
		return studentcode;
	}

	public void setStudentcode(int studentcode) {
		this.studentcode = studentcode;
	}

	public int getRollnumber() {
		return rollnumber;
	}

	public void setRollnumber(int rollnumber) {
		this.rollnumber = rollnumber;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFathername() {
		return fathername;
	}

	public void setFathername(String fathername) {
		this.fathername = fathername;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public LocalDate getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(LocalDate dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public College getCollegeEntity() {
		return collegeEntity;
	}

	public void setCollegeEntity(College collegeEntity) {
		this.collegeEntity = collegeEntity;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	@Override
	public int compareTo(Student other) {
		return this.firstname.compareTo(other.firstname);
	}

	@Override
	public String toString() {
		return "Student [studentcode=" + studentcode + ", rollnumber=" + rollnumber + ", marks=" + marks + ", branch="
				+ branch + ", college=" + college + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", fathername=" + fathername + ", mobileno=" + mobileno + ", dateofbirth=" + dateofbirth
				+ ", address=" + address + ", status=" + status + ", image=" + (image != null ? "Exists" : "Null")
				+ ", collegeEntity=" + (collegeEntity != null ? collegeEntity.getCollegeName() : "None") + ", courses="
				+ (courses != null ? courses.size() : 0) + "]";
	}
}
