package com.student.detail.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.student.detail.exception.CollegeNotFoundException;
import com.student.detail.model.College;
import com.student.detail.util.DBUtil;

public class CollegeDBService implements CollegeService {

	public College addCollege(College college) {
		Connection con = null;
		Statement stmt = null;
		String sql = "INSERT INTO colleges (collegeId, collegeName, address, city, state, zipcode, phoneNumber) VALUES ("
				+ college.getCollegeId() + ", '" + college.getCollegeName() + "', '" + college.getAddress() + "', '"
				+ college.getCity() + "', '" + college.getState() + "', '" + college.getZipcode() + "', '"
				+ college.getPhoneNumber() + "')";
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			int rowsAffected = stmt.executeUpdate(sql);
			if (rowsAffected > 0) {
				return college;
			} else {
				throw new SQLException("Failed to add college");
			}

		} catch (SQLException e) {
			System.err.println("Error adding college: " + e.getMessage());
			return null;
		} finally {
			DBUtil.closeConnection(null, stmt, con);
		}
	}

	@Override
	public College updateCollege(College college) throws CollegeNotFoundException {
		Connection con = null;
		Statement stmt = null;
		String sql = "UPDATE colleges SET collegeName = '" + college.getCollegeName() + "', address = '"
				+ college.getAddress() + "', city = '" + college.getCity() + "', state = '" + college.getState()
				+ "', zipcode = '" + college.getZipcode() + "', phoneNumber = '" + college.getPhoneNumber()
				+ "' WHERE collegeId = " + college.getCollegeId();
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			int rowsAffected = stmt.executeUpdate(sql);
			if (rowsAffected > 0) {
				return college;
			} else {
				throw new CollegeNotFoundException("College with ID " + college.getCollegeId() + " not found");
			}

		} catch (SQLException e) {
			System.err.println("Error updating college: " + e.getMessage());
			return null;
		} finally {
			DBUtil.closeConnection(null, stmt, con);
		}
	}

	@Override
	public boolean deleteCollege(College college) {
		Connection con = null;
		Statement stmt = null;
		String sql = "DELETE FROM colleges WHERE collegeId = " + college.getCollegeId();
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			int rowsAffected = stmt.executeUpdate(sql);
			return rowsAffected > 0;

		} catch (SQLException e) {
			System.err.println("Error deleting college: " + e.getMessage());
			return false;
		} finally {
			DBUtil.closeConnection(null, stmt, con);
		}
	}

	@Override
	public List<College> getAllColleges() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<College> colleges = new ArrayList<>();
		String sql = "SELECT * FROM colleges";
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			while (rs.next()) {
				College college = new College(rs.getInt("collegeId"), rs.getString("collegeName"),
						rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zipcode"),
						rs.getString("phoneNumber"));
				colleges.add(college);
			}
			return colleges;

		} catch (SQLException e) {
			System.err.println("Error retrieving colleges: " + e.getMessage());
			return colleges;
		} finally {
			DBUtil.closeConnection(rs, stmt, con);
		}
	}

	@Override
	public College findCollegeByName(String name) throws CollegeNotFoundException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM colleges WHERE collegeName = '" + name + "'";
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return new College(rs.getInt("collegeId"), rs.getString("collegeName"), rs.getString("address"),
						rs.getString("city"), rs.getString("state"), rs.getString("zipcode"),
						rs.getString("phoneNumber"));
			} else {
				throw new CollegeNotFoundException("College with name " + name + " not found");
			}

		} catch (SQLException e) {
			System.err.println("Error finding college by name: " + e.getMessage());
			return null;
		} finally {
			DBUtil.closeConnection(rs, stmt, con);
		}
	}

	@Override
	public College findCollegeById(int id) throws CollegeNotFoundException {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT * FROM colleges WHERE collegeId = " + id;
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				return new College(rs.getInt("collegeId"), rs.getString("collegeName"), rs.getString("address"),
						rs.getString("city"), rs.getString("state"), rs.getString("zipcode"),
						rs.getString("phoneNumber"));
			} else {
				throw new CollegeNotFoundException("College with ID " + id + " not found");
			}

		} catch (SQLException e) {
			System.err.println("Error finding college by ID: " + e.getMessage());
			return null;
		} finally {
			DBUtil.closeConnection(rs, stmt, con);
		}
	}

	@Override
	public List<College> findCollegesByCity(String city) {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		List<College> colleges = new ArrayList<>();
		String sql = "SELECT * FROM colleges WHERE city = '" + city + "'";
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				College college = new College(rs.getInt("collegeId"), rs.getString("collegeName"),
						rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zipcode"),
						rs.getString("phoneNumber"));
				colleges.add(college);
			}
		} catch (SQLException e) {
			System.err.println("Error finding colleges by city: " + e.getMessage());
		} finally {
			DBUtil.closeConnection(rs, stmt, con);
		}

		return colleges;
	}

	@Override
	public int getCountOfColleges() {
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT COUNT(*) FROM colleges";
		try {
			con = DBUtil.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				return rs.getInt(1);
			} else {
				throw new SQLException("Failed to count colleges");
			}

		} catch (SQLException e) {
			System.err.println("Error counting colleges: " + e.getMessage());
			return 0;
		} finally {
			DBUtil.closeConnection(rs, stmt, con);
		}
	}
}
