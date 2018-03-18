package Order;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SubtotalController {
	@FXML
	Label discountLabel;
	@FXML
	Label NYTaxLabel;

	@FXML
	Label subtotalLabel;

	@FXML
	Label taxLabel;

	@FXML
	Label totalLabel;
	@FXML
	Label receiveLabel;
	@FXML
	Label returnBalanceLabel;
	@FXML
	Label serviceFeeLabel;

	public Label getDiscountLabel() {
		return discountLabel;
	}

	public Label getNYTaxLabel() {
		return NYTaxLabel;
	}

	public Label getSubtotalLabel() {
		return subtotalLabel;
	}

	public Label getTaxLabel() {
		return taxLabel;
	}

	public Label getTotalLabel() {
		return totalLabel;
	}

	public void setDiscountLabel(Label discountLabel) {
		this.discountLabel = discountLabel;
	}
	public void setNYTaxLabel(Label nYTaxLabel) {
		NYTaxLabel = nYTaxLabel;
	}
	public void setSubtotalLabel(Label subtotalLabel) {
		this.subtotalLabel = subtotalLabel;
	}
	public void setSubtotalLable(double subtotal) {
		subtotalLabel.setText(Double.toString(subtotal));
	}
	
	public void setTaxLabel(Label taxLabel) {
		this.taxLabel = taxLabel;
	}
	
	public void setTotalLabel(Label totalLabel) {
		this.totalLabel = totalLabel;
	}
	/*
	public void updateSubtotal() {
		subtotalLabel.setText("$" + MainController.getOrderController().getSubtotal());
		taxLabel.setText("$" + MainController.getOrderController().getTax());
		discountLabel.setText("$ -" + MainController.getOrderController().getDiscount());
		NYTaxLabel.setText("$" + MainController.getOrderController().getNYtax());
		totalLabel.setText("$" + MainController.getOrderController().getTotal());
		
		if(MainController.getOrderController().getReceiveAmount()!=null) {
		receiveLabel.setText("$" + MainController.getOrderController().getReceiveAmount());
		returnBalanceLabel.setText("$" + (MainController.getOrderController().getTotal()
				.subtract(MainController.getOrderController().getReceiveAmount())));
		}
	}
	*/
}
