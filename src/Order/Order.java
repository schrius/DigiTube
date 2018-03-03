package Order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Order{
	@Id
	@GeneratedValue
	long orderID;
	String productID;
	int customerID;
	int employeeID;
	int quantity;
	double price;
	double regularPrice;
	double discount;
	String IMEI;
	String serialNumber;
	String invoice;
	String group;
	String phoneNumber;
	String categories;
	String plan;
	String carrier;
	String description;
	String orderDate;
	String simcard;
	String PUK;
	String status;
	public Order() {
		
	}

	public Order(long orderID, String productID, int customerID, int employeeID, int quantity, double price,
			double regularPrice, double discount, String iMEI, String serialNumber, String invoice, String group,
			String phoneNumber, String categories, String plan, String carrier, String description, String orderDate,
			String simcard, String pUK, String status) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.customerID = customerID;
		this.employeeID = employeeID;
		this.quantity = quantity;
		this.price = price;
		this.regularPrice = regularPrice;
		this.discount = discount;
		IMEI = iMEI;
		this.serialNumber = serialNumber;
		this.invoice = invoice;
		this.group = group;
		this.phoneNumber = phoneNumber;
		this.categories = categories;
		this.plan = plan;
		this.carrier = carrier;
		this.description = description;
		this.orderDate = orderDate;
		this.simcard = simcard;
		PUK = pUK;
		this.status = status;
	}

	public long getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getRegularPrice() {
		return regularPrice;
	}
	public void setRegularPrice(double regularPrice) {
		this.regularPrice = regularPrice;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}

}