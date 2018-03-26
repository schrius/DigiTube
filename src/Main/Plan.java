package Main;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Plan")
public class Plan {
	@Id
	@GeneratedValue
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

	private double regularPrice;
	
	public Plan() {
		super();

	}

	public Plan(long planID, String phoneNumber, String carrier, String planType, String sim, String pUK,
			double regularPrice) {
		super();
		this.planID = planID;
		this.phoneNumber = phoneNumber;
		this.carrier = carrier;
		this.planType = planType;
		this.sim = sim;
		PUK = pUK;
		this.regularPrice = regularPrice;
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
		PUK = pUK;
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
	
}
