package ua.lviv.lgs.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtils {

	static String url = "jdbc:mysql://localhost:3306/shop";
	static String username = "peles";
	static String password = "654321";
	
	public static Connection connect() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}
