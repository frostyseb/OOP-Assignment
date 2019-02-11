package oop_assignment;

import oop_assignment.UserRole;
import oop_assignment.EmpStatus;

public class Employee {

	String empID, password, name, department, position, superiorID;
	UserRole userRole;
	EmpStatus empStatus;
	
	public Employee() {
		
	}
	
	public void SetEmpID(String id) {
		empID = id;
	}
	
	public String GetEmpID() {
		return empID;
	}
	
	public void SetPassword(String pass) {
		password = pass;
	}
	
	public String GetPassword() {
		return password;
	}
	
	public void SetName(String nm) {
		name = nm;
	}
	
	public String GetName() {
		return name;
	}
	
	public void SetDepartment(String dep) {
		department = dep;
	}
	
	public String GetDepartment() {
		return department;
	}
	
	public void SetPosition(String pos){
		position = pos;
	}
	
	public String GetPosition() {
		return position;
	}
	
	public void SetUserRole(UserRole ur) {
		userRole = ur;
	}
	
	public UserRole GetUserRole() {
		return userRole;
	}
	
	public void SetStatus(EmpStatus stat) {
		empStatus = stat;
	}
	
	public EmpStatus GetStatus() {
		return empStatus;
	}
	
	public void SetSuperiorID(String superID) {
		superiorID = superID;
	}
	
	public String GetSuperiorID() {
		return superiorID;
	}
	
}


