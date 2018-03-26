package Order;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DeviceController {
	@FXML
	ComboBox<String> deviceBox;
	@FXML
	TextField productName;
	@FXML
	TextField priceField;
	@FXML
	TextField serialField;
	@FXML
	TextField IMEIField;
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
		deviceBox.getItems().addAll("iPhone", "Samsung", "iPad", "Other");
	}
	
	public void searchButtonListener() {
		
	}
	public void cancelButtonListener() throws IOException {
		MainController.getOrderController().updateRightPane();
	}
	public void submitButtonListener() {
		
	}
}
