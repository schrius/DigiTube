package Order;

import java.io.IOException;
import javax.swing.JOptionPane;

import Main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class OrderRightPaneController {
		BorderPane orderPane;
		GridPane carrierPane;
		GridPane rightPane;
		GridPane subtotalPane;
		FXMLLoader fxmlLoader;
		Parent parent;
		
		double input;
		
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
			MainController.getOrderController().processTransaction(TransactionEnum.REFILL);
		}
		
		public void serviceListener() throws IOException {
			MainController.getOrderController().processTransaction(TransactionEnum.SERIVCE);
		}
		
		public void AcitivationListener() throws IOException {
			MainController.getOrderController().processTransaction(TransactionEnum.ACTIVATION);
		}
		
		public void cashButtonListener() throws IOException {
			MainController.getOrderController().processTransaction(TransactionEnum.CASH);
		}
		public void devicesButtonListener() {
			
		}
		public void accessoriesButtonListener() {
			
		}
		public void payBillButtonListener() {
			
		}
		
		public void cardButtonListener() {
			
		}
		public void refundButtonListener() {
			
		}

		
		public void removeListener() throws IOException{
			if(MainController.getOrderController().getOrderTable().getSelectionModel().getSelectedItem() != null) {
			Order removeOrder = MainController.getOrderController().getOrderTable().getSelectionModel().getSelectedItem();
			MainController.getOrderController().removeTableItem(removeOrder);
			MainController.getOrderController().updateTable();
			}
		}
		
		public void changeQuantityListener() throws IOException {
			if(MainController.getOrderController().getOrderTable().getSelectionModel().getSelectedItem() != null){
				int quantity = Integer.parseInt((JOptionPane.showInputDialog("Enter new quantity")));
			MainController.getOrderController().getOrderTable().getSelectionModel().getSelectedItem().setQuantity(quantity);
			}
			MainController.getOrderController().updateTable();
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
			}
		}
		}
		
		public void printButtonListener() {
			
		}
		public void cancelButtonListener() {
			
		}
		
		public void processButtonListener() {
			
		}
}

