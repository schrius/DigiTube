package Order;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ServiceController {
	
	@FXML
	Button processButton;
	@FXML
	Button cancelButton;
	@FXML
	DatePicker completeDate;
	@FXML
	ComboBox<String> serviceBox;
	@FXML
	TextField serviceFeeField;
	@FXML
	TextField contactInfo;
	@FXML
	TextField deviceField;
	
	@FXML
	public void initialize() {
		serviceBox.getItems().addAll("Unlock", "Repair", "Other");
		serviceBox.getSelectionModel().selectFirst();
	}
	
	public void cancelButtonListener() throws IOException {
		MainController.getOrderController().updateRightPane();
	}
	
	public void processButtonListener() throws IOException {
		MainController.getOrderController().getOrder().setQuantity(1);
		MainController.getOrderController().getOrder().setPrice(Double.parseDouble(serviceFeeField.getText()));
		MainController.getOrderController().processOrder();
		MainController.getOrderController().addService(serviceBox.getValue(), deviceField.getText(), contactInfo.getText(), 
				completeDate.getValue(), Double.parseDouble(serviceFeeField.getText()));
	}
	
}
