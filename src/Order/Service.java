package Order;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Service {
	@Id
	@GeneratedValue
	@Column(name = "SERVICE_ID")
	long serviceID;
	double serviceFee;
	String contactInfo;
	String device;
	String serviceType;
	java.time.LocalDate acceptDate;
	java.time.LocalDate completeDate;
	String status;
	public Service() {

	}
	public Service(long serviceID, double serviceFee, String contactInfo, String device,
			String serviceType, LocalDate acceptDate, LocalDate completeDate, String status) {
		super();
		this.serviceID = serviceID;
		this.serviceFee = serviceFee;
		this.contactInfo = contactInfo;
		this.device = device;
		this.serviceType = serviceType;
		this.acceptDate = acceptDate;
		this.completeDate = completeDate;
		this.status = status;
	}
	public long getServiceID() {
		return serviceID;
	}
	public void setServiceID(long serviceID) {
		this.serviceID = serviceID;
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
	public String getDevice() {
		return device;
	}
	public void setDevice(String device) {
		this.device = device;
	}
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
	}
	public java.time.LocalDate getAcceptDate() {
		return acceptDate;
	}
	public void setAcceptDate(java.time.LocalDate acceptDate) {
		this.acceptDate = acceptDate;
	}
	public java.time.LocalDate getCompleteDate() {
		return completeDate;
	}
	public void setCompleteDate(java.time.LocalDate completeDate) {
		this.completeDate = completeDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
