package Order;

import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class OrderController {

	static private ObservableList<Order> orderList;
	static private Order order;
	
	public static ObservableList<Order> getOrderList() {
		return orderList;
	}

	public static void setOrderList(ObservableList<Order> orderList) {
		OrderController.orderList = orderList;
	}

	public static BorderPane getOrderPane() {
		return orderPane;
	}

	public static void setOrderPane(BorderPane orderPane) {
		OrderController.orderPane = orderPane;
	}

	public static TableView<Order> getOrderTable() {
		return orderTable;
	}

	public static void setOrderTable(TableView<Order> orderTable) {
		OrderController.orderTable = orderTable;
	}
	
	public static void setOrder(Order order) {
		OrderController.order = order;
	}
	public static Order getOrder() {
		return order;
	}
	
	@FXML
	static private BorderPane orderPane;
	
	@FXML
	private static TableView<Order> orderTable;
	
	//Search pane
	@FXML
	ComboBox<String> searchComboBox;
	@FXML
	TextField searchField;
	@FXML
	Button searchButton;
	@FXML
	ComboBox<Integer> quanityComboBox;
	
	// Orders buttons
	@FXML
	Button refillButton;
	@FXML
	Button activationButton;
	@FXML
	Button serviceButton;
	@FXML
	Button devicesButton;
	@FXML
	Button accessoriesButton;
	@FXML
	Button payBillButton;
	
	// payment method
	@FXML
	Button cashButton;
	@FXML
	Button cardButton;
	@FXML
	Button unpaidButton;
	
	@FXML
	Button changeQuantityButton;
	@FXML
	Button changePriceButton;
	@FXML
	Button removeButton;
	
	@FXML
	Button printButton;
	@FXML
	Button refundButton;
	@FXML
	Button cancelButton;
	
	@FXML
	Label subtotalLabel;
	@FXML
	Label discountLabel;
	@FXML
	Label taxLabel;
	@FXML
	Label NYTaxLabel;
	@FXML
	Label totalLabel;
	
	@FXML
	public void initialize() throws IOException {		
		searchComboBox.setItems(FXCollections.observableArrayList("Barcon","Product ID"));
		searchComboBox.setValue("Barcon");
		quanityComboBox.setEditable(true);
		quanityComboBox.setItems(FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9));
		quanityComboBox.getSelectionModel().selectFirst();
		orderList = FXCollections.observableArrayList();
		order = new Order();
	}
	
	public static void processOrder() {

		updateTable();
	}
	
	@SuppressWarnings("unchecked")
	public static void updateTable() {
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

		orderList.add(order);
		table.setItems(orderList);
		table.getColumns().addAll(phoneNumberColumn, quantityColumn ,regularPriceColumn, discountPriceColumn, 
				categoriesColumn,descriptionColumn);
		orderTable = table;
		
		orderPane.setCenter(orderTable);
	}
	
	public void refillListener() throws IOException {
		order = new Order();
		order.categories = "Refill";
		GridPane carrierPane = FXMLLoader.load(getClass().getResource("../Refill/CarrierFX.fxml"));
		orderPane.setRight(carrierPane);
	}

}