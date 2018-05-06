package Mobile;
/*
 * NET10 Mobile plans controller
 */
import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NET10Controller {
	// plans choice
	@FXML
	Button plan35Button;
	@FXML
	Button plan40Button;
	@FXML
	Button plan50Button;
	@FXML
	Button plan60Button;
	@FXML
	Button plan65Button;
	@FXML
	Button plan75Button;


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
	public void plan65ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(65);
	}
	public void plan75ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(70);
	}
}
