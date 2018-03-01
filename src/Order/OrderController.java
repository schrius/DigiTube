package Order;

import java.io.IOException;
import java.math.BigDecimal;

import Main.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class OrderController {

	OrderRightPaneController orderRightPaneController;
	
	FXMLLoader	fxmlLoader;
	private ObservableList<Order> orderList;
	private Order order;
	private BigDecimal tax;
	private BigDecimal NYTax;
	private BigDecimal subtotal;
	private BigDecimal discoount;
	private BigDecimal total;
	
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
	public void initialize() throws IOException {		
		searchComboBox.getItems().addAll("Barcon","Product ID");
		searchComboBox.getSelectionModel().selectFirst();
		quanityComboBox.getItems().addAll(1,2,3,4,5,6,7,8,9);
		quanityComboBox.getSelectionModel().selectFirst();
		
		orderList = FXCollections.observableArrayList();
		order = new Order();
		tax = new BigDecimal(0);
		NYTax = new BigDecimal(0);
		subtotal = new BigDecimal(0);
		discoount = new BigDecimal(0);
		total = new BigDecimal(0);
	}
	
	public void processOrder(boolean refill) {
		subtotal = subtotal.add(new BigDecimal(order.getRegularPrice()*order.getQuantity()));
		tax = tax.add(new BigDecimal(subtotal.doubleValue()*MainController.TAXRATE));
		if(refill)
		NYTax = NYTax.add(new BigDecimal(order.getQuantity()*MainController.NYTAX));	
		tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
		subtotal = subtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
		NYTax = NYTax.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		if(order.getRegularPrice() > order.getDiscountPrice()) {
			discoount = discoount.add(new BigDecimal((order.getRegularPrice() - order.getDiscountPrice()) *
					order.getQuantity()));
		}

		total = total.add(new BigDecimal(subtotal.doubleValue() + tax.doubleValue() + NYTax.doubleValue()));
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
		updateTable();
	}
	
	@SuppressWarnings("unchecked")
	public void updateTable() {
		TableColumn<Order, String> phoneNumberColumn = new TableColumn<>("Phone Number");
		phoneNumberColumn.setPrefWidth(80);
		phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
		
		TableColumn<Order, Double> regularPriceColumn = new TableColumn<>("Reg.Price");
		regularPriceColumn.setPrefWidth(50);
		regularPriceColumn.setCellValueFactory(new PropertyValueFactory<>("regularPrice"));
		
		TableColumn<Order, Double> discountPriceColumn = new TableColumn<>("Dis.Price");
		discountPriceColumn.setPrefWidth(50);
		discountPriceColumn.setCellValueFactory(new PropertyValueFactory<>("discountPrice"));
		
		TableColumn<Order, Integer> quantityColumn = new TableColumn<>("Qty");
		quantityColumn.setPrefWidth(30);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		TableColumn<Order, String> categoriesColumn = new TableColumn<>("Categories");
		categoriesColumn.setMinWidth(100);
		categoriesColumn.setCellValueFactory(new PropertyValueFactory<>("categories"));
		
		TableColumn<Order, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setMinWidth(100);
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		TableView<Order> table = new TableView<>();

		orderList.add(new Order(order.getOrderID(),order.getCustomerID(),order.getEmployeeID(),order.getQuantity(),
				order.getRegularPrice(), order.getDiscountPrice(), order.getCustomerPhone(), order.getCategories(),
				order.getCarrier(), order.getPlan(), order.getDescription(), order.getOrderDate()));
		table.setItems(orderList);
		table.getColumns().addAll(phoneNumberColumn, quantityColumn ,regularPriceColumn, discountPriceColumn, 
				categoriesColumn,descriptionColumn);
		orderTable = table;
		
		orderPane.setCenter(orderTable);
	}
	
	public void refillListener() throws IOException {
		order = new Order();
		order.setCategories(MainController.refill);
		GridPane carrierPane = FXMLLoader.load(getClass().getResource("../Refill/CarrierFX.fxml"));
		orderPane.setRight(carrierPane);
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
	public BigDecimal getTax() {
		return tax;
	}

	public void setTax(BigDecimal tax) {
		this.tax = tax;
	}

	public BigDecimal getNYtax() {
		return NYTax;
	}

	public void setNYtax(BigDecimal nYtax) {
		NYTax = nYtax;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	public BigDecimal getDiscoount() {
		return discoount;
	}

	public void setDiscoount(BigDecimal discoount) {
		this.discoount = discoount;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}


}