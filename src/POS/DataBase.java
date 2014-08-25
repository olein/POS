package POS;

import java.sql.*;

public class DataBase {

	public Connection connect() {
		Connection conn = null;
		String url = "jdbc:mysql://localhost:3306/";
		String dbName = "POS";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "root";
		String password = "";
		try {
			Class.forName(driver).newInstance();
			conn = DriverManager
					.getConnection(url + dbName, userName, password);
			System.out.println("Connected to the database");
			
			return conn;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.print("failed\n");
		}
		return conn;
	}

}
