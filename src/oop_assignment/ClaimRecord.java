package oop_assignment;
import java.util.*;
import java.text.*;
public class ClaimRecord {
	String claimID, empID, claimTypeID, approverID, remark, decisionRemark, date;
	float amount;
	Scanner input = new Scanner(System.in);
	enum Status {
		PENDING, APPROVED, REJECTED, CANCELLED;
	}
	Status sts;
	
	public ClaimRecord(Status sts) {
		//constructor
		this.sts = sts;
	}
	
	public void ApplyClaim(float limit) {
		/*create new claim object + details*/
		
		//select claim type based on position
		//enter amount & remark
		//total amount of same type /> limit
		//requires validation

		//Imagine A > B > C > D
		if(amount < (0.5 * limit)) {
			//C approver of D
		}
		else if(amount > (0.5 * limit) && amount < (0.8 * limit)) {
			//B approver of D
		}
		else if(amount > (0.8 * limit)) {
			//A approver of D
		}
		else {
			//error
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
		System.out.println("Any remarks? ");
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
