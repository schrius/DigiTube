package Main;

import java.io.IOException;
import java.time.LocalDate;

import Order.OrderController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class MainController {
	private static Employee employee;

	// search customer information by phone
	@FXML
	TextField searchField;
	@FXML
	Button searchButton;
	
	// create transaction
	@FXML
	Button orderButton;
	
	// search date picker
	@FXML
	DatePicker beginDate;
	@FXML
	DatePicker endDate;
	
	// show associated lists items
	@FXML
	Button toDoListButton;
	@FXML
	Button payBillListButton;
	@FXML
	Button repairListButton;
	@FXML
	Button unlockListButton;
	@FXML
	Button unpaidListButton;
	
	// Prepaid list components
	@FXML
	CheckBox refillBox;
	@FXML
	CheckBox swapBox;
	@FXML
	CheckBox swapFamilyBox;
	@FXML
	CheckBox flexBox;
	@FXML
	CheckBox flexMixBox;
	@FXML
	CheckBox GVBox;
	@FXML
	CheckBox GVFamilyBox;
	@FXML
	Button showListButton;

	// Operator information
	@FXML
	Label nameLabel;
	@FXML
	Label currentDateLabel;
	@FXML
	TextArea noteArea;
	
	@FXML
	public void initialize() throws IOException, InterruptedException {
		 currentDateLabel.setText(LocalDate.now().toString());
	}

	public void orderButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Order/OrderFX.fxml"));
		OrderController.setOrderPane(fxmlLoader.load());
		stage.setScene(new Scene(OrderController.getOrderPane()));
		stage.showAndWait();
	}
	
	public void setEmployee(Employee employee) {
		MainController.employee = new Employee(employee.getEmployeeID(), employee.getName(), employee.getPhoneNumber(),
				employee.getSalary() ,employee.getHiredate());
		 nameLabel.setText(employee.getName());
	}
	public static Employee getEmloyee() {
		return employee;
	}
}
