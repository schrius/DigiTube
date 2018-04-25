package superUser;

import DataManipulater.DataManipulater;
import Order.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NewProductController {
	DataManipulater dataManipulater = new DataManipulater();
	Product product;

	@FXML
	TextField productName;
	@FXML
	TextField price;
	@FXML
	TextField barcode;
	@FXML
	TextField IMEI;
	@FXML
	TextField serial;
	@FXML
	TextField description;
	@FXML
	TextField mininumPrice;
	@FXML
	Button submitButton;
	@FXML
	Button cancelButton;
	
	public void submitButtonListener() {
		product = new Product();
		product.setBarcode(barcode.getText());
		product.setDescription(description.getText());
		product.setIMEI(IMEI.getText());
		product.setSerialNumber(serial.getText());
		product.setMinPrice(Double.parseDouble(mininumPrice.getText()));
		product.setRegularPrice(Double.parseDouble(price.getText()));
		
		if(dataManipulater.addProduct(product)){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("New Product!");
		alert.setHeaderText("New Product information update.");
		alert.setContentText("Product ID: " + product.getProductID());
		alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setHeaderText("Unable to add employee " + product.getProductName());
			alert.setContentText(null);
			alert.showAndWait();
		}
	}
	
	public void cancelButtonListener() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}


