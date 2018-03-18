package CustomerInfo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue
	private int customerID;
	private int pin;
	private double oweAmount;
	private String phoneNumber;
	private String language;
	private String carrier;
	private String newCarrier;
	private String preCarrier;
	private String newExpireDate;
	private String expireDate;
	private String portDate;
	private String LTEdata;
	private String newPlan;
	private String plan;
	private String newsimcard;
	private String simcard;
	private String PUK;
	private String device;
	private String group;
	private String groupNumber;
	private String groupTitle;
	private String account;
	private String status;
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String states;
	private String zipcode;
	private String offerPrice;
	private String comment;

	public Customer() {

	}

	public Customer(int customerID, int pin, double oweAmount, String phoneNumber, String language, String carrier,
			String newCarrier, String preCarrier, String newExpireDate, String expireDate, String portDate,
			String lTEdata, String newPlan, String plan, String newsimcard, String simcard, String pUK, String device,
			String group, String groupNumber, String groupTitle, String account, String status, String firstName,
			String lastName, String address, String city, String states, String zipcode, String offerPrice,
			String comment) {
		super();
		this.customerID = customerID;
		this.pin = pin;
		this.oweAmount = oweAmount;
		this.phoneNumber = phoneNumber;
		this.language = language;
		this.carrier = carrier;
		this.newCarrier = newCarrier;
		this.preCarrier = preCarrier;
		this.newExpireDate = newExpireDate;
		this.expireDate = expireDate;
		this.portDate = portDate;
		LTEdata = lTEdata;
		this.newPlan = newPlan;
		this.plan = plan;
		this.newsimcard = newsimcard;
		this.simcard = simcard;
		PUK = pUK;
		this.device = device;
		this.group = group;
		this.groupNumber = groupNumber;
		this.groupTitle = groupTitle;
		this.account = account;
		this.status = status;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.states = states;
		this.zipcode = zipcode;
		this.offerPrice = offerPrice;
		this.comment = comment;
	}



	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
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
	public String getNewExpireDate() {
		return newExpireDate;
	}
	public void setNewExpireDate(String newExpireDate) {
		this.newExpireDate = newExpireDate;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
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

	public String getPortDate() {
		return portDate;
	}

	public void setPortDate(String portDate) {
		this.portDate = portDate;
	}

	public String getPUK() {
		return PUK;
	}

	public void setPUK(String pUK) {
		PUK = pUK;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
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
	
}
