package Order;
/*
 * Controller for accessories product input
 */
import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class AccessoriesController {
	@FXML
	ComboBox<String> accessoriesBox;
	@FXML
	TextField productName;
	@FXML
	TextField description;
	@FXML
	TextField priceField;
	@FXML
	Button cancelButton;
	@FXML
	Button submitButton;
	
	@FXML
	private void initialize() {
		accessoriesBox.getItems().addAll("iPhone Case", "iPhone Protector", "iPad Case", "iPad Protector", 
				"iPhone Headset", "iPhone Charger", "iPhone Cable", "Bluetooth Headset", "Other");
	}
	
	public void cancelButtonListener() throws IOException {
		MainController.getOrderController().updateRightPane();
	}
	public void submitButtonListener() throws IOException {
		Product product = new Product();
		if(accessoriesBox.getValue().equals("Other"))
			product.setProductName(productName.getText());
		else product.setProductName(accessoriesBox.getValue());
	
		product.setDescription(description.getText());
		product.setPrice(Double.parseDouble(priceField.getText()));
		
		MainController.getOrderController().getOrder().setQuantity(1);
		MainController.getOrderController().getOrder().setRegularPrice(product.getPrice());
		MainController.getOrderController().getOrder().setPrice(product.getPrice());
		MainController.getOrderController().getOrder().setProduct(product);

		MainController.getOrderController().processOrder();
	}

}
