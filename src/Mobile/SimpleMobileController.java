package Mobile;
/*
 * Simple Mobile plans controller
 */
import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SimpleMobileController {
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
		MainController.getOrderController().setPlan(25);
	}
	public void plan30ButtonListener() throws IOException {		
		MainController.getOrderController().setPlan(30);
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
