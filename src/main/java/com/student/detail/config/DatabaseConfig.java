package com.student.detail.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

	// Hardcoding values for the database configuration
	private String url = "jdbc:mysql://localhost:3306/studentdb?useSSL=false";
	private String username = "root";
	private String password = "Kumaraswamy123@";
	private String driverClassName = "com.mysql.cj.jdbc.Driver";

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDriverClassName(driverClassName);
		return dataSource;
	}
}
