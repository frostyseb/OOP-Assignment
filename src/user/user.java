package user;

import java.util.*;
import java.sql.*;
import oop_assignment.ClaimRecord;

public class user {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
			
			System.out.print("Enter your Employee ID: ");
			Scanner input = new Scanner(System.in);
			String id = input.nextLine();
			
			ClaimRecord record = new ClaimRecord();
			record.ApplyClaim(id);
			
			input.close();
			stmt.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
