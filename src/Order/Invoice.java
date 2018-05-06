package Order;
/*
 * Invoice persistent object
 */
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import Main.TableEntry;

@Entity
public class Invoice implements TableEntry {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="INVOICE_ID")
	private long invoiceID;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice", cascade = CascadeType.ALL)
	private List<Orders> order = new ArrayList<>();
	private double total;
	private double subtotal;
	private double PSCS;
	private double NYTax;
	private double discount;
	private double serviceFee;
	private double receiveCash;
	private double returnBalance;
	private double refund;
	
	@Column(length = 32)
	private String paymentMethod;
	
	private LocalDateTime orderDate;
	private LocalDateTime lastUpdate;
	
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

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public double getRefund() {
		return refund;
	}

	public void setRefund(double refund) {
		this.refund = refund;
	}

	public LocalDateTime getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(LocalDateTime lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	
}
