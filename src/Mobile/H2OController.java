package Mobile;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class H2OController {
	// plans choice
	@FXML
	Button plan10Button;
	@FXML
	Button plan20Button;
	@FXML
	Button plan25Button;
	@FXML
	Button plan30Button;
	@FXML
	Button plan35Button;
	@FXML
	Button plan40Button;
	@FXML
	Button plan50Button;
	@FXML
	Button plan60Button;


	public void plan10ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(10);
	}
	public void plan20ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(20);
	}
	public void plan25ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(25);
	}
	public void plan30ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(30);
	}
	public void plan35ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(35);
	}
	public void plan40ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(40);
	}
	public void plan50ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(50);
	}
	public void plan60ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(60);
	}
}
