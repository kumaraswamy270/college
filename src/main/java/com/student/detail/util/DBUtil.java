package com.student.detail.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

    public static Connection getConnection() {
        try {
            // Load and register MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver"); // Use 'com.mysql.cj.jdbc.Driver' for newer versions

            // Updated connection URL to disable SSL warning
            String url = "jdbc:mysql://localhost:3306/studentdb?useSSL=false"; 
            String username = "root";
            String password = "Kumaraswamy123@";

            // Establishing a connection with the database
            Connection con = DriverManager.getConnection(url, username, password);

            // Returning the connection object
            return con;
        } catch (SQLException | ClassNotFoundException e) {
            // Print any exception that occurs
            System.err.println("Error establishing connection: " + e.getMessage());
            return null;
        }
    }

    // Close connection method for ResultSet, PreparedStatement, and Connection
    public static void closeConnection(ResultSet rs, PreparedStatement ps, Connection con) {
        closeConnection(rs, ps, null, con);
    }

    // Close connection method for ResultSet, Statement, and Connection
    public static void closeConnection(ResultSet rs, Statement stmt, Connection con) {
        closeConnection(rs, null, stmt, con);
    }

    // Private method to close ResultSet, PreparedStatement, Statement, and Connection
    private static void closeConnection(ResultSet rs, PreparedStatement ps, Statement stmt, Connection con) {
        try {
            if (rs != null) rs.close();
        } catch (SQLException e) {
            System.err.println("Error closing ResultSet: " + e.getMessage());
        }
        try {
            if (ps != null) ps.close();
        } catch (SQLException e) {
            System.err.println("Error closing PreparedStatement: " + e.getMessage());
        }
        try {
            if (stmt != null) stmt.close();
        } catch (SQLException e) {
            System.err.println("Error closing Statement: " + e.getMessage());
        }
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            System.err.println("Error closing Connection: " + e.getMessage());
        }
    }
}
