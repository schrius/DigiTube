package Mobile;
/*
 * Ultra Mobile Flex plans controller
 */
import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class FlexController {
	// plans choice
	@FXML
	Button plan20Button;
	@FXML
	Button plan35Button;
	@FXML
	Button plan50Button;


	public void plan20ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(20);
	}
	public void plan35ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(35);
	}
	public void plan50ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(50);
	}
}
