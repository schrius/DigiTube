package Employee;

import java.time.LocalDateTime;

public class EmployeeWorkingTime {
	private long workingTimeID;
	private long employeeID;
	private LocalDateTime punchIn;
	private LocalDateTime punchOut;
	private double workingHour;
	public EmployeeWorkingTime() {
		super();
	}
	public EmployeeWorkingTime(long workingTimeID, long employeeID, LocalDateTime punchIn, LocalDateTime punchOut,
			double workingHour) {
		super();
		this.workingTimeID = workingTimeID;
		this.employeeID = employeeID;
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
	public long getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(long employeeID) {
		this.employeeID = employeeID;
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
