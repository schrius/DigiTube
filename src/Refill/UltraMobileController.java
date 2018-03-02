package Refill;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class UltraMobileController {
	VBox processBox;
	// plans choice
	@FXML
	Button plan19Button;
	@FXML
	Button plan23Button;
	@FXML
	Button plan29Button;
	@FXML
	Button plan35Button;
	@FXML
	Button plan45Button;
	@FXML
	Button plan50Button;
	@FXML
	Button payAsYouGoButton;

	public void plan19ButtonListener() throws IOException {
		processBox  = FXMLLoader.load(getClass().getResource("RefillFX.fxml"));
		MainController.getOrderController().getOrder().setPlan("$19");
		MainController.getOrderController().getOrder().setPrice(19);
		MainController.getOrderController().getOrder().setPrice(19);
		MainController.getOrderController().getOrderPane().setRight(processBox);
	}
}
