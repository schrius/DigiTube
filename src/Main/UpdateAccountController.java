package Main;

import CustomerInfo.Customer;
import DataManipulater.DataManipulater;
import Order.Plan;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateAccountController {
	@FXML
	TextField numberField;
	@FXML
	TextField accountField;
	@FXML
	TextField pinField;
	@FXML
	ComboBox<String> planBox;
	@FXML
	Button submitButton;
	@FXML
	Button cancelButton;
	@FXML
	Label warningLabel;
	
	@FXML
	public void initialize()  {
		planBox.getItems().addAll( "CurrentPlan", "PrePlan");
		planBox.getSelectionModel().selectFirst();
	}
	
	public void submitButtonListener() {
		DataManipulater dataManipulater = new DataManipulater();
		Customer customer = dataManipulater.searchCustomer(Long.parseLong(numberField.getText()));
		if(customer!=null) {
			if(planBox.getValue().equals("CurrentPlan")) {
				if(customer.getCurrentPlan().getPlanID() == 1) {
					Plan currentPlan = new Plan();
					currentPlan.setPhoneNumber(customer.getPhoneNumber());
					currentPlan.setAccount(accountField.getText());
					currentPlan.setPin(Integer.parseInt(pinField.getText()));
					customer.setCurrentPlan(currentPlan);
				}
				else {
					customer.getCurrentPlan().setAccount(accountField.getText());
					customer.getCurrentPlan().setPin(Integer.parseInt(pinField.getText()));
				}
				dataManipulater.updateCustomer(customer);
			}
			else {
				if(customer.getPrePlan().getPlanID() == 1) {
					Plan prePlan = new Plan();
					prePlan.setPhoneNumber(customer.getPhoneNumber());
					prePlan.setAccount(accountField.getText());
					prePlan.setPin(Integer.parseInt(pinField.getText()));
					customer.setPrePlan(prePlan);
				}
				else {
					customer.getPrePlan().setAccount(accountField.getText());
					customer.getPrePlan().setPin(Integer.parseInt(pinField.getText()));
				}
				dataManipulater.updateCustomer(customer);
			}
		}
		else warningLabel.setText("Customer does not exist! Try again!");
	}
	
	public void cancelButtonListener() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
