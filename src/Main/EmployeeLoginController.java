package Main;

/*
 * Main login controller.
 * Main function to start the system.
 * Include initializing all essential data for new environment.
 */
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import CustomerInfo.Customer;
import CustomerInfo.CustomerGroup;
import DataManipulater.DataManipulater;
import Employee.Employee;
import Order.Plan;
import Order.Product;
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
	private DataManipulater dataManipulater;
	private Employee employee;
	Parent parent;

	@FXML
	private Button loginButton;
	@FXML
	private Button changePassWord;
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
	
	// Initialize all essential data for new database.
	@FXML
	private void initialize() {
		dataManipulater = new DataManipulater();
		employeeRadioButton.setSelected(true);
		if(dataManipulater.searchEmployee(100)==null) {
			Employee init = new Employee();
			init.setEmployeeID(100);
			init.setHiredate(LocalDate.now());
			init.setPhoneNumber("1000000000");
			init.setPosition("Manager");
			init.setFirstName("Admin");
			init.setSalary(100);
			init.setPassowrd("admin");
			dataManipulater.addEmployee(init);
		}
		if(dataManipulater.searchPlan(1L)==null) {
			Plan initplan = new Plan();
			initplan.setPlanID(1L);
			initplan.setAccount("N/A");
			initplan.setPin(0);
			initplan.setPUK("N/A");
			initplan.setPhoneNumber("Unspecified");
			initplan.setCarrier("Unspecifed");
			initplan.setPlanType("Unspecified");
			initplan.setRegularPrice(99);
			initplan.setSim("Unspecified");
			dataManipulater.addPlan(initplan);
		}
		if(dataManipulater.searchCustomer(1L)==null) {
			Customer initCustomer = new Customer();
			CustomerGroup customerGroup = dataManipulater.searchCustomerGroup(1);
			initCustomer.setCustomerID(1);
			initCustomer.setAction("Unspecified");
			initCustomer.setComment("Unspecified");
			initCustomer.setCurrentPlan(dataManipulater.searchPlan(1L));
			initCustomer.setPrePlan(dataManipulater.searchPlan(1L));
			initCustomer.setNewPlan(dataManipulater.searchPlan(1L));
			initCustomer.setCustomerCredit(0);
			initCustomer.setEmployee(dataManipulater.searchEmployee(100));
			initCustomer.setFirstName("Administrator");
			initCustomer.setGroupTitle("Admin");
			initCustomer.setStatus("Unspecified");
			initCustomer.setLTEdata("Unspecified");
			initCustomer.setPhoneNumber("1000000000");
			dataManipulater.addCustomer(initCustomer);
			if(customerGroup==null) {
				customerGroup = new CustomerGroup();
				customerGroup.setGroupdID(1L);
				customerGroup.setGroupPlan("Normal");
				customerGroup.setGroupParent(initCustomer);
				dataManipulater.addCustomerGroup(customerGroup);
			}
			initCustomer.setGroupNumber(customerGroup);
			dataManipulater.updateCustomer(initCustomer);
		}
		Employee employee1 = new Employee();
		System.out.println(employee1.getClass());
		employee1 = (Employee) dataManipulater.searchData(100, employee1.getClass());
		System.out.println(employee1.getEmployeeID());
	}
	// Employee login, start main system
	public void loginListener() throws IOException, SQLException {
		if(usernameField.getText()!=null && passwordField.getText()!=null) 
			employee = dataManipulater.searchEmployee(Integer.parseInt(usernameField.getText()));
		// employee login
		if(employeeRadioButton.isSelected()) {
				if(employee!=null && employee.getPassowrd().equals(passwordField.getText())) {
					Stage stage = new Stage();
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainFX.fxml"));
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
				}
				else wrongPassword.setText("User or Password is not match!");
		}
		else {
			// manager login
			if(employee!=null && employee.getPassowrd().equals(passwordField.getText())) {
				if(employee.getPosition().equals("Manager")) {
				Stage stage = new Stage();
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../superUser/ManagerFX.fxml"));
				parent = (Parent)fxmlLoader.load();
				Scene scene = new Scene(parent);
				stage.setTitle("Digital Mobile");
				stage.setScene(scene);
				stage.show();
				}else wrongPassword.setText("Lack of privilege.");
			}
			else {
				wrongPassword.setText("User or Password is not match!");
			}
		}
	}
	public void changePasswordListener() throws IOException {
		Stage stage = new Stage();
		parent = FXMLLoader.load(getClass().getResource("ChangePasswordFX.fxml"));
		stage.setTitle("Change Password");
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
}
