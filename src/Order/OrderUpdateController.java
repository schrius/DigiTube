package Order;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class OrderUpdateController {
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
	ComboBox<Integer> quanityBox;
	@FXML
	ComboBox<String> categoriesBox;
	@FXML
	ComboBox<String> groupBox;
	@FXML
	ComboBox<String> carrierBox;
	@FXML
	ComboBox<String> planBox;
	@FXML
	ComboBox<String> statusBox;
	@FXML
	DatePicker orderDatePicker;

}
