package com.student.detail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.dao.EmptyResultDataAccessException;
import com.student.detail.model.Login;
import com.student.detail.service.LoginService;

@Service
public class LoginDBService implements LoginService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// RowMapper for Login model to map the result set to a Login object
	private RowMapper<Login> loginRowMapper = (rs, rowNum) -> new Login(rs.getString("username"),
			rs.getString("password"), rs.getBoolean("isActive"));

	// Method to validate a user based on the username and password
	@Override
	public boolean validateUser(String username, String password) {
		String validateSql = "SELECT COUNT(*) FROM login WHERE username = ? AND password = md5(?)";

		int count = jdbcTemplate.queryForObject(validateSql, Integer.class, username, password);

		return count > 0;

	}

	// Method to retrieve user details based on the username
	@Override
	public Login getUserDetails(String username) {
		String selectSql = "SELECT * FROM login WHERE username = ?";

		try {
			// Query for user details using JdbcTemplate
			return jdbcTemplate.queryForObject(selectSql, loginRowMapper, username);
		} catch (EmptyResultDataAccessException e) {
			return null; // Return null if no user found
		}
	}
}
