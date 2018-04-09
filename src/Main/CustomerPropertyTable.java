package Main;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerPropertyTable implements TableEntry{

	private StringProperty phoneNumber;
	private DoubleProperty oweAmount;
	private StringProperty action;
	private StringProperty groupNumber;
	private StringProperty groupTitle;
	private StringProperty status;
	private StringProperty expireDate;

	private StringProperty newCarrier;
	private StringProperty newPlan;
	private StringProperty sim;
	private StringProperty PUK;
	
	private StringProperty currentCarrier;
	private StringProperty currentPlan;
	private StringProperty account;
	private IntegerProperty pin;

	private StringProperty preCarrier;
	private StringProperty offerPrice;
	private IntegerProperty customerCredit;
	private StringProperty comment;
	
	
	
	public CustomerPropertyTable() {
		this.phoneNumber = new SimpleStringProperty();
		this.oweAmount = new SimpleDoubleProperty();
		this.action = new SimpleStringProperty();
		this.groupNumber = new SimpleStringProperty();
		this.groupTitle = new SimpleStringProperty();
		this.status = new SimpleStringProperty();
		this.expireDate = new SimpleStringProperty();
		this.newCarrier = new SimpleStringProperty();
		this.newPlan = new SimpleStringProperty();
		this.sim = new SimpleStringProperty();
		PUK =  new SimpleStringProperty();
		this.currentCarrier = new SimpleStringProperty();
		this.currentPlan = new SimpleStringProperty();
		this.account = new SimpleStringProperty();
		this.pin = new SimpleIntegerProperty();;
		this.preCarrier =  new SimpleStringProperty();
		this.offerPrice =  new SimpleStringProperty();
		this.customerCredit =  new SimpleIntegerProperty();
		this.comment =  new SimpleStringProperty();
	}


	public CustomerPropertyTable(double oweAmount, String action, String phoneNumber,
			String groupNumber, String groupTitle, String status, String expireDate,
			String newCarrier, String newPlan, String sim, String pUK,
			String currentCarrier, String currentPlan, String account, Integer pin,
			String preCarrier, String offerPrice, Integer customerCredit,
			String comment) {
		this.oweAmount.set(oweAmount);
		this.action.set(action);
		this.phoneNumber.set(phoneNumber);
		this.groupNumber.set(groupNumber);
		this.groupTitle.set(groupTitle);
		this.status.set(status);
		this.expireDate.set(expireDate);
		this.currentCarrier.set(currentCarrier);
		this.currentPlan.set(currentPlan);
		this.account.set(account);
		this.pin.set(pin);
		this.preCarrier.set(preCarrier);
		this.offerPrice.set(offerPrice);
		this.customerCredit.set(customerCredit);
		this.comment.set(comment);
	}
	
	public CustomerPropertyTable(DoubleProperty oweAmount, StringProperty action, StringProperty phoneNumber,
			StringProperty groupNumber, StringProperty groupTitle, StringProperty status, StringProperty expireDate,
			StringProperty newCarrier, StringProperty newPlan, StringProperty sim, StringProperty pUK,
			StringProperty currentCarrier, StringProperty currentPlan, StringProperty account, IntegerProperty pin,
			StringProperty preCarrier, StringProperty offerPrice, IntegerProperty customerCredit,
			StringProperty comment) {
		super();
		this.oweAmount = oweAmount;
		this.action = action;
		this.phoneNumber = phoneNumber;
		this.groupNumber = groupNumber;
		this.groupTitle = groupTitle;
		this.status = status;
		this.expireDate = expireDate;
		this.newCarrier = newCarrier;
		this.newPlan = newPlan;
		this.sim = sim;
		PUK = pUK;
		this.currentCarrier = currentCarrier;
		this.currentPlan = currentPlan;
		this.account = account;
		this.pin = pin;
		this.preCarrier = preCarrier;
		this.offerPrice = offerPrice;
		this.customerCredit = customerCredit;
		this.comment = comment;
	}
	public double getOweAmount() {
		return oweAmount.get();
	}
	public void setOweAmount(double oweAmount) {
		this.oweAmount.setValue(11);
	}
	public String getAction() {
		return action.get();
	}
	public void setAction(String action) {
		this.action.set(action);
	}
	public String getPhoneNumber() {
		return phoneNumber.get();
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber.set(phoneNumber);
	}
	public String getGroupNumber() {
		return groupNumber.get();
	}
	public void setGroupNumber(String groupNumber) {
		this.groupNumber.set(groupNumber);
	}
	public String getGroupTitle() {
		return groupTitle.get();
	}
	public void setGroupTitle(String groupTitle) {
		this.groupTitle.set(groupTitle);
	}
	public String getStatus() {
		return status.get();
	}
	public void setStatus(String status) {
		this.status.set(status);
	}
	public String getExpireDate() {
		return expireDate.get();
	}
	public void setExpireDate(String expireDate) {
		this.expireDate.set(expireDate);
	}
	public String getNewCarrier() {
		return newCarrier.get();
	}
	public void setNewCarrier(String newCarrier) {
		this.newCarrier.set(newCarrier);
	}
	public String getNewPlan() {
		return newPlan.get();
	}
	public void setNewPlan(String newPlan) {
		this.newPlan.set(newPlan);
	}
	public String getSim() {
		return sim.get();
	}
	public void setSim(String sim) {
		this.sim.set(sim);
	}
	public String getPUK() {
		return PUK.get();
	}
	public void setPUK(String pUK) {
		this.PUK.set(pUK);
	}
	public String getCurrentCarrier() {
		return currentCarrier.get();
	}
	public void setCurrentCarrier(String currentCarrier) {
		this.currentCarrier.set(currentCarrier);;
	}
	public String getCurrentPlan() {
		return currentPlan.get();
	}
	public void setCurrentPlan(String currentPlan) {
		this.currentPlan.set(currentPlan);
	}
	public String getAccount() {
		return account.get();
	}
	public void setAccount(String account) {
		this.account.set(account);
	}
	public int getPin() {
		return pin.get();
	}
	public void setPin(int pin) {
		this.pin.set(pin);
	}
	public String getPreCarrier() {
		return preCarrier.get();
	}
	public void setPreCarrier(String preCarrier) {
		this.preCarrier.set(preCarrier);
	}
	public String getOfferPrice() {
		return offerPrice.get();
	}
	public void setOfferPrice(String offerPrice) {
		this.offerPrice.set(offerPrice);
	}
	public int getCustomerCredit() {
		return customerCredit.get();
	}
	public void setCustomerCredit(int customerCredit) {
		this.customerCredit.setValue(customerCredit);
	}
	public String getComment() {
		return comment.get();
	}
	public void setComment(String comment) {
		this.comment.set(comment);
	}
}
