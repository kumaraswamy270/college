package com.student.detail.model;

public class Login implements Comparable<Login> {
	private String username;
	private String password;
	private boolean isActive;

	// Constructor
	public Login(String username, String password, boolean isActive) {
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}

	// Getters and Setters
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	// Implementing compareTo to compare by username
	public int compareTo(Login other) {
		return this.username.compareTo(other.username);
	}

	// toString method for displaying login information
	@Override
	public String toString() {
		return "Login [username=" + username + ", isActive=" + isActive + "]";
	}
}
