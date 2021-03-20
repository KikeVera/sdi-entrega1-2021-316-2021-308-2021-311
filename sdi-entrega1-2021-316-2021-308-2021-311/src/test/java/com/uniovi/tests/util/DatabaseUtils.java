package com.uniovi.tests.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DatabaseUtils {
	
	
	private static final String PASSWORD="";
	private static final String USER="SA";
	private static final String URL="jdbc:hsqldb:hsql://localhost:9001";
	
	public static List<String> getUsersEmail() throws SQLException, ClassNotFoundException {

		Connection con = DriverManager.getConnection(URL,USER,PASSWORD);

		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from user");

		List<String> emails = new ArrayList<>();
		while (rs.next()) {
			
			if(rs.getString("role").equals("ROLE_USER")) {
				emails.add(rs.getString("email"));
			}

		}

		return emails;

	}
	
}
