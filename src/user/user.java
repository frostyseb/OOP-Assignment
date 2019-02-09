package user;

import java.sql.*;

public class user {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
