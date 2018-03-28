package Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Invoice {
	@Id
	@SequenceGenerator(name="invoice_seq", sequenceName="invoice_id_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invoice_seq")
	@Column(name="INVOICE_ID")
	private long invoiceID;
	@OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Orders> order = new ArrayList<>();
	private double total;
	private double subtotal;
	private double PSCS;
	private double NYTax;
	private double discount;
	private double serviceFee;
	private double receiveCash;
	private double returnBalance;
	private LocalDateTime orderDate;
	public Invoice() {

	}

	public Invoice(long invoiceID, List<Orders> order, LocalDateTime orderDate) {
		super();
		this.invoiceID = invoiceID;
		this.order = order;
		this.orderDate = orderDate;
	}

	
	public double getReceiveCash() {
		return receiveCash;
	}

	public void setReceiveCash(double receiveCash) {
		this.receiveCash = receiveCash;
	}

	public double getReturnBalance() {
		return returnBalance;
	}

	public void setReturnBalance(double returnBalance) {
		this.returnBalance = returnBalance;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getPSCS() {
		return PSCS;
	}

	public void setPSCS(double pSCS) {
		PSCS = pSCS;
	}

	public double getNYTax() {
		return NYTax;
	}

	public void setNYTax(double nYTax) {
		NYTax = nYTax;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public long getInvoiceID() {
		return invoiceID;
	}

	public void setInvoiceID(long invoiceID) {
		this.invoiceID = invoiceID;
	}

	public List<Orders> getOrder() {
		return order;
	}

	public void setOrder(List<Orders> order) {
		this.order = order;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}
	
}
