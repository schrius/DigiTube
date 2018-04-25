package Order;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;
import CustomerInfo.Customer;
import Employee.Employee;
import Main.TableEntry;

@Entity
@Table(name="PayBack")
public class PayBack implements TableEntry{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="PayBack_ID")
	private int paybackid;
	private double paybackAmount;
	@OneToOne
	@JoinColumn(name="Invoice_ID", nullable=false)
	private Invoice invoice;
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID", foreignKey = @ForeignKey(name = "PAYBACK_CUSTOMER_ID_FK"))
	private Customer customer;
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID", foreignKey = @ForeignKey(name = "PAYBACK_EMPLOYEE_ID_FK"))
	private Employee employee;
	
	private LocalDateTime lastUpdate;

	public int getPaybackid() {
		return paybackid;
	}

	public void setPaybackid(int paybackid) {
		this.paybackid = paybackid;
	}

	public double getPaybackAmount() {
		return paybackAmount;
	}

	public void setPaybackAmount(double paybackAmount) {
		this.paybackAmount = paybackAmount;
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

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
}
