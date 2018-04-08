package Order;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bill {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "BILL_ID")
	long billID;
	@Column(length = 32)
	String billCarrier;
	@Column(length = 32)
	String billingAccount;
	double billAmount;
	double serviceFess;
	@Column(length = 32)
	String contactInfo;
	@Column(length = 32)
	String status;

	public Bill() {
		
	}

	public Bill(long billID, String billCarrier, String billingAccount, double billAmount,
			double serviceFess, String status) {
		super();
		this.billID = billID;
		this.billCarrier = billCarrier;
		this.billingAccount = billingAccount;
		this.billAmount = billAmount;
		this.serviceFess = serviceFess;
		this.status = status;
	}

	public long getBillID() {
		return billID;
	}

	public void setBillID(long billID) {
		this.billID = billID;
	}

	public String getBillCarrier() {
		return billCarrier;
	}

	public void setBillCarrier(String billCarrier) {
		this.billCarrier = billCarrier;
	}

	public String getBillingAccount() {
		return billingAccount;
	}

	public void setBillingAccount(String billingAccount) {
		this.billingAccount = billingAccount;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public double getServiceFess() {
		return serviceFess;
	}

	public void setServiceFess(double serviceFess) {
		this.serviceFess = serviceFess;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	

}
