package Mobile;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class CTController {
	// plans choice
	@FXML
	Button plan19Button;
	@FXML
	Button plan29Button;
	@FXML
	Button plan39Button;
	@FXML
	Button plan45Button;

	public void plan19ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(19);
	}
	public void plan29ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(29);
	}
	public void plan39ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(39);
	}
	public void plan45ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(45);
	}
}
