package Mobile;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GoSmartController {
	// plans choice
	@FXML
	Button plan25Button;
	@FXML
	Button plan35Button;
	@FXML
	Button plan45Button;
	@FXML
	Button plan55Button;


	public void plan25ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(25);
	}
	public void plan35ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(35);
	}
	public void plan45ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(45);
	}
	public void plan55ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(55);
	}
}
