package CustomerInfo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CustomerUpdateController {
	@FXML
	TextField customerIDField;
	@FXML
	TextField phoneField;
	@FXML
	TextField oweAmountField;
	@FXML
	TextField deviceField;
	@FXML
	TextField languageField;
	@FXML
	TextField LTEField;
	@FXML
	TextField priceField;
	@FXML
	TextField groupField;
	@FXML
	TextField newSimField;
	@FXML
	TextField simField;
	@FXML
	TextField PUKField;
	@FXML
	TextField accountField;
	@FXML
	TextField pinField;
	@FXML
	TextField commentField;
	@FXML
	TextField firstnameField;
	@FXML
	TextField lastNameField;
	@FXML
	TextField addressField;
	@FXML
	TextField cityField;
	@FXML
	TextField zipCodeField;
	@FXML
	DatePicker newExpireDate;
	@FXML
	DatePicker ExpireDate;
	@FXML
	DatePicker portDate;
	@FXML
	ComboBox<String> newCarrier;
	@FXML
	ComboBox<String> carrier;
	@FXML
	ComboBox<String> preCarrier;
	@FXML
	ComboBox<String> group;
	@FXML
	ComboBox<String> groupTitle;
	@FXML
	ComboBox<String> status;
	@FXML
	ComboBox<String> newPlan;
	@FXML
	ComboBox<String> plan;
	@FXML
	ComboBox<String> state;
	
	@FXML
	Button cancelButton;
	@FXML
	Button submitButton;
	
}
