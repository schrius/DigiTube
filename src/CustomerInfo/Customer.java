package CustomerInfo;
/*
 * Customer persisten objects
 */
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;
import Employee.Employee;
import Main.TableEntry;
import Order.Plan;
@Entity
@Table(name = "Customer")
public class Customer implements TableEntry {
	@Id
	@Column(name = "CUSTOMER_ID")
	private long customerID;
	private double oweAmount;
	@Column(length = 32)
	private String action;
	@Column(unique = true, nullable = false, length = 16)
	private String phoneNumber;
	@Column(length = 32)
	private String language;
	@Column(length = 16)
	private String LTEdata;

	@ManyToOne
    @JoinColumn(name = "group_id", foreignKey = @ForeignKey(name = "Customer_GROUPID__FK"))
	private CustomerGroup groupNumber;
	@Column(length = 16)
	private String groupTitle;
	@Column(length = 16)
	private String status;
	@Column(length = 32)
	private LocalDate expireDate;

	@OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "NewPlan", foreignKey = @ForeignKey(name = "NewPlan_ID_FK"))
	private Plan newPlan;
	@OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "currentPlan", foreignKey = @ForeignKey(name = "CurrentPlan_ID_FK"))
	private Plan currentPlan;
	@OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "prePlan", foreignKey = @ForeignKey(name = "PrePlan_ID_FK"))
	private Plan prePlan;
	
	@Column(length = 16)
	private String device;
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

	public long getCustomerID() {
		return customerID;
	}

	public void setCustomerID(long customerID) {
		this.customerID = customerID;
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

	public Plan getNewPlan() {
		return newPlan;
	}

	public void setNewPlan(Plan newPlan) {
		this.newPlan = newPlan;
	}

	public Plan getCurrentPlan() {
		return currentPlan;
	}

	public void setCurrentPlan(Plan currentlan) {
		this.currentPlan = currentlan;
	}

	public Plan getPrePlan() {
		return prePlan;
	}

	public void setPrePlan(Plan prePlan) {
		this.prePlan = prePlan;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public CustomerGroup getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(CustomerGroup groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getGroupTitle() {
		return groupTitle;
	}

	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
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

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getCustomerCredit() {
		return customerCredit;
	}

	public void setCustomerCredit(int customerCredit) {
		this.customerCredit = customerCredit;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
}
