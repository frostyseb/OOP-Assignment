package AdministratorModule;

import java.util.*;
import java.sql.*;
import oop_assignment.ClaimType;

public class AdminClaim {
	
	static Scanner input = new Scanner (System.in);
	
	public static void AddClaim () {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee?useTimezone=true&serverTimezone=UTC", "root", "");
			Statement stmt = conn.createStatement();
	
			ClaimType claim1 = new ClaimType();
			
			System.out.println("Enter a claim ID :");
			String theclaimTypeID = input.nextLine();
			claim1.setClaimTypeID(theclaimTypeID);
			
			System.out.println("Enter a claim name :");
			String theclaimTypeName = input.nextLine();
			claim1.setClaimTypeName(theclaimTypeName);
			
			System.out.println("Enter the applicable to position :");
			String theappToPosition = input.nextLine();
			claim1.setAppToPosition(theappToPosition);
			
			System.out.println("Enter a limit :");
			float thelimit = input.nextFloat();
			claim1.setLimit(thelimit);
			
			stmt.executeUpdate("INSERT INTO claimtype VALUE('" + theclaimTypeID + "','" + theclaimTypeName + "','" + theappToPosition + "','" + thelimit + "')");
			System.out.println("Data entered successfully!");
			
			input.close();
			conn.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void EditClaim () {
		
		int eflag = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee?useTimezone=true&serverTimezone=UTC", "root", "");
			Statement stmt = conn.createStatement();


			do {

			System.out.print("Enter the claim ID: ");
			String id = input.nextLine();
			
			ResultSet rs = stmt.executeQuery("SELECT claimTypeID FROM claimtype");
			while(rs.next()) {
				String claimTypeID = rs.getString("claimTypeID");
				if(id.equals(claimTypeID)) {
					eflag = 1;

					System.out.println("Choose what you would like to edit");
					System.out.println("1. Claim Name");
					System.out.println("2. Applicable to Position");
					System.out.println("3. Limit");
					
					int choice = input.nextInt();
					
					if(choice==1) {
						
						System.out.println("Enter new claim name: ");
						String newClaimName = input.next();
						
						String sq1 = "UPDATE claimtype SET claimTypeName= '" + newClaimName + "' WHERE claimTypeID ='" +id+"'" ;
					
						stmt.executeUpdate(sq1);
						System.out.println("Data successfully editted!");
					break;
					}
					else if(choice==2) {
						
						System.out.println("Enter new applicable to postion: ");
						String newAppPos = input.next();
						
						String sq1 = "UPDATE claimtype SET applicableToPosition= '" + newAppPos + "' WHERE claimTypeID ='" +id+"'" ;
					
						stmt.executeUpdate(sq1);
						System.out.println("Data successfully editted!");
					break;
					}
					else if(choice==3) {
						
						System.out.println("Enter new limit: ");
						float newLimit = input.nextFloat();
						
						String sq1 = "UPDATE claimtype SET claimLimit= '" + newLimit + "' WHERE claimTypeID ='" +id+"'" ;
					
						stmt.executeUpdate(sq1);
						System.out.println("Data successfully editted!");
					break;
					}
				}
			}
			if(eflag == 0) {
				System.out.println("Claim ID not found. Please enter again.");
			}
			
			}while(eflag == 0);
				
			input.close();
			conn.close();
		}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
	}
	
	public static void DelClaim() {
		
		int eflag = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee?useTimezone=true&serverTimezone=UTC", "root", "");
			Statement stmt = conn.createStatement();


			do {

			System.out.print("Enter the claim ID: ");
			String id = input.nextLine();
			
			ResultSet rs = stmt.executeQuery("SELECT claimTypeID FROM claimtype");
			while(rs.next()) {
				String claimTypeID = rs.getString("claimTypeID");
				if(id.equals(claimTypeID)) {
					eflag = 1;
					String sq1 = "DELETE FROM claimtype WHERE claimTypeID ='" +id+"'" ;
					
					stmt.executeUpdate(sq1);
					System.out.println("Data deleted successfully!");
					break;
				}
			}
			
			if(eflag == 0) {
				System.out.println("Claim ID not found. Please enter again.");
			}
			
			}while(eflag == 0);
			
			input.close();
			conn.close();
		}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}

	}
}
