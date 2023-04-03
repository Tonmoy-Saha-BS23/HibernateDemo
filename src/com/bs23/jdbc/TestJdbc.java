package com.bs23.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost:3306/student_tracker?useSSL=false";
		String username = "tonmoyjdbc";
		String passwd = "tonmoy";
		try {
			System.out.println("Connecting to " + jdbcUrl);
			
			Connection myCon = DriverManager.getConnection(jdbcUrl, username, passwd);
			
			System.out.println("Connection is successful");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
