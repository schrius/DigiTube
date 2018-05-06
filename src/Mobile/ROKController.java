package Mobile;
/*
 * ROK Mobile plans controller
 */
import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ROKController {
	// plans choice
	@FXML
	Button plan10Button;
	@FXML
	Button plan40Button;
	@FXML
	Button plan60Button;
	
	public void plan10ButtonListener() throws IOException {		
		MainController.getOrderController().setPlan(10);
	}

	public void plan40ButtonListener() throws IOException {		
		MainController.getOrderController().setPlan(40);
	}

	public void plan60ButtonListener() throws IOException {		
		MainController.getOrderController().setPlan(60);
	}
}
