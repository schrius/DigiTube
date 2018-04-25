package Main;
// manage group members
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
		long groupID = Long.parseLong(groupNumberField.getText());
		if(groupID>2 && groupID<11) {
		if(memberField.getText().length()==10) {
		Customer child = dataManipulater.searchCustomer(Long.parseLong(memberField.getText()));
		CustomerGroup customerGroup = dataManipulater.searchCustomerGroup(groupID);
		
		if(customerGroup!=null) {
			if(child==null){
				warningLabel.setText("Child does not exist!");
			}
			else {
				if(!child.getGroupTitle().equals(FixedElements.PARENT)) {
				child.setGroupNumber(customerGroup);
				child.setGroupTitle(groupTitleBox.getValue());
				dataManipulater.updateCustomer(child);
				if(child.getGroupTitle().equals(FixedElements.PARENT)) {
					Customer currentParent = dataManipulater.searchCustomer(customerGroup.getGroupParent().getCustomerID());
					customerGroup.setGroupParent(child);
					currentParent.setGroupTitle(FixedElements.CHILD);
					dataManipulater.updateCustomer(currentParent);
					dataManipulater.updateCustomerGroup(customerGroup);
					}
				}
				else {
					warningLabel.setText("Child is a parent of " + child.getGroupNumber().getGroupdID());
				}
			}
		}
		else warningLabel.setText("Group does not exist! Try again!");
		} else warningLabel.setText("Member id is 10 digits.");
		}
		else warningLabel.setText("Group ID must range 3-10 digit!");
	}
	
	public void removeButtonListener() {
		long groupID = Long.parseLong(groupNumberField.getText());
		if(memberField.getText().length()==10) {
			if(groupID>2 && groupID<11) {
		Customer child = dataManipulater.searchCustomer(Long.parseLong(memberField.getText()));
		CustomerGroup customerGroup = dataManipulater.searchCustomerGroup(1L);
		
		if(customerGroup!=null) {
			if(child==null){
				warningLabel.setText("Child does not exist!");
			}
			else {
				if(customerGroup.getGroupdID() == child.getGroupNumber().getGroupdID()) {
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
					});
				}
				child.setGroupNumber(dataManipulater.searchCustomerGroup(1L));
				child.setGroupTitle(FixedElements.PRIME);
				dataManipulater.updateCustomer(child);
				} else warningLabel.setText("Customer belong to another group.");
			}
			}
			else warningLabel.setText("Group does not exist! Try again!");
		} else warningLabel.setText("Member id is 10 digits.");
		}
		else warningLabel.setText("Group ID must range 3-10 digit!");
	}
	public void cancelButtonListener() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
}
