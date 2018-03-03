package Main;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	@GeneratedValue
	private int employeeID;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private double salary;
	private String Hiredate;
	private String loginTime;
	private String logoutTime;
	private String hours;
	private String position;
	private String SSN;
	public Employee() {

	}
	public Employee(int employeeID, String firstName, String lastName, String phoneNumber, double salary,
			String hiredate, String loginTime, String logoutTime, String hours, String position, String sSN) {
		super();
		this.employeeID = employeeID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.salary = salary;
		Hiredate = hiredate;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.hours = hours;
		this.position = position;
		SSN = sSN;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getHiredate() {
		return Hiredate;
	}
	public void setHiredate(String hiredate) {
		Hiredate = hiredate;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLogoutTime() {
		return logoutTime;
	}
	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}
	public String getHours() {
		return hours;
	}
	public void setHours(String hours) {
		this.hours = hours;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}

}
