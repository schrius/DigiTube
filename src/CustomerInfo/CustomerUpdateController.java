package CustomerInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import DataManipulater.CustomerDataManipulater;
import DataManipulater.CustomerGroupDataManipulater;
import Employee.Employee;
import Main.FixedElements;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class CustomerUpdateController {
	
	Employee employee;
	Customer customer;
	CustomerDataManipulater customerDataManipulater;
	CustomerGroupDataManipulater customerGroupDataManipulater;
	
	@FXML
	TextField searchField;
	@FXML
	Button searchButton;
	@FXML
	Label warningLabel;
	
	@FXML
	TextField customerIDField;
	@FXML
	TextField phoneField;
	@FXML
	TextField deviceField;
	@FXML
	TextField languageField;
	@FXML
	TextField LTEField;
	@FXML
	TextField priceField;
	@FXML
	TextField groupNumber;
	@FXML
	TextField newSimField;
	@FXML
	TextField simField;
	@FXML
	TextField PUKField;
	@FXML
	TextField accountField;
	@FXML
	TextField pinField;
	@FXML
	TextField commentField;
	@FXML
	TextField firstnameField;
	@FXML
	TextField lastNameField;
	@FXML
	TextField addressField;
	@FXML
	TextField cityField;
	@FXML
	TextField zipCodeField;
	@FXML
	DatePicker newExpireDate;
	@FXML
	DatePicker ExpireDate;
	@FXML
	DatePicker portDate;
	@FXML
	ComboBox<String> newCarrier;
	@FXML
	ComboBox<String> carrier;
	@FXML
	ComboBox<String> preCarrier;
	@FXML
	ComboBox<String> group;
	@FXML
	ComboBox<String> groupTitle;
	@FXML
	ComboBox<String> status;
	@FXML
	ComboBox<String> newPlan;
	@FXML
	ComboBox<String> plan;
	@FXML
	ComboBox<String> state;
	@FXML
	ComboBox<String> actionBox;
	
	/* 
	 *OweAmountField is not editable.
	 *Customer Owe Amount Field should not allow to edit by employee 
	*/
	@FXML
	TextField oweAmountField;
	
	@FXML
	Label creditLabel;
	@FXML
	Label lastUpdate;
	@FXML
	Label updateEmployee;
	
	@FXML
	Button cancelButton;
	@FXML
	Button submitButton;
	
	@FXML
	public void initialize() {
		newCarrier.getItems().addAll(FixedElements.CARRIERS);
		carrier.getItems().addAll(FixedElements.CARRIERS);
		preCarrier.getItems().addAll(FixedElements.CARRIERS);
		group.getItems().addAll(FixedElements.GROUP);
		groupTitle.getItems().addAll(FixedElements.GROUPTITLE);
		status.getItems().addAll(FixedElements.ACTIVATIONSTATUS);
		newPlan.getItems().addAll(FixedElements.PLAN);
		plan.getItems().addAll(FixedElements.PLAN);
		state.getItems().addAll(FixedElements.STATES);
		actionBox.getItems().addAll(FixedElements.ACTION);
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	//search customer info and display on the pane
	public void searchButtonListener() {
		if(searchField.getText().length() == 10) {
			if(customerDataManipulater == null) {
				customerDataManipulater = new CustomerDataManipulater();
			}
			customer = customerDataManipulater.searchCustomer(Long.parseLong(searchField.getText()));
			if(customer!=null) {
					System.out.println(customer.getCustomerID());
					warningLabel.setText("");
					customerIDField.setText(Long.toString(customer.getCustomerID()));
					phoneField.setText(customer.getPhoneNumber());

					pinField.setText(Integer.toString(customer.getPin()));
					oweAmountField.setText(Double.toString(customer.getOweAmount()));
					creditLabel.setText("Credit: " + customer.getCustomerCredit());
					if(customer.getLanguage()!=null) {
						languageField.setText(customer.getLanguage());
					}
					if(customer.getCarrier()!=null) {
						carrier.setValue(customer.getCarrier());
					}
					if(customer.getAction()!=null) {
						actionBox.setValue(customer.getAction());
					}
					if(customer.getNewCarrier()!=null) {
						newCarrier.setValue(customer.getCarrier());
					}
					if(customer.getPreCarrier()!=null) {
						preCarrier.setValue(customer.getPreCarrier());
					}
					if(customer.getNewExpireDate() != null) {
						newExpireDate.setValue(customer.getNewExpireDate());
					}
					if(customer.getExpireDate() != null) {
						ExpireDate.setValue(customer.getExpireDate());
					}
					if(customer.getPortDate() != null) {
						portDate.setValue(customer.getPortDate());
					}
					if(customer.getLTEdata()!=null) {
						LTEField.setText(customer.getLTEdata());
					}
					if(customer.getNewPlan()!=null) {
						newPlan.setValue(customer.getNewPlan());
					}
					if(customer.getPlan()!=null) {
						plan.setValue(customer.getPlan());
					}
					if(customer.getNewsimcard()!=null) {
						newSimField.setText(customer.getNewsimcard());
					}
					if(customer.getSimcard()!=null) {
						simField.setText(customer.getSimcard());
					}
					if(customer.getPUK()!=null) {
						PUKField.setText(customer.getPUK());
					}
					if(customer.getDevice()!=null) {
						deviceField.setText(customer.getDevice());
					}
					if(customer.getGroupNumber().getGroupPlan()!=null) {
						group.setValue(customer.getGroupNumber().getGroupPlan());
					}
					if(customer.getGroupNumber()!=null) {
						groupNumber.setText(Long.toString(customer.getGroupNumber().getGroupdID()));
					}
					if(customer.getGroupTitle()!=null) {
						groupTitle.setValue(customer.getGroupTitle());
					}
					if(customer.getAccount()!=null) {
						accountField.setText(customer.getAccount());
					}
					if(customer.getStatus()!=null) {
						status.setValue(customer.getStatus());
					}
					if(customer.getFirstName()!=null) {
						firstnameField.setText(customer.getFirstName());
					}
					if(customer.getLastName()!=null) {
						lastNameField.setText(customer.getLastName());
					}
					if(customer.getAddress()!=null) {
						addressField.setText(customer.getAddress());
					}
					if(customer.getCity()!=null) {
						cityField.setText(customer.getCity());
					}
					if(customer.getStates()!=null) {
						state.setValue(customer.getStates());
					}
					if(customer.getZipcode()!=null) {
						zipCodeField.setText(customer.getZipcode());
					}
					if(customer.getOfferPrice()!=null) {
						priceField.setText(customer.getOfferPrice());
					}
					if(customer.getComment()!=null) {
						commentField.setText(customer.getComment());
					}
					if(customer.getEmployee()!=null) {
						updateEmployee.setText(Long.toString(customer.getEmployee().getEmployeeID()));
					}
					if(customer.getLastUpdate()!=null) {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
						lastUpdate.setText(customer.getLastUpdate().format(formatter));
					}
				}
			else warningLabel.setText("Customer does not exist.");
			}
	}
	
	public void cancelButtonListener() {
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
	}
	public void submitButtonListener() {
		//phone number must NotNull.
		if(phoneField.getText() ==null || phoneField.getText().length() != 10) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Incorrect Phone Number");
			alert.setContentText("Phone Number must be 10 digits.");
			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation Dialog");
			alert.setHeaderText("Customer information will be replaced.");
			alert.setContentText("Are you sure to continue?");
			alert.showAndWait().ifPresent(rs-> {
				if(rs == ButtonType.OK) {
					if(customerIDField.getText().isEmpty() || !(phoneField.getText().equals(customerIDField.getText()))) {
						customer = new Customer();
						customer.setCustomerID(Long.parseLong(phoneField.getText()));
						}
					
						customer.setPhoneNumber(phoneField.getText());
						
						if(!pinField.getText().isEmpty()) {
							customer.setPin(Integer.parseInt(pinField.getText()));
						}
						if(languageField.getText()!=null) {
							customer.setLanguage(languageField.getText());
						}
						if(actionBox.getValue()!=null) {
							customer.setAction(actionBox.getValue());
						}
						if(carrier.getValue()!=null) {
							customer.setCarrier(carrier.getValue());
						}
						if(newCarrier.getValue()!=null) {
							customer.setNewCarrier(newCarrier.getValue());
						}
						if(preCarrier.getValue()!=null) {
							customer.setPreCarrier(preCarrier.getValue());
						}
						if(newExpireDate.getValue() != null) {
							customer.setNewExpireDate(newExpireDate.getValue());
						}
						if(ExpireDate.getValue() != null) {
							customer.setExpireDate(ExpireDate.getValue());
						}
						if(portDate.getValue() != null) {
							customer.setExpireDate(portDate.getValue());
						}
						if(LTEField.getText()!=null) {
							customer.setLTEdata(LTEField.getText());
						}
						if(newPlan.getValue()!=null) {
							customer.setNewPlan(newPlan.getValue());
						}
						if(plan.getValue()!=null) {
							customer.setPlan(plan.getValue());
						}
						if(newSimField.getText()!=null) {
							customer.setNewsimcard(newSimField.getText());
						}
						if(simField.getText()!=null) {
							customer.setSimcard(simField.getText());
						}
						if(PUKField.getText()!=null) {
							customer.setPUK(PUKField.getText());
						}
						if(deviceField.getText()!=null) {
							customer.setDevice(deviceField.getText());
						}

						if(groupTitle.getValue()!=null) {
							customer.setGroupTitle(groupTitle.getValue());
						}
						if(accountField.getText()!=null) {
							customer.setAccount(accountField.getText());
						}
						if(status.getValue()!=null) {
							customer.setStatus(status.getValue());
						}
						if(firstnameField.getText()!=null) {
							customer.setFirstName(firstnameField.getText());
						}
						if(lastNameField.getText()!=null) {
							customer.setLastName(lastNameField.getText());
						}
						if(addressField.getText()!=null) {
							customer.setAddress(addressField.getText());
						}
						if(cityField.getText()!=null) {
							customer.setCity(cityField.getText());
						}
						if(state.getValue()!=null) {
							customer.setStates(state.getValue());
						}
						if(zipCodeField.getText()!=null) {
							customer.setZipcode(zipCodeField.getText());
						}
						if(priceField.getText()!=null) {
							customer.setOfferPrice(priceField.getText());
						}
						if(commentField.getText()!=null) {
							customer.setComment(commentField.getText());
						}
						customer.setLastUpdate(LocalDateTime.now());
						customer.setEmployee(employee);
						
						System.out.println(customer.getCustomerID());
						
						//update customer info
						if(customerDataManipulater == null) {
							customerDataManipulater = new CustomerDataManipulater();
						}
						if(customerDataManipulater.searchCustomer(customer.getCustomerID()) != null ) {
							if(!groupNumber.getText().equals(Long.toString(customer.getGroupNumber().getGroupdID()))							){
								CustomerGroup customerGroup = new CustomerGroup(Integer.parseInt(groupNumber.getText()),
										Long.parseLong(phoneField.getText()), 
										group.getValue());
								customerGroupDataManipulater = new CustomerGroupDataManipulater();
								if(customerGroupDataManipulater.searchCustomerGroup(customerGroup.getGroupdID())!=null)
								customerGroupDataManipulater.updateCustomerGroup(customerGroup);
								else customerGroupDataManipulater.addCustomerGroup(customerGroup);
								customer.setGroupNumber(customerGroup);
							}
							else if(groupNumber.getText().equals(Long.toString(customer.getGroupNumber().getGroupdID()))
									&& !group.getValue().equals(customer.getGroupNumber().getGroupPlan())) {
								customer.getGroupNumber().setGroupPlan(group.getValue());
								customerGroupDataManipulater.updateCustomerGroup(customer.getGroupNumber());
							}
							if(customerDataManipulater.updateCustomer(customer)) {
							Alert alertConfirm = new Alert(AlertType.INFORMATION);
							alertConfirm.setTitle("Update Success!");
							alertConfirm.setHeaderText(null);
							alertConfirm.setContentText("Customer information is updated.");
							alertConfirm.showAndWait().ifPresent( rs1-> {
								if(rs1 == ButtonType.OK) {
									Stage stage = (Stage)submitButton.getScene().getWindow();
									stage.close();
								}
							});
							}
						}
						else {
							CustomerGroup customerGroup;
							if(!groupNumber.getText().isEmpty())
								customerGroup = new CustomerGroup(Integer.parseInt(groupNumber.getText()),
				    				customer.getCustomerID(), group.getValue());
							else customerGroup = new CustomerGroup(customer.getCustomerID(),
				    				customer.getCustomerID(), group.getValue());
							if(customerGroupDataManipulater==null)
								customerGroupDataManipulater = new CustomerGroupDataManipulater();
							customerGroupDataManipulater.addCustomerGroup(customerGroup);
							
							if(customerDataManipulater.addCustomer(customer)) {
								Alert alertConfirm1 = new Alert(AlertType.INFORMATION);
								alertConfirm1.setTitle("Add Success!");
								alertConfirm1.setHeaderText(null);
								alertConfirm1.setContentText("Customer information is added");
								alertConfirm1.showAndWait().ifPresent( rs2-> {
									if(rs2 == ButtonType.OK) {
										Stage stage = (Stage)submitButton.getScene().getWindow();
										stage.close();
									}
								});
						}
				}
			}});
		}
	}
}

