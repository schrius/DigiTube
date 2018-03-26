package Order;

import java.io.IOException;

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
	Button searchButton;
	
	@FXML
	private void initialize() {
		carrierBox.getItems().addAll("conEdison", "nationalGrid", "Verison", "Optimum", "Other");
	}
	
	public void searchButtonListener() {
		
	}

	public void cancelButtonListener() throws IOException {
		MainController.getOrderController().updateRightPane();
	}
	
	public void submitButtonListener() {
		
	}
}
