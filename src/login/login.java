package login;

import java.sql.*;
import java.util.*;
import AdministratorModule.Administrator;
import user.user;

public class login {

	public static void main(String[] args) {
		try {
			int pflag = 0, eflag = 0;
			
			Scanner input = new Scanner(System.in);
			
			Class.forName("com.mysql.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/employee?useTimezone=true&serverTimezone=UTC", "root", "");
			Statement stmt = conn.createStatement();
			
			System.out.println("\tLOGIN");
			System.out.println("--------------------");
			
			do {
				System.out.print("Enter your employee ID: ");
				
				String id = input.nextLine();
				
				ResultSet rs = stmt.executeQuery("SELECT empID, stat FROM empdetails");
				while(rs.next()) {
					String empID = rs.getString("empID");
					String loginStat = rs.getString("stat");
					if(id.equals(empID) && loginStat.equals("ACTIVE")) {
						eflag = 1;
						break;
					}
				}
				
				if(eflag == 0) {
					System.out.println("Employee profile doesn't exist or the employee profile is inactive. Please enter again.");
				}
				else {
					do {
						System.out.print("Enter your password: ");
						String pw = input.nextLine();

						rs = stmt.executeQuery("SELECT pass,empID FROM empdetails WHERE empID = '" + id + "'");
						
						while(rs.next()) {
							String pass = rs.getString("pass");
							if(pw.equals(pass)) {
								System.out.println("Password correct.");
								pflag = 0;	
								break;
							}
							else {
								System.out.println("Password incorrect. Please enter again.");
								pflag = 1;
							}
						}
												
					}while(pflag == 1);
					
					if(id.equals("ADMIN") || id.equals("EMP1101") || id.equals("EMP1102") || id.equals("EMP1103")) {
						Administrator admin = new Administrator();
						admin.Ad();
					}
					else {
						user us = new user();
						us.UserModule(id);;
					}
				}
			}while(eflag == 0);
			
			input.close();	
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}