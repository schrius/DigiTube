package Order;

import java.io.IOException;
import java.math.BigDecimal;

import Main.MainController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class OrderController {

	OrderRightPaneController orderRightPaneController;
	SubtotalController subtotalController;
	
	FXMLLoader	fxmlLoader;
	private ObservableList<Order> orderList;
	private Order order;
	private BigDecimal tax;
	private BigDecimal NYTax;
	private BigDecimal subtotal;
	private BigDecimal discount;
	private BigDecimal total;
	
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
	public void initialize() throws IOException {		
		searchComboBox.getItems().addAll("Barcon","Product ID");
		searchComboBox.getSelectionModel().selectFirst();
		quanityComboBox.getItems().addAll(1,2,3,4,5,6,7,8,9);
		quanityComboBox.getSelectionModel().selectFirst();
		
		orderList = FXCollections.observableArrayList();
		order = new Order();
	}
	
	public void processOrder() throws IOException{
		order.setDescription(order.getCarrier() + order.getCategories() + order.getPlan());
		orderList.add(order);

		updateTable();
		updateTotal();
	}
	
	@SuppressWarnings("unchecked")
	public void updateTable() {	
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
	}
	
	public void updateTotal() throws IOException {
		tax = new BigDecimal(0);
		NYTax = new BigDecimal(0);
		subtotal = new BigDecimal(0);
		discount = new BigDecimal(0);
		total = new BigDecimal(0);
		
		for(Order currentOrder : orderList ) {
			subtotal = subtotal.add(new BigDecimal(currentOrder.getPrice()*currentOrder.getQuantity()));
			tax = tax.add(new BigDecimal(subtotal.doubleValue()*MainController.TAXRATE));
			if(currentOrder.getCategories().equals(MainController.REFILL))
			NYTax = NYTax.add(new BigDecimal(currentOrder.getQuantity()*MainController.NYTAX));	
			tax = tax.setScale(2, BigDecimal.ROUND_HALF_UP);
			subtotal = subtotal.setScale(2, BigDecimal.ROUND_HALF_UP);
			NYTax = NYTax.setScale(2, BigDecimal.ROUND_HALF_UP);
			discount = discount.add(new BigDecimal((currentOrder.getDiscount() * currentOrder.getQuantity()))); 		
		}

		total = total.add(new BigDecimal(subtotal.doubleValue() + tax.doubleValue() + NYTax.doubleValue()));
		total = total.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../Order/SubtotalFX.fxml"));
		SubtotalController subtotalController = new SubtotalController();
		fxmlLoader.setController(subtotalController);
		parent = fxmlLoader.load();
		subtotalController.updateSubtotal();
		orderPane.setBottom(parent);
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

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discoount) {
		this.discount = discoount;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public void removeTableItem(Order removeOrder) {
		if(orderTable.getItems().remove(removeOrder))
			System.out.println("Removed");
	}
}