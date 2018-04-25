package Main;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import CustomerInfo.Customer;
import CustomerInfo.CustomerUpdateController;
import DataManipulater.DataManipulater;
import Employee.Employee;
import Employee.EmployeeWorkingTime;
import Order.Bill;
import Order.OrderController;
import Order.Plan;
import Order.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class MainController {
	
	Stage stage;
	private Employee employee;
	private EmployeeWorkingTime employeeWorkingTime = new EmployeeWorkingTime();
	static private OrderController orderController;
	private Customer customer;
	private ObservableList<Customer> customersList;
	private ObservableList<Tab> tabList;
	private Tab tab;
	private DataManipulater dataManipulater = null;
	
	Parent parent;
	
	@FXML
	BorderPane mainPane;
	//TopPane
	// search customer information by phone
	@FXML
	TextField searchField;
	@FXML
	Button searchButton;
	@FXML
	ComboBox<String> searchBox;
	@FXML
	Label searchLabel;
	
	// search date picker
	@FXML
	DatePicker beginDate;
	@FXML
	DatePicker endDate;
	@FXML
	Label currentDateLabel;
	
	// BottomPane
	@FXML
	Label nameLabel;
	@FXML
	TextArea noteArea;
	
	//RightPane
	// create transaction
	@FXML
	Button orderButton;
	@FXML
	Button showListButton;
	@FXML
	Button payBillListButton;
	// Prepaid list components
	@FXML
	RadioButton refillRadio;
	@FXML
	RadioButton ActivationRadio;
	@FXML
	RadioButton flexRadio;
	@FXML
	RadioButton flexMixRadio;
	@FXML
	RadioButton GVRadio;
	@FXML
	RadioButton GVFamilyRadio;
	
	@FXML
	Button serviceListButton;

	@FXML
	RadioButton swapFamilyRadio;
	// show associated lists items
	@FXML
	Button toDoListButton;
	@FXML
	Button portListButton;
	@FXML
	Button unpaidListButton;
	
	@FXML
	Button updateCustomerButton;
	@FXML
	Button updateOrderButton;
	@FXML
	Button updateStatusButton;
	@FXML
	Button punchInButton;
	@FXML
	Button punchOutButton;
	@FXML
	Button updateAccountButton;
	@FXML
	Button addGroupButton;
	@FXML
	Button updateGroupButton;
	@FXML
	Button paybackButton;
	
	@FXML
	ToggleGroup plangroup;
	@FXML
	ToggleGroup actiongroup;
	
	// center pane
	@FXML
	TabPane tabPane;
	@FXML
	TableView<? extends TableEntry> tableView;

	@FXML
	public void initialize() {
		tabPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
		searchBox.getItems().addAll("Phone#", "GroupID", "BillAccount");
		searchBox.getSelectionModel().selectFirst();
		 currentDateLabel.setText(LocalDate.now().toString());
		 dataManipulater = new DataManipulater();
		 orderController = new OrderController();
	}
	
	public MainController() {
		
	}
	
	public void searchButtonListener() throws IOException {
		if(searchBox.getValue().equals("Phone#")) {
			customer = dataManipulater.searchCustomer(Long.parseLong(searchField.getText()));
			if(customer!=null) {
				searchLabel.setText("");
				customersList = FXCollections.observableArrayList();
				
				customersList.add(customer);
				updateTableView();
			}
			else {
				searchLabel.setText("Customer not exist.");
			}
		}
		else if(searchBox.getValue().equals("GroupID")) {
			String hql = "FROM Customer c WHERE c.groupNumber=" + searchField.getText();
			customersList = dataManipulater.customerList(hql);
			updateTableView();
		}
	}
	
	
	public void orderButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Order/OrderFX.fxml"));
	//	orderController = new OrderController();
		orderController.setEmployee(employee);
		fxmlLoader.setController(orderController);
		parent = fxmlLoader.load();
		orderController.setStage(stage);
		stage.setOnCloseRequest(e -> orderController.closeOrderPane());
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
	
	public void updateCustomerButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../CustomerInfo/CustomerUpdateFX.fxml"));
		parent = fxmlLoader.load();
		CustomerUpdateController customerUpdateController = fxmlLoader.getController();
		customerUpdateController.setEmployee(employee);
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
	public void updateOrderButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Order/OrderUpdateFX.fxml"));
		parent = fxmlLoader.load();
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
	
	@SuppressWarnings("unchecked")
	public void updateStatusButtonListener(){
		ChoiceDialog<String> dialog = new ChoiceDialog<>(null, FixedElements.ACTIVATIONSTATUS);
		dialog.setTitle("Update Status");
		dialog.setHeaderText(null);
		dialog.setContentText("Update Customer Status");

		dialog.showAndWait().ifPresent(status -> {
			tableView = (TableView<? extends TableEntry>) tabPane.getSelectionModel().getSelectedItem().getContent();
			
			Customer customer = (Customer) tableView.getSelectionModel().getSelectedItem();
			
			if(status.equals(FixedElements.COMPLETE)) {
				ChoiceDialog<String> credits = new ChoiceDialog<>(null, "1", "2", "3", "4", "5","6","7","8","9");
				credits.setTitle("Update Status");
				credits.setHeaderText(null);
				credits.setContentText("How many months?");
				
				credits.showAndWait().ifPresent(months -> {
					if(Integer.parseInt(months)>customer.getCustomerCredit()) {
						Alert alert = new Alert(AlertType.CONFIRMATION);
						alert.setTitle("Warning");
						alert.setHeaderText("Customer credit is lower than topup amount!");
						alert.setContentText("Are you sure to continue?");

						Optional<ButtonType> result = alert.showAndWait();
						if (result.get() == ButtonType.OK){
						    customer.setCustomerCredit(customer.getCustomerCredit() - Integer.parseInt(months));
						    if(customer.getAction().equals(FixedElements.ACTIVATION)) {
						    	customer.setCurrentPlan(customer.getNewPlan());
						    	customer.setNewPlan(dataManipulater.searchPlan(1L));
							    customer.setExpireDate(LocalDate.now().plusDays(Integer.parseInt(months)*29));
						    }
						    else customer.setExpireDate(customer.getExpireDate().plusDays(Integer.parseInt(months)*30));
						    dataManipulater.updateCustomer(customer);
						} else {
						    alert.close();
						}
					}
					else {
					    customer.setCustomerCredit(customer.getCustomerCredit() - Integer.parseInt(months));
					    if(customer.getAction().equals(FixedElements.ACTIVATION)) {
					    	customer.setCurrentPlan(customer.getNewPlan());
					    	customer.setNewPlan(dataManipulater.searchPlan(1L));
						    customer.setExpireDate(LocalDate.now().plusDays(Integer.parseInt(months)*29));
					    }
					    else customer.setExpireDate(customer.getExpireDate().plusDays(Integer.parseInt(months)*30));
					    dataManipulater.updateCustomer(customer);
					}
				});
			}
			else {
				customer.setStatus(status);
			}
		});
	}
	
	public void payBillListButtonListener() {
		String hql = "FROM Bill b WHERE b.status= 'Waiting'";
		ObservableList<Bill> billList;
		billList = dataManipulater.BillwaitingList(hql);
		
		tableView = new TableViewGenerator().getBillTable(billList);
		tab = new Tab("BillList");
		tab.setClosable(true);
		tab.setContent(tableView);
		tabList = tabPane.getTabs();
		tabList.add(tab);
	}
	
	public void serviceListButtonListener() {
		String hql = "FROM Service s WHERE s.status= 'Waiting'";
		ObservableList<Service> serviceList;
		serviceList = dataManipulater.serviceWaitingList(hql);
		
		tableView = new TableViewGenerator().getServiceTable(serviceList);
		tab = new Tab("Service List");
		tab.setClosable(true);
		tab.setContent(tableView);
		tabList = tabPane.getTabs();
		tabList.add(tab);
	}
	
	public void toDoListButtonListener() {
		String hql = "FROM Customer c WHERE c.status = 'Waiting' AND c.expireDate = '" + LocalDate.now() + "'";
		ObservableList<Customer> todoList= dataManipulater.customerList(hql);
		
		tableView = new TableViewGenerator().getToDoTable(todoList);
		tab = new Tab("To-Do List");
		tab.setClosable(true);
		tab.setContent(tableView);
		tabList = tabPane.getTabs();
		tabList.add(tab);
	}
	public void portListButtonListener() {
		String hql = "FROM Plan p WHERE p.portdate = '" + LocalDate.now() + "'";
		ObservableList<Plan> portList= dataManipulater.planList(hql);
		
		tableView = new TableViewGenerator().getPortListTable(portList);
		tab = new Tab("Port List");
		tab.setClosable(true);
		tab.setContent(tableView);
		tabList = tabPane.getTabs();
		tabList.add(tab);
	}
	
	public void showListButtonListener() {
		String hql = "FROM Customer c ";
		if(swapFamilyRadio.isSelected()||flexRadio.isSelected()||flexMixRadio.isSelected()||
				GVRadio.isSelected()|| GVFamilyRadio.isSelected()) {
			
			if(swapFamilyRadio.isSelected())
			hql += "INNER JOIN CustomerGroup g ON g.groupdID = c.groupNumber WHERE g.groupPlan='Swap Family'";
			
			if(flexRadio.isSelected())
				hql += "INNER JOIN CustomerGroup g ON g.groupdID = c.groupNumber WHERE g.groupPlan='Flex Family'";
			if(flexMixRadio.isSelected())
				hql += "INNER JOIN CustomerGroup g ON g.groupdID = c.groupNumber WHERE g.groupPlan='Flex Mix'";
			if(GVRadio.isSelected())
				hql += "INNER JOIN CustomerGroup g ON g.groupdID = c.groupNumber WHERE g.groupPlan='GV'";
			if(GVFamilyRadio.isSelected())
				hql += "INNER JOIN CustomerGroup g ON g.groupdID = c.groupNumber WHERE g.groupPlan='GV Family'";
			if(beginDate.getValue()!=null) 
				hql = hql + " AND c.expireDate BETWEEN '" + beginDate.getValue() + "' AND '";
			else hql = hql + " AND c.expireDate BETWEEN '" + LocalDate.now() + "' AND '";
			
			if(endDate.getValue()!=null) 
				hql = hql + endDate.getValue() + "'";
			else hql = hql + LocalDate.now() + "'";
			
		}
		else {
			if(beginDate.getValue()!=null) 
				hql = hql + " WHERE c.expireDate BETWEEN '" + beginDate.getValue() + "' AND '";
			else hql = hql + " WHERE c.expireDate BETWEEN '" + LocalDate.now() + "' AND '";
			
			if(endDate.getValue()!=null) 
				hql = hql + endDate.getValue() + "'";
			else hql = hql + LocalDate.now() + "'";
		}
		
		if(refillRadio.isSelected()) {
			hql += " AND c.action='" + FixedElements.REFILL + "'";
		}
		if(ActivationRadio.isSelected()) {
			hql += " AND c.action='" + FixedElements.ACTIVATION + "'";
		}
		
		ObservableList<Customer> customerList= dataManipulater.customerList(hql);
		tableView = new TableViewGenerator().getCustomerTable(customerList);
		tab = new Tab("List");
		tab.setClosable(true);
		tab.setContent(tableView);
		tabList = tabPane.getTabs();
		tabList.add(tab);
	}
	
	public void clearButtonListener() {
		actiongroup.selectToggle(null);
		plangroup.selectToggle(null);
	}
	
	public void unpaidListButtonListener() {
		String hql = "FROM Customer c WHERE c.oweAmount > 0";
		ObservableList<Customer> unpaidList= dataManipulater.customerList(hql);
		
		tableView = new TableViewGenerator().getUnpaidTable(unpaidList);
		tab = new Tab("Unpaid List");
		tab.setClosable(true);
		tab.setContent(tableView);
		tabList = tabPane.getTabs();
		tabList.add(tab);
	}
	
	public void punchInButtonListener(){
		employeeWorkingTime.setEmployee(this.employee);
		employeeWorkingTime.setPunchIn(LocalDateTime.now());
		dataManipulater.addWorkingTime(employeeWorkingTime);
	}
	public void punchOutButtonListener(){
		long minutes = ChronoUnit.MINUTES.between(employeeWorkingTime.getPunchIn(), LocalDateTime.now());
		employeeWorkingTime.setPunchOut(LocalDateTime.now());
		employeeWorkingTime.setWorkingHour(minutes);

		dataManipulater.updateWorkingTime(employeeWorkingTime);
	}
	
	public void updateGroupButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("GroupManageFX.fxml")));
		parent = fxmlLoader.load();
		stage.setScene(new Scene(parent));
		stage.showAndWait();		
	}
	
	public void addGroupButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("AddCustomerGroupFX.fxml")));
		parent = fxmlLoader.load();
		stage.setScene(new Scene(parent));
		stage.showAndWait();	
	}
	
	public void updateAccountButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(("UpdateAccountFX.fxml")));
		parent = fxmlLoader.load();
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
	
	public void setEmployee(Employee employee) {
		this.employee = employee;
		 nameLabel.setText(employee.getFirstName());
	}

	public Employee getEmployee() {
		return employee;
	}

	public static OrderController getOrderController() {
		return orderController;
	}
	public static void setOrderController(OrderController orderController) {
		MainController.orderController = orderController;
	}

	public void updateTableView() throws IOException {	
		tableView = new TableViewGenerator().getCustomerTable(customersList);
		tab = new Tab("Customer");
		tab.setClosable(true);
		tab.setContent(tableView);
		tabList = tabPane.getTabs();
		tabList.add(tab);
	}
	
	@SuppressWarnings("unchecked")
	public void paybackButtonListener() {
		tableView = (TableView<? extends TableEntry>) tabPane.getSelectionModel().getSelectedItem().getContent();

		if(tableView !=null) {
		Customer paybackCustomer = (Customer) tableView.getSelectionModel().getSelectedItem();
		if(paybackCustomer != null && paybackCustomer.getOweAmount()>0) {
			TextInputDialog dialog = new TextInputDialog("Pay Back");
			dialog.setTitle("Customer Pay back");
			dialog.setHeaderText("Customer Pay back own amount.");
			dialog.setContentText("Enter amount:");
			Optional<String> amount = dialog.showAndWait();
			if (amount.isPresent()){
			    paybackCustomer.setOweAmount(paybackCustomer.getOweAmount() - Double.parseDouble(amount.get()));
			    dataManipulater.updateCustomer(paybackCustomer);
				}
			}
		}
	}
	
	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void closeMain() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Warning");
		alert.setHeaderText("Make Sure Punch Out, if you done for today.");
		alert.setContentText("You may not get pay if you did not.");
		alert.showAndWait().ifPresent(rs-> {
			if(rs == ButtonType.OK) {
					stage.close();
			}
		});
	}
}
