package Order;

import java.io.IOException;

import Main.FixedElements;
import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PayBillController {
	@FXML
	ComboBox<String> carrierBox;
	@FXML
	TextField otherCarrier;
	@FXML
	TextField accountField;
	@FXML
	TextField balanceField;
	@FXML
	TextField serviceFeeField;
	@FXML
	TextField phoneField;
	@FXML
	Button cancelButton;
	@FXML
	Button submitButton;
	
	@FXML
	private void initialize() {
		carrierBox.getItems().addAll("conEdison", "nationalGrid", "Verison", "Optimum", "Other");
	}

	public void cancelButtonListener() throws IOException {
		MainController.getOrderController().updateRightPane();
	}
	
	public void submitButtonListener() throws IOException {
		Bill bill = new Bill();
		if(carrierBox.getValue().equals("Other")) {
			bill.setBillCarrier(otherCarrier.getText());
		}else bill.setBillCarrier(carrierBox.getValue());
		
		bill.setBillAmount(Double.parseDouble(balanceField.getText()));
		bill.setServiceFess(Double.parseDouble(serviceFeeField.getText()));
		bill.setBillingAccount(accountField.getText());
		bill.setContactInfo(phoneField.getText());
		bill.setStatus(FixedElements.WAITING);
		
		MainController.getOrderController().getOrder().setQuantity(1);
		MainController.getOrderController().getOrder().setRegularPrice(bill.getServiceFess() + bill.getBillAmount());
		MainController.getOrderController().getOrder().setPrice(bill.getServiceFess() + bill.getBillAmount());
		MainController.getOrderController().getOrder().setBill(bill);
		
		MainController.getOrderController().processOrder();
	}
}
