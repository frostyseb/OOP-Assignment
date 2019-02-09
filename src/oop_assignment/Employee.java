package oop_assignment;

import java.util.*;
import java.sql.*;

public class Employee {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
			
			
			
			System.out.print("Enter your empID: ");
			Scanner input = new Scanner(System.in);
			String emp = input.nextLine();
			
			System.out.print("Enter your password: ");
			String passw = input.nextLine();
		
			
			
			stmt.executeUpdate("INSERT INTO empdetails VALUES(" + "'" + emp + "','" + passw + "', 'Robert', 'PR', 'Manager', 'ADMIN', 'ACTIVE', '12345')");
			
			input.close();
			
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}

		
	}
	
}


