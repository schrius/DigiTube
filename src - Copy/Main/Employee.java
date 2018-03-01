package Main;

public class Employee {
	int employeeID;
	String name;
	String phoneNumber;
	double salary;
	String Hiredate;
	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Employee(int employeeID, String name, String phoneNumber, double salary, String hiredate) {
		super();
		this.employeeID = employeeID;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.salary = salary;
		Hiredate = hiredate;
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

	public Employee() {
	}
	
	Employee(int employeeID, String name) {
		super();
		this.employeeID = employeeID;
		this.name = name;
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
}
