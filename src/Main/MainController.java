package Main;

import java.io.IOException;
import java.time.LocalDate;

import Order.OrderController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class MainController {
	final static public double TAXRATE = 0.08875;
	final static public double NYTAX = 1.2;
	final static public String REFILL = "Refill";
	final static public String ACTIVATION = "Activation";
	final static public String SERVICE = "Service";
	final static public String DEVICE = "Device";
	final static public String ACCESSORIES = "Accessories";
	final static public String PAYBILL = "PayBill";
	
	final static public String LYCA = "LycaMobile";
	final static public String ULTRA = "Ultra Mobile";
	final static public String SIMPLE = "Simple Mobile";
	final static public String H2O = "H2O";
	final static public String TM = "T-Mobile";
	final static public String ATT = "AT&T";
	final static public String ROK = "ROK Mobile";
	final static public String TELLCELL = "TellCell";
	final static public String GOSMART = "GoSmart";
	final static public String EASYGO = "EasyGo";
	final static public String NET10 = "Net10";
	final static public String CT = "CT Mobile";
	final static public String EOT = "EOT";
	
	final static public String NEWACTIVATION = "New Activation";
	final static public String SWAP = "Swap";
	final static public String SWAPF = "Swap Family";
	final static public String FLEX = "Flex";
	final static public String FLEXM = "Flex Mix";
	final static public String GV = "GV";
	final static public String GVF = "GV Family";
	
	private static Employee employee;
	private static OrderController orderController;
	
	Parent parent;
	// search date picker
	@FXML
	DatePicker beginDate;
	@FXML
	Label currentDateLabel;
	
	@FXML
	DatePicker endDate;
	
	@FXML
	CheckBox flexBox;
	@FXML
	CheckBox flexMixBox;
	
	@FXML
	CheckBox GVBox;
	@FXML
	CheckBox GVFamilyBox;
	// Operator information
	@FXML
	Label nameLabel;
	@FXML
	TextArea noteArea;
	// create transaction
	@FXML
	Button orderButton;

	@FXML
	Button payBillListButton;
	// Prepaid list components
	@FXML
	CheckBox refillBox;
	@FXML
	Button repairListButton;
	@FXML
	Button searchButton;
	// search customer information by phone
	@FXML
	TextField searchField;
	@FXML
	Button showListButton;
	@FXML
	CheckBox swapBox;

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
	public void initialize() throws IOException, InterruptedException {
		 currentDateLabel.setText(LocalDate.now().toString());
	}
	
	public void orderButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Order/OrderFX.fxml"));
		orderController = new OrderController();
		fxmlLoader.setController(orderController);
		parent = fxmlLoader.load();
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
	public void setEmployee(Employee employee) {
		MainController.employee = employee;
		nameLabel.setText(employee.getName());
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
}
