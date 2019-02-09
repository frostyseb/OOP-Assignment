package oop_assignment;
import java.util.*;
import java.text.*;
public class ClaimRecord {
	enum Status {
		PENDING, APPROVED, REJECTED, CANCELLED;
	}
	Status stat;
	public ClaimRecord(Status stat) {
		//constructor
		this.stat = stat;
	}
	
	public void ApplyClaim(float amount, float limit) {
		//new claim object + details
		//select claim type based on position
		//enter amount & remark
		//total amount of same type /> limit
		String approverID;
		System.out.println("");

		//A > B > C > D
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
		//claimTypeID, amount, remark (editable)
		//update Date
		String claimID, empID, claimTypeID, remark;
		float amount;
		Scanner input = new Scanner(System.in);
		
		System.out.println("To edit Claim;");
		System.out.print("Enter your Claim ID: ");
		claimID = input.nextLine();
		System.out.print("Enter your Employee ID: ");
		empID = input.nextLine();
		
		System.out.println("You may edit your Claim type, amount & remark.");
		
		//editing part, unsure
		
		System.out.println("Enter your new Claim type: ");
		claimTypeID = input.nextLine();
		System.out.println("Enter your new amount: ");
		amount = input.nextFloat();
		System.out.println("Any remarks? ");
		remark = input.nextLine();
		
		DisplayClaim();
		
	}
	
	public void ApproveClaim() {

	}
	
	public void CancelClaim() {
		//enter claimID to cancel claim
		DisplayClaim();
		String claimID;
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter your Claim ID to cancel the claim :");
		claimID = input.nextLine();
	}
	
	public void DisplayClaim() {
		//list only PENDING
		//show date
	    SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-DD");  
	    Date date = new Date();
		switch(stat) {
			case PENDING:
				//print out the claim records + date
				break;
			default:
				break;
		}

	}
	
	public void main(String[] args) {
		String claimID;
		String empID;
		String claimTypeID;
		String approverID;
		String remark;
		String decisionRemark;
		String date;
		float amount;

		
	}

}
