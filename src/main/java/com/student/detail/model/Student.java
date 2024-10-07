package com.student.detail.model;

import java.time.LocalDate;

public class Student implements Comparable<Student> {
	private int studentcode;
	private int rollnumber;
	private int marks;
	private String branch;
	private String college;
	private String firstname;
	private String lastname;
	private String fathername;
	private String mobileno;
	private LocalDate dateofbirth;
	private String address;
	private boolean status;
	private byte[] image;

	// Updated constructor with image
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
		this.image = image; // Initialize image field
	}

	public Student() {
		// TODO Auto-generated constructor stub
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

	public byte[] getimage() { // Getter for image
		return image;
	}

	public void setimage(byte[] image) { // Setter for image
		this.image = image;
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
				+ ", address=" + address + ", status=" + status + ", image=" + (image) + "]";
	}

}