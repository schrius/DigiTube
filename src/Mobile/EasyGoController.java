package Mobile;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class EasyGoController {
	// plans choice
	@FXML
	Button plan20Button;
	@FXML
	Button plan30Button;


	public void plan20ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(20);
	}
	public void plan30ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(30);
	}
}
