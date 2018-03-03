package Main;

public class PortList {

	String simcard;
	String PUK;
	String phoneNumber;
	String plan;
	String carrier;
	String portDate;
	String comment;
	String status;
	String account;
	String pin;
	public PortList() {
		super();
	}
	public PortList(String simcard, String pUK, String phoneNumber, String plan, String carrier, String portDate,
			String comment, String status, String account, String pin) {
		super();
		this.simcard = simcard;
		PUK = pUK;
		this.phoneNumber = phoneNumber;
		this.plan = plan;
		this.carrier = carrier;
		this.portDate = portDate;
		this.comment = comment;
		this.status = status;
		this.account = account;
		this.pin = pin;
	}
	public String getSimcard() {
		return simcard;
	}
	public void setSimcard(String simcard) {
		this.simcard = simcard;
	}
	public String getPUK() {
		return PUK;
	}
	public void setPUK(String pUK) {
		PUK = pUK;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getPortDate() {
		return portDate;
	}
	public void setPortDate(String portDate) {
		this.portDate = portDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	
}
