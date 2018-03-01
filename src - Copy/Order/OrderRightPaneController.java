package Order;

import java.io.IOException;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class OrderRightPaneController {
		Parent parent;
		BorderPane orderPane;
		GridPane carrierpane;
		
		MainController mainController;
		
		@FXML
		Order order;
		
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

		public void setMainController(MainController mainController) {
			this.mainController = mainController;
		}
		
		public void refillListener() throws IOException {
			parent = refillButton.getScene().getRoot();
			orderPane = (BorderPane)parent;
			carrierpane  = FXMLLoader.load(getClass().getResource("../Refill/CarrierFX.fxml"));
			orderPane.setRight(carrierpane);
		}
}

