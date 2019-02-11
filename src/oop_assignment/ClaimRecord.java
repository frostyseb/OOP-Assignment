package oop_assignment;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;

public class ClaimRecord {
	static String claimID;
	static String empID;
	static String claimTypeID;
	String date;
	String approverID;
	String remark;
	String decisionRemark;
	float amount;
	Scanner input = new Scanner(System.in);
	enum Status {
		PENDING, APPROVED, REJECTED, CANCELLED;
	}
	Status stat;
	
	int count = 0;
	
	public ClaimRecord() {
		
	}
	
	public void ApplyClaim(String id) {
		/*create new claim object + details
		total amount of same type /> limit
		requires validation*/
		
		//select claim type based on position
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
			
			int limitFlag = 0, claimTypeFlag = 0;
			float limit = 0, amt = 0;
			String typeID;
			Date claimDate = new Date();
			SimpleDateFormat simpleDF = new SimpleDateFormat("EEE MMM dd, yyyy");
			
			empID = id;
			
			
			System.out.println("\n\nThe type of claims available:\n");
			System.out.println("Claim Type ID\tClaim Type\tDate");
			
			ResultSet rs = stmt.executeQuery("SELECT claimTypeID, claimTypeName FROM empdetails INNER JOIN claimtype ON empID = '" + empID + "' AND empdetails.position = claimtype.applicableToPosition");
			while(rs.next()) {
				String claimID = rs.getString("claimTypeID");
				String claimName = rs.getString("claimTypeName");
				System.out.print(claimID + "\t\t");
				System.out.print(claimName + "\t");
		
				System.out.println(simpleDF.format(claimDate));
				
			}
			
			date = simpleDF.format(claimDate);
			
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
			
			claimTypeID = typeID;
			amount = amt;
			
			System.out.print("Enter your remark: ");
			Scanner input = new Scanner(System.in);
			String state = input.nextLine();
			
			remark = state;
			
			String superID = "";
			
			if(typeID.equals("CLM001")) {
				rs = stmt.executeQuery("SELECT superiorID, claimLimit FROM empdetails INNER JOIN claimtype ON empdetails.empID = '" + empID + "' AND claimtype.claimTypeID = 'CLM001'");
				while(rs.next()) {
					limit = rs.getFloat("claimLimit");
					superID = rs.getString("superiorID");
					if(amount < (0.5 * limit)) {
						System.out.println("Your approver ID is " + superID);
						
					}
					else if(amount > (0.5 * limit) && amount < (0.8 * limit)) {
						ResultSet rs2 = stmt.executeQuery("SELECT superiorID FROM empdetails WHERE empID = '" + superID + "'");
						while(rs2.next()) {
							superID = rs2.getString("superiorID");
							System.out.println("Your approver ID is " + superID);
							break;
						}
						break;
					}
					else if(amount > (0.8 * limit)) {
						ResultSet rs2 = stmt.executeQuery("SELECT superiorID FROM empdetails WHERE empID = '" + superID + "'");
						while(rs2.next()) {
							superID = rs2.getString("superiorID");
							ResultSet rs3 = stmt.executeQuery("SELECT superiorID FROM empdetails WHERE empID = '" + superID + "'");
							while(rs3.next()) {
								superID = rs3.getString("superiorID");
								System.out.println("Your approver ID is " + superID);
								break;
							}
							break;
						}
						break;
					}
					
				}

			}
			else if(typeID.equals("CLM002")) {
				ResultSet rs4 = stmt.executeQuery("SELECT superiorID, claimLimit FROM empdetails INNER JOIN claimtype ON empdetails.empID = '" + empID + "' AND claimtype.claimTypeID = 'CLM002'");
				while(rs4.next()) {
					limit = rs4.getFloat("claimLimit");
					superID = rs4.getString("superiorID");
					if(amount < (0.5 * limit)) {
						System.out.println("Your approver ID is " + superID);
						
					}
					else if(amount > (0.5 * limit) && amount < (0.8 * limit)) {
						ResultSet rs2 = stmt.executeQuery("SELECT superiorID FROM empdetails WHERE empID = '" + superID + "'");
						while(rs2.next()) {
							superID = rs2.getString("superiorID");
							System.out.println("Your approver ID is " + superID);
							break;
						}
						break;
					}
					else if(amount > (0.8 * limit)) {
						ResultSet rs2 = stmt.executeQuery("SELECT superiorID FROM empdetails WHERE empID = '" + superID + "'");
						while(rs2.next()) {
							superID = rs2.getString("superiorID");
							ResultSet rs3 = stmt.executeQuery("SELECT superiorID FROM empdetails WHERE empID = '" + superID + "'");
							while(rs3.next()) {
								superID = rs3.getString("superiorID");
								System.out.println("Your approver ID is " + superID);
								break;
							}
							break;
						}
						break;
					}
				}
				
			}
			
			count++;
			claimID = String.format("%05d", count);
			stat = Status.PENDING;
			approverID = superID;
			
			stmt.executeUpdate("INSERT INTO claimrecord(claimID, empID, claimTypeID, date, amount, remark, approverID, claimStatus) VALUES('" + claimID + "', '" + empID + "', '" + claimTypeID + "', '" + date + "', " + amount + ", '" + remark + "', '" + approverID + "', '" + stat + "')");
			
			input.close();
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void EditClaim(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
			
			Scanner input = new Scanner(System.in);
			empID = id;
			String typeID = "", state = "", clID = "";
			float amt = 0;
		
			DisplayClaim(empID);
			
			System.out.print("Enter the claim ID to be edited: ");
			clID = input.next();
			
			System.out.print("Enter new Claim Type ID: ");
			String newTypeID = input.next();
			
			System.out.print("\nEnter new amount: ");
			float newAmt = input.nextFloat();
			
			
			System.out.print("\nEnter new remark: ");
			String newState = input.nextLine();
			
			typeID = newTypeID;
			amt = newAmt;
			state = newState;
			
			claimID = clID;
			claimTypeID = typeID;
			amount = amt;
			remark = state;
			
			stmt.executeUpdate("UPDATE claimrecord SET claimTypeID = '" + claimTypeID + "', amount = " + amount + ", remark = '" + remark + "' WHERE empID = '" + empID + "' AND claimID = '" + claimID + "'");
			
			input.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void ApproveClaim(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
			
			empID = id;
			String typeID = "", state = "", st = "", clID = "";
			float amt = 0;
			
			Scanner input = new Scanner(System.in);
			System.out.print("Enter claim ID to search: ");
			clID = input.nextLine();
		
			ResultSet rs = stmt.executeQuery("SELECT claimID, claimTypeID, amount, remark, claimStatus FROM claimrecord WHERE claimID = '" + claimID + "' AND claimStatus = 'PENDING'");
			System.out.println("\nCLaim ID\tClaim Type ID\t\tAmount\tRemark\tStatus");
			while(rs.next()) {
				clID = rs.getString("claimID");
				typeID = rs.getString("claimTypeID");
				amt = rs.getFloat("amount");
				state = rs.getString("remark");
				st = rs.getString("claimStatus");
								
				System.out.println(clID + "\t" + typeID + "\t\t" + amt + "\t" + state + "\t" + st);
			}
			
			System.out.println("Choose from the below: ");
			System.out.println("1. PENDING\n2. APPROVED\n3. REJECTED\n4. CANCELLED");
			int choice = input.nextInt();
			
			if(choice == 1) {
				stat = Status.PENDING;
			}
			else if(choice == 2) {
				stat = Status.APPROVED;
			}
			else if(choice == 3) {
				stat = Status.REJECTED;
			}
			else {
				stat = Status.CANCELLED;
			}
			
			claimID = clID;
			
			stmt.executeUpdate("UPDATE claimrecord SET claimStatus = '" + stat + "' WHERE claimID = '" + claimID + "'"); 
			
			input.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void CancelClaim(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
			
			Scanner input = new Scanner(System.in);
			empID = id;
			
			DisplayClaim(empID);
			
			System.out.print("Enter the claim ID to be deleted: ");
			String clID = input.next();
			
			claimID = clID;

			stmt.executeUpdate("DELETE FROM claimrecord WHERE claimID = '" + claimID + "' AND empID = '" + empID + "'");
			
			input.close();
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void DisplayClaim(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
				Statement stmt = conn.createStatement();
			String typeID = "", state = "", st = "", clID = "";
			float amt = 0;
			
			empID = id;
			
			ResultSet rs = stmt.executeQuery("SELECT claimID, claimTypeID, amount, remark, claimStatus FROM claimrecord WHERE empID = '" + empID + "' AND claimStatus = 'PENDING'");
			System.out.println("\nCLaim ID\tClaim Type ID\t\tAmount\tRemark\tStatus");
			while(rs.next()) {
				clID = rs.getString("claimID");
				typeID = rs.getString("claimTypeID");
				amt = rs.getFloat("amount");
				state = rs.getString("remark");
				st = rs.getString("claimStatus");
								
				System.out.println(clID + "\t" + typeID + "\t\t" + amt + "\t" + state + "\t" + st);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void DisClm(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee?useTimezone=true&serverTimezone=UTC", "root", "");
				Statement stmt = conn.createStatement();
			String empID = "", typeID = "", date = "", state = "", st = "", clID = "";
			float amt = 0;
			
			empID = id;
			
			ResultSet rs = stmt.executeQuery("SELECT claimID, empID, claimTypeID, date, amount, remark, claimStatus FROM claimrecord WHERE empID = '" + empID +"'");
			System.out.println("\nClaim ID\tClaim Type ID\t\tEmp ID\tDate\tAmount\tRemark\tStatus");
			while(rs.next()) {
				clID = rs.getString("claimID");
				empID = rs.getString("empID");
				typeID = rs.getString("claimTypeID");
				date = rs.getString("date");
				amt = rs.getFloat("amount");
				state = rs.getString("remark");
				st = rs.getString("claimStatus");
								
				System.out.println(clID + "\t" + empID + "\t\t" + typeID + "\t" + date + "\t" + amt + "\t" + state + "\t" + st);

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void DisClm2(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee?useTimezone=true&serverTimezone=UTC", "root", "");
				Statement stmt = conn.createStatement();
			String empID = "", typeID = "", date = "", state = "", st = "", clID = "";
			float amt = 0;
			
			claimTypeID = id;
			
			ResultSet rs = stmt.executeQuery("SELECT claimID, empID, claimTypeID, date, amount, remark, claimStatus FROM claimrecord WHERE claimTypeID = '" + claimTypeID +"'");
			System.out.println("\nClaim ID\tClaim Type ID\t\tEmp ID\tDate\tAmount\tRemark\tStatus");
			while(rs.next()) {
				clID = rs.getString("claimID");
				empID = rs.getString("empID");
				typeID = rs.getString("claimTypeID");
				date = rs.getString("date");
				amt = rs.getFloat("amount");
				state = rs.getString("remark");
				st = rs.getString("claimStatus");
								
				System.out.println(clID + "\t" + empID + "\t\t" + typeID + "\t" + date + "\t" + amt + "\t" + state + "\t" + st);

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void DisClm3(String id) {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee?useTimezone=true&serverTimezone=UTC", "root", "");
				Statement stmt = conn.createStatement();
			String empID = "", typeID = "", date = "", state = "", st = "", clID = "";
			float amt = 0;
			
			claimID = id;
			
			ResultSet rs = stmt.executeQuery("SELECT claimID, empID, claimTypeID, date, amount, remark, claimStatus FROM claimrecord WHERE claimID = '" + claimID +"'");
			System.out.println("\nClaim ID\tClaim Type ID\t\tEmp ID\tDate\tAmount\tRemark\tStatus");
			while(rs.next()) {
				clID = rs.getString("claimID");
				empID = rs.getString("empID");
				typeID = rs.getString("claimTypeID");
				date = rs.getString("date");
				amt = rs.getFloat("amount");
				state = rs.getString("remark");
				st = rs.getString("claimStatus");
								
				System.out.println(clID + "\t" + empID + "\t\t" + typeID + "\t" + date + "\t" + amt + "\t" + state + "\t" + st);

			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}