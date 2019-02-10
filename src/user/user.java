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
			
			int empFlag = 0;
			String empID;
			
			Scanner input = new Scanner(System.in);
			
			do {
				System.out.print("Enter your Employee ID: ");
				empID = input.nextLine();
				
				ResultSet rs = stmt.executeQuery("SELECT empID FROM empdetails");
				while(rs.next()) {
					String id = rs.getString("empID");
					if(empID.equals(id)) {
						empFlag = 0;
						break;
					}
					else {
						empFlag = 1;
					}
				}
				
				if(empFlag == 1) {
					System.out.println("Employee ID does not exist. Please enter again.");
				}
			}while(empFlag == 1);
			
			
			
			
			
			ClaimRecord record = new ClaimRecord();
			
			record.EditClaim(empID);
			
			input.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
