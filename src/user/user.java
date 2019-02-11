package user;

import java.util.*;

import oop_assignment.ClaimRecord;

public class user {

	public void UserModule(String empID) {
		int choice = 0;
		
		Scanner input = new Scanner(System.in);
		
		ClaimRecord record = new ClaimRecord();
		
		System.out.println("\tUser\n--------------------");
		
		do {
			System.out.println("Choose one from the below:");
			System.out.println("1. Apply claim.\n2. Edit claim\n3. Cancel claim");
			choice = input.nextInt();
			
			if(choice == 1) {
				record.ApplyClaim(empID);
			}
			else if(choice == 2) {
				record.EditClaim(empID);
			}
			else if(choice == 3) {
				record.CancelClaim(empID);
			}
		}while(choice < 0 || choice > 3);
		
		
		input.close();
			
		
	}

}
