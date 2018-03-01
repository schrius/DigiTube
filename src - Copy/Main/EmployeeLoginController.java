package Main;

/*
 * Student login controller handle for login.
 */
import java.io.IOException;
import java.sql.SQLException;
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
	
	
	public void loginListener() throws IOException, SQLException {
		Stage stage = new Stage();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainFX.fxml"));
		parent = (Parent)fxmlLoader.load();
		MainController mainController = fxmlLoader.<MainController>getController();
		mainController.setEmployee(new Employee(1112, "Jrfry", "3473303324", 8888, "2018-05-07"));

		Scene scene = new Scene(parent);
		stage.setTitle("Digital Mobile");
		stage.setScene(scene);
		((Stage)loginButton.getScene().getWindow()).close();
		stage.show();
		
		/*		if(username.getText()!=null && password.getText()!=null) {
		String sql = "SELECT StudentID, Password FROM Student WHERE StudentID ='"
				+ username.getText() + "'";
		RegisterJDBC.excuteSQL(sql);

		if(RegisterJDBC.result.next()) {
		if((RegisterJDBC.result.getString("StudentID")).equals(username.getText())&&
				(RegisterJDBC.result.getString("Password")).equals(password.getText())) {
		//load student information after login
			sql = "SELECT * FROM Student WHERE StudentID ='" + username.getText() + "'";
			RegisterJDBC.excuteSQL(sql);
			RegisterJDBC.result.next();

		Stage stage = (Stage)loginButton.getScene().getWindow();
		parent = FXMLLoader.load(getClass().getResource("StudentCenterFX.fxml"));
		Scene scene = new Scene(parent);
		stage.setScene(scene);
		}else
		wrongPassword.setText("User or Password is not match!");
		}
		else {
			wrongPassword.setText("User does not exist.");
		}
		}*/
	}
}
