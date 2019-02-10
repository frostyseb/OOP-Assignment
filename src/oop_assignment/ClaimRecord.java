package oop_assignment;

import java.util.*;
import java.sql.*;
import java.text.*;

public class ClaimRecord {
	String claimID, empID, claimTypeID, approverID, remark, decisionRemark, date;
	float amount;
	Scanner input = new Scanner(System.in);
	enum Status {
		PENDING, APPROVED, REJECTED, CANCELLED;
	}
	Status sts;
	
	public ClaimRecord() {
		
	}
	
	public void ApplyClaim(String id) {
		/*create new claim object + details
		total amount of same type /> limit
		requires validation*/
		
		//select claim type based on position
		
		empID = id;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT claimTypeID, claimTypeName FROM empdetails INNER JOIN claimtype ON empID = '" + empID + "' AND empdetails.position = claimtype.applicableToPosition");
			while(rs.next()) {
				String claimID = rs.getString("claimTypeID");
				String claimName = rs.getString("claimTypeName");
				
				System.out.println("\nThe type of claims available:");
				System.out.print(claimID + "\t");
				System.out.println(claimName);
			}
			
			stmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void EditClaim() {
		//enter claimID to edit
		System.out.println("Claim search: ");
		System.out.print("Enter your Claim ID: ");
		claimID = input.nextLine();
		System.out.print("Enter your Employee ID: ");
		empID = input.nextLine();
				
		//editing part, unsure
		
		//claimTypeID, amount, remark (editable)		
		System.out.println("Enter your new Claim type: ");
		claimTypeID = input.nextLine();
		System.out.println("Enter your new amount: ");
		amount = input.nextFloat();
		System.out.println("Enter new remark: ");
		remark = input.nextLine();
		
		DisplayClaim();
		
	}
	
	public void ApproveClaim() {
		/*unsure, confused*/
		
		System.out.println("Claim search: ");
		System.out.print("Enter your Claim ID: ");
		claimID = input.nextLine();
		System.out.print("Enter your Employee ID: ");
		empID = input.nextLine();
		
		//check PENDING claims
		
		System.out.print("Enter A to approve and R to reject: ");
		String approve = input.next();
		char appr = approve.charAt(0);
		
		if(appr == 'A' || appr == 'a') {
			System.out.println("Claim status: " + Status.APPROVED);
		}
		else if(appr == 'R' || appr == 'a') {
			System.out.println("Claim status:" + Status.REJECTED);
		}
		
		System.out.println("Remarks: ");
		decisionRemark = input.nextLine();
	}
	
	public void CancelClaim() {
		//enter claimID to cancel claim
		DisplayClaim();
		
		System.out.println("Claim search: ");
		System.out.print("Enter your Claim ID: ");
		claimID = input.nextLine();
		System.out.print("Enter your Employee ID: ");
		empID = input.nextLine();
		
		//Search for claim
		
		System.out.println("Claim status: " + Status.CANCELLED);
	}
	
	public void DisplayClaim() {
		/*list only PENDING*/
	    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-DD");  
	    Date date = new Date();
		switch(sts) {
			case PENDING:
				//print out the claim records + date
				break;
			default:
				break;
		}

	}
}
