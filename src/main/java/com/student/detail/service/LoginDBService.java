package com.student.detail.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.student.detail.model.Login;
import com.student.detail.service.LoginService;
import com.student.detail.util.DBUtil;

public class LoginDBService implements LoginService {

	// Method to validate a user based on the username and password
	@Override
	public boolean validateUser(String username, String password) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String validateSql = "SELECT COUNT(*) FROM login WHERE username = ? AND password = md5(?)";

		try {
			con = DBUtil.getConnection(); // Get database connection
			ps = con.prepareStatement(validateSql);
			ps.setString(1, username);
			ps.setString(2, password);

			rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt(1) > 0; // Return true if a user is found
			}
		} catch (SQLException e) {
			System.err.println("Error validating user: " + e.getMessage());
		} finally {
			DBUtil.closeConnection(rs, ps, con); // Close resources
		}

		return false; // Return false if an error occurs or no user is found
	}

	// Method to retrieve user details
	@Override
	public Login getUserDetails(String username) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String selectSql = "SELECT * FROM login WHERE username = ?";

		try {
			con = DBUtil.getConnection(); // Get database connection
			ps = con.prepareStatement(selectSql);
			ps.setString(1, username);

			rs = ps.executeQuery();

			if (rs.next()) {
				String password = rs.getString("password");
				boolean isActive = rs.getBoolean("isActive");

				return new Login(username, password, isActive); // Return Login object
			}
		} catch (SQLException e) {
			System.err.println("Error retrieving user details: " + e.getMessage());
		} finally {
			DBUtil.closeConnection(rs, ps, con); // Close resources
		}

		return null; // Return null if no user is found
	}
}
