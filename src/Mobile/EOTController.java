package Mobile;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EOTController {
	// plans choice
	@FXML
	Button plan10Button;

	public void plan10ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(10);
	}
}
