package com.student.detail.model;

public class College {
	private int collegeId;
	private String collegeName;
	private String address;
	private String city;
	private String state;
	private String zipcode;
	private String phoneNumber;

	// Constructor
	public College(int collegeId, String collegeName, String address, String city, String state, String zipcode,
			String phoneNumber) {
		this.collegeId = collegeId;
		this.collegeName = collegeName;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zipcode = zipcode;
		this.phoneNumber = phoneNumber;
	}

	public College() {
		// TODO Auto-generated constructor stub
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

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	// toString method
	@Override
	public String toString() {
		return "College{" + "collegeId=" + collegeId + ", collegeName='" + collegeName + '\'' + ", address='" + address
				+ '\'' + ", city='" + city + '\'' + ", state='" + state + '\'' + ", zipcode='" + zipcode + '\''
				+ ", phoneNumber='" + phoneNumber + '\'' + '}';
	}
}
