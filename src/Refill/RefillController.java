package Refill;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
public class RefillController {

	GridPane orderRightPane;
	Parent parent;
	
	@FXML
	private Button processButton;
	@FXML
	private ComboBox<Integer> quanityBox;

	@FXML
	private TextField phoneNumberField;
	@FXML
	private ImageView carrierImage;
	
	@FXML
	public void initialize() {
		quanityBox.getItems().addAll(1,2,3,4,5,6,7,8,9);
		quanityBox.getSelectionModel().selectFirst();
	}
	
	public void processButtonListener() throws IOException {
		if(phoneNumberField.getText().length() == 10) {	
		MainController.getOrderController().getOrder().setPhoneNumber(phoneNumberField.getText());
		MainController.getOrderController().getOrder().setQuantity(quanityBox.getValue());
		MainController.getOrderController().processOrder();
		}
	}
}
