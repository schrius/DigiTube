package Main;
/*
 * Main function controller
 * Launch order pane, update account, update customer information, generate lists of unpaid, to-do, port, services
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.math.BigDecimal;
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
import Order.Invoice;
import Order.OrderController;
import Order.PayBack;
import Order.Plan;
import Order.Service;
import Order.Unpaid;
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
	
	// bottom pane
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
		searchBox.getItems().addAll("Customer#", "GroupID", "Plan List");
		searchBox.getSelectionModel().selectFirst();
		 currentDateLabel.setText(LocalDate.now().toString());
		 orderController = new OrderController();
		 
		 try {  
	            Reader reader = new FileReader("note.txt");  
	            int data = reader.read();  
	            while (data != -1) {  
	            	noteArea.appendText(Character.toString((char)data)); 
	                data = reader.read();  
	            }  
	            reader.close();  
	        } catch (Exception ex) {  
	            System.out.println(ex.getMessage());  
	        }        
      }  
	
	public MainController() {
		
	}
	
	@SuppressWarnings("unchecked")
	public void searchButtonListener() throws IOException {
		if(searchBox.getValue().equals("Customer#")) {
			customer = (Customer) DataManipulater.searchData(Long.parseLong(searchField.getText()), Customer.class);
			if(customer!=null) {
				searchLabel.setText("");
				customersList = FXCollections.observableArrayList();
				customersList.add(customer);
				tableView = new TableViewGenerator().getCustomerTable(customersList);
				updateTableView(customer.getPhoneNumber());
			}
			else {
				searchLabel.setText("Customer not exist.");
			}
		}
		else if(searchBox.getValue().equals("GroupID")) {
			String hql = "FROM Customer c WHERE c.groupNumber=" + searchField.getText();
			customersList = (ObservableList<Customer>) DataManipulater.ListData(hql);
			tableView = new TableViewGenerator().getCustomerTable(customersList);
			updateTableView(searchField.getText());
		}
		else if(searchBox.getValue().equals("Plan List")) {
			String hql = "FROM Plan p WHERE p.phoneNumber='" + searchField.getText() + "'";
			ObservableList<Plan> planlist = (ObservableList<Plan>) DataManipulater.ListData(hql);
			tableView = new TableViewGenerator().getPortListTable(planlist);
			updateTableView(searchField.getText());
		}
	}
	
	public void orderButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Order/OrderFX.fxml"));
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
			
			String selectedItem = tableView.getSelectionModel().getSelectedItem().getClass().getName();
			
			if(selectedItem.equals("CustomerInfo.Customer")) {
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
						    	customer.setNewPlan((Plan) DataManipulater.searchData(1L, Plan.class));
							    customer.setExpireDate(LocalDate.now().plusDays(Integer.parseInt(months)*30-1));
						    }
						    else customer.setExpireDate(customer.getExpireDate().plusDays(Integer.parseInt(months)*30));
						} else {
						    alert.close();
						}
					}
					else {
					    customer.setCustomerCredit(customer.getCustomerCredit() - Integer.parseInt(months));
					    if(customer.getAction().equals(FixedElements.ACTIVATION)) {
					    	customer.setCurrentPlan(customer.getNewPlan());
					    	customer.setNewPlan((Plan) DataManipulater.searchData(1L, Plan.class));
						    customer.setExpireDate(LocalDate.now().plusDays(Integer.parseInt(months)*30-1));
					    }
					    else customer.setExpireDate(customer.getExpireDate().plusDays(Integer.parseInt(months)*30));
					}
				});
			}
			else {
				customer.setStatus(status);
			}
			DataManipulater.updateData(customer);
			}
			else if(selectedItem.equals("Order.Service")) {
				Service service = (Service) tableView.getSelectionModel().getSelectedItem();
				service.setStatus(status);
				DataManipulater.updateData(service);
			}
			else if(selectedItem.equals("Order.Bill")) {
				Bill bill = (Bill) tableView.getSelectionModel().getSelectedItem();
				bill.setStatus(status);
				DataManipulater.updateData(bill);
			}
			else if(selectedItem.equals("Order.Plan")) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Warning");
				alert.setHeaderText("Use to-do-list to complete refill/port");
				alert.setContentText(null);
				alert.showAndWait();
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public void payBillListButtonListener() throws IOException {
		String hql = "FROM Bill b WHERE NOT b.status= 'Complete'";
		ObservableList<Bill> billList;
		billList = (ObservableList<Bill>) DataManipulater.ListData(hql);
		
		tableView = new TableViewGenerator().getBillTable(billList);
		updateTableView("Bill List");
	}
	
	@SuppressWarnings("unchecked")
	public void serviceListButtonListener() throws IOException {
		String hql = "FROM Service s WHERE NOT s.status= 'Complete'";
		ObservableList<Service> serviceList = (ObservableList<Service>) DataManipulater.ListData(hql);
		
		tableView = new TableViewGenerator().getServiceTable(serviceList);
		updateTableView("Service List");
	}
	
	@SuppressWarnings("unchecked")
	public void toDoListButtonListener() throws IOException {
		String hql = "FROM Customer c WHERE c.status != 'Complete' AND c.customerCredit>0 AND c.expireDate <= '" + LocalDate.now() + "'";
		ObservableList<Customer> todoList= (ObservableList<Customer>) DataManipulater.ListData(hql);
		
		tableView = new TableViewGenerator().getToDoTable(todoList);
		updateTableView("To-Do List");
	}
	@SuppressWarnings("unchecked")
	public void portListButtonListener() throws IOException {
		String hql = "FROM Plan p WHERE p.portdate < '" + LocalDate.now() + "' AND p.status = 'Waiting'";
		ObservableList<Plan> portList= (ObservableList<Plan>) DataManipulater.ListData(hql);
		
		tableView = new TableViewGenerator().getPortListTable(portList);
		updateTableView("Port List");
	}
	
	@SuppressWarnings("unchecked")
	public void showListButtonListener() throws IOException {
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
		
		ObservableList<Customer> customerList= (ObservableList<Customer>) DataManipulater.ListData(hql);
		tableView = new TableViewGenerator().getCustomerTable(customerList);
		updateTableView("Customer list");
	}
	
	public void clearButtonListener() {
		actiongroup.selectToggle(null);
		plangroup.selectToggle(null);
	}
	
	@SuppressWarnings("unchecked")
	public void unpaidListButtonListener() throws IOException {
		String hql = "FROM Customer c WHERE c.oweAmount > 0";
		ObservableList<Customer> unpaidList= (ObservableList<Customer>) DataManipulater.ListData(hql);
		
		tableView = new TableViewGenerator().getUnpaidTable(unpaidList);
		updateTableView("Unpaid list");
	}
	
	public void punchInButtonListener(){
		employeeWorkingTime.setEmployee(this.employee);
		employeeWorkingTime.setPunchIn(LocalDateTime.now());
		DataManipulater.addData(employeeWorkingTime);
	}
	public void punchOutButtonListener(){
		long minutes = ChronoUnit.MINUTES.between(employeeWorkingTime.getPunchIn(), LocalDateTime.now());
		employeeWorkingTime.setPunchOut(LocalDateTime.now());
		employeeWorkingTime.setWorkingHour(minutes);

		DataManipulater.updateData(employeeWorkingTime);
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

	public void updateTableView(String tabTitle) throws IOException {	
		tab = new Tab(tabTitle);
		tab.setClosable(true);
		tab.setContent(tableView);
		tabList = tabPane.getTabs();
		tabList.add(tab);
		tabPane.getSelectionModel().select(tab);
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
			    DataManipulater.updateData(paybackCustomer);
			    
			    String hql = "FROM Unpaid u WHERE customer=" + paybackCustomer.getCustomerID();
			    ObservableList<Unpaid> list = (ObservableList<Unpaid>) DataManipulater.ListData(hql);
			    BigDecimal unpaidAmount = new BigDecimal(0);
			    Invoice payinvoice = null;
			    for(Unpaid unpaid : list) {
			    	unpaidAmount.add(new BigDecimal(unpaid.getOweamount()));
			    	payinvoice = unpaid.getInvoice();
			    	if(unpaidAmount.doubleValue()<= Double.parseDouble(amount.get())) {
			    		payinvoice.setPaymentMethod(FixedElements.COMPLETE);
			    		payinvoice.setLastUpdate(LocalDateTime.now());
			    		DataManipulater.updateData(payinvoice);
			    	}
			    }
		    	PayBack payBack = new PayBack();
		    	payBack.setCustomer(paybackCustomer);
		    	payBack.setEmployee(employee);
		    	if(list.size()==1)
		    	payBack.setInvoice(payinvoice);
		    	payBack.setPaybackAmount(Double.parseDouble(amount.get()));
		    	payBack.setLastUpdate(LocalDate.now());
		    	DataManipulater.addData(payBack);

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("PayBack");
				alert.setHeaderText(null);
				alert.setContentText("Customer own " + paybackCustomer.getOweAmount() + " payback "+ amount.get());
				alert.showAndWait();
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
				if(employeeWorkingTime.getPunchIn()!=null && employeeWorkingTime.getPunchOut()==null) {
					punchOutButtonListener();
				}
				try{    
					Writer w = new FileWriter("note.txt");  
		            w.write(noteArea.getText());  
		            w.close();  
				}catch (Exception e) {
					e.getMessage();
				}
					stage.close();
			}
				
		});
	}
}
