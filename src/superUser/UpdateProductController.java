package superUser;

import DataManipulater.DataManipulater;
import Order.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UpdateProductController {
	DataManipulater dataManipulater = new DataManipulater();
	Product product;

	@FXML
	TextField id;
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
	Button searchButton;
	@FXML
	Button submitButton;
	@FXML
	Button cancelButton;
	
	public void searchButtonListener() {
		product = dataManipulater.searchProduct(Integer.parseInt(id.getText()));
		if(product!=null) {
			productName.setText(product.getProductName());
			price.setText(Double.toString(product.getPrice()));
			barcode.setText(product.getBarcode());
			IMEI.setText(product.getIMEI());
			serial.setText(product.getSerialNumber());
			description.setText(product.getDescription());
			mininumPrice.setText(Double.toString(product.getMinPrice()));
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setHeaderText("Invalid ID " + id.getText());
			alert.setContentText(null);
			alert.showAndWait();
		}
	}
	
	public void submitButtonListener() {
		if(product!=null) {
			product.setBarcode(barcode.getText());
			product.setDescription(description.getText());
			product.setIMEI(IMEI.getText());
			product.setSerialNumber(serial.getText());
			product.setMinPrice(Double.parseDouble(mininumPrice.getText()));
			product.setRegularPrice(Double.parseDouble(price.getText()));
			
			if(dataManipulater.updateProduct(product)){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("New Product!");
				alert.setHeaderText("New Product information update.");
				alert.setContentText("Product ID: " + product.getProductID());
				alert.showAndWait();
				}else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR!");
					alert.setHeaderText("Unable to update product " + product.getProductName());
					alert.setContentText(null);
					alert.showAndWait();
				}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setHeaderText("Search Product before update");
			alert.setContentText(null);
			alert.showAndWait();
		}
	}
	
	public void cancelButtonListener() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}


