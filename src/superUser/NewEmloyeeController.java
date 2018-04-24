package superUser;

import DataManipulater.DataManipulater;
import Employee.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class NewEmloyeeController {
	DataManipulater dataManipulater = new DataManipulater();
	Employee employee;

	@FXML
	TextField id;
	@FXML
	TextField phone;
	@FXML
	TextField firstName;
	@FXML
	TextField lastName;
	@FXML
	TextField address;
	@FXML
	TextField city;
	@FXML
	TextField state;
	@FXML
	TextField zipCode;
	@FXML
	TextField payRate;
	@FXML
	TextField ssn;
	@FXML
	PasswordField passWord;
	@FXML
	ComboBox<String> position;
	@FXML
	DatePicker hireDate;
	@FXML
	Button submitButton;
	@FXML
	Button cancelButton;

	@FXML
	public void initialize() {
		position.getItems().addAll("Employee", "Manager");
	}
	
	public void submitButtonListener() {
		employee = new Employee();
		employee.setEmployeeID(Integer.parseInt(id.getText()));
		employee.setAddress(address.getText());
		employee.setCity(city.getText());
		employee.setFirstName(firstName.getText());
		employee.setLastName(lastName.getText());
		employee.setHiredate(hireDate.getValue());
		employee.setPassowrd(passWord.getText());
		employee.setPhoneNumber(phone.getText());
		employee.setPosition(position.getValue());
		employee.setSalary(Double.parseDouble(payRate.getText()));
		employee.setSSN(ssn.getText());
		employee.setState(state.getText());
		employee.setZipcode(zipCode.getText());
		
		if(dataManipulater.addEmployee(employee)){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("New Employee!");
		alert.setHeaderText("New Employee information update.");
		alert.setContentText(null);
		alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setHeaderText("Unable to add employee " + employee.getEmployeeID());
			alert.setContentText(null);
			alert.showAndWait();
		}
	}
	
	public void cancelButtonListener() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}


