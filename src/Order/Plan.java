package Order;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import Main.TableEntry;

@Entity
@Table(name="Plan")
public class Plan implements TableEntry{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PLAN_ID")
	private long planID;
	
	@Column(length = 16)
	private String phoneNumber;
	@Column(length = 32)
	private String carrier;
	@Column(length = 32)
	private String planType;
	@Column(length = 32)
	private String sim;
	@Column(length = 32)
	private String PUK;
	@Column(length = 16)
	private String account;
	private int pin;
	private LocalDate portdate;

	private double regularPrice;
	
	public Plan() {
		super();

	}
	
	public long getPlanID() {
		return planID;
	}

	public void setPlanID(long planID) {
		this.planID = planID;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	public String getSim() {
		return sim;
	}

	public void setSim(String sim) {
		this.sim = sim;
	}

	public String getPUK() {
		return PUK;
	}

	public void setPUK(String pUK) {
		this.PUK = pUK;
	}

	public double getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(double regularPrice) {
		this.regularPrice = regularPrice;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public LocalDate getPortdate() {
		return portdate;
	}

	public void setPortdate(LocalDate portdate) {
		this.portdate = portdate;
	}

}
