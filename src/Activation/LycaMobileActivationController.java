package Activation;

import java.io.IOException;
import Main.FixedElements;
import Main.MainController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;

public class LycaMobileActivationController {
	VBox processBox;
	// plans choice
	@FXML
	Button plan19Button;
	@FXML
	Button plan23Button;
	@FXML
	Button plan29Button;
	@FXML
	Button plan35Button;
	@FXML
	Button plan45Button;
	@FXML
	Button plan50Button;
	@FXML
	Button payAsYouGoButton;
	

	public void plan19ButtonListener() throws IOException {
		process(19, FixedElements.P$19);
	}
	public void plan23ButtonListener() throws IOException {
		process(23, FixedElements.P$23);
	}
	
	public void plan29ButtonListener() throws IOException {
		process(29, FixedElements.P$29);
	}
	
	public void plan35ButtonListener() throws IOException {
		process(35, FixedElements.P$35);
	}

	public void plan45ButtonListener() throws IOException {
		process(45, FixedElements.P$45);
	}
	
	public void plan50ButtonListener() throws IOException {
		process(50, FixedElements.P$50);
	}
	
	public void payAsYouGoButtonListener() throws IOException {
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Pay As You Go");
		dialog.setHeaderText(null);
		dialog.setContentText("Please enter refill amount:");
		dialog.showAndWait().ifPresent( plan -> {
			if(!plan.isEmpty()) {
				try {
					process(Integer.parseInt(plan), FixedElements.PSYG);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void process(int price, String plan) throws IOException {
		processBox  = FXMLLoader.load(getClass().getResource("ActivationFX.fxml"));
		MainController.getOrderController().getOrder().getPlan().setPlanType(plan);
		MainController.getOrderController().getOrder().setPrice(price);
		MainController.getOrderController().getOrder().getPlan().setRegularPrice(price);
		MainController.getOrderController().getOrder().setRegularPrice(price);
		MainController.getOrderController().getOrderPane().setRight(processBox);
	}
}
