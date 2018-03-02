package Order;

import java.io.IOException;

import javax.swing.JOptionPane;
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
			MainController.getOrderController().setOrder(new Order());
			MainController.getOrderController().getOrder().setCategories(MainController.REFILL);
		}
		
		public void serviceListener() throws IOException {
			//order = new Order();
			//order.setCategories(MainController.refill);
		}
		
		public void AcitivationListener() throws IOException {
			carrierPane  = FXMLLoader.load(getClass().getResource("../Activation/ActivationCarrierFX.fxml"));
			MainController.getOrderController().getOrderPane().setRight(carrierPane);
			MainController.getOrderController().setOrder(new Order());
			MainController.getOrderController().getOrder().setCategories(MainController.ACTIVATION);
		}
		
		public void removeListener() throws IOException{
			if(MainController.getOrderController().getOrderTable().getSelectionModel().getSelectedItem() != null) {
			Order removeOrder = MainController.getOrderController().getOrderTable().getSelectionModel().getSelectedItem();
			MainController.getOrderController().removeTableItem(removeOrder);
			MainController.getOrderController().updateTable();
			MainController.getOrderController().updateTotal();
			}
		}
		
		public void changeQuantityListener() throws IOException {
			if(MainController.getOrderController().getOrderTable().getSelectionModel().getSelectedItem() != null){
				int quantity = Integer.parseInt((JOptionPane.showInputDialog("Enter new quantity")));
			MainController.getOrderController().getOrderTable().getSelectionModel().getSelectedItem().setQuantity(quantity);
			}
			MainController.getOrderController().updateTable();
			MainController.getOrderController().updateTotal();
		}
		
		public void changePriceListener() throws IOException{
			if(MainController.getOrderController().getOrderTable().getSelectionModel().getSelectedItem() != null){

				String newPrice = JOptionPane.showInputDialog("Enter New Price");
				if(newPrice != null) {
			MainController.getOrderController().getOrderTable().getSelectionModel().getSelectedItem().setPrice(Double.parseDouble(newPrice));
			MainController.getOrderController().getOrderTable().getSelectionModel().getSelectedItem().setDiscount(
					MainController.getOrderController().getOrderTable().getSelectionModel()
					.getSelectedItem().getRegularPrice() - Double.parseDouble(newPrice));
			MainController.getOrderController().updateTable();
			MainController.getOrderController().updateTotal();
			}
		}
		}
}

