package Order;
/*
 * Service persistent Object
 */
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import Main.TableEntry;

@Entity
public class Service implements TableEntry {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "SERVICE_ID")
	private long serviceID;
	private double serviceFee;
	@Column(length = 32)
	private String contactInfo;
	@Column(length = 32)
	private String device;
	@Column(length = 32)
	private String serviceType;
	private java.time.LocalDate acceptDate;
	private java.time.LocalDate completeDate;
	@Column(length = 32)
	private String status;
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
