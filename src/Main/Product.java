package Main;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Product {
	@Id
	@GeneratedValue
	private String productID;
	private String description;
	private String price;
	private String barcode;
	private String IMEI;
	private String serialNumber;
	private String sim;
	private String PUK;
	public Product() {
	}
	public Product(String productID, String description, String price, String barcode, String iMEI, String serialNumber,
			String sim, String pUK) {
		super();
		this.productID = productID;
		this.description = description;
		this.price = price;
		this.barcode = barcode;
		IMEI = iMEI;
		this.serialNumber = serialNumber;
		this.sim = sim;
		PUK = pUK;
	}
	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getIMEI() {
		return IMEI;
	}
	public void setIMEI(String iMEI) {
		IMEI = iMEI;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getSim() {
		return sim;
	}
	public void setSim(String sim) {
		this.sim = sim;
	}
	public String getPUK() {
		return PUK;
	}
	public void setPUK(String pUK) {
		PUK = pUK;
	}
	
}
