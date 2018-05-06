package Order;
/*
 * Unpaid persistent Object
 */
import java.time.LocalDate;
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
@Table(name="Unpaid")
public class Unpaid implements TableEntry{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Unpaid_ID")
	private long unpaidid;
	private double oweamount;
	@OneToOne
	@JoinColumn(name="Invoice_ID", nullable=false)
	private Invoice invoice;
	@ManyToOne
	@JoinColumn(name="CUSTOMER_ID", foreignKey = @ForeignKey(name = "Unpaid_cutomer_ID_FK"))
	private Customer customer;
	@ManyToOne
	@JoinColumn(name="EMPLOYEE_ID", foreignKey = @ForeignKey(name = "Unpaid_employee_ID_FK"))
	private Employee employee;
	
	private LocalDate lastUpdate;

	public long getUnpaidid() {
		return unpaidid;
	}

	public void setUnpaidid(long unpaidid) {
		this.unpaidid = unpaidid;
	}

	public double getOweamount() {
		return oweamount;
	}

	public void setOweamount(double oweamount) {
		this.oweamount = oweamount;
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

	public LocalDate getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDate lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
}
