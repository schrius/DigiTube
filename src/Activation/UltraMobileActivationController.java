package Activation;

import java.io.IOException;

import Main.FixedElements;
import Main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class UltraMobileActivationController {
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
	
	Button plan23X2Button;
	@FXML
	Button plan29X2Button;
	@FXML
	Button plan35X2Button;

	public void plan19ButtonListener() throws IOException {
		process(19, FixedElements.P$19);
	}
	
	public void plan23ButtonListener() throws IOException {
		
	}
	
	public void plan23X2ButtonListener() throws IOException {
		
	}
	public void plan29ButtonListener() throws IOException {
		
	}
	public void plan29X2ButtonListener() throws IOException {
		
	}
	public void plan35ButtonListener() throws IOException {
		
	}
	public void plan35X2ButtonListener() throws IOException {
		
	}
	public void process(int price, String plan) throws IOException {
		processBox  = FXMLLoader.load(getClass().getResource("ActivationFX.fxml"));
		MainController.getOrderController().getOrder().getPlan().setPlanType(plan);
		MainController.getOrderController().getOrder().setPrice(price);
		MainController.getOrderController().getOrder().setRegularPrice(price);
		MainController.getOrderController().getOrderPane().setRight(processBox);
	}
}
