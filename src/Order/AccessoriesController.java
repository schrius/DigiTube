package Order;

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
	TextField phoneField;
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
	public void submitButtonListener() {
		
	}

}
