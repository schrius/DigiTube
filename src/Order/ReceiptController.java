package Order;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class ReceiptController {
	@FXML
	Label invoiceDate;
	
	@FXML
	Label invoiceNumber;
	
	@FXML
	GridPane itemlist;

	public Label getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Label invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public Label getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(Label invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

	public GridPane getItemlist() {
		return itemlist;
	}

	public void setItemlist(GridPane itemlist) {
		this.itemlist = itemlist;
	}
	
	
}
