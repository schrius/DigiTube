package Main;

import java.io.IOException;
import java.time.LocalDate;
import CustomerInfo.Customer;
import CustomerInfo.CustomerUpdateController;
import DataManipulater.CustomerDataManipulater;
import Employee.Employee;
import Order.OrderController;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
	Button repairListButton;

	@FXML
	CheckBox swapFamilyBox;
	// show associated lists items
	@FXML
	Button toDoListButton;
	@FXML
	Button unlockListButton;
	
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
	TableView<Customer> tableView;

	@FXML
	public void initialize() {
		searchBox.getItems().addAll("Phone#", "CustomerID", "Account", "Carrier");
		searchBox.getSelectionModel().selectFirst();
		 currentDateLabel.setText(LocalDate.now().toString());
		 customerDataManipulater = new CustomerDataManipulater();
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

	@SuppressWarnings("unchecked")
	public void updateTableView() throws IOException {	
		TableColumn<Customer, Long> IDColumn = new TableColumn<>("CustomerID");
		IDColumn.setPrefWidth(80);
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		
		TableColumn<Customer, String> accountColumn = new TableColumn<>("Account");
		accountColumn.setPrefWidth(80);
		accountColumn.setCellValueFactory(new PropertyValueFactory<>("account"));
		
		TableColumn<Customer, Integer> pinColumn = new TableColumn<>("PIN");
		pinColumn.setPrefWidth(30);
		pinColumn.setCellValueFactory(new PropertyValueFactory<>("pin"));
		
		TableColumn<Customer, String> actionColumn = new TableColumn<>("Action");
		actionColumn.setMinWidth(80);
		actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
		
		TableColumn<Customer, Double> oweAmountColumn = new TableColumn<>("Owe");
		oweAmountColumn.setMinWidth(50);
		oweAmountColumn.setCellValueFactory(new PropertyValueFactory<>("oweAmount"));
		
		TableView<Customer> table = new TableView<>(customersList);

		table.getColumns().addAll(IDColumn, accountColumn, pinColumn, actionColumn, oweAmountColumn);
		tableView = table;	
		tab = new Tab("Customer");
		tab.setClosable(true);
		tab.setContent(tableView);
		tabList = tabPane.getTabs();
		tabList.add(tab);
	}
}
