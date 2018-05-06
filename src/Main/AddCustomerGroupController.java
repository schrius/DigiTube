package Main;
// Controller for adding new customer group
import CustomerInfo.Customer;
import CustomerInfo.CustomerGroup;
import DataManipulater.DataManipulater;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AddCustomerGroupController {
	@FXML
	TextField groupNumberField;
	@FXML
	TextField groupParentField;
	@FXML
	ComboBox<String> groupPlanBox;
	@FXML
	Button submitButton;
	@FXML
	Button cancelButton;
	@FXML
	Label warningLabel;
	
	@FXML
	public void initialize()  {
		groupPlanBox.getItems().addAll(FixedElements.GROUP);
	}
	
	public void submitButtonListener() {
		CustomerGroup customerGroup = new CustomerGroup();
		Customer parent = null;
		if(groupNumberField.getText().length()>2 && groupNumberField.getText().length()<11) {
		customerGroup.setGroupdID(Long.parseLong(groupNumberField.getText()));
		customerGroup.setGroupPlan(groupPlanBox.getValue());
		if(DataManipulater.searchData(customerGroup.getGroupdID(), CustomerGroup.class)==null) {
			parent = (Customer) DataManipulater.searchData(Long.parseLong(groupParentField.getText()), Customer.class);
			if(parent==null) {
				warningLabel.setText("Parent does not exist!");
			}
			else {
				if(parent.getGroupTitle().equals(FixedElements.PARENT)) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Error");
					alert.setHeaderText("The parent belong to another group!");
					alert.setContentText("Choose another one.");
					alert.showAndWait();
				}
				else {
					customerGroup.setGroupParent(parent);
					DataManipulater.addData(customerGroup);
					parent.setGroupTitle(FixedElements.PARENT);
					parent.setGroupNumber(customerGroup);
					DataManipulater.updateData(parent);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Group Added");
					alert.setHeaderText("Success adding group!" + customerGroup.getGroupdID());
					alert.setContentText(null);
					alert.showAndWait().ifPresent(status -> {
						((Stage)submitButton.getScene().getWindow()).close();
					});
				}
				}
			}
			else warningLabel.setText("Group already exist! Try again!");
		}else warningLabel.setText("Group ID must range from 3-10 digit.");
	}
	
	public void cancelButtonListener() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
