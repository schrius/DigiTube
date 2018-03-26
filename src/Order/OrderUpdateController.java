package Order;

import java.io.IOException;
import Main.FixedElements;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OrderUpdateController {
	@FXML
	TextField searchField;
	@FXML
	TextField orderIDField;
	@FXML
	TextField customerIDField;
	@FXML
	TextField productIDField;
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
	TextField IMEIField;
	@FXML
	TextField serialField;
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
	ComboBox<String> groupBox;
	@FXML
	ComboBox<String> carrierBox;
	@FXML
	TextField planField;
	@FXML
	TextField statusField;
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
		groupBox.getItems().addAll(FixedElements.GROUP);
		carrierBox.getItems().addAll(FixedElements.CARRIERS);
	}
	
	public void searchButtonListener() {
		
	}
	
	public void cancelButtonListener() throws IOException {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	public void submitButtonListener() {
		
	}

}
