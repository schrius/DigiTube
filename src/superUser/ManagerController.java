package superUser;

import DataManipulater.DataManipulater;
import Employee.Employee;
import Employee.EmployeeWorkingTime;
import Main.TableEntry;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ManagerController {
	DataManipulater dataManipulater;
	Employee employee;
	EmployeeWorkingTime worksheet;
	
	@FXML
	ComboBox<String> searchBox;
	@FXML
	TextField searchField;
	
	@FXML
	TableView<? extends TableEntry> table;
	
	// left border
	@FXML
	Button newEmployeeButton;
	@FXML
	Button updateEmployeeButton;
	@FXML
	Button newProductButton;
	@FXML
	Button updateProductButton;
	@FXML
	Button updateOrderButton;
	@FXML
	Button updateCustomerButton;
	
	// right
	@FXML
	DatePicker beginDate;
	@FXML
	DatePicker endDate;
	@FXML
	Button showAndCalcuate;
	
	@FXML
	Label hours;
	@FXML
	Label payRate;
	@FXML
	Label grossPay;
	@FXML
	Label sales;
	@FXML
	Label commission;
	@FXML
	Label totalPay;
	@FXML
	Label refund;
	@FXML
	Label cash;
	@FXML
	Label credit;
	@FXML
	Label unpaid;
	@FXML
	Label discount;
	@FXML
	Label total;
	
	@FXML
	public void initialized() {
		searchBox.getItems().addAll("EmployeeID", "Invoice", "Sales");
		searchBox.getSelectionModel().selectFirst();
	}
	
	public void newEmployeeButtonListener() {
		
	}
	public void updateEmployeeButtonListener() {
		
	}
	public void newProductButtonListener() {
		
	}
	public void updateProductButtonListener() {
		
	}
	public void updateOrderButtonListener() {
		
	}
	public void updateCustomerButtonListener() {
		
	}
	public void showAndCalcuateListener() {
		
	}

}


