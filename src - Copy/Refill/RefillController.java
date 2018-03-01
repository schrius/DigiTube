package Refill;

import java.io.IOException;

import Order.OrderController;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
public class RefillController {

	GridPane orderRightPane;
	FXMLLoader fxmlLoader;
	
	@FXML
	private Button processButton;
	@FXML
	private ComboBox<Integer> quanityBox;

	@FXML
	private TextField phoneNumberField;
	@FXML
	private ImageView refillImage;
	
	@FXML
	public void initialize() {
		quanityBox.setEditable(true);
		quanityBox.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9));
		quanityBox.getSelectionModel().selectFirst();
	}
	
	public void processButtonListener() throws IOException {
		if(phoneNumberField.getText().length() == 10) {	
		orderRightPane = FXMLLoader.load(getClass().getResource("../Order/OrderRightPaneFX.fxml"));
		OrderController.getOrder().setCustomerPhone(phoneNumberField.getText());
		OrderController.getOrder().setQuantity(quanityBox.getValue());
		OrderController.processOrder();
		OrderController.getOrderPane().setRight(orderRightPane);
		}
	}
}
