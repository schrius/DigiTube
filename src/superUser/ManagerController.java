package superUser;

import java.io.IOException;
import java.time.LocalDate;
import DataManipulater.DataManipulater;
import Employee.Employee;
import Employee.EmployeeWorkingTime;
import Main.TableEntry;
import Main.TableViewGenerator;
import Order.Invoice;
import Order.Orders;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ManagerController {
	Employee employee;
	EmployeeWorkingTime worksheet;
	Orders order;
	Invoice invoice;
	Parent parent;
	
	@FXML
	BorderPane borderPane;
	@FXML
	ComboBox<String> searchBox;
	@FXML
	TextField searchField;
	
	@FXML
	TableView<? extends TableEntry> table;
	
	// left border
	@FXML
	Button newEmployeeButton;
	@FXML
	Button updateEmployeeButton;
	@FXML
	Button newProductButton;
	@FXML
	Button updateProductButton;
	@FXML
	Button updateOrderButton;
	@FXML
	Button updateCustomerButton;
	
	// right
	@FXML
	DatePicker beginDate;
	@FXML
	DatePicker endDate;
	@FXML
	Button showAndCalcuate;
	
	@FXML
	Label hours;
	@FXML
	Label payRate;
	@FXML
	Label grossPay;
	@FXML
	Label sales;
	@FXML
	Label commission;
	@FXML
	Label totalPay;
	@FXML
	Label refund;
	@FXML
	Label cash;
	@FXML
	Label credit;
	@FXML
	Label unpaid;
	@FXML
	Label payback;
	@FXML
	Label discount;
	@FXML
	Label total;
	
	@FXML
	public void initialized() {
		searchBox.getItems().addAll("EmployeeID", "Invoice", "Sales");
		searchBox.getSelectionModel().selectFirst();
	}
	
	public void newEmployeeButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		parent = FXMLLoader.load(getClass().getResource(("newEmployeeFX.fxml")));
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
	public void updateEmployeeButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		parent = FXMLLoader.load(getClass().getResource(("UpdateEmployeeFX.fxml")));
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
	public void newProductButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		parent = FXMLLoader.load(getClass().getResource(("NewProductFX.fxml")));
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
	public void updateProductButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		parent = FXMLLoader.load(getClass().getResource(("UpdateProductFX.fxml")));
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
	public void updateOrderButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		parent = FXMLLoader.load(getClass().getResource(("../Order/OrderUpdateFX.fxml")));
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
	public void updateCustomerButtonListener() throws IOException {
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		parent = FXMLLoader.load(getClass().getResource(("../CustomerInfo/CustomerUpdateFX.fxml")));
		stage.setScene(new Scene(parent));
		stage.showAndWait();
	}
	@SuppressWarnings("unchecked")
	public void showAndCalcuateListener() {	
		if(searchBox.getValue().equals("EmployeeID")) {
			String hql = "FROM EmployeeWorkingTime e WHERE employee ="+ searchField.getText();
					
			if(beginDate.getValue()!=null) 
				hql = hql + " AND c.expireDate BETWEEN '" + beginDate.getValue() + "' AND '";
			else hql = hql + " AND c.expireDate BETWEEN '" + LocalDate.now() + "' AND '";
					
			if(endDate.getValue()!=null) 
				hql = hql + endDate.getValue() + "'";
			else hql = hql + LocalDate.now() + "'";
			
			ObservableList<EmployeeWorkingTime> worksheet= (ObservableList<EmployeeWorkingTime>) DataManipulater.ListData(hql);
			table = new TableViewGenerator().getWorkSheetTable(worksheet);
			borderPane.setCenter(table);
		}
	}

}


