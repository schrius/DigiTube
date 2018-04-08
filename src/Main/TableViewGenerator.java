package Main;

import CustomerInfo.Customer;
import Order.Orders;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewGenerator {

	public TableViewGenerator() {
		
	}
	@SuppressWarnings("unchecked")
	public TableView<Orders> getOrderTable(ObservableList<Orders> orderList) {
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
		
		return table;
	}
	
	@SuppressWarnings("unchecked")
	public TableView<Customer> getCustomerTable(ObservableList<Customer> customersList){
		TableColumn<Customer, Double> PriceColumn = new TableColumn<>("Price");
		PriceColumn.setPrefWidth(50);
		PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		TableColumn<Customer, Double> discountColumn = new TableColumn<>("Dis.");
		discountColumn.setPrefWidth(50);
		discountColumn.setCellValueFactory(new PropertyValueFactory<>("discount"));
		
		TableColumn<Customer, Integer> quantityColumn = new TableColumn<>("Qty");
		quantityColumn.setPrefWidth(30);
		quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		
		TableColumn<Customer, String> categoriesColumn = new TableColumn<>("Categories");
		categoriesColumn.setMinWidth(100);
		categoriesColumn.setCellValueFactory(new PropertyValueFactory<>("categories"));
		
		TableColumn<Customer, String> descriptionColumn = new TableColumn<>("Description");
		descriptionColumn.setMinWidth(100);
		descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
		
		TableView<Customer> customerTable = new TableView<>(customersList);

		customerTable.getColumns().addAll(categoriesColumn, descriptionColumn,quantityColumn ,PriceColumn, discountColumn);
		
		return customerTable;
	}
}
