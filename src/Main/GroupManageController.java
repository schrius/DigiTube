package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import CustomerInfo.Customer;
import CustomerInfo.CustomerGroup;
import DataManipulater.DataManipulater;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GroupManageController {
	DataManipulater dataManipulater = new DataManipulater();
	@FXML
	TextField groupNumberField;
	@FXML
	TextField memberField;
	@FXML
	ComboBox<String> groupTitleBox;
	@FXML
	Button submitButton;
	@FXML
	Button cancelButton;
	@FXML
	Button removeButton;
	@FXML
	Label warningLabel;
	
	@FXML
	public void initialize() {
		groupTitleBox.getItems().addAll(FixedElements.CHILD, FixedElements.PARENT);
	}
	
	public void submitButtonListener() {
		Customer child = dataManipulater.searchCustomer(Long.parseLong(memberField.getText()));
		long groupID = Long.parseLong(groupNumberField.getText());
		CustomerGroup customerGroup = dataManipulater.searchCustomerGroup(groupID);
		
		if(customerGroup!=null) {
			if(child==null){
				warningLabel.setText("Child does not exist!");
			}
			else {
				child.setGroupNumber(customerGroup);
				child.setGroupTitle(groupTitleBox.getValue());
				dataManipulater.updateCustomer(child);
				if(child.getGroupTitle().equals(FixedElements.PARENT)) {
					customerGroup.setGroupParent(child);
					Customer currentParent = dataManipulater.searchCustomer(customerGroup.getGroupParent().getCustomerID());
					currentParent.setGroupTitle(FixedElements.CHILD);
					dataManipulater.updateCustomer(currentParent);
					dataManipulater.updateCustomerGroup(customerGroup);
				}
			}
		}
		else warningLabel.setText("Group does not exist! Try again!");
	}
	
	public void removeButtonListener() {
		Customer child = dataManipulater.searchCustomer(Long.parseLong(memberField.getText()));
		CustomerGroup customerGroup = dataManipulater.searchCustomerGroup(100L);
		
		if(customerGroup!=null) {
			if(child==null){
				warningLabel.setText("Child does not exist!");
			}
			else {
				if(child.getGroupTitle().equals(FixedElements.PARENT)) {
					String hql = "FROM Customer WHERE group_id =" + child.getGroupNumber().getGroupdID();
					ObservableList<Customer> children= dataManipulater.customerList(hql);

					List<String> choices = new ArrayList<>();
					for(Customer customer : children) {
						choices.add(customer.getPhoneNumber());
					}
					
					ChoiceDialog<String> dialog = new ChoiceDialog<>(null, choices);
					dialog.setTitle("New Parent");
					dialog.setHeaderText("Parent is removed!");
					dialog.setContentText("Choose new Parent for the group:");
					Optional<String> result = dialog.showAndWait();
					
					result.ifPresent(choice -> {
					Customer newParent = dataManipulater.searchCustomer(Long.parseLong(choice));
					CustomerGroup updateGroup = dataManipulater.searchCustomerGroup(newParent.getGroupNumber().getGroupdID());
					updateGroup.setGroupParent(newParent);
					newParent.setGroupTitle(FixedElements.PARENT);
					
					dataManipulater.updateCustomer(newParent);
					dataManipulater.updateCustomerGroup(updateGroup);
					}
					);
				}
				child.setGroupNumber(customerGroup);
				child.setGroupTitle(FixedElements.PRIME);
				dataManipulater.updateCustomer(child);
			}
		}
		else warningLabel.setText("Group does not exist! Try again!");
	}
		
	
	public void cancelButtonListener() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
