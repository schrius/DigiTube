package Mobile;

import java.io.IOException;
import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TELCELController {
	// plans choice
	@FXML
	Button plan25Button;
	@FXML
	Button plan30Button;
	@FXML
	Button plan40Button;
	@FXML
	Button plan55Button;
	@FXML
	Button plan60Button;


	public void plan25ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(25);
	}
	public void plan30ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(30);
	}
	public void plan40ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(40);
	}
	public void plan55ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(55);
	}
	public void plan60ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(60);
	}
}
