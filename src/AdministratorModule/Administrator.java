package AdministratorModule;

import java.util.*;
import AdministratorModule.AdminEmp;
import AdministratorModule.AdminClaim;
import oop_assignment.ClaimRecord.*;

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
			DisplayClaim();
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
