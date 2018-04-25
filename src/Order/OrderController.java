package Order;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import CustomerInfo.Customer;
import CustomerInfo.CustomerGenerater;
import CustomerInfo.CustomerGroup;
import DataManipulater.DataManipulater;
import Employee.Employee;
import Main.FixedElements;
import Main.TableViewGenerator;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OrderController {

	Stage stage;
	Employee employee;
	Customer customer;
	Customer unpaidCustomer;
	CustomerGenerater customerGenerater = null;

	@FXML
	OrderRightPaneController orderRightPaneController;
	@FXML
	GridPane orderRightPane;
	
	FXMLLoader	fxmlLoader;
	
	Invoice invoice;
	private Orders order;
	
	private ObservableList<Orders> orderList;
	private Map<Orders, LocalDate> expireDateList;
	private BigDecimal PSCS;
	private BigDecimal NYTax;
	private BigDecimal subtotal;
	private BigDecimal discount;
	private BigDecimal total;
	private BigDecimal serviceFee;
	private BigDecimal receiveAmount;
	private BigDecimal returnBalance;
	private StringProperty PSCSProperty;
	private StringProperty NYTaxProperty;
	private StringProperty subtotalProperty;
	private StringProperty discountProperty;
	private StringProperty totalProperty;
	private StringProperty serviceFeeProperty;
	private StringProperty receiveAmountProperty;
	private StringProperty returnBalanceProperty;
	
	GridPane gridPane;
	VBox planBox;
	VBox processBox;
	Parent parent;

	
	@FXML
	private BorderPane orderPane;
	@FXML
	private TableView<Orders> orderTable;
	
	//Search pane
	@FXML
	ComboBox<String> searchComboBox;
	@FXML
	TextField searchField;
	@FXML
	Button searchButton;
	@FXML
	ComboBox<Integer> quanityComboBox;
	
	// Payment pane labels
	@FXML
	Label discountLabel;
	@FXML
	Label PSCSLabel;
	@FXML
	Label subtotalLabel;
	@FXML
	Label taxLabel;
	@FXML
	Label totalLabel;
	@FXML
	Label receiveLabel;
	@FXML
	Label returnBalanceLabel;
	@FXML
	Label serviceFeeLabel;
	
	@FXML
	public void initialize() throws IOException {		
		searchComboBox.getItems().addAll("Barcode","Product ID", "Invoice", "CustomerID", "Order#");
		searchComboBox.getSelectionModel().selectFirst();
		quanityComboBox.getItems().addAll(1,2,3,4,5,6,7,8,9);
		quanityComboBox.getSelectionModel().selectFirst();
		
		PSCSProperty = new SimpleStringProperty();
		NYTaxProperty = new SimpleStringProperty();
		subtotalProperty = new SimpleStringProperty();
		discountProperty = new SimpleStringProperty();
		totalProperty = new SimpleStringProperty();
		serviceFeeProperty = new SimpleStringProperty();
		receiveAmountProperty = new SimpleStringProperty();
		returnBalanceProperty = new SimpleStringProperty();
		
		PSCSLabel.textProperty().bind(PSCSProperty);
		subtotalLabel.textProperty().bind(subtotalProperty);
		taxLabel.textProperty().bind(NYTaxProperty);
		totalLabel.textProperty().bind(totalProperty);
		receiveLabel.textProperty().bind(receiveAmountProperty);
		discountLabel.textProperty().bind(discountProperty);
		returnBalanceLabel.textProperty().bind(returnBalanceProperty);
		serviceFeeLabel.textProperty().bind(serviceFeeProperty);
		
		PSCS = new BigDecimal(0);
		NYTax = new BigDecimal(0);
		subtotal = new BigDecimal(0);
		discount = new BigDecimal(0);
		serviceFee = new BigDecimal(0);
		total = new BigDecimal(0);
		receiveAmount = new BigDecimal(0);
		returnBalance = new BigDecimal(0);
		
		invoice = new Invoice();
		this.expireDateList = new HashMap<>();
		orderList = FXCollections.observableArrayList();
	}
	
	public void processOrder() throws IOException{
		if(order.getCategories().equals(FixedElements.ACCESSORIES)){
			order.setDescription(order.getProduct().getProductName());
		}
		else if(order.getCategories().equals(FixedElements.DEVICE)) {
			order.setDescription(order.getProduct().getProductName());
		}
		else if(order.getCategories().equals(FixedElements.SERVICE)) {
			order.setDescription(order.getService().getServiceType() + order.getService().getDevice());
		}
		else if(order.getCategories().equals(FixedElements.PAYBILL)) {
			order.setDescription(order.getBill().getBillCarrier() + " " + order.getBill().getBillingAccount());
		}
		else if(order.getCategories().equals(FixedElements.REFILL)
				|| order.getCategories().equals(FixedElements.ACTIVATION)) {
			order.setDescription(order.getPlan().getCarrier() + " " + order.getPlan().getPlanType());
		}
		
		orderList.add(order);

		updateRightPane();
		updateTable();
		updateTotal();
	}

	public void updateTable() throws IOException {	
		TableViewGenerator tableViewGenerator = new TableViewGenerator();
		orderTable = tableViewGenerator.getOrderTable(orderList);	
		orderPane.setCenter(orderTable);
	//	updateTotal();
	}
	
	public void updateTotal() throws IOException {
		PSCS = new BigDecimal(0);
		NYTax = new BigDecimal(0);
		subtotal = new BigDecimal(0);
		discount = new BigDecimal(0);
		serviceFee = new BigDecimal(0);
		total = new BigDecimal(0);
		
		for(Orders currentOrder : orderList ) {
			subtotal = subtotal.add(new BigDecimal(currentOrder.getPrice()*currentOrder.getQuantity()));

			if(currentOrder.getCategories().equals(FixedElements.REFILL)) {
			PSCS = PSCS.add(new BigDecimal(currentOrder.getQuantity()*FixedElements.PSCSTAX));	
			NYTax = NYTax.add(new BigDecimal(subtotal.doubleValue()*FixedElements.TAXRATE));
				if(currentOrder.getPlan().getCarrier().equals(FixedElements.ULTRA) ||
						currentOrder.getPlan().getCarrier().equals(FixedElements.LYCA))
					serviceFee.add(new BigDecimal(1.00));
			}
			NYTax = NYTax.setScale(2, BigDecimal.ROUND_HALF_UP);
			subtotal = subtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
			PSCS = PSCS.setScale(2, BigDecimal.ROUND_HALF_UP);
			serviceFee = serviceFee.setScale(2, BigDecimal.ROUND_HALF_UP);
			discount = discount.add(new BigDecimal((currentOrder.getDiscount() * currentOrder.getQuantity()))); 		
		}

		total = total.add(new BigDecimal(subtotal.doubleValue() + NYTax.doubleValue() + PSCS.doubleValue() + serviceFee.doubleValue()));
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		PSCSProperty.setValue("$" + PSCS);
		NYTaxProperty.setValue("$" + NYTax);
		subtotalProperty.setValue("$" + subtotal);
		discountProperty.setValue("$" + discount);
		totalProperty.setValue("$" + total);
		serviceFeeProperty.setValue("$" + serviceFee);
		if(receiveAmount.intValue() != 0)
			returnBalanceProperty.set("$" + (this.receiveAmount.subtract(total)));
	}

	public void removeTableItem(Orders removeOrder) {
		if(orderTable.getItems().remove(removeOrder))
			System.out.println("Removed");
	}
	
	public void processTransaction(TransactionEnum type) throws IOException {
		switch (type) {
		case CANCEL:
			updateRightPane();
			break;
		case REFILL:
			gridPane  = FXMLLoader.load(getClass().getResource("../Mobile/CarrierFX.fxml"));
			orderPane.setRight(gridPane);
			order = new Orders();
			order.setPlan(new Plan());
			order.setCategories(FixedElements.REFILL);
			break;
		case ACTIVATION:
			gridPane  = FXMLLoader.load(getClass().getResource("../Mobile/CarrierFX.fxml"));
			orderPane.setRight(gridPane);
			order = new Orders();
			order.setPlan(new Plan());
			order.setCategories(FixedElements.ACTIVATION);
			break;
		case SERIVCE:
			gridPane  = FXMLLoader.load(getClass().getResource("./ServiceFX.fxml"));
			orderPane.setRight(gridPane);
			order = new Orders();
			order.setCategories(FixedElements.SERVICE);
			break;
		case DEVICE:
			gridPane  = FXMLLoader.load(getClass().getResource("./DeviceFX.fxml"));
			orderPane.setRight(gridPane);
			order = new Orders();
			order.setCategories(FixedElements.DEVICE);
			break;
		case ACCESSORIES:
			gridPane  = FXMLLoader.load(getClass().getResource("./AccessoriesFX.fxml"));
			orderPane.setRight(gridPane);
			order = new Orders();
			order.setCategories(FixedElements.ACCESSORIES);
			break;
		case PAYBILL:
			gridPane  = FXMLLoader.load(getClass().getResource("./PayBillFX.fxml"));
			orderPane.setRight(gridPane);
			order = new Orders();
			order.setCategories(FixedElements.PAYBILL);
			break;
		case CASH:
			parent = FXMLLoader.load(getClass().getResource("../Main/DigiInputFX.fxml"));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(parent));
			stage.showAndWait();
		case OTHER:
			
			break;

		default:
			break;
		}
	}
	
	public void setCarrierPane(CarrierNum carrier) throws IOException {
		switch(carrier) {
		case LYCA:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/LycaMobileFX.fxml"));
			order.getPlan().setCarrier(FixedElements.LYCA);
			orderPane.setRight(planBox);
		break;
		case ULTRA:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/UltraMobileFX.fxml"));
		order.getPlan().setCarrier(FixedElements.ULTRA);
			orderPane.setRight(planBox);
		break;
		case SIMPLE:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/SimpleMobileFX.fxml"));
		order.getPlan().setCarrier(FixedElements.SIMPLE);
			orderPane.setRight(planBox);
		break;
		case H2O:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/H2OFX.fxml"));
		order.getPlan().setCarrier(FixedElements.H2O);
			orderPane.setRight(planBox);
		break;
		case FLEX:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/FlexFX.fxml"));
		order.getPlan().setCarrier(FixedElements.FLEX);
			orderPane.setRight(planBox);
		break;
		case REDPOCKET:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/RedPocketFX.fxml"));
		order.getPlan().setCarrier(FixedElements.REDPOCKET);
			orderPane.setRight(planBox);
		break;
		case ROK:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/ROKFX.fxml"));
		order.getPlan().setCarrier(FixedElements.ROK);
			orderPane.setRight(planBox);
		break;
		case TMOBILE:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/TMobileFX.fxml"));
		order.getPlan().setCarrier(FixedElements.TM);
			orderPane.setRight(planBox);
		break;
		case ATT:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/ATTFX.fxml"));
		order.getPlan().setCarrier(FixedElements.ATT);
			orderPane.setRight(planBox);
		break;
		case TELCEL:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/TELCELFX.fxml"));
		order.getPlan().setCarrier(FixedElements.TELCEL);
			orderPane.setRight(planBox);
		break;
		case GOSMART:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/GOSmartFX.fxml"));
		order.getPlan().setCarrier(FixedElements.GOSMART);
			orderPane.setRight(planBox);
		break;
		case EASYGO:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/EasyGoFX.fxml"));
		order.getPlan().setCarrier(FixedElements.EASYGO);
			orderPane.setRight(planBox);
		break;
		case NET10:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/NET10FX.fxml"));
		order.getPlan().setCarrier(FixedElements.NET10);
			orderPane.setRight(planBox);
		break;
		case CT:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/CTFX.fxml"));
		order.getPlan().setCarrier(FixedElements.CT);
			orderPane.setRight(planBox);
		break;
		case EOT:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/EOTFX.fxml"));
		order.getPlan().setCarrier(FixedElements.EOT);
			orderPane.setRight(planBox);
		break;
		case OTHER:
		planBox  = FXMLLoader.load(getClass().getResource("../Mobile/OtherFX.fxml"));
		order.getPlan().setCarrier(FixedElements.OTHER);
			orderPane.setRight(planBox);
		break;
		default:
			break;
		}
		
	}
	public void setPlan(int price) throws IOException {
		switch (price) {
		case 0:
			TextInputDialog dialog = new TextInputDialog();
			dialog.setTitle("Pay As You Go");
			dialog.setHeaderText(null);
			dialog.setContentText("Please enter refill amount:");
			dialog.showAndWait().ifPresent( plan -> {
				if(!plan.isEmpty()) {
					try {
						setRefillPane(Double.parseDouble(plan), FixedElements.PSYG);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			break;
		case 10:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(10, FixedElements.P$10);
			else setRefillPane(10, FixedElements.P$10);
			break;
		case 15:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(15, FixedElements.P$15);
			else setRefillPane(15, FixedElements.P$15);
			break;
		case 19:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(19, FixedElements.P$19);
			else setRefillPane(19, FixedElements.P$19);
			break;
		case 20:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(20, FixedElements.P$20);
			else setRefillPane(20, FixedElements.P$20);
			break;
		case 23:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(23, FixedElements.P$23);
			else setRefillPane(23, FixedElements.P$23);
			break;
		case 25:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(25, FixedElements.P$25);
			else setRefillPane(25, FixedElements.P$25);
			break;
		case 29:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(29, FixedElements.P$29);
			else setRefillPane(29, FixedElements.P$29);
			break;
		case 30:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(30, FixedElements.P$30);
			else setRefillPane(30, FixedElements.P$30);
			break;
		case 34:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(34, FixedElements.P$34);
			else setRefillPane(34, FixedElements.P$34);
			break;
		case 35:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(35, FixedElements.P$35);
			else setRefillPane(35, FixedElements.P$35);
			break;
		case 39:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(39, FixedElements.P$39);
			else setRefillPane(39, FixedElements.P$39);
			break;
		case 40:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(40, FixedElements.P$40);
			else setRefillPane(40, FixedElements.P$40);
			break;
		case 45:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(45, FixedElements.P$45);
			else setRefillPane(45, FixedElements.P$45);
			break;
		case 49:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(49, FixedElements.P$49);
			else setRefillPane(49, FixedElements.P$49);
			break;
		case 50:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(50, FixedElements.P$50);
			else setRefillPane(50, FixedElements.P$50);
			break;
		case 55:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(55, FixedElements.P$55);
			else setRefillPane(55, FixedElements.P$55);
			break;
		case 60:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(60, FixedElements.P$60);
			else setRefillPane(60, FixedElements.P$60);
			break;
		case 65:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(65, FixedElements.P$65);
			else setRefillPane(65, FixedElements.P$65);
			break;
		case 70:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(70, FixedElements.P$70);
			else setRefillPane(70, FixedElements.P$70);
			break;
		case 75:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(75, FixedElements.P$75);
			else setRefillPane(75, FixedElements.P$75);
			break;

		default:
			if(order.getCategories().equals(FixedElements.ACTIVATION))
				setActivationPane(price, FixedElements.UNKNOWN);
			else setRefillPane(price, FixedElements.UNKNOWN);
			break;
		}
	}
	public void setRefillPane(double price, String plan) throws IOException {
		processBox  = FXMLLoader.load(getClass().getResource("../Mobile/RefillFX.fxml"));
		order.getPlan().setPlanType(plan);
		order.setPrice(price);
		order.getPlan().setRegularPrice(price);
		order.setRegularPrice(price);
		orderPane.setRight(processBox);
	}
	
	public void setActivationPane(double price, String plan) throws IOException {
		processBox  = FXMLLoader.load(getClass().getResource("../Mobile/ActivationFX.fxml"));
		order.getPlan().setPlanType(plan);
		order.setPrice(price);
		order.getPlan().setRegularPrice(price);
		order.setRegularPrice(price);
		orderPane.setRight(processBox);
	}
	
	public void processActivation(String phoneNumber, int quantity, String sim, String puk, 
			LocalDate portdate, String categories) throws IOException {
		order.getPlan().setPhoneNumber(phoneNumber);
		order.setQuantity(quantity);
		order.getPlan().setPhoneNumber(phoneNumber);
		order.getPlan().setPUK(puk);
		order.getPlan().setSim(sim);
		order.getPlan().setPortdate(portdate);
	//	order.setDescription(categories);
		processOrder();
	}
	
	public void processRefill(String phoneNumber, int quantity, LocalDate expiredate) throws IOException {
		order.getPlan().setPhoneNumber(phoneNumber);
		order.setQuantity(quantity);
		this.expireDateList.put(order, expiredate);
		processOrder();
	}
	
	public void receiveCash(BigDecimal receiveAmount) {
		if(total!=null) {
		this.receiveAmount = receiveAmount;
		this.returnBalance = total.subtract(receiveAmount);
		receiveAmountProperty.set("$" + this.receiveAmount);
		returnBalanceProperty.set("$" + (this.returnBalance));
		}
	}
	
	public void removeItem(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation");
		alert.setHeaderText("Selected item will be removed.");
		alert.setContentText("Are you sure to continue?");
		alert.showAndWait().ifPresent(rs-> {
			if(rs == ButtonType.OK) {
		if(getOrderTable().getSelectionModel().getSelectedItem() != null) {
			Orders removeOrder = getOrderTable().getSelectionModel().getSelectedItem();
			removeTableItem(removeOrder);
			try {
				updateTable();
			} catch (IOException e) {
			e.printStackTrace();
				}
				}
			}
		});
	}
	
	public void changeQuantity(){
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Change Quantity");
		dialog.setHeaderText("Quantity will be updated.");
		dialog.setContentText("Please enter new quantity:");
		dialog.showAndWait().ifPresent( qty -> {
			if(!qty.isEmpty()) {
			getOrderTable().getSelectionModel().getSelectedItem().setQuantity(Integer.parseInt(qty));
			try {
				updateTable();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		});

	}
	
	public void changePrice(){
		TextInputDialog dialog = new TextInputDialog();
		dialog.setTitle("Change Price");
		dialog.setHeaderText("Price will be overriden.");
		dialog.setContentText("Please enter new price:");
		dialog.showAndWait().ifPresent( price -> {
			if(!price.isEmpty()) {
			getOrderTable().getSelectionModel().getSelectedItem().setPrice(Double.parseDouble(price));
			
			getOrderTable().getSelectionModel().getSelectedItem().setDiscount(
			getOrderTable().getSelectionModel().getSelectedItem().getRegularPrice() - Double.parseDouble(price));
			try {
				updateTable();
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
		});
	}
	
	// complete order and store all order data into database.
	// uncomment print() to enable printer
	public boolean completeOrder(String payment) throws IOException {
		if(payment.equals(FixedElements.CASH)) {
			if(receiveAmount.compareTo(total)>= 0) {
				generateInvoice(payment);
			//	print();
				initialize();
				orderTable.getItems().clear();
				return true;
			}
		}
		else if(payment.equals(FixedElements.CREDIT)) {
			generateInvoice(payment);
		//	print();
			initialize();
			orderTable.getItems().clear();
			return true;
		}
		else if(payment.equals(FixedElements.UNPAID)){
			generateInvoice(payment);
		//	print();
			initialize();
			orderTable.getItems().clear();
			return true;
		}
		return false;
	}
	
	public void generateInvoice(String payment) {
		invoice.setOrderDate(LocalDateTime.now());
		invoice.setDiscount(discount.doubleValue());
		invoice.setNYTax(NYTax.doubleValue());
		invoice.setSubtotal(subtotal.doubleValue());
		invoice.setTotal(total.doubleValue());
		invoice.setServiceFee(serviceFee.doubleValue());
		invoice.setReceiveCash(receiveAmount.doubleValue());
		invoice.setReturnBalance(returnBalance.doubleValue());
		if(payment.equals(FixedElements.CASH)) {
			invoice.setPaymentMethod(FixedElements.CASH);
		}
		if(payment.equals(FixedElements.CREDIT)) {
			invoice.setPaymentMethod(FixedElements.CREDIT);
		}
		if(payment.equals(FixedElements.UNPAID)) {
			invoice.setPaymentMethod(FixedElements.UNPAID);
			TextInputDialog dialog = new TextInputDialog();
			Boolean complete = false;
			while(!complete) {
			dialog.setTitle("Unpaid Order");
			dialog.setHeaderText("Unpaid amount will add to customer.");
			dialog.setContentText("Enter Customer phone number:");
			Optional<String> result = dialog.showAndWait();
			if (result.isPresent()){
			    if(result.get().length()==10) {
			    	unpaidCustomer = (Customer) DataManipulater.searchData(Long.parseLong(result.get()), Customer.class);
			    	if(unpaidCustomer==null) {
			    		Plan plan = (Plan) DataManipulater.searchData(1L, Plan.class);
			    		unpaidCustomer = new Customer();
			    		unpaidCustomer.setGroupNumber((CustomerGroup) DataManipulater.searchData(100L, CustomerGroup.class));
			    		unpaidCustomer.setCustomerID(Long.parseLong(result.get()));
			    		unpaidCustomer.setPhoneNumber(result.get());
			    		unpaidCustomer.setGroupTitle(FixedElements.NORMAL);
			    		unpaidCustomer.setEmployee(employee);
			    		unpaidCustomer.setNewPlan(plan);
			    		unpaidCustomer.setPrePlan(plan);
			    		unpaidCustomer.setCurrentPlan(plan);
			    		unpaidCustomer.setAction(FixedElements.COMPLETE);
			    		
			    		unpaidCustomer.setOweAmount(total.doubleValue());
			    		DataManipulater.addData(unpaidCustomer);
			    	}
			    	else {
			    		unpaidCustomer.setOweAmount(customer.getOweAmount() + total.doubleValue());
			    		DataManipulater.updateData(unpaidCustomer);
			    	}
			    }
			}
			}
		}

		for(Orders orders : orderList) {
			orders.setEmployee(employee);
			if(payment.equals(FixedElements.UNPAID))
				orders.setStatus(FixedElements.UNPAID);
			else orders.setStatus(FixedElements.COMPLETE);
			orders.setInvoice(invoice);
			if(orders.getCategories().equals(FixedElements.REFILL) ||
					orders.getCategories().equals(FixedElements.ACTIVATION)) {
				customer = (Customer) DataManipulater.searchData(Long.parseLong(orders.getPlan().getPhoneNumber()), Customer.class);
				if(customer == null) {
					customerGenerater = new CustomerGenerater();
					customer = customerGenerater.generateCustomer(orders, employee);
					if(expireDateList.get(orders)!=null)
						customer.setExpireDate(expireDateList.get(orders));
					else customer.setExpireDate(LocalDate.now());

					DataManipulater.addData(customer);
					orders.setCustomer(customer);
				}
				else {
					customer.setCustomerCredit(customer.getCustomerCredit() + orders.getQuantity());
					if(orders.getCategories().equals(FixedElements.REFILL)) {
						customer.setAction(FixedElements.REFILL);
					}
					if(orders.getCategories().equals(FixedElements.ACTIVATION)) {
						customer.setAction(FixedElements.ACTIVATION);
						customer.setNewPlan(orders.getPlan());
					}
					customer.setStatus(FixedElements.WAITING);
					DataManipulater.updateData(customer);
					orders.setCustomer(customer);
				}
			}
			if(invoice.getReceiveCash()>0)
				invoice.setPaymentMethod(FixedElements.CASH);
			invoice.getOrder().add(orders);
	}
		DataManipulater.addData(invoice);
		if(invoice.getPaymentMethod().equals(FixedElements.UNPAID)) {
			Unpaid unpaid = new Unpaid();
			unpaid.setCustomer(unpaidCustomer);
			unpaid.setEmployee(employee);
			unpaid.setInvoice(invoice);
			unpaid.setLastUpdate(LocalDate.now());
			unpaid.setOweamount(invoice.getTotal());
			DataManipulater.addData(unpaid);
		}
		
		for(Orders orders : orderList) {
			if(orders.getCategories().equals(FixedElements.ACTIVATION)) {
			Customer updateCustomer = orders.getCustomer();
			updateCustomer.setNewPlan(orders.getPlan());
			DataManipulater.updateData(updateCustomer);
			
			}
			if(orders.getCategories().equals(FixedElements.REFILL)) {
			Customer updateCustomer = orders.getCustomer();
			updateCustomer.setCurrentPlan(orders.getPlan());

			DataManipulater.updateData(updateCustomer);
			}
		}
	}
	
	public void searchButtonListener() throws IOException {
		String choice = searchComboBox.getValue();
		if(choice.equals("Invoice")) {
			Invoice invoice = (Invoice) DataManipulater.searchData(Long.valueOf(searchField.getText()), Invoice.class);
			
			List<Orders> InvoiceOrderList = invoice.getOrder();
			
			orderList = FXCollections.observableArrayList();
			for(Orders orders : InvoiceOrderList)
				orderList.add(orders);
			
			updateTable();
		}
	}
	
	public void refund() {
		Orders refundOrder = orderTable.getSelectionModel().getSelectedItem();
		if(refundOrder == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Select Order!");
			alert.setHeaderText("No Order is selected.");
			alert.setContentText("Select order before processing refund.");
			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation");
			alert.setHeaderText("Selected item will be refunded.");
			alert.setContentText("Are you sure to continue?");
			alert.showAndWait().ifPresent(rs-> {
				if(rs == ButtonType.OK) {
		refundOrder.setStatus(FixedElements.REFUND);
		refundOrder.getInvoice().setRefund(refundOrder.getInvoice().getRefund() + refundOrder.getPrice()*refundOrder.getQuantity());
		refundOrder.setPrice(0);
		if(refundOrder.getCategories().equals(FixedElements.REFILL)||refundOrder.getCategories().equals(FixedElements.ACTIVATION)) {
			refundOrder.getCustomer().setCustomerCredit(refundOrder.getCustomer().getCustomerCredit() - refundOrder.getQuantity());
			refundOrder.getCustomer().setStatus(FixedElements.REFUND);
			refundOrder.getCustomer().setAction(FixedElements.CANCEL);
		}
		else if(refundOrder.getCategories().equals(FixedElements.SERVICE)) {
			refundOrder.getService().setStatus(FixedElements.REFUND);
		}
		else if(refundOrder.getCategories().equals(FixedElements.PAYBILL)) {
			refundOrder.getBill().setStatus(FixedElements.REFUND);
		}

		DataManipulater.updateData(refundOrder);
		DataManipulater.updateData(refundOrder.getInvoice());
				}
			});
		}
	}

	public void print() throws IOException {
		Printer defaultPrinter = Printer.getDefaultPrinter();

		if(defaultPrinter!=null) {
			PrinterJob job = PrinterJob.createPrinterJob(defaultPrinter);
			if(job!=null) {
				// wait for receipt design
				if(orderList!=null && orderList.size() !=0) {
					if(invoice!=null) {
						FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReceiptFX.fxml"));
						ReceiptController receiptController = fxmlLoader.<ReceiptController>getController();
						fxmlLoader.setController(receiptController);
						parent = (Parent)fxmlLoader.load();
						
						receiptController.setInvoiceDate(new Label(LocalDateTime.now().toString()));
						receiptController.setInvoiceNumber(new Label(Long.toString((invoice.getInvoiceID()))));
						int row = 1;
						for(Orders printorder : orderList) {
							receiptController.getItemlist().add(new Label(printorder.getCategories()), 1, row);
							receiptController.getItemlist().add(new Label(Double.toString(printorder.getPrice())), 2, row);
							row++;
						}
						receiptController.getItemlist().add(new Label("ITEMS:"), 1, row);
						receiptController.getItemlist().add(new Label(Integer.toString(orderList.size())), 2, row);
						row++;
						receiptController.getItemlist().add(new Label("Total:"), 1, row);
						receiptController.getItemlist().add(new Label(Double.toString(invoice.getTotal())), 2, row);
					}
				}
				
				boolean printed = job.printPage(parent);
				
				if(printed) {
					job.endJob();
				}
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Printer Error");
			alert.setHeaderText("No printer detected.");
			alert.setContentText("Make sure printer is ON");
			alert.showAndWait();
		}
	}
	
	public void closeOrderPane() {

		stage.close();
	}

	public void updateRightPane() throws IOException {
		this.getOrderPane().setRight(orderRightPane);
	}
	
	// other setter and getter
	public ObservableList<Orders> getOrderList() {
		return orderList;
	}

	public void setOrderList(ObservableList<Orders> orderList) {
		this.orderList = orderList;
	}

	public BorderPane getOrderPane() {
		return orderPane;
	}

	public void setOrderPane(BorderPane orderPane) {
		this.orderPane = orderPane;
	}

	public TableView<Orders> getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(TableView<Orders> orderTable) {
		this.orderTable = orderTable;
	}
	
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Orders getOrder() {
		return order;
	}
	
	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Employee getEmployee() {
		return employee;
	}

	public Map<Orders, LocalDate> getExpireDate() {
		return expireDateList;
	}

	public void setExpireDate(Map<Orders, LocalDate> expireDate) {
		this.expireDateList = expireDate;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
}