package Refill;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class CarrierController {

	VBox planBox;
	FXMLLoader fxmlLoader;

	// Carrier choice
	@FXML
	Button lycaMobileButton;
	@FXML
	Button ultraMobileButton;
	@FXML
	Button simpleMobileButton;
	@FXML
	Button tMobileButton;
	@FXML
	Button aTTButton;
	@FXML
	Button H2OButton;
	@FXML
	Button tellcellButton;
	@FXML
	Button goSmartButton;
	@FXML
	Button net10Button;
	@FXML
	Button easyGoButton;
	@FXML
	Button cTMobileButton;
	@FXML
	Button rokMobileButton;
	@FXML
	Button eOTButton;

	
	public void lycaMobileButtonListener() throws IOException {
		planBox  = FXMLLoader.load(getClass().getResource("../Refill/LycaMobileFX.fxml"));
		MainController.getOrderController().getOrder().setCarrier("Lyca");
		MainController.getOrderController().getOrderPane().setRight(planBox);

	}
	
	public void ultraMobileButtonListener() throws IOException {
		planBox  = FXMLLoader.load(getClass().getResource("../Refill/UltraMobileFX.fxml"));
		MainController.getOrderController().getOrder().setCarrier("Ultra");
		MainController.getOrderController().getOrderPane().setRight(planBox);
	}
	
	public void simpleMobileButtonListener() throws IOException {
		planBox  = FXMLLoader.load(getClass().getResource("../Refill/SimpleMobileFX.fxml"));
		MainController.getOrderController().getOrder().setCarrier("Simple");
		MainController.getOrderController().getOrderPane().setRight(planBox);
	}
}
