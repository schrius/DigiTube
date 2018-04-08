package Mobile;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
public class RefillController {

	@FXML
	Button cancelButton;
	@FXML
	private Button processButton;
	@FXML
	private ComboBox<Integer> quanityBox;

	@FXML
	private TextField phoneNumberField;
	@FXML
	private ImageView carrierImage;
	@FXML
	private DatePicker expireDate;
	
	@FXML
	public void initialize() {
		quanityBox.getItems().addAll(1,2,3,4,5,6,7,8,9);
		quanityBox.getSelectionModel().selectFirst();
	}
	public void cancelButtonListener() throws IOException {
		MainController.getOrderController().updateRightPane();
	}
	
	public void processButtonListener() throws IOException {
		if(phoneNumberField.getText().length() == 10) {	
			MainController.getOrderController().processRefill(phoneNumberField.getText(), quanityBox.getValue(), expireDate.getValue());
		}
	}
}
