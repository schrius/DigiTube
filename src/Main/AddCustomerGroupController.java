package Main;

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
		DataManipulater dataManipulater = new DataManipulater();
		CustomerGroup customerGroup = new CustomerGroup();
		Customer parent = null;
		customerGroup.setGroupdID(Long.parseLong(groupNumberField.getText()));
		customerGroup.setGroupPlan(groupPlanBox.getValue());
		if(dataManipulater.searchCustomerGroup(customerGroup.getGroupdID())==null) {
			parent = dataManipulater.searchCustomer(Long.parseLong(groupParentField.getText()));
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
					parent.setGroupTitle(FixedElements.PARENT);
					dataManipulater.updateCustomer(parent);
					dataManipulater.addCustomerGroup(customerGroup);
				}

			}
		}
		else warningLabel.setText("Group already exist! Try again!");
	}
	
	public void cancelButtonListener() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
