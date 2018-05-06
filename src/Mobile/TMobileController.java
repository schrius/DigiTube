package Mobile;
/*
 * T Mobile plans controller
 */
import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TMobileController {
	// plans choice
	@FXML
	Button confirmButton;
	@FXML
	TextField refillAmountField;

	public void confirmButtonListener() throws IOException {
		MainController.getOrderController().setPlan(Integer.parseInt(refillAmountField.getText()));
	}
}
