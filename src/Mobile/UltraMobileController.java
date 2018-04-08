package Mobile;

import java.io.IOException;
import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class UltraMobileController {
	// plans choice
	@FXML
	Button plan19Button;
	@FXML
	Button plan23Button;
	@FXML
	Button plan29Button;
	@FXML
	Button plan34Button;
	@FXML
	Button plan39Button;
	@FXML
	Button plan49Button;

	public void plan19ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(19);
	}
	public void plan23ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(23);
	}
	public void plan29ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(29);
	}
	public void plan34ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(34);
	}
	public void plan39ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(39);
	}
	public void plan49ButtonListener() throws IOException {
		MainController.getOrderController().setPlan(49);
	}
}
