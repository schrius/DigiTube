package Employee;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Employee {
	@Id
	@SequenceGenerator(name="employee_seq", sequenceName="employee_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
	@Column(name="EMPLOYEE_ID")
	private int employeeID;
	@Column(length = 32)
	private String firstName;
	@Column(length = 32)
	private String lastName;
	@Column(length = 16)
	private String phoneNumber;
	private double salary;
	@Column(length = 32)
	private String Hiredate;
	private int hours;
	@Column(length = 32)
	private String position;
	@Column(length = 32)
	private String SSN;
	@Column(length = 32)
	private String passowrd;
	public Employee() {

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
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
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

	public String getPassowrd() {
		return passowrd;
	}

	public void setPassowrd(String passowrd) {
		this.passowrd = passowrd;
	}

}