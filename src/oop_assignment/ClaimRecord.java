package oop_assignment;

import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class ClaimRecord {
	String claimID, empID, claimTypeID, approverID, remark, decisionRemark, date;
	float amount;
	Scanner input = new Scanner(System.in);
	enum Status {
		PENDING, APPROVED, REJECTED, CANCELLED;
	}
	Status stat;
	
	public ClaimRecord() {
		
	}
	
	public void ApplyClaim(String id, float amt, String typeID) {
		/*create new claim object + details
		total amount of same type /> limit
		requires validation*/
		
		//select claim type based on position
		
		
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();
			
			empID = id;
			amount = amt;
			
					
			if(typeID.equals("CLM001")) {
				ResultSet rs = stmt.executeQuery("SELECT superiorID, claimLimit FROM empdetails INNER JOIN claimtype ON empdetails.empID = '" + empID + "' AND claimtype.claimTypeID = 'CLM001'");
				while(rs.next()) {
					float limit = rs.getFloat("claimLimit");
					String superID = rs.getString("superiorID");
					if(amount < (0.5 * limit)) {
						System.out.println("Your approver ID is " + superID);
					}
					else if(amount > (0.5 * limit) && amount < (0.8 * limit)) {
						ResultSet rs2 = stmt.executeQuery("SELECT superiorID FROM empdetails WHERE empID = '" + superID + "'");
						while(rs2.next()) {
							String super2ID = rs2.getString("superiorID");
							System.out.println("Your approver ID is " + super2ID);
							break;
						}
						break;
					}
					else if(amount > (0.8 * limit)) {
						ResultSet rs2 = stmt.executeQuery("SELECT superiorID FROM empdetails WHERE empID = '" + superID + "'");
						while(rs2.next()) {
							String super2ID = rs2.getString("superiorID");
							ResultSet rs3 = stmt.executeQuery("SELECT superiorID FROM empdetails WHERE empID = '" + super2ID + "'");
							while(rs3.next()) {
								String super3ID = rs3.getString("superiorID");
								System.out.println("Your approver ID is " + super3ID);
								break;
							}
							break;
						}
						break;
					}
					
				}

			}
			else {
				
			}
			
			conn.close();
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
	    
		switch(stat) {
			case PENDING:
				//print out the claim records + date
				break;
			default:
				break;
		}

	}
}
