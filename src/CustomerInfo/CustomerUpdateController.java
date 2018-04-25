package CustomerInfo;
/*
 *  Customer information update controller allow employee to update customer information
 *  To keep data consistent, most of data are not editable
 *  Only information that does not relative to Plan and Service are editable
 */
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import DataManipulater.DataManipulater;
import Employee.Employee;
import Main.FixedElements;
import Order.Plan;
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
	DataManipulater dataManipulater;
	
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
	TextField group;
	@FXML
	TextField groupTitle;
	@FXML
	ComboBox<String> status;
	@FXML
	ComboBox<String> newPlan;
	@FXML
	ComboBox<String> currentPlan;
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
		status.getItems().addAll(FixedElements.ACTIVATIONSTATUS);
		newPlan.getItems().addAll(FixedElements.PLAN);
		currentPlan.getItems().addAll(FixedElements.PLAN);
		state.getItems().addAll(FixedElements.STATES);
		actionBox.getItems().addAll(FixedElements.ACTION);
		dataManipulater = new DataManipulater();
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	//search customer info and display on the pane
	public void searchButtonListener() {
		if(searchField.getText().length() == 10) {
			customer = dataManipulater.searchCustomer(Long.parseLong(searchField.getText()));
			if(customer!=null) {
					warningLabel.setText("");
					customerIDField.setText(Long.toString(customer.getCustomerID()));
					phoneField.setText(customer.getPhoneNumber());

					oweAmountField.setText(Double.toString(customer.getOweAmount()));
					creditLabel.setText(Integer.toString(customer.getCustomerCredit()));
					if(customer.getLanguage()!=null) {
						languageField.setText(customer.getLanguage());
					}
					
					if(customer.getCurrentPlan()!=null) {
						pinField.setText(Integer.toString(customer.getCurrentPlan().getPin()));
						carrier.setValue(customer.getCurrentPlan().getCarrier());
						currentPlan.setValue(customer.getCurrentPlan().getPlanType());
						simField.setText(customer.getCurrentPlan().getSim());
						accountField.setText(customer.getCurrentPlan().getAccount());
					}

					if(customer.getNewPlan()!=null) {
						newCarrier.setValue(customer.getNewPlan().getCarrier());
						portDate.setValue(customer.getNewPlan().getPortdate());
						newPlan.setValue(customer.getNewPlan().getPlanType());
						newSimField.setText(customer.getNewPlan().getSim());
						PUKField.setText(customer.getNewPlan().getPUK());
					}
							
					if(customer.getPrePlan()!=null) {
						preCarrier.setValue(customer.getPrePlan().getCarrier());
					}
					
					if(customer.getAction()!=null) {
						actionBox.setValue(customer.getAction());
					}

					if(customer.getExpireDate() != null) {
						ExpireDate.setValue(customer.getExpireDate());
					}
					if(customer.getLTEdata()!=null) {
						LTEField.setText(customer.getLTEdata());
					}

					if(customer.getDevice()!=null) {
						deviceField.setText(customer.getDevice());
					}
					if(customer.getGroupNumber().getGroupPlan()!=null) {
						group.setText(customer.getGroupNumber().getGroupPlan());
					}
					if(customer.getGroupNumber()!=null) {
						groupNumber.setText(Long.toString(customer.getGroupNumber().getGroupdID()));
					}
					if(customer.getGroupTitle()!=null) {
						groupTitle.setText(customer.getGroupTitle());
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
						
						if(languageField.getText()!=null) {
							customer.setLanguage(languageField.getText());
						}
						if(actionBox.getValue()!=null) {
							customer.setAction(actionBox.getValue());
						}

						if(ExpireDate.getValue() != null) {
							customer.setExpireDate(ExpireDate.getValue());
						}

						if(LTEField.getText()!=null) {
							customer.setLTEdata(LTEField.getText());
						}
						
						if(deviceField.getText()!=null) {
							customer.setDevice(deviceField.getText());
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
						
						if(!newPlan.getValue().equals(customer.getNewPlan().getPlanType())
								|| !newSimField.getText().equals(customer.getNewPlan().getSim()) 
								|| !PUKField.getText().equals(customer.getNewPlan().getPUK())
								|| !newCarrier.getValue().equals(customer.getNewPlan().getCarrier()) 
								|| !portDate.getValue().equals(customer.getNewPlan().getPortdate())) {
							Plan newplan = customer.getNewPlan();
							if(newplan.getPlanID() == 1) {
								newplan = new Plan();
							
							newplan.setPlanType(newPlan.getValue());
							newplan.setSim(newSimField.getText());
							newplan.setPUK(PUKField.getText());
							newplan.setCarrier(newCarrier.getValue());
							newplan.setPortdate(portDate.getValue());

								dataManipulater.addPlan(newplan);
								customer.setNewPlan(newplan);
							}
							else dataManipulater.updatePlan(newplan);
						}
						
						if(!currentPlan.getValue().equals(customer.getCurrentPlan().getPlanType())
								|| !simField.getText().equals(customer.getCurrentPlan().getSim())
								|| !pinField.getText().equals(Integer.toString(customer.getCurrentPlan().getPin()))
								|| !accountField.getText().equals(customer.getCurrentPlan().getAccount())
								|| !carrier.getValue().equals(customer.getCurrentPlan().getCarrier())) {
							Plan curPlan = customer.getCurrentPlan();
							if(curPlan.getPlanID() == 1) {
								curPlan = new Plan();
							
							curPlan.setCarrier(carrier.getValue());
							curPlan.setPlanType(currentPlan.getValue());
							curPlan.setSim(simField.getText());
							curPlan.setPin(Integer.parseInt(pinField.getText()));
							curPlan.setAccount(accountField.getText());
							curPlan.setSim(simField.getText());
							
							dataManipulater.addPlan(curPlan);	
							customer.setCurrentPlan(curPlan);
							}
							else dataManipulater.updatePlan(curPlan);
						}

						if(!preCarrier.getValue().equals(customer.getPrePlan().getCarrier())) {
							Plan prePlan = 	customer.getPrePlan();
							if(prePlan.getPlanID() == 1) {
								prePlan = new Plan();
								prePlan.setCarrier(preCarrier.getValue());
								dataManipulater.addPlan(prePlan);
								customer.setPrePlan(prePlan);
							}
							else dataManipulater.updatePlan(prePlan);
						}
						
						//update customer info
						if(dataManipulater == null) {
							dataManipulater = new DataManipulater();
						}
						if(dataManipulater.searchCustomer(customer.getCustomerID()) != null ) {
							if(dataManipulater.updateCustomer(customer)) {
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
							if(dataManipulater.addCustomer(customer)) {
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

