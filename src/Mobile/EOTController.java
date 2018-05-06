package Mobile;
/*
 * EOT Mobile plans controller
 */
import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EOTController {
	@FXML
	Button plan10Button;

	public void plan10ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(10);
	}
}
