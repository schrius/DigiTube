package CustomerInfo;
// Customer generator creates new customer with default information and information from order 
import java.time.LocalDateTime;

import DataManipulater.DataManipulater;
import Employee.Employee;
import Main.FixedElements;
import Order.Orders;

public class CustomerGenerater {
	Customer customer;
	DataManipulater dataManipulater = new DataManipulater();
	CustomerGroup customerGroup;
	
	public CustomerGenerater() {

	}
	
	public Customer generateCustomer(Orders orders, Employee employee) {
		customer = new Customer();
		customer.setPrePlan(dataManipulater.searchPlan(1L));
		customer.setCurrentPlan(dataManipulater.searchPlan(1L));
		customer.setNewPlan(dataManipulater.searchPlan(1L));
		customer.setPhoneNumber(orders.getPlan().getPhoneNumber());
		customer.setCustomerID(Long.parseLong(orders.getPlan().getPhoneNumber()));
		customer.setAction(orders.getCategories());
		customer.setCustomerCredit(orders.getQuantity());
		customer.setEmployee(employee);
		customer.setGroupTitle(FixedElements.PRIME);
		customer.setLastUpdate(LocalDateTime.now());
		customer.setStatus(FixedElements.WAITING);
		customerGroup = dataManipulater.searchCustomerGroup(1);
		customer.setGroupNumber(customerGroup);

		return customer;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
