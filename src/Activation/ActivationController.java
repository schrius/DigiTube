package Activation;

import java.io.IOException;

import Main.FixedElements;
import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
public class ActivationController {

	@FXML
	private Button processButton;
	@FXML
	private Button cancelButton;
	@FXML
	private ComboBox<Integer> quanityBox;

	@FXML
	private TextField phoneNumberField;
	@FXML
	private TextField simField;
	@FXML
	private TextField pukField;
	@FXML
	private ImageView carrierImage;
	@FXML
	private ComboBox<String> categoriesBox;
	@FXML
	private DatePicker portDate;
	
	@FXML
	public void initialize() {
		quanityBox.getItems().addAll(1,2,3,4,5,6,7,8,9);
		quanityBox.getSelectionModel().selectFirst();
		categoriesBox.getItems().addAll(FixedElements.ACTIVATION);
		categoriesBox.getSelectionModel().selectFirst();
	}
	public void cancelButtonListener() throws IOException {
		MainController.getOrderController().updateRightPane();
	}
	
	public void processButtonListener() throws IOException {
		if(phoneNumberField.getText().length() == 10) {	
		MainController.getOrderController().processActivation(phoneNumberField.getText(), quanityBox.getValue(), 
				simField.getText(), pukField.getText(), portDate.getValue(), categoriesBox.getValue());
		}
	}

}
