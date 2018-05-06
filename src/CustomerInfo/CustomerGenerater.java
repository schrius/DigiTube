package CustomerInfo;
/*
 *  Customer generator creates new customer with default information and information from order 
 */
import java.time.LocalDateTime;

import DataManipulater.DataManipulater;
import Employee.Employee;
import Main.FixedElements;
import Order.Orders;
import Order.Plan;

public class CustomerGenerater {
	Customer customer;
	CustomerGroup customerGroup;
	
	public Customer generateCustomer(Orders orders, Employee employee) {
		customer = new Customer();
		customer.setPrePlan((Plan) DataManipulater.searchData(1L, Plan.class));
		customer.setCurrentPlan((Plan) DataManipulater.searchData(1L, Plan.class));
		customer.setNewPlan((Plan) DataManipulater.searchData(1L, Plan.class));
		customer.setPhoneNumber(orders.getPlan().getPhoneNumber());
		customer.setCustomerID(Long.parseLong(orders.getPlan().getPhoneNumber()));
		customer.setAction(orders.getCategories());
		customer.setCustomerCredit(0);
		customer.setEmployee(employee);
		customer.setGroupTitle(FixedElements.PRIME);
		customer.setLastUpdate(LocalDateTime.now());
		customer.setStatus(FixedElements.WAITING);
		customerGroup = (CustomerGroup) DataManipulater.searchData(1L, CustomerGroup.class);
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
