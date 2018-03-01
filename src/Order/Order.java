package Order;

public class Order{
	int orderID;
	int customerID;
	int employeeID;
	int quantity;
	double price;
	double regularPrice;
	public double getRegularPrice() {
		return regularPrice;
	}
	public void setRegularPrice(double regularPrice) {
		this.regularPrice = regularPrice;
	}
	double discount;
	double discountPrice;
	String customerPhone;
	String categories;
	String plan;
	String carrier;
	String description;
	String orderDate;
	
	public Order() {

	}
	public Order(int orderID, int customerID, int employeeID, int quantity, double regularPrice,
			double discountPrice, String customerPhone, String categories, String carrier, String plan, String description, String orderDate) {
		super();
		this.orderID = orderID;
		this.customerID = customerID;
		this.employeeID = employeeID;
		this.quantity = quantity;
		this.price = regularPrice;
		this.discount = discountPrice;
		this.customerPhone = customerPhone;
		this.categories = categories;
		this.plan = plan;
		this.carrier = carrier;
		this.description = description;
		this.orderDate = orderDate;
	}
	
	public String getCarrier() {
		return carrier;
	}
	
	public String getCategories() {
		return categories;
	}

	public int getCustomerID() {
		return customerID;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public String getDescription() {
		return description;
	}
	public double getDiscount() {
		return discount;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public int getOrderID() {
		return orderID;
	}
	public double getPrice() {
		return price;
	}
	public String getPlan() {
		return plan;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setDiscoun(double discount) {
		this.discount = discount;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
