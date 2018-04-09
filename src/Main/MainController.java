package Main;

import java.io.IOException;
import java.time.LocalDate;

import org.hibernate.sql.HSQLCaseFragment;

import CustomerInfo.Customer;
import CustomerInfo.CustomerUpdateController;
import DataManipulater.CustomerDataManipulater;
import DataManipulater.DataManipulater;
import Employee.Employee;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class MainController {
	
	
	private Employee employee;
	static private OrderController orderController;
	private Customer customer;
	private ObservableList<Customer> customersList;
	private ObservableList<Tab> tabList;
	private Tab tab;
	private CustomerDataManipulater customerDataManipulater;
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
	
	@FXML
	CheckBox flexBox;
	@FXML
	CheckBox flexMixBox;
	
	@FXML
	CheckBox GVBox;
	@FXML
	CheckBox GVFamilyBox;
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
	@FXML
	CheckBox swapBox;
	// Prepaid list components
	@FXML
	CheckBox refillBox;
	@FXML
	Button serviceListButton;

	@FXML
	CheckBox swapFamilyBox;
	// show associated lists items
	@FXML
	Button toDoListButton;
	@FXML
	Button portListButton;
	
	@FXML
	Button unpaidListButton;
	
	@FXML
	Button updateButton;
	@FXML
	Button updateOrderButton;
	@FXML
	Button updateStatusButton;
	@FXML
	Button punchInButton;
	@FXML
	Button punchOutButton;
	
	// center pane
	@FXML
	TabPane tabPane;
	@FXML
	TableView<? extends TableEntry> tableView;

	@FXML
	public void initialize() {
		tabPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
		searchBox.getItems().addAll("Phone#", "CustomerID", "BillAccount", "Carrier");
		searchBox.getSelectionModel().selectFirst();
		 currentDateLabel.setText(LocalDate.now().toString());
		 customerDataManipulater = new CustomerDataManipulater();
		 dataManipulater = new DataManipulater();
		 orderController = new OrderController();
	}
	
	public MainController() {
		
	}
	
	public void searchButtonListener() throws IOException {
		if(searchBox.getValue().equals("Phone#")) {
			customer = customerDataManipulater.searchCustomer(Long.parseLong(searchField.getText()));
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
	
	public void updateButtonListener() throws IOException {
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
	
	public void updateStatusButtonListener(){
		ChoiceDialog<String> dialog = new ChoiceDialog<>(null, FixedElements.ACTIVATIONSTATUS);
		dialog.setTitle("Update Status");
		dialog.setHeaderText(null);
		dialog.setContentText("Update Customer Status");

		dialog.showAndWait().ifPresent(status -> {
			System.out.println("Your choice: " + status);
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
		String hql = "FROM Customer c WHERE c.expireDate BETWEEN '";
		if(beginDate.getValue()!=null)
			hql = hql + beginDate.getValue() + "' AND '";
		else hql = hql + LocalDate.now() + "' AND '";
		
		if(endDate.getValue()!=null) {
			System.out.println(endDate.getValue());
			hql = hql + endDate.getValue()+ "'";
		}
		else hql = hql + LocalDate.now() + "'";
		
		System.out.println(LocalDate.now());
		
		ObservableList<Customer> customerList= dataManipulater.customerList(hql);
		tableView = new TableViewGenerator().getCustomerTable(customerList);
		tab = new Tab("List");
		tab.setClosable(true);
		tab.setContent(tableView);
		tabList = tabPane.getTabs();
		tabList.add(tab);
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

	}
	public void punchOutButtonListener(){

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
}
