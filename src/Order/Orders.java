package Order;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import CustomerInfo.Customer;
import Employee.Employee;
import Main.Product;
import Main.Plan;

@Entity
@Table(name="Orders")
public class Orders{
	@Id
	@SequenceGenerator(name="orders_seq", sequenceName="orders_id_seq", allocationSize=3)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
	@Column(name="ORDER_ID")
	long orderID;
	
	@ManyToOne
	@JoinColumn(name="PRODUCT_ID", foreignKey = @ForeignKey(name = "PRODUCT_ID_FK"))
	Product product;
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID", foreignKey = @ForeignKey(name = "CUSTOMER_ID_FK"))
	private Customer customer;
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID", foreignKey = @ForeignKey(name = "EMPLOYEE_ID_FK"))
	private Employee employee;
	@ManyToOne
	private Invoice invoice;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "SERVICE_ID")
	private Service service;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "BILL_ID")
	private PayBill bill;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "PLAN_ID")
	private Plan plan;
	
	private int quantity;
	private double price;
	private double discount;
	private double regularPrice;
	
	private String categories;
	private String description;
	private String status;
	private String paymentMethod;
	private LocalDateTime orderDate;
	private LocalDateTime lastUpdate;
	public Orders() {
	//	service = new Service();
	//	bill = new PayBill();
	//	plan = new Plan();
	}
	
	public Plan getPlan() {
		return plan;
	}


	public void setPlan(Plan plan) {
		this.plan = plan;
	}


	public long getOrderID() {
		return orderID;
	}
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
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
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public String getCategories() {
		return categories;
	}
	public void setCategories(String categories) {
		this.categories = categories;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public LocalDateTime getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public PayBill getBill() {
		return bill;
	}

	public void setBill(PayBill bill) {
		this.bill = bill;
	}

	public double getRegularPrice() {
		return regularPrice;
	}

	public void setRegularPrice(double regularPrice) {
		this.regularPrice = regularPrice;
	}
	
}