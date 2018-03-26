package CustomerInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;
import Employee.Employee;
@Entity
@Table(name = "CUSTOMER")
public class Customer {
	@Id
	@Column(name = "CUSTOMER_ID")
	private long customerID;
	private int pin;
	private double oweAmount;
	@Column(name = "ACTION", length = 32)
	private String action;
	@Column(unique = true, nullable = false, length = 16)
	private String phoneNumber;
	@Column(length = 32)
	private String language;
	@Column(length = 32)
	private String carrier;
	@Column(length = 32)
	private String newCarrier;
	@Column(length = 32)
	private String preCarrier;
	@Column(length = 32)
	private LocalDate newExpireDate;
	@Column(length = 32)
	private LocalDate expireDate;
	@Column(length = 32)
	private LocalDate portDate;
	@Column(length = 16)
	private String LTEdata;
	@Column(length = 16)
	private String newPlan;
	@Column(length = 16)
	private String plan;
	@Column(length = 32)
	private String newsimcard;
	@Column(length = 32)
	private String simcard;
	@Column(length = 16)
	private String PUK;
	@Column(length = 16)
	private String device;
	@ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "GROUP_ID_FK"))
	private CustomerGroup groupNumber;
	@Column(length = 16)
	private String groupTitle;
	@Column(length = 16)
	private String account;
	@Column(length = 16)
	private String status;
	@Column(length = 32)
	private String firstName;
	@Column(length = 32)
	private String lastName;
	@Column(length = 100)
	private String address;
	@Column(length = 32)
	private String city;
	@Column(length = 32)
	private String states;
	@Column(length = 16)
	private String zipcode;
	@Column(length = 32)
	private String offerPrice;
	private String comment;
	private int customerCredit;
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID", foreignKey = @ForeignKey(name = "GROUP_ID_FK"))
	private Employee employee;
	@Column(length = 32)
	private LocalDateTime lastUpdate;

	public Customer() {

	}
	
	public int getCustomerCredit() {
		return customerCredit;
	}


	public void setCustomerCredit(int customerCredit) {
		this.customerCredit = customerCredit;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}

	public long getCustomerID() {
		return customerID;
	}
	public void setCustomerID(long customerID) {
		this.customerID = customerID;
	}
	public String getCarrier() {
		return carrier;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public String getNewCarrier() {
		return newCarrier;
	}
	public void setNewCarrier(String newCarrier) {
		this.newCarrier = newCarrier;
	}
	public String getPreCarrier() {
		return preCarrier;
	}
	public void setPreCarrier(String preCarrier) {
		this.preCarrier = preCarrier;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLTEdata() {
		return LTEdata;
	}
	public void setLTEdata(String lTEdata) {
		LTEdata = lTEdata;
	}
	public String getPlan() {
		return plan;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public String getNewPlan() {
		return newPlan;
	}
	public void setNewPlan(String newPlan) {
		this.newPlan = newPlan;
	}
	public String getNewsimcard() {
		return newsimcard;
	}
	public void setNewsimcard(String newsimcard) {
		this.newsimcard = newsimcard;
	}
	public String getSimcard() {
		return simcard;
	}
	public void setSimcard(String simcard) {
		this.simcard = simcard;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public LocalDate getNewExpireDate() {
		return newExpireDate;
	}
	public void setNewExpireDate(LocalDate newExpireDate) {
		this.newExpireDate = newExpireDate;
	}
	public LocalDate getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}
	public String getGroupTitle() {
		return groupTitle;
	}
	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getOfferPrice() {
		return offerPrice;
	}
	public void setOfferPrice(String offerPrice) {
		this.offerPrice = offerPrice;
	}
	public String getComment() {
		return comment;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public LocalDate getPortDate() {
		return portDate;
	}

	public void setPortDate(LocalDate portDate) {
		this.portDate = portDate;
	}

	public String getPUK() {
		return PUK;
	}

	public void setPUK(String pUK) {
		PUK = pUK;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public double getOweAmount() {
		return oweAmount;
	}

	public void setOweAmount(double oweAmount) {
		this.oweAmount = oweAmount;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public CustomerGroup getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(CustomerGroup groupNumber) {
		this.groupNumber = groupNumber;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
