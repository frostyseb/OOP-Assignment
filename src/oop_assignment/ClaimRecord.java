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
	
	enum Status {
		PENDING, APPROVED, REJECTED, CANCELLED;
	}
	Status stat;
	
	Random rand = new Random();
	
	public ClaimRecord() {
		
	}
	
	public void ApplyClaim(String id) {
		/*create new claim object + details
		total amount of same type /> limit
		requires validation*/
		
		//select claim type based on position
		Scanner input = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
			
			int limitFlag = 0, claimTypeFlag = 0, cIDFlag = 0;
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
			input.nextLine();
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
			
			do {
				int count = rand.nextInt(99999) + 1;
				claimID = String.format("%05d", count);
				rs = stmt.executeQuery("SELECT claimID FROM claimrecord");
				while(rs.next()) {
					String cID = rs.getString("claimID");
					
					if(claimID.equals(cID)) {
						cIDFlag = 0;
						break;
					}
					else {
						cIDFlag = 1;
					}
				}
			}while(cIDFlag == 0);
			
			stat = Status.PENDING;
			approverID = superID;
			
			stmt.executeUpdate("INSERT INTO claimrecord(claimID, empID, claimTypeID, date, amount, remark, approverID, claimStatus) VALUES('" + claimID + "', '" + empID + "', '" + claimTypeID + "', '" + date + "', " + amount + ", '" + remark + "', '" + approverID + "', '" + stat + "')");
			
			
			conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		input.close();
	}
	
	public void EditClaim(String id) {
		Scanner input = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
			
			
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
			input.nextLine();
			String newState = input.nextLine();
			
			typeID = newTypeID;
			amt = newAmt;
			state = newState;
			
			claimID = clID;
			claimTypeID = typeID;
			amount = amt;
			remark = state;
			
			stmt.executeUpdate("UPDATE claimrecord SET claimTypeID = '" + claimTypeID + "', amount = " + amount + ", remark = '" + remark + "' WHERE empID = '" + empID + "' AND claimID = '" + claimID + "'");
			
			
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		input.close();
	}
	
	public void ApproveClaim() {
		Scanner input = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
			

			String typeID = "", state = "", st = "", clID = "", decRem;
			float amt = 0;
			
			
			System.out.print("Enter claim ID to search: ");
			clID = input.nextLine();
			
			claimID = clID;
			
			ResultSet rs = stmt.executeQuery("SELECT claimID, claimTypeID, amount, remark, claimStatus, decisionRemark FROM claimrecord WHERE claimID = '" + claimID + "' AND claimStatus = 'PENDING'");
			System.out.println("\nCLaim ID\tClaim Type ID\tAmount\tRemark\tStatus\t\tDecision Remark");
			while(rs.next()) {
				clID = rs.getString("claimID");
				typeID = rs.getString("claimTypeID");
				amt = rs.getFloat("amount");
				state = rs.getString("remark");
				st = rs.getString("claimStatus");
				decRem = rs.getString("decisionRemark");
				
				if(st.equals("APRROVED") || st.equals("CANCELLED") || st.equals("REJECTED")) {
					System.out.println(clID + "\t\t" + typeID + "\t\t" + amt + "\t" + state + "\t" + st + "\t" + decRem);
				}
				else {
					System.out.println(clID + "\t\t" + typeID + "\t\t" + amt + "\t" + state + "\t" + st + "\t\t" + decRem);
				}
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
			
			System.out.print("Enter decision remark: ");
			input.nextLine();
			String dRem = input.nextLine();
			
			decisionRemark = dRem;
			
			stmt.executeUpdate("UPDATE claimrecord SET claimStatus = '" + stat + "', decisionRemark = '" + decisionRemark + "' WHERE claimID = '" + claimID + "'"); 		
			
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		input.close();
	}
	
	public void CancelClaim(String id) {
		Scanner input = new Scanner(System.in);
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
			
			
			empID = id;
			
			DisplayClaim(empID);
			
			System.out.print("Enter the claim ID to be deleted: ");
			String clID = input.next();
			
			claimID = clID;

			stmt.executeUpdate("DELETE FROM claimrecord WHERE claimID = '" + claimID + "' AND empID = '" + empID + "'");
			
			
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		input.close();
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
			System.out.println("\nCLaim ID\tClaim Type ID\tAmount\tRemark\tStatus");
			while(rs.next()) {
				clID = rs.getString("claimID");
				typeID = rs.getString("claimTypeID");
				amt = rs.getFloat("amount");
				state = rs.getString("remark");
				st = rs.getString("claimStatus");
								
				System.out.println(clID + "\t\t" + typeID + "\t\t" + amt + "\t" + state + "\t" + st);
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
			String empID = "", typeID = "", date = "", state = "", st = "", clID = "", decRem = "";
			float amt = 0;
			
			empID = id;
			
			ResultSet rs = stmt.executeQuery("SELECT claimID, empID, claimTypeID, date, amount, remark, claimStatus, decisionRemark FROM claimrecord WHERE empID = '" + empID +"'");
			System.out.println("\nClaim ID\tEmp ID\t\tClaim Type ID\tDate\t\t\tAmount\tRemark\tStatus\t\tDecision Remark");
			while(rs.next()) {
				clID = rs.getString("claimID");
				empID = rs.getString("empID");
				typeID = rs.getString("claimTypeID");
				date = rs.getString("date");
				amt = rs.getFloat("amount");
				state = rs.getString("remark");
				st = rs.getString("claimStatus");
				decRem = rs.getString("decisionRemark");
				
				if(st.equals("APPROVED") || st.equals("CANCELLED") || st.equals("REJECTED")) {
					System.out.println(clID + "\t\t" + empID + "\t\t" + typeID + "\t\t" + date + "\t" + amt + "\t" + state + "\t" + st + "\t" + decRem);
				}
				else {
					System.out.println(clID + "\t\t" + empID + "\t\t" + typeID + "\t\t" + date + "\t" + amt + "\t" + state + "\t" + st + "\t\t" + decRem);
				}
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
			String empID = "", typeID = "", date = "", state = "", st = "", clID = "", decRem = "";
			float amt = 0;
			
			claimTypeID = id;
			
			ResultSet rs = stmt.executeQuery("SELECT claimID, empID, claimTypeID, date, amount, remark, claimStatus, decisionRemark FROM claimrecord WHERE claimTypeID = '" + claimTypeID +"'");
			System.out.println("\nClaim ID\tEmp ID\t\tClaim Type ID\tDate\t\t\tAmount\tRemark\tStatus\t\tDecision Remark");
			while(rs.next()) {
				clID = rs.getString("claimID");
				empID = rs.getString("empID");
				typeID = rs.getString("claimTypeID");
				date = rs.getString("date");
				amt = rs.getFloat("amount");
				state = rs.getString("remark");
				st = rs.getString("claimStatus");
				decRem = rs.getString("decisionRemark");
				
				if(st.equals("APPROVED") || st.equals("CANCELLED") || st.equals("REJECTED")) {
					System.out.println(clID + "\t\t" + empID + "\t\t" + typeID + "\t\t" + date + "\t" + amt + "\t" + state + "\t" + st + "\t" + decRem);
				}
				else {
					System.out.println(clID + "\t\t" + empID + "\t\t" + typeID + "\t\t" + date + "\t" + amt + "\t" + state + "\t" + st + "\t\t" + decRem);
				}
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
			String empID = "", typeID = "", date = "", state = "", st = "", clID = "", decRem = "";
			float amt = 0;
			
			claimID = id;
			
			ResultSet rs = stmt.executeQuery("SELECT claimID, empID, claimTypeID, date, amount, remark, claimStatus, decisionRemark FROM claimrecord WHERE claimID = '" + claimID +"'");
			System.out.println("\nClaim ID\tEmp ID\t\tClaim Type ID\tDate\t\t\tAmount\tRemark\tStatus\t\tDecision Remark");
			while(rs.next()) {
				clID = rs.getString("claimID");
				empID = rs.getString("empID");
				typeID = rs.getString("claimTypeID");
				date = rs.getString("date");
				amt = rs.getFloat("amount");
				state = rs.getString("remark");
				st = rs.getString("claimStatus");
				decRem = rs.getString("decisionRemark");
				
				if(st.equals("APPROVED") || st.equals("CANCELLED") || st.equals("REJECTED")) {
					System.out.println(clID + "\t\t" + empID + "\t\t" + typeID + "\t\t" + date + "\t" + amt + "\t" + state + "\t" + st + "\t" + decRem);
				}
				else {
					System.out.println(clID + "\t\t" + empID + "\t\t" + typeID + "\t\t" + date + "\t" + amt + "\t" + state + "\t" + st + "\t\t" + decRem);
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}