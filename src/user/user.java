package user;

import java.util.*;
import java.util.Date;
import java.sql.*;
import java.text.SimpleDateFormat;

import oop_assignment.ClaimRecord;

public class user {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
			
			int empFlag = 0, limitFlag = 0, claimTypeFlag = 0;
			String empID, typeID;
			float limit = 0, amt = 0;
			
			Scanner input = new Scanner(System.in);
			
			do {
				System.out.print("Enter your Employee ID: ");
				empID = input.nextLine();
				
				ResultSet rs = stmt.executeQuery("SELECT empID FROM empdetails");
				while(rs.next()) {
					String id = rs.getString("empID");
					if(empID.equals(id)) {
						empFlag = 0;
					}
					else {
						empFlag = 1;
					}
				}
				
				if(empFlag == 1) {
					System.out.println("Employee ID does not exist. Please enter again.");
				}
			}while(empFlag == 1);
			
			System.out.println("\n\nThe type of claims available:\n");
			System.out.println("Claim Type ID\tClaim Type\tDate");
			
			ResultSet rs = stmt.executeQuery("SELECT claimTypeID, claimTypeName FROM empdetails INNER JOIN claimtype ON empID = '" + empID + "' AND empdetails.position = claimtype.applicableToPosition");
			while(rs.next()) {
				String claimID = rs.getString("claimTypeID");
				String claimName = rs.getString("claimTypeName");
				System.out.print(claimID + "\t\t");
				System.out.print(claimName + "\t");
				
				Date date = new Date();
				SimpleDateFormat simpleDF = new SimpleDateFormat("EEE MMM dd, yyyy");
				System.out.println(simpleDF.format(date));
				
			}
			
			do {
				System.out.print("\nEnter your desired claim type ID: ");
				typeID = input.nextLine();
				
				rs = stmt.executeQuery("SELECT claimTypeID FROM claimtype");
				while(rs.next()) {
					String claimTypeID = rs.getString("claimTypeID");
					if(typeID.equals(claimTypeID)) {
						claimTypeFlag = 0;
						break;
					}
					else {
						claimTypeFlag = 1;
					}
				}
				
				if(claimTypeFlag == 1) {
					System.out.println("Claim type ID does not exist. Please enter again.");
				}
				
			}while(claimTypeFlag == 1);
			
			do {
				System.out.print("Enter your amount: ");
				amt = input.nextFloat();
				
				rs = stmt.executeQuery("SELECT claimLimit FROM claimtype WHERE claimTypeID = '" + typeID + "'");
				while(rs.next()) {
					limit = rs.getFloat("claimLimit");
					if(amt > limit) {
						limitFlag = 1;
					}
					else if(amt <= 0) {
						limitFlag = 2;
					}
					else {
						limitFlag = 0;
					}
				}
				
				if(limitFlag == 1) {
					System.out.printf("Your specified amount should be within RM%.2f\n", limit);
				}
				else if(limitFlag == 2) {
					System.out.println("Please enter an amount larger than 0.");
				}
			}while(limitFlag == 1 || limitFlag == 2);
			
			ClaimRecord record = new ClaimRecord();
			record.ApplyClaim(empID, amt, typeID);
			
			input.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
