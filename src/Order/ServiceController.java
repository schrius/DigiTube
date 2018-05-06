package Order;
/*
 * Controller for service order input
 */
import java.io.IOException;
import java.time.LocalDate;

import Main.FixedElements;
import Main.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class ServiceController {
	
	@FXML
	Button processButton;
	@FXML
	Button cancelButton;
	@FXML
	DatePicker completeDate;
	@FXML
	ComboBox<String> serviceBox;
	@FXML
	TextField serviceFeeField;
	@FXML
	TextField contactInfo;
	@FXML
	TextField deviceField;
	
	@FXML
	public void initialize() {
		serviceBox.getItems().addAll("Unlock", "Repair", "Other");
		serviceBox.getSelectionModel().selectFirst();
	}
	
	public void cancelButtonListener() throws IOException {
		MainController.getOrderController().updateRightPane();
	}
	
	public void processButtonListener() throws IOException {

		
		Service service = new Service();
		service.setAcceptDate(LocalDate.now());
		service.setCompleteDate(completeDate.getValue());
		service.setServiceType(serviceBox.getValue());
		service.setDevice(deviceField.getText());
		service.setServiceFee(Double.parseDouble(serviceFeeField.getText()));
		service.setContactInfo(contactInfo.getText());
		service.setStatus(FixedElements.WAITING);
		
		MainController.getOrderController().getOrder().setQuantity(1);
		MainController.getOrderController().getOrder().setService(service);
		
		MainController.getOrderController().getOrder().setPrice(service.getServiceFee());

		MainController.getOrderController().processOrder();
	}
	
}
