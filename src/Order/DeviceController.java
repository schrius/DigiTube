package Order;
/*
 * Controller for device product input
 */
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
	
	public void cancelButtonListener() throws IOException {
		MainController.getOrderController().updateRightPane();
	}
	public void submitButtonListener() throws IOException {
		Product product = new Product();
		if(deviceBox.getValue().equals("Other"))
			product.setProductName(productName.getText());
		else product.setProductName(deviceBox.getValue());
	
		product.setPrice(Double.parseDouble(priceField.getText()));
		product.setIMEI(IMEIField.getText());
		product.setSerialNumber(serialField.getText());
		product.setDescription(phoneField.getText());
		
		MainController.getOrderController().getOrder().setQuantity(1);
		MainController.getOrderController().getOrder().setPrice(product.getPrice());
		MainController.getOrderController().getOrder().setRegularPrice(product.getPrice());
		MainController.getOrderController().getOrder().setProduct(product);
		
		MainController.getOrderController().processOrder();
	}
}
