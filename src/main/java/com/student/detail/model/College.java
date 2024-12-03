package com.student.detail.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // This annotation marks the class as a Hibernate entity
@Table(name = "colleges") // This specifies the table name in the database
public class College {
	@Id // This annotation marks the primary key
	private int collegeId;
	private String collegeName;
	private String address;
	private String city;
	private String state;
	private int zipcode;
	private String phoneNumber;

	// Default constructor
	public College() {
	}

	// Parameterized constructor
	public College(int collegeId, String collegeName, String address, String city, String state, int zipcode,
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
	public int getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(int collegeId) {
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

	// Override toString for better debugging
	@Override
	public String toString() {
		return "College{" + "collegeId=" + collegeId + ", collegeName='" + collegeName + '\'' + ", address='" + address
				+ '\'' + ", city='" + city + '\'' + ", state='" + state + '\'' + ", zipcode=" + zipcode
				+ ", phoneNumber='" + phoneNumber + '\'' + '}';
	}
}