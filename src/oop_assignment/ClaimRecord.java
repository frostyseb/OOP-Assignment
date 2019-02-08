package oop_assignment;
import java.util.*;

public class ClaimRecord {
	enum status {PENDING, APPROVED, REJECTED, CANCELLED}
	public ClaimRecord() {
		//constructor
	}
	
	public static void ApplyClaim() {
		//select claim type based on position
		//enter amount & remark
		//total amount of same type /> limit
	}
	
	public static void EditClaim() {
		//enter claimID to edit
		//claimTypeID, amount, remark (editable)
		//update Date
	}
	
	public static void ApproveClaim(float amount) {
		float limit = 0;
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
	
	public static void CancelClaim() {
		//enter claimID to cancel claim

	}
	
	public static void DisplayClaim() {
		//list only PENDING
		//show date

	}
	
	public static void main(String[] args) {
		String[] claimID = new String[20];
		String[] empID = new String[20];
		String[] claimTypeID = new String[20];
		String[] approverID = new String[20];
		String[] remark = new String[100];
		String[] decisionRemark = new String[100];
		String date;
		float amount;

		
	}

}
