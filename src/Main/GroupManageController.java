package Main;
/*
 *  Manage group members controller
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import CustomerInfo.Customer;
import CustomerInfo.CustomerGroup;
import DataManipulater.DataManipulater;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class GroupManageController {
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
		if(groupNumberField.getText().length()>2 && groupNumberField.getText().length()<11) {
		if(memberField.getText().length()==10) {
		Customer child = (Customer) DataManipulater.searchData(Long.parseLong(memberField.getText()), Customer.class);
		CustomerGroup customerGroup = (CustomerGroup) DataManipulater.searchData(groupID, CustomerGroup.class);
		
		if(customerGroup!=null) {
			if(child==null){
				warningLabel.setText("Child does not exist!");
			}
			else {
				if(!child.getGroupTitle().equals(FixedElements.PARENT)) {
				child.setGroupNumber(customerGroup);
				child.setGroupTitle(groupTitleBox.getValue());
				DataManipulater.updateData(child);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Group Added");
				alert.setHeaderText("Success adding group member to " + customerGroup.getGroupdID());
				alert.setContentText(null);
				alert.showAndWait().ifPresent(status -> {
					((Stage)submitButton.getScene().getWindow()).close();
				});
				if(child.getGroupTitle().equals(FixedElements.PARENT)) {
					Customer currentParent = (Customer) DataManipulater.searchData(customerGroup.getGroupParent().getCustomerID(), Customer.class);
					customerGroup.setGroupParent(child);
					currentParent.setGroupTitle(FixedElements.CHILD);
					DataManipulater.updateData(currentParent);
					DataManipulater.updateData(customerGroup);

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
	
	@SuppressWarnings("unchecked")
	public void removeButtonListener() {
		long groupID = Long.parseLong(groupNumberField.getText());
		if(memberField.getText().length()==10) {
			if(groupID>2 && groupID<11) {
		Customer child = (Customer) DataManipulater.searchData(Long.parseLong(memberField.getText()), Customer.class);
		CustomerGroup customerGroup = (CustomerGroup) DataManipulater.searchData(1L, CustomerGroup.class);
		
		if(customerGroup!=null) {
			if(child==null){
				warningLabel.setText("Child does not exist!");
			}
			else {
				if(customerGroup.getGroupdID() == child.getGroupNumber().getGroupdID()) {
				if(child.getGroupTitle().equals(FixedElements.PARENT)) {
					String hql = "FROM Customer WHERE group_id =" + child.getGroupNumber().getGroupdID();
					ObservableList<Customer> children= (ObservableList<Customer>) DataManipulater.ListData(hql);

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
					Customer newParent = (Customer) DataManipulater.searchData(Long.parseLong(choice), Customer.class);
					CustomerGroup updateGroup = (CustomerGroup) DataManipulater.searchData(newParent.getGroupNumber().getGroupdID(), CustomerGroup.class);
					updateGroup.setGroupParent(newParent);
					newParent.setGroupTitle(FixedElements.PARENT);
					
					DataManipulater.updateData(newParent);
					DataManipulater.updateData(updateGroup);
					});
				}
				child.setGroupNumber((CustomerGroup) DataManipulater.searchData(1L, CustomerGroup.class));
				child.setGroupTitle(FixedElements.PRIME);
				DataManipulater.updateData(child);
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
