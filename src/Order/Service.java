package Order;

public class Service {
	long serviceID;
	long orderID;
	double serviceFee;
	String contactInfo;
	String serviceType;
	java.time.LocalDate acceptDate;
	java.time.LocalDate completeDate;
	String status;
	public Service() {

	}
	public Service(long serviceID, long orderID, double serviceFee, String contactInfo, String serviceType,
			java.time.LocalDate acceptDate, java.time.LocalDate completeDate, String status) {
		super();
		this.acceptDate = acceptDate;
		this.serviceID = serviceID;
		this.orderID = orderID;
		this.serviceFee = serviceFee;
		this.contactInfo = contactInfo;
		this.serviceType = serviceType;
		this.completeDate = completeDate;
		this.status = status;
	}
	public long getServiceID() {
		return serviceID;
	}
	public void setServiceID(long serviceID) {
		this.serviceID = serviceID;
	}
	public long getOrderID() {
		return orderID;
	}
	public void setOrderID(long orderID) {
		this.orderID = orderID;
	}
	public double getServiceFee() {
		return serviceFee;
	}
	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}
	public String getContactInfo() {
		return contactInfo;
	}
	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public java.time.LocalDate getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(java.time.LocalDate completeDate) {
		this.completeDate = completeDate;
	}
	public java.time.LocalDate getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(java.time.LocalDate acceptDate) {
		this.acceptDate = acceptDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
