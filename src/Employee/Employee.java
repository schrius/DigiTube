package Employee;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import Main.TableEntry;
@Entity
public class Employee implements TableEntry{
	@Id
	@Column(name="EMPLOYEE_ID")
	private int employeeID;
	@Column(length = 32)
	private String firstName;
	@Column(length = 32)
	private String lastName;
	@Column(length = 16)
	private String phoneNumber;
	private double salary;
	private LocalDate Hiredate;
	private int hours;
	@Column(length = 32)
	private String position;
	@Column(length = 32)
	private String SSN;
	@Column(length = 32)
	private String passowrd;
	@Column(length = 32)
	private String address;
	@Column(length = 32)
	private String city;
	@Column(length = 32)
	private String state;
	@Column(length = 32)
	private String zipcode;
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
	public LocalDate getHiredate() {
		return Hiredate;
	}
	public void setHiredate(LocalDate hiredate) {
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
