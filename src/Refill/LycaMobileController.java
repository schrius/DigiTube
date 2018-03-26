package Refill;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class LycaMobileController {
	// plans choice
	@FXML
	Button plan19Button;
	@FXML
	Button plan23Button;
	@FXML
	Button plan29Button;
	@FXML
	Button plan35Button;
	@FXML
	Button plan45Button;
	@FXML
	Button plan50Button;
	@FXML
	Button payAsYouGoButton;

	public void plan19ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(19);
	}
	public void plan23ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(23);
	}
	public void plan29ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(29);
	}
	public void plan35ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(35);
	}
	public void plan45ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(45);
	}
	public void plan50ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(50);
	}
	public void payAsYouGoButtonListener() throws IOException {
		MainController.getOrderController().setPlan(0);
	}
}
