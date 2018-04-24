package Order;

import java.io.IOException;
import java.math.BigDecimal;
import Main.FixedElements;
import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class OrderRightPaneController {

		boolean paid;
		String payment;
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
		@FXML
		Button processButton;
		
		@FXML
		public void initialize() {
			paid = false;
		}
		
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

			this.payment = FixedElements.CASH;
			MainController.getOrderController().getInvoice().setPaymentMethod(FixedElements.CREDIT);
			paid = true;
		}
		public void devicesButtonListener() throws IOException {
			MainController.getOrderController().processTransaction(TransactionEnum.DEVICE);
		}
		public void accessoriesButtonListener() throws IOException {
			MainController.getOrderController().processTransaction(TransactionEnum.ACCESSORIES);
		}
		public void payBillButtonListener() throws IOException {
			MainController.getOrderController().processTransaction(TransactionEnum.PAYBILL);
		}
		
		public void cardButtonListener() {
			MainController.getOrderController().getInvoice().setPaymentMethod(FixedElements.CREDIT);
			MainController.getOrderController().receiveCash(new BigDecimal(0));
			this.payment = FixedElements.CREDIT;
			paid = true;
		}
		public void refundButtonListener() {
			MainController.getOrderController().refund();
		}
		
		public void unpaidButtonListener() {
			MainController.getOrderController().receiveCash(new BigDecimal(0));

			this.payment = FixedElements.UNPAID;
			MainController.getOrderController().getInvoice().setPaymentMethod(FixedElements.UNPAID);
			paid = true;
		}
		
		public void removeListener() throws IOException{
			MainController.getOrderController().removeItem();
		}
		
		public void changeQuantityListener() throws IOException {
			MainController.getOrderController().changeQuantity();
		}
		
		public void changePriceListener() throws IOException{
			MainController.getOrderController().changePrice();
		}
		
		public void printButtonListener() throws IOException {
				MainController.getOrderController().print();
		}
		public void cancelButtonListener() {
			Stage stage = (Stage) cancelButton.getScene().getWindow();
			stage.close();
		}
		
		public void processButtonListener() throws IOException {
			if(paid) {
				MainController.getOrderController().completeOrder(payment);
				paid = false;
				}
			}
}

