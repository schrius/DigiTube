package superUser;

import java.io.IOException;
import java.math.BigDecimal;
import DataManipulater.DataManipulater;
import Employee.Employee;
import Employee.EmployeeWorkingTime;
import Main.FixedElements;
import Main.TableEntry;
import Main.TableViewGenerator;
import Order.Invoice;
import Order.Orders;
import Order.PayBack;
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
	Parent parent;
	
	@FXML
	BorderPane borderPane;
	@FXML
	ComboBox<String> searchBox;
	@FXML
	TextField searchField;
	
	@FXML
	TableView<? extends TableEntry> table;
	
	// left pane
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
	
	// right pane
	@FXML
	DatePicker beginDate;
	@FXML
	DatePicker endDate;
	@FXML
	Button showAndCalcuate;
	//bottom pane
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
	public void initialize() {
		searchBox.getItems().addAll("EmployeeID", "Sales");
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
			employee = (Employee) DataManipulater.searchData(Long.parseLong(searchField.getText()), Employee.class);
			if(employee!=null) {
			String hql = "FROM EmployeeWorkingTime e WHERE e.employee ="+ searchField.getText();
					
			if(beginDate.getValue()!=null && endDate.getValue()!=null) {
				hql = hql + " AND e.punchOut BETWEEN '" + beginDate.getValue() 
				+ "' AND '" + endDate.getValue() + "'";
			}
			String salesHQL = "FROM Orders o WHERE o.employee="+employee.getEmployeeID();
			if(beginDate.getValue()!=null && endDate.getValue()!=null) {
				salesHQL = salesHQL + " AND o.orderDate BETWEEN '" + beginDate.getValue() 
				+ "' AND '" + endDate.getValue() + "'";
			}
			
			ObservableList<EmployeeWorkingTime> worksheet= (ObservableList<EmployeeWorkingTime>) DataManipulater.ListData(hql);
			ObservableList<Orders> salesOrder = (ObservableList<Orders>) DataManipulater.ListData(salesHQL);
			table = new TableViewGenerator().getWorkSheetTable(worksheet);
			
			BigDecimal worktime = new BigDecimal(0);
			BigDecimal GrossPay = new BigDecimal(0);
			int simSales = 0;
			for(EmployeeWorkingTime time : worksheet) {
				worktime.add(new BigDecimal(time.getWorkingHour()));
			}
			for(Orders order : salesOrder) {
				if(order.getCategories().equals(FixedElements.ACTIVATION))
					simSales++;
			}
			worktime = worktime.divide(new BigDecimal(60));
			worktime = worktime.setScale(2, BigDecimal.ROUND_HALF_UP);
			hours.setText(worktime.toString());
			payRate.setText(Double.toString(employee.getSalary()));
			GrossPay = GrossPay.add(new BigDecimal(employee.getSalary()*worktime.doubleValue()));
			grossPay.setText(GrossPay.toString());
			sales.setText(Integer.toString(simSales));
			commission.setText(Integer.toString(simSales*2));
			totalPay.setText(Double.toString((GrossPay.add(new BigDecimal(simSales*2)).doubleValue())));
			
			borderPane.setCenter(table);
		}
		else if(searchBox.getValue().equals("Sales")) {
			String hql = "FROM Invoice i WHERE ";
			
			if(beginDate.getValue()!=null && endDate.getValue()!=null) {
				hql = hql + " i.lastUpdate BETWEEN '" + beginDate.getValue() 
				+ "' AND '" + endDate.getValue() + "'";
			}
			ObservableList<Invoice> invoicelist= (ObservableList<Invoice>) DataManipulater.ListData(hql);
			table = new TableViewGenerator().getInvoiceTable(invoicelist);
			BigDecimal byCredit = new BigDecimal(0);
			BigDecimal byCash = new BigDecimal(0);
			BigDecimal unpaid = new BigDecimal(0);
			BigDecimal discount = new BigDecimal(0);
			BigDecimal refund = new BigDecimal(0);
			BigDecimal payback = new BigDecimal(0);
			BigDecimal totalsales = new BigDecimal(0);
			for(Invoice invoice : invoicelist) {
				if(invoice.getPaymentMethod().equals(FixedElements.CREDIT))
					byCredit = byCredit.add(new BigDecimal(invoice.getTotal()));
				if(invoice.getPaymentMethod().equals(FixedElements.CASH))
					byCash = byCash.add(new BigDecimal(invoice.getTotal()));
				if(invoice.getPaymentMethod().equals(FixedElements.UNPAID))
					unpaid= unpaid.add(new BigDecimal(invoice.getTotal()));
					discount = discount.add(new BigDecimal(invoice.getDiscount()));
					refund = refund.add(new BigDecimal(invoice.getRefund()));
					totalsales= totalsales.add(new BigDecimal(invoice.getTotal()));
			}
			
			String paybackHQL = "FROM PayBack p WHERE ";
			if(beginDate.getValue()!=null && endDate.getValue()!=null) {
				paybackHQL = paybackHQL + " i.lastUpdate BETWEEN '" + beginDate.getValue() 
				+ "' AND '" + endDate.getValue() + "'";
			}
			ObservableList<PayBack> paybacklist= (ObservableList<PayBack>) DataManipulater.ListData(paybackHQL);
			for(PayBack item : paybacklist) {
				payback= payback.add(new BigDecimal(item.getPaybackAmount()));
			}
			credit.setText(byCredit.toString());
			cash.setText(byCash.toString());
			this.unpaid.setText(unpaid.toString());
			this.discount.setText(discount.toString());
			this.refund.setText(refund.toString());
			this.payback.setText(payback.toString());
			total.setText(totalsales.toString());
			
			borderPane.setCenter(table);
		}
		}
	}
}


