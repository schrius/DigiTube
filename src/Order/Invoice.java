package Order;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Invoice {
	@Id
	@GeneratedValue
	private long orderID;
	private String productID;
	private String description;
	private double regularPrice;
	private double price;
	private String orderDate;
	private int employeeID;
	public Invoice() {

	}
	public Invoice(long orderID, String productID, String description, double regularPrice, double price,
			String orderDate, int employeeID) {
		super();
		this.orderID = orderID;
		this.productID = productID;
		this.description = description;
		this.regularPrice = regularPrice;
		this.price = price;
		this.orderDate = orderDate;
		this.employeeID = employeeID;
	}
	public long getOrderID() {
		return orderID;
	}
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getRegularPrice() {
		return regularPrice;
	}
	public void setRegularPrice(double regularPrice) {
		this.regularPrice = regularPrice;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public int getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
}
