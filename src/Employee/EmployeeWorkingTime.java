package Employee;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import Main.TableEntry;
@Entity
public class EmployeeWorkingTime implements TableEntry{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long workingTimeID;
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID", nullable=false, foreignKey = @ForeignKey(name = "EMPLOYEE_FK"))
	private Employee employee;
	private LocalDateTime punchIn;
	private LocalDateTime punchOut;
	private long workingHour;
	
	public EmployeeWorkingTime() {

	}
	public EmployeeWorkingTime(long workingTimeID, Employee employee, LocalDateTime punchIn, LocalDateTime punchOut,
			long workingHour) {
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
	public long getWorkingHour() {
		return workingHour;
	}
	public void setWorkingHour(long workingHour) {
		this.workingHour = workingHour;
	}
	
}
