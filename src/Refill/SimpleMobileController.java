package Refill;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class SimpleMobileController {
	Parent parent;
	VBox processBox;
	
	// plans choice
	@FXML
	Button plan25Button;
	@FXML
	Button plan30Button;
	@FXML
	Button plan40Button;
	@FXML
	Button plan50Button;
	@FXML
	Button plan60Button;
	
	public void plan25ButtonListener() throws IOException {
		processBox  = FXMLLoader.load(getClass().getResource("RefillFX.fxml"));
		MainController.getOrderController().getOrder().setPlan("$25");
		MainController.getOrderController().getOrder().setPrice(25);
		MainController.getOrderController().getOrder().setPrice(25);
		MainController.getOrderController().getOrderPane().setRight(processBox);
	}
}
