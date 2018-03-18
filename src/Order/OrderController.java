package Order;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import Main.MainController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OrderController {

	@FXML
	OrderRightPaneController orderRightPaneController;
	@FXML
	GridPane orderRightPane;
	
	FXMLLoader	fxmlLoader;
	private ObservableList<Order> orderList;
	private Order order;
	private Map<Order, Service> serviceList;
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
	Parent parent;

	@FXML
	private BorderPane orderPane;
	
	@FXML
	private TableView<Order> orderTable;
	
	//Search pane
	@FXML
	ComboBox<String> searchComboBox;
	@FXML
	TextField searchField;
	@FXML
	Button searchButton;
	@FXML
	ComboBox<Integer> quanityComboBox;
	
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
		searchComboBox.getItems().addAll("Barcode","Product ID");
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
		
		orderList = FXCollections.observableArrayList();
		order = new Order();
		
	}
	
	public void processOrder() throws IOException{
		order.setDescription(order.getCarrier() + order.getPlan());
		orderList.add(order);

		updateRightPane();
		updateTable();
		updateTotal();
	}
	
	@SuppressWarnings("unchecked")
	public void updateTable() throws IOException {	
		TableColumn<Order, Double> PriceColumn = new TableColumn<>("Price");
		PriceColumn.setPrefWidth(50);
		PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		TableColumn<Order, Double> discountColumn = new TableColumn<>("Dis.");
		discountColumn.setPrefWidth(50);
		discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
		
		TableColumn<Order, Integer> quantityColumn = new TableColumn<>("Qty");
		quantityColumn.setPrefWidth(30);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		TableColumn<Order, String> categoriesColumn = new TableColumn<>("Categories");
		categoriesColumn.setMinWidth(100);
		categoriesColumn.setCellValueFactory(new PropertyValueFactory<>("categories"));
		
		TableColumn<Order, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setMinWidth(100);
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		TableView<Order> table = new TableView<>(orderList);

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
		
		for(Order currentOrder : orderList ) {
			subtotal = subtotal.add(new BigDecimal(currentOrder.getPrice()*currentOrder.getQuantity()));

			if(currentOrder.getCategories().equals(MainController.REFILL)) {
			PSCS = PSCS.add(new BigDecimal(currentOrder.getQuantity()*MainController.PSCSTAX));	
			NYTax = NYTax.add(new BigDecimal(subtotal.doubleValue()*MainController.TAXRATE));
				if(currentOrder.getCarrier().equals("Ultra"))
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
	//	receiveAmountProperty.setValue(receiveAmount.toString());
		
	/*
	//	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Order/SubtotalFX.fxml"));
	//	SubtotalController subtotalController = new SubtotalController();
	//	fxmlLoader.setController(subtotalController);
	//	parent = fxmlLoader.load();
	//	subtotalController.updateSubtotal();
	//	orderPane.setBottom(subtotalPane);
	 * 
	 */
	//	updateSubtotal();

	}
	
	public void updateRightPane() throws IOException {
		this.getOrderPane().setRight(orderRightPane);
	}
	
	
	public ObservableList<Order> getOrderList() {
		return orderList;
	}

	public void setOrderList(ObservableList<Order> orderList) {
		this.orderList = orderList;
	}

	public BorderPane getOrderPane() {
		return orderPane;
	}

	public void setOrderPane(BorderPane orderPane) {
		this.orderPane = orderPane;
	}

	public TableView<Order> getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(TableView<Order> orderTable) {
		this.orderTable = orderTable;
	}
	
	public void setOrder(Order order) {
		this.order = order;
	}
	public Order getOrder() {
		return order;
	}
	

	public BigDecimal getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	public void removeTableItem(Order removeOrder) {
		if(orderTable.getItems().remove(removeOrder))
			System.out.println("Removed");
		if(removeOrder.getCategories().equals(MainController.SERVICE))
			serviceList.remove(removeOrder);
	}
	public void addService(String serviceType, String contactInfo, java.time.LocalDate completeDate, 
			double serviceFee) {
		Service service = new Service();
		service.setAcceptDate(LocalDate.now());
		service.setCompleteDate(completeDate);
		service.setServiceType(serviceType);
		service.setServiceFee(serviceFee);
		service.setContactInfo(contactInfo);
		this.serviceList.put(order, service);
	}
	public void processTransaction(TransactionEnum type) throws IOException {
		switch (type) {
		case REFILL:
			gridPane  = FXMLLoader.load(getClass().getResource("../Refill/CarrierFX.fxml"));
			orderPane.setRight(gridPane);
			order = new Order();
			order.setCategories(MainController.REFILL);
			break;
		case ACTIVATION:
			gridPane  = FXMLLoader.load(getClass().getResource("../Activation/ActivationCarrierFX.fxml"));
			orderPane.setRight(gridPane);
			order = new Order();
			order.setCategories(MainController.ACTIVATION);
			break;
		case SERIVCE:
			gridPane  = FXMLLoader.load(getClass().getResource("./ServiceFX.fxml"));
			orderPane.setRight(gridPane);
			order = new Order();
			order.setCategories(MainController.SERVICE);
			break;
		case CASH:
			parent = FXMLLoader.load(getClass().getResource("../Main/DigiInputFX.fxml"));
			Stage stage = new Stage();
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(parent));
			stage.showAndWait();
			updateTotal();
		case OTHER:
			
			break;

		default:
			break;
		}
	}
	public void receiveCash(BigDecimal receiveAmount) {
		this.receiveAmount = receiveAmount;
		receiveAmountProperty.set("$" + this.receiveAmount);
		returnBalanceProperty.set("$" + (this.receiveAmount.subtract(total)));
	}
}