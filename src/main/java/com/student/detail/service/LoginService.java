package com.student.detail.service;

import com.student.detail.model.Login;

public interface LoginService {

	// Method to validate a user based on the username and password
	boolean validateUser(String username, String password);

	// Method to retrieve user details
	Login getUserDetails(String username);
}
