package Main;

import java.io.IOException;
import java.time.LocalDate;

import Order.OrderController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
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
	final static public double TAXRATE = 0.08875;
	final static public double NYTAX = 1.2;
	final static public String refill = "Refill";
	
	private static Employee employee;
	private static OrderController orderController;
	
	public static Employee getEmloyee() {
		return employee;
	}

	public static Employee getEmployee() {
		return employee;
	}

	public static OrderController getOrderController() {
		return orderController;
	}
	public static void setOrderController(OrderController orderController) {
		MainController.orderController = orderController;
	}
	
	
	Parent parent;
	// search date picker
	@FXML
	DatePicker beginDate;
	@FXML
	Label currentDateLabel;
	
	@FXML
	DatePicker endDate;
	
	@FXML
	CheckBox flexBox;
	@FXML
	CheckBox flexMixBox;
	
	@FXML
	CheckBox GVBox;
	@FXML
	CheckBox GVFamilyBox;
	// Operator information
	@FXML
	Label nameLabel;
	@FXML
	TextArea noteArea;
	// create transaction
	@FXML
	Button orderButton;

	@FXML
	Button payBillListButton;
	// Prepaid list components
	@FXML
	CheckBox refillBox;
	@FXML
	Button repairListButton;
	@FXML
	Button searchButton;
	// search customer information by phone
	@FXML
	TextField searchField;
	@FXML
	Button showListButton;
	@FXML
	CheckBox swapBox;

	@FXML
	CheckBox swapFamilyBox;
	// show associated lists items
	@FXML
	Button toDoListButton;
	@FXML
	Button unlockListButton;
	
	@FXML
	Button unpaidListButton;

	@FXML
	public void initialize() throws IOException, InterruptedException {
		 currentDateLabel.setText(LocalDate.now().toString());
	}
	
	public void orderButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Order/OrderFX.fxml"));
		orderController = new OrderController();
		fxmlLoader.setController(orderController);
		parent = fxmlLoader.load();
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
	public void setEmployee(Employee employee) {
		MainController.employee = new Employee(employee.getEmployeeID(), employee.getName(), employee.getPhoneNumber(),
				employee.getSalary() ,employee.getHiredate());
		 nameLabel.setText(employee.getName());
	}
}
