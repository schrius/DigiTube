package Order;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import DataManipulater.InvoiceDataManipulater;
import DataManipulater.OrderDataManipulater;
import Employee.Employee;
import Main.FixedElements;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OrderController {

	Employee employee;
	OrderDataManipulater orderDataManipulater;
	InvoiceDataManipulater invoiceDataManipulater;
	@FXML
	OrderRightPaneController orderRightPaneController;
	@FXML
	GridPane orderRightPane;
	
	FXMLLoader	fxmlLoader;
	private ObservableList<Orders> orderList;
	private Orders order;
	private Map<Orders, Service> serviceList;
	private BigDecimal PSCS;
	private BigDecimal NYTax;
	private BigDecimal subtotal;
	private BigDecimal discount;
	private BigDecimal total;
	private BigDecimal serviceFee;
	private BigDecimal receiveAmount;
//	private BigDecimal returnBalance;
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
		searchComboBox.getItems().addAll("Barcode","Product ID", "Invoice", "CustomerID");
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
		
		serviceList = new HashMap<Orders, Service>();
		orderList = FXCollections.observableArrayList();
	}
	
	public void processOrder() throws IOException{
	//	order.setDescription(order.getProduct().getDescription());
		orderList.add(order);

		updateRightPane();
		updateTable();
		updateTotal();
	}
	
	@SuppressWarnings("unchecked")
	public void updateTable() throws IOException {	
		TableColumn<Orders, Double> PriceColumn = new TableColumn<>("Price");
		PriceColumn.setPrefWidth(50);
		PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		TableColumn<Orders, Double> discountColumn = new TableColumn<>("Dis.");
		discountColumn.setPrefWidth(50);
		discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
		
		TableColumn<Orders, Integer> quantityColumn = new TableColumn<>("Qty");
		quantityColumn.setPrefWidth(30);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		TableColumn<Orders, String> categoriesColumn = new TableColumn<>("Categories");
		categoriesColumn.setMinWidth(100);
		categoriesColumn.setCellValueFactory(new PropertyValueFactory<>("categories"));
		
		TableColumn<Orders, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setMinWidth(100);
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		TableView<Orders> table = new TableView<>(orderList);

		table.getColumns().addAll(categoriesColumn, descriptionColumn,quantityColumn ,PriceColumn, discountColumn);
		orderTable = table;	
		orderPane.setCenter(orderTable);
		updateTotal();
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
				if(currentOrder.getPlan().getCarrier().equals("Ultra"))
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
		if(receiveAmount!=null)
			returnBalanceProperty.set("$" + (this.receiveAmount.subtract(total)));
	}

	public void removeTableItem(Orders removeOrder) {
		if(orderTable.getItems().remove(removeOrder))
			System.out.println("Removed");
		if(removeOrder.getCategories().equals(FixedElements.SERVICE))
			serviceList.remove(removeOrder);

	}
	
	public void addService(String serviceType, String device, String contactInfo, java.time.LocalDate completeDate, 
			double serviceFee) {
		Service service = new Service();
		service.setAcceptDate(LocalDate.now());
		service.setCompleteDate(completeDate);
		service.setServiceType(serviceType);
		service.setDevice(device);
		service.setServiceFee(serviceFee);
		service.setContactInfo(contactInfo);
		this.serviceList.put(order, service);
	}
	
	public void processTransaction(TransactionEnum type) throws IOException {
		switch (type) {
		case CANCEL:
			updateRightPane();
			break;
		case REFILL:
			gridPane  = FXMLLoader.load(getClass().getResource("../Refill/CarrierFX.fxml"));
			orderPane.setRight(gridPane);
			order = new Orders();
			order.setCategories(FixedElements.REFILL);
			break;
		case ACTIVATION:
			gridPane  = FXMLLoader.load(getClass().getResource("../Activation/ActivationCarrierFX.fxml"));
			orderPane.setRight(gridPane);
			order = new Orders();
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
		planBox  = FXMLLoader.load(getClass().getResource("../Refill/LycaMobileFX.fxml"));
			order.getPlan().setCarrier(FixedElements.LYCA);
			orderPane.setRight(planBox);
		break;
		case ULTRA:
		planBox  = FXMLLoader.load(getClass().getResource("../Refill/UltraMobileFX.fxml"));
		order.getPlan().setCarrier(FixedElements.ULTRA);
			orderPane.setRight(planBox);
		break;
		case SIMPLE:
		planBox  = FXMLLoader.load(getClass().getResource("../Refill/SimpleMobileFX.fxml"));
		order.getPlan().setCarrier(FixedElements.SIMPLE);
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
		case 19:
			setRefillPane(19, FixedElements.P$19);
			break;
		case 23:
			setRefillPane(23, FixedElements.P$23);
			break;
		case 25:
			setRefillPane(25, FixedElements.P$25);
			break;

		default:
			break;
		}
	}
	public void setRefillPane(double price, String plan) throws IOException {
		processBox  = FXMLLoader.load(getClass().getResource("../Refill/RefillFX.fxml"));
		order.getPlan().setPlanType(plan);
		order.setPrice(price);
		order.getPlan().setRegularPrice(price);
		orderPane.setRight(processBox);
	}
	
	public void processRefill(String phoneNumber, int quantity) throws IOException {
		order.getPlan().setPhoneNumber(phoneNumber);
		order.setQuantity(quantity);
		processOrder();
	}
	
	public void receiveCash(BigDecimal receiveAmount) {
		if(total!=null) {
		this.receiveAmount = receiveAmount;
		receiveAmountProperty.set("$" + this.receiveAmount);
		returnBalanceProperty.set("$" + (this.receiveAmount.subtract(total)));
		}
	}
	
	public void removeItem(){
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog");
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
	public boolean completeOrder(String payment) throws IOException {
		if(payment.equals(FixedElements.CASH)) {
			if(receiveAmount.compareTo(total)>= 0) {
/*
 * 				//if(orderDataManipulater == null) {
				//	orderDataManipulater = new OrderDataManipulater();
				//}
				for(Orders order: orderList) {
					order.setPaymentMethod(FixedElements.CASH);
					orderDataManipulater.addOrder(order);
				}
 */
				generateInvoice();
				initialize();
				orderTable.getItems().clear();
				return true;
			}
		}
		return false;
	}
	
	public void generateInvoice() {
		Invoice invoice = new Invoice();
		if(invoiceDataManipulater == null)
			invoiceDataManipulater = new InvoiceDataManipulater();
		invoice.setOrderDate(LocalDateTime.now());
		for(Orders orders : orderList) {
			invoice.getOrder().add(orders);
		}

		invoiceDataManipulater.addInvoice(invoice);
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
}