package Main;

/*
 * Student login controller handle for login.
 */
import java.io.IOException;
import java.sql.SQLException;
import DataManipulater.EmployeeDataManipulater;
import Employee.Employee;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;


public class EmployeeLoginController {
	private EmployeeDataManipulater employeeDataManipulater;
	private Employee employee;
	Parent parent;
	
	@FXML
	private Button loginButton;
	
	@FXML
	private Label wrongPassword;
	
	@FXML
	private PasswordField passwordField;
	
	@FXML
	private TextField usernameField;
	
	@FXML
	private ToggleGroup loginTggle;
	
	@FXML
	private RadioButton employeeRadioButton;
	
	@FXML
	private RadioButton managerRadioButton;
	
	@FXML
	private void initialize() {
		employeeDataManipulater = new EmployeeDataManipulater();
		employeeRadioButton.setSelected(true);
	}
	
	
	public void loginListener() throws IOException, SQLException {
		if(employeeRadioButton.isSelected()) {
			if(usernameField.getText()!=null && passwordField.getText()!=null) {
				employee = employeeDataManipulater.searchEmployee(Integer.parseInt(usernameField.getText()));
				
				System.out.println(employee.getEmployeeID() + employee.getPassowrd());
				if(employee!=null && employee.getPassowrd().equals(passwordField.getText())) {
					Stage stage = new Stage();
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Main/MainFX.fxml"));
					parent = (Parent)fxmlLoader.load();
					MainController mainController = fxmlLoader.<MainController>getController();
					mainController.setEmployee(employee);
					Scene scene = new Scene(parent);
					mainController.setStage(stage);
					stage.setOnCloseRequest(e -> mainController.closeMain());
					stage.setTitle("Digital Mobile");
					stage.setScene(scene);
					((Stage)loginButton.getScene().getWindow()).close();
					stage.show();
				}else {
					wrongPassword.setText("User or Password is not match!");
				}
			}else wrongPassword.setText("User is not exist!");
		}
	}
}
