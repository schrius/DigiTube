package Order;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class OrderRightPaneController {
		BorderPane orderPane;
		GridPane carrierPane;
		GridPane subtotalPane;
		
		// Orders buttons
		@FXML
		Button refillButton;
		@FXML
		Button activationButton;
		@FXML
		Button serviceButton;
		@FXML
		Button devicesButton;
		@FXML
		Button accessoriesButton;
		@FXML
		Button payBillButton;
		
		// payment method
		@FXML
		Button cashButton;
		@FXML
		Button cardButton;
		@FXML
		Button unpaidButton;
		
		@FXML
		Button changeQuantityButton;
		@FXML
		Button changePriceButton;
		@FXML
		Button removeButton;
		
		@FXML
		Button printButton;
		@FXML
		Button refundButton;
		@FXML
		Button cancelButton;
		
		public void refillListener() throws IOException {
			carrierPane  = FXMLLoader.load(getClass().getResource("../Refill/CarrierFX.fxml"));
			
			MainController.getOrderController().getOrderPane().setRight(carrierPane);
		}
		

}

