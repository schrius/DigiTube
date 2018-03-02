package Main;

public class Employee {
	int employeeID;
	String name;
	String phoneNumber;
	double salary;
	String Hiredate;
	String loginTime;
	String logoutTime;
	String hours;
	String position;
	public Employee() {

	}
	public Employee(int employeeID, String name, String phoneNumber, double salary, String hiredate, String loginTime,
			String logoutTime, String hours, String position) {
		super();
		this.employeeID = employeeID;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.salary = salary;
		Hiredate = hiredate;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.hours = hours;
		this.position = position;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
}
