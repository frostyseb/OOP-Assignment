package AdministratorModule;

import java.util.*;
import AdministratorModule.AdminEmp;
import AdministratorModule.AdminClaim;

public class Administrator {
	
	static Scanner input = new Scanner (System.in);
	
	public static void Ad () {
		
		System.out.println("   ADMINISTRATOR");
		System.out.println("--------------------");
		System.out.println();
		System.out.println("Choose the actions you want to do");
		System.out.println("1. Employee Records");
		System.out.println("2. Claim Types Records");
		System.out.println("3. Claim Records Report");
		
		int choice = input.nextInt();
		
		if (choice == 1) {
			Emp();
		}
		else if (choice == 2) {
			Claim();
		}
		else if (choice == 3) {
			System.out.println("Search records by ");
			System.out.println("1. Employee ID");
			System.out.println("2. Claim Type ID");
			System.out.println("3. Claim ID");
			
			int choice3 = input.nextInt();
				if(choice3==1) {
					System.out.println("Enter employee ID:");
					String id = input.next();
					
					oop_assignment.ClaimRecord.DisClm(id);
				}
				else if(choice3==2) {
					System.out.println("Enter Claim Type ID:");
					String clm = input.next();
					
					oop_assignment.ClaimRecord.DisClm2(clm);
				}
				else if (choice3==3) {
					System.out.println("Enter Claim  ID:");
					String clmID = input.next();
					
					oop_assignment.ClaimRecord.DisClm3(clmID);
				}
			
		}
		else {
			System.out.println("Invalid input! Try Again.");
			System.out.println("--------------------");
			Ad();
		}
		
		
	}
	
	public static void Emp () {
			
			System.out.println("--------------------");
			System.out.println("Choose the actions you want to do");
			System.out.println("1. Add employee records");
			System.out.println("2. Edit employee records");
			System.out.println("3. Delete emplyee records");
			
			int choice2 = input.nextInt();
			
			switch (choice2) {
			case 1 : 
				AdminEmp.AddEmp();
				break;
			case 2 :
				AdminEmp.EditEmp();
				break;
			case 3:
				AdminEmp.DelEmp();
				break;
			}
			
	}
	
	public static void Claim () {

			System.out.println("--------------------");
			System.out.println("Choose the actions you want to do");
			System.out.println("1. Add claim type records");
			System.out.println("2. Edit claim type records");
			System.out.println("3. Delete claim type records");
			
			int choice2 = input.nextInt();
			
			switch (choice2) {
			case 1 : 
				AdminClaim.AddClaim();
				break;
			case 2 :
				AdminClaim.EditClaim();
				break;
			case 3 :
				AdminClaim.DelClaim();
				break;
			}
	}
	
}
