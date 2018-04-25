package Main;
// change password controller provide change password function to user
import DataManipulater.DataManipulater;
import Employee.Employee;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ChangePasswordController {
	@FXML
	TextField id;
	@FXML
	PasswordField oldPassword;
	@FXML
	PasswordField newPassword;
	@FXML
	PasswordField confirmPassword;
	@FXML
	Button cancelButton;
	@FXML
	Button submitButton;
	@FXML
	Label warningLabel;
	
	public void submitButtonListener() {
		Employee employee = (Employee) DataManipulater.searchData(Long.parseLong(id.getText()), Employee.class);

		if(employee!=null) {
			if(oldPassword.getText().equals(employee.getPassowrd())){
				if(newPassword.getText().equals(confirmPassword.getText())) {
					employee.setPassowrd(newPassword.getText());
					DataManipulater.updateData(employee);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Success!");
					alert.setHeaderText("Your password has been updated.");
					alert.setContentText(null);
					alert.showAndWait().ifPresent(rs-> {
						if(rs == ButtonType.OK) {
								Stage stage= (Stage) submitButton.getScene().getWindow();
								stage.close();
						}
					});
				}
				else warningLabel.setText("New password does not match.");
			}else warningLabel.setText("Invalid password.");
		}
		else warningLabel.setText("Invalid Employee.");
	}
	
	public void cancelButtonListener() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
