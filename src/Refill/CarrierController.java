package Refill;

import java.io.IOException;

import Main.MainController;
import Order.CarrierNum;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
public class CarrierController {

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
	Button telcelButton;
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
		MainController.getOrderController().setCarrierPane(CarrierNum.LYCA);
	}
	
	public void ultraMobileButtonListener() throws IOException {
		MainController.getOrderController().setCarrierPane(CarrierNum.ULTRA);
	}
	
	public void simpleMobileButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.SIMPLE);
	}
}
