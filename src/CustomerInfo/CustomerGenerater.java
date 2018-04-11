package CustomerInfo;

import java.time.LocalDateTime;

import Employee.Employee;
import Main.FixedElements;
import Order.Orders;

public class CustomerGenerater {
	Customer customer;

	public CustomerGenerater() {
		super();

	}

	public Customer generateCustomerRefill(Orders orders, Employee employee) {
		customer = new Customer();
		customer.setPhoneNumber(orders.getPlan().getPhoneNumber());
		customer.setCustomerID(Long.parseLong(orders.getPlan().getPhoneNumber()));
		customer.setAction(FixedElements.REFILL);
		customer.setCustomerCredit(orders.getQuantity());
		customer.setEmployee(employee);
		customer.setGroupTitle(FixedElements.PRIME);
		customer.setLastUpdate(LocalDateTime.now());
		customer.setStatus(FixedElements.WAITING);

		return customer;
	}
	public Customer generateCustomerActivation(Orders orders, Employee employee) {
		customer = new Customer();
		customer.setPhoneNumber(orders.getPlan().getPhoneNumber());
		customer.setCustomerID(Long.parseLong(orders.getPlan().getPhoneNumber()));
		customer.setAction(FixedElements.ACTIVATION);
		customer.setCustomerCredit(orders.getQuantity());
		customer.setEmployee(employee);
		customer.setGroupTitle(FixedElements.PRIME);
		customer.setLastUpdate(LocalDateTime.now());
		customer.setStatus(FixedElements.WAITING);
	
		return customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
