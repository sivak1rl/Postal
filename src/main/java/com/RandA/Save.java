package com.RandA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.springframework.web.multipart.MultipartFile;

public class Save {

	public static boolean SaveImageToDb(MultipartFile file, String name) {

		return false;
	}

	/**
	 * 
	 * @return true if successfully created the images table
	 * @throws SQLException
	 */
	public static boolean CreateImagesTable() throws SQLException {
		String cmd = "create table testblob (image_id tinyint(3) not null default '0',image_typevarchar(25) not null default '',image blob not null,image_size varchar(25) not null default '',image_ctgy varchar(25) not null default '', image_name varchar(50) not null default '');";
		Statement stmt = getConnection().createStatement();
		stmt.executeQuery(cmd);

		return true;
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", "sivak1rl");
		connectionProps.put("password", "abc123");

		conn = DriverManager.getConnection("jdbc:mysql://localhost:8080/" + connectionProps);

		return conn;
	}
}
