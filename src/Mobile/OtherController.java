package Mobile;

import java.io.IOException;
import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class OtherController {
	// plans choice
	@FXML
	TextField carrierField;
	@FXML
	TextField refillAmountField;
	@FXML
	Button confirmButton;


	public void confirmButtonListener() throws IOException {
		MainController.getOrderController().getOrder().getPlan().setCarrier(carrierField.getText());
		MainController.getOrderController().setPlan(Integer.parseInt(refillAmountField.getText()));
	}

}
