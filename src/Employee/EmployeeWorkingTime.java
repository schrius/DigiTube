package Employee;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class EmployeeWorkingTime {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="time_id")
	private long workingTimeID;
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID", foreignKey = @ForeignKey(name = "EMPLOYEE_ID_FK"))
	private Employee employee;
	private LocalDateTime punchIn;
	private LocalDateTime punchOut;
	private double workingHour;
	public EmployeeWorkingTime() {
		super();
	}
	public EmployeeWorkingTime(long workingTimeID, Employee employee, LocalDateTime punchIn, LocalDateTime punchOut,
			double workingHour) {
		super();
		this.workingTimeID = workingTimeID;
		this.employee = employee;
		this.punchIn = punchIn;
		this.punchOut = punchOut;
		this.workingHour = workingHour;
	}
	public long getWorkingTimeID() {
		return workingTimeID;
	}
	public void setWorkingTimeID(long workingTimeID) {
		this.workingTimeID = workingTimeID;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public LocalDateTime getPunchIn() {
		return punchIn;
	}
	public void setPunchIn(LocalDateTime punchIn) {
		this.punchIn = punchIn;
	}
	public LocalDateTime getPunchOut() {
		return punchOut;
	}
	public void setPunchOut(LocalDateTime punchOut) {
		this.punchOut = punchOut;
	}
	public double getWorkingHour() {
		return workingHour;
	}
	public void setWorkingHour(double workingHour) {
		this.workingHour = workingHour;
	}
	
}
