package Refill;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class SimpleMobileController {
	Parent parent;
	BorderPane orderPane;
	BorderPane refillPane;
	
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
		parent = plan25Button.getScene().getRoot();
		orderPane = (BorderPane)parent;
		refillPane  = FXMLLoader.load(getClass().getResource("RefillFX.fxml"));
		orderPane.setRight(refillPane);
	}
}
