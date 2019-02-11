package AdministratorModule;

import java.util.*;
import java.sql.*;
import oop_assignment.EmpStatus;
import oop_assignment.Employee;
import oop_assignment.UserRole;

public class AdminEmp {
	
	static Scanner input = new Scanner (System.in);
	static UserRole role;
	static UserRole newRole;
	static EmpStatus stat;
	static EmpStatus newStat;
	
	public void AddEmp () {
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee", "root", "");
			Statement stmt = conn.createStatement();

			Employee emp1 = new Employee ();
			
			System.out.println();
			System.out.println("Enter an employee ID :");
			String empID = input.nextLine();
			emp1.SetEmpID(empID);
			
			System.out.println("Enter a password :");
			String password = input.nextLine();
			emp1.SetPassword(password);
			
			System.out.println("Enter the name :");
			String name = input.nextLine();
			emp1.SetName(name);
			
			System.out.println("Enter the department :");
			String department = input.nextLine();
			emp1.SetDepartment(department);
			
			System.out.println("Enter the position :");
			String position = input.nextLine();
			emp1.SetPosition(position);
			
			System.out.println("Choose the user role :");
			System.out.println("1. ADMIN\n2. USER\n");
			int choice = input.nextInt();
			
			if(choice == 1) {
				role = UserRole.ADMIN;
			}
			else if(choice == 2) {
				role = UserRole.USER;
			}
				
			System.out.println("Choose the employee status :");
			System.out.println("1. ACTIVE\n2. INACTIVE\n");
			int choice2 = input.nextInt();
			
			if(choice2 == 1) {
				stat = 	EmpStatus.ACTIVE;
			}
			else if(choice2 == 2) {
				stat = EmpStatus.INACTIVE;
			}
			
			System.out.println("Enter the superior ID :");
			String superiorID = input.next();
			
			stmt.executeUpdate("INSERT INTO empdetails VALUE('" + empID + "','" + password + "','" + name + "','" + department +  "','"  + position + "','" + role + "','" + stat +  "','"  + superiorID + "')");
			System.out.println("Data entered successfully!");
	
			input.close();
			conn.close();

		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void EditEmp() {

		int eflag = 0;
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee?useTimezone=true&serverTimezone=UTC", "root", "");
			Statement stmt = conn.createStatement();


			do {

			System.out.print("Enter the Employee ID: ");
			String id = input.nextLine();
			
			ResultSet rs = stmt.executeQuery("SELECT empID FROM empdetails");
			while(rs.next()) {
				String empID = rs.getString("empID");
				if(id.equals(empID)) {
					eflag = 1;

					System.out.println("Choose what you would like to edit");
					System.out.println("1. Password");
					System.out.println("2. Name");
					System.out.println("3. Department");
					System.out.println("4. Position");
					System.out.println("5. User Role");
					System.out.println("6. Employee Status");
					System.out.println("7. Superior ID");
					
					int choice = input.nextInt();
					
					if(choice==1) {
						
						System.out.println("Enter new password: ");
						String newPass = input.next();
						
						String sq1 = "UPDATE empdetails SET pass= '" + newPass + "' WHERE empID ='" +id+"'" ;
					
						stmt.executeUpdate(sq1);
						System.out.println("Data successfully edited!");
					break;
					}
					else if(choice==2) {
						
						System.out.println("Enter new name: ");
						String newName = input.next();
						
						String sq1 = "UPDATE empdetails SET name= '" + newName + "' WHERE empID ='" +id+"'" ;
					
						stmt.executeUpdate(sq1);
						System.out.println("Data successfully edited!");
					break;
					}
					else if(choice==3) {
						
						System.out.println("Enter new department: ");
						String newDep = input.next();
						
						String sq1 = "UPDATE empdetails SET department= '" + newDep + "' WHERE empID ='" +id+"'" ;
					
						stmt.executeUpdate(sq1);
						System.out.println("Data successfully edited!");
					break;
					}
					else if(choice==4) {
						
						System.out.println("Enter new position: ");
						String newPos = input.next();
						
						String sq1 = "UPDATE empdetails SET position= '" + newPos + "' WHERE empID ='" +id+"'" ;
					
						stmt.executeUpdate(sq1);
						System.out.println("Data successfully edited!");
					break;
					}
					else if(choice==5) {
						
						System.out.println("Enter new user role: ");
						System.out.println("1. ADMIN\n2. USER\n");
						int ch = input.nextInt();
						
						if(ch == 1) {
							newRole = UserRole.ADMIN;
						}
						else if(ch == 2) {
							newRole = UserRole.USER;
						}
						
						String sq1 = "UPDATE empdetails SET userRole= '" + newRole + "' WHERE empID ='" +id+"'" ;
					
						stmt.executeUpdate(sq1);
						System.out.println("Data successfully edited!");
					break;
					}
					else if(choice==6) {
						
						System.out.println("Enter new employee status: ");
						System.out.println("1. ACTIVE\n2. INACTIVE\n");
						int ch = input.nextInt();
						
						if(ch == 1) {
							newStat = EmpStatus.ACTIVE;
						}
						else if(ch == 2) {
							newStat = EmpStatus.INACTIVE;
						}
						
						String sq1 = "UPDATE empdetails SET stat= '" + newStat + "' WHERE empID ='" +id+"'" ;
					
						stmt.executeUpdate(sq1);
						System.out.println("Data successfully edited!");
					break;
					}
					else if(choice==7) {
						
						System.out.println("Enter new superior ID: ");
						String newSup = input.next();
						
						String sq1 = "UPDATE empdetails SET superiorID	= '" + newSup + "' WHERE empID ='" +id+"'" ;
					
						stmt.executeUpdate(sq1);
						System.out.println("Data successfully edited!");
					break;
					}
				}
			}
			if(eflag == 0) {
				System.out.println("Claim ID not found. Please enter again.");
			}
			
			}while(eflag == 0);
				
			input.close();
			conn.close();
		}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
	
	}
	
	
	public void DelEmp () {
		
		
		int eflag = 0;
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee?useTimezone=true&serverTimezone=UTC", "root", "");
			Statement stmt = conn.createStatement();


			do {

			System.out.print("Enter the employee ID: ");
			String id = input.nextLine();
			
			ResultSet rs = stmt.executeQuery("SELECT empID FROM empdetails");
			while(rs.next()) {
				String claimTypeID = rs.getString("empID");
				if(id.equals(claimTypeID)) {
					eflag = 1;
					String sq1 = "DELETE FROM empdetails WHERE empID ='" +id+"'" ;
					
					stmt.executeUpdate(sq1);
					System.out.println("Data deleted successfully!");
					break;
				}
			}
			
			if(eflag == 0) {
				System.out.println("Employee ID not found. Please enter again.");
			}
			
			}while(eflag == 0);
			
			input.close();
			conn.close();
		}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}

	}

}
