package superUser;
/*
 * Controller for updating existing employee data
 */
import DataManipulater.DataManipulater;
import Employee.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UpdateEmloyeeController {
	Employee employee = null;

	@FXML
	TextField searchField;
	@FXML
	Button searchButton;
	@FXML
	Label warningLabel;
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
	
	public void searchButtonListener() {
		employee = (Employee) DataManipulater.searchData(Long.parseLong(searchField.getText()), Employee.class);
		if(employee!=null) {
			id.setText(Long.toString(employee.getEmployeeID()));
			phone.setText(employee.getPhoneNumber());
			firstName.setText(employee.getFirstName());
			lastName.setText(employee.getLastName());
			address.setText(employee.getAddress());
			city.setText(employee.getCity());
			state.setText(employee.getState());
			zipCode.setText(employee.getZipcode());
			payRate.setText(Double.toString(employee.getSalary()));
			position.setValue(employee.getPosition());
			ssn.setText(employee.getSSN());
			passWord.setText(employee.getPassowrd());
			hireDate.setValue(employee.getHiredate());
		}
		else {
			warningLabel.setText("ID does not exsit.");
		}
		
	}
	
	public void submitButtonListener() {
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
		
		if(DataManipulater.updateData(employee)){
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Update Employee!");
		alert.setHeaderText("Employee information update.");
		alert.setContentText(null);
		alert.showAndWait();
		}else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("ERROR!");
			alert.setHeaderText("Unable to update information: " + employee.getEmployeeID());
			alert.setContentText(null);
			alert.showAndWait();
		}
	}
	
	public void cancelButtonListener() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}


