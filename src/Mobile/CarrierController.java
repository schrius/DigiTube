package Mobile;

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
	Button FlexButton;
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
	@FXML
	Button redPocketButton;
	@FXML
	Button otherButton;

	
	public void lycaMobileButtonListener() throws IOException {
		MainController.getOrderController().setCarrierPane(CarrierNum.LYCA);
	}
	
	public void ultraMobileButtonListener() throws IOException {
		MainController.getOrderController().setCarrierPane(CarrierNum.ULTRA);
	}
	
	public void simpleMobileButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.SIMPLE);
	}
	public void tMobileButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.TMOBILE);
	}
	public void aTTButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.ATT);
	}
	public void H2OButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.H2O);
	}
	public void telcelButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.TELCEL);
	}
	public void goSmartButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.GOSMART);
	}
	public void net10ButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.NET10);
	}
	public void easyGoButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.EASYGO);
	}
	public void cTMobileButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.CT);
	}
	public void rokMobileButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.ROK);
	}
	public void eOTButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.EOT);
	}
	public void FlexButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.FLEX);
	}
	public void redPocketButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.REDPOCKET);
	}
	public void otherButtonListener() throws IOException {		
		MainController.getOrderController().setCarrierPane(CarrierNum.OTHER);
	}
	
}
