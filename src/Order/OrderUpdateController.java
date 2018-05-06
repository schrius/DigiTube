package Order;
/*
 * Controller for updating order information
 */
import java.io.IOException;
import java.time.format.DateTimeFormatter;

import DataManipulater.DataManipulater;
import Main.FixedElements;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class OrderUpdateController {
	Orders order = null;
	
	@FXML
	TextField searchField;
	@FXML
	TextField customerIDField;
	@FXML
	TextField productName;
	@FXML
	TextField regularPriceField;
	@FXML
	TextField priceField;
	@FXML
	TextField discountField;
	@FXML
	TextField invoiceField;
	@FXML
	TextField phoneField;
	@FXML
	TextField billAccount;
	@FXML
	TextField serialField;
	@FXML
	TextField IMEI;
	@FXML
	TextField newSimField;
	@FXML
	TextField PUKField;
	@FXML
	TextField descriptionField;
	@FXML
	TextField employeeIDField;

	@FXML
	TextField quanityField;
	@FXML
	TextField categories;
	@FXML
	TextField deviceField;
	@FXML
	TextField serviceField;
	@FXML
	ComboBox<String> carrierBox;
	@FXML
	TextField planField;
	@FXML
	ComboBox<String> statusBox;
	@FXML
	TextField paymentField;
	@FXML
	TextField orderDateField;
	@FXML
	Label lastUpdateLabel;

	@FXML
	Button cancelButton;
	@FXML
	Button submitButton;
	@FXML
	Button searchButton;
	@FXML
	private void initialize() {
		statusBox.getItems().addAll(FixedElements.ORDERSTATUS);
		carrierBox.getItems().addAll(FixedElements.CARRIERS);
	}
	
	public void searchButtonListener() {
		order = (Orders) DataManipulater.searchData(Long.parseLong(searchField.getText()), Orders.class);
		
		if(order!=null) {
			invoiceField.setText(Long.toString(order.getInvoice().getInvoiceID()));
			if(order.getCustomer()!=null) {
				customerIDField.setText(Long.toString(order.getCustomer().getCustomerID()));
			}
			categories.setText(order.getCategories());
			regularPriceField.setText(Double.toString(order.getRegularPrice()));
			priceField.setText(Double.toString(order.getPrice()));
			discountField.setText(Double.toString(order.getDiscount()));
			quanityField.setText(Integer.toString(order.getQuantity()));
			statusBox.setValue(order.getStatus());
			descriptionField.setText(order.getDescription());
			employeeIDField.setText(Long.toString(order.getEmployee().getEmployeeID()));
			paymentField.setText(order.getInvoice().getPaymentMethod());
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			orderDateField.setText(order.getOrderDate().format(formatter));
			
			if(order.getCategories().equals(FixedElements.ACTIVATION)||order.getCategories().equals(FixedElements.REFILL)) {
				carrierBox.setValue(order.getPlan().getCarrier());
				planField.setText(order.getPlan().getPlanType());
				if(order.getPlan().getSim()!=null) {
					newSimField.setText(order.getPlan().getSim());
				}
				if(order.getPlan().getPUK()!=null) {
					PUKField.setText(order.getPlan().getPUK());
				}
			}
			else if(order.getCategories().equals(FixedElements.SERVICE)) {
				serviceField.setText(order.getService().getServiceType());
				deviceField.setText(order.getService().getDevice());
			}
			else if(order.getCategories().equals(FixedElements.PAYBILL)) {
				billAccount.setText(order.getBill().getBillingAccount());
			}
			else{
				productName.setText(order.getProduct().getProductName());
				if(order.getProduct().getIMEI()!=null)
					IMEI.setText(order.getProduct().getIMEI());
				if(order.getProduct().getSerialNumber()!=null)
					serialField.setText(order.getProduct().getSerialNumber());
			}
		}
	}
	
	public void cancelButtonListener() throws IOException {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	public void submitButtonListener() {
		if(order!=null) {
			
			if(order.getCategories().equals(FixedElements.ACTIVATION)||order.getCategories().equals(FixedElements.REFILL)) {
				order.getPlan().setCarrier(carrierBox.getValue());

				if(!newSimField.getText().equals(order.getPlan().getSim())) {
					order.getPlan().setSim(newSimField.getText());
				}
				if(!PUKField.getText().equals(order.getPlan().getPUK())) {
					order.getPlan().setPUK(PUKField.getText());
				}
			}
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Order Update!");
			alert.setHeaderText("Order information update.");
			alert.setContentText(null);
			alert.showAndWait();
		}
	}

}
