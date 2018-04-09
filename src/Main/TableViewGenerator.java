package Main;

import CustomerInfo.Customer;
import Order.Bill;
import Order.Orders;
import Order.Plan;
import Order.Service;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class TableViewGenerator {

	public TableViewGenerator() {
		
	}
	@SuppressWarnings("unchecked")
	public TableView<Orders> getOrderTable(ObservableList<Orders> orderList) {
		TableColumn<Orders, Double> PriceColumn = new TableColumn<>("Price");
		PriceColumn.setPrefWidth(50);
		PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		TableColumn<Orders, Double> regPriceColumn = new TableColumn<>("Price");
		regPriceColumn.setPrefWidth(50);
		regPriceColumn.setCellValueFactory(new PropertyValueFactory<>("regularPrice"));
		
		TableColumn<Orders, Double> discountColumn = new TableColumn<>("Disc.");
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

		table.getColumns().addAll(categoriesColumn, descriptionColumn,quantityColumn ,PriceColumn, regPriceColumn,discountColumn);
		
		return table;
	}
	
	@SuppressWarnings("unchecked")
	public TableView<Customer> getCustomerTable(ObservableList<Customer> customersList){
		TableColumn<Customer, Long> IDColumn = new TableColumn<>("CustomerID");
		IDColumn.setPrefWidth(80);
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		
		TableColumn<Customer, String> creditColumn = new TableColumn<>("Credit");
		creditColumn.setPrefWidth(30);
		creditColumn.setCellValueFactory(new PropertyValueFactory<>("customerCredit"));
		
		TableColumn<Customer, Integer> titleColumn = new TableColumn<>("Title");
		titleColumn.setPrefWidth(80);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("groupTitle"));
		
		TableColumn<Customer, String> actionColumn = new TableColumn<>("Action");
		actionColumn.setMinWidth(80);
		actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
		
		TableColumn<Customer, Double> oweAmountColumn = new TableColumn<>("Owe");
		oweAmountColumn.setMinWidth(50);
		oweAmountColumn.setCellValueFactory(new PropertyValueFactory<>("oweAmount"));
		
		TableColumn<Customer, String> newPlanColumn = new TableColumn<>("NewPlan");
		newPlanColumn.setMinWidth(50);
		newPlanColumn.setCellValueFactory(new PropertyValueFactory<>("newPlan"));
		
		TableColumn<Customer, String> currentPlanColumn = new TableColumn<>("CurrentPlan");
		currentPlanColumn.setMinWidth(50);
		currentPlanColumn.setCellValueFactory(new PropertyValueFactory<>("currentPlan"));
		
		TableColumn<Customer, String> prePlanColumn = new TableColumn<>("PrePlan");
		prePlanColumn.setMinWidth(50);
		prePlanColumn.setCellValueFactory(new PropertyValueFactory<>("prePlan"));
		
		newPlanColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer , String>, ObservableValue<String>>() {
		    @Override
		    public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer , String> param) {
		        return new SimpleObjectProperty<>(param.getValue().getNewPlan().getCarrier());
		    }
		});
		
		currentPlanColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer , String>, ObservableValue<String>>() {
		    @Override
		    public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer , String> param) {
		        return new SimpleObjectProperty<>(param.getValue().getCurrentPlan().getCarrier());
		    }
		});
		
		prePlanColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Customer , String>, ObservableValue<String>>() {
		    @Override
		    public ObservableValue<String> call(TableColumn.CellDataFeatures<Customer , String> param) {
		        return new SimpleObjectProperty<>(param.getValue().getPrePlan().getCarrier());
		    }
		});
		
		TableView<Customer> customerTable = new TableView<>(customersList);

		customerTable.getColumns().addAll(IDColumn, creditColumn, titleColumn, actionColumn, 
				oweAmountColumn, newPlanColumn, currentPlanColumn, prePlanColumn);
		
		return customerTable;
	}
	
	@SuppressWarnings("unchecked")
	public TableView<Customer> getUnpaidTable(ObservableList<Customer> unpaidList) {
		TableColumn<Customer, Long> idColumn = new TableColumn<>("Customer");
		idColumn.setPrefWidth(80);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		
		TableColumn<Customer, String> offerPriceColumn = new TableColumn<>("OfferPrice");
		offerPriceColumn.setPrefWidth(50);
		offerPriceColumn.setCellValueFactory(new PropertyValueFactory<>("offerPrice"));
		
		TableColumn<Customer, String> commentColumn = new TableColumn<>("Comment");
		commentColumn.setPrefWidth(100);
		commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
		
		TableColumn<Customer, Double> oweAmountColumn = new TableColumn<>("Owe");
		oweAmountColumn.setPrefWidth(80);
		oweAmountColumn.setCellValueFactory(new PropertyValueFactory<>("oweAmount"));
		
		TableColumn<Customer, String> firstNameColumn = new TableColumn<>("FirstName");
		firstNameColumn.setMinWidth(50);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
		TableColumn<Customer, String> lastNameColumn = new TableColumn<>("LastName");
		lastNameColumn.setMinWidth(50);
		lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		
		TableColumn<Customer, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setMinWidth(80);
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		TableView<Customer> unpaidTable = new TableView<>(unpaidList);

		unpaidTable.getColumns().addAll(idColumn,offerPriceColumn, oweAmountColumn,commentColumn ,firstNameColumn, lastNameColumn,statusColumn);
		
		return unpaidTable;
	}
	
	@SuppressWarnings("unchecked")
	public TableView<Customer> getToDoTable(ObservableList<Customer> unpaidList) {
		TableColumn<Customer, Integer> idColumn = new TableColumn<>("Customer");
		idColumn.setPrefWidth(80);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("customerID"));
		
		TableColumn<Customer, Integer> creditColumn = new TableColumn<>("Credit");
		creditColumn.setPrefWidth(50);
		creditColumn.setCellValueFactory(new PropertyValueFactory<>("customerCredit"));
		
		TableColumn<Customer, String> expireDateColumn = new TableColumn<>("ExpireBy");
		expireDateColumn.setPrefWidth(80);
		expireDateColumn.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
		
		TableColumn<Customer, String> actionColumn = new TableColumn<>("Action");
		actionColumn.setPrefWidth(80);
		actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
		
		TableColumn<Customer, String> commentColumn = new TableColumn<>("Comment");
		commentColumn.setPrefWidth(100);
		commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));
		
		TableColumn<Customer, String> languageColumn = new TableColumn<>("Language");
		languageColumn.setPrefWidth(50);
		languageColumn.setCellValueFactory(new PropertyValueFactory<>("language"));
		
		TableColumn<Customer, String> LTEColumn = new TableColumn<>("LTE");
		LTEColumn.setMinWidth(50);
		LTEColumn.setCellValueFactory(new PropertyValueFactory<>("LTEdata"));
		
		TableColumn<Customer, String> titleColumn = new TableColumn<>("Title"); 
		titleColumn.setMinWidth(50);
		titleColumn.setCellValueFactory(new PropertyValueFactory<>("groupTitle"));
		
		TableColumn<Customer, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setMinWidth(50);
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		TableView<Customer> unpaidTable = new TableView<>(unpaidList);

		unpaidTable.getColumns().addAll(idColumn, titleColumn,creditColumn, expireDateColumn, actionColumn ,commentColumn ,languageColumn, LTEColumn,statusColumn);
		
		return unpaidTable;
	}
	
	@SuppressWarnings("unchecked")
	public TableView<Plan> getPortListTable(ObservableList<Plan> portList) {
		TableColumn<Plan, Integer> idColumn = new TableColumn<>("ID");
		idColumn.setPrefWidth(30);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("planID"));
		
		TableColumn<Plan, String> phoneColumn = new TableColumn<>("PhoneNumber");
		phoneColumn.setMinWidth(50);
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		
		TableColumn<Plan, String> simColumn = new TableColumn<>("SimCard");
		simColumn.setPrefWidth(80);
		simColumn.setCellValueFactory(new PropertyValueFactory<>("sim"));
		
		TableColumn<Plan, Double> PUKColumn = new TableColumn<>("PUK");
		PUKColumn.setMinWidth(50);
		PUKColumn.setCellValueFactory(new PropertyValueFactory<>("PUK"));
		
		TableColumn<Plan, String> carrierColumn = new TableColumn<>("Carrier");
		carrierColumn.setPrefWidth(80);
		carrierColumn.setCellValueFactory(new PropertyValueFactory<>("carrier"));
		
		TableColumn<Plan, String> accountColumn = new TableColumn<>("Account");
		accountColumn.setPrefWidth(100);
		accountColumn.setCellValueFactory(new PropertyValueFactory<>("account"));
		
		TableColumn<Plan, Integer> pinColumn = new TableColumn<>("Pin");
		pinColumn.setPrefWidth(100);
		pinColumn.setCellValueFactory(new PropertyValueFactory<>("pin"));
		
		TableColumn<Plan, Double> planTypeColumn = new TableColumn<>("Plan");
		planTypeColumn.setPrefWidth(80);
		planTypeColumn.setCellValueFactory(new PropertyValueFactory<>("planType"));
	
		
		TableColumn<Plan, String> portdateColumn = new TableColumn<>("PortBy");
		portdateColumn.setMinWidth(100);
		portdateColumn.setCellValueFactory(new PropertyValueFactory<>("portdate"));
		
		TableColumn<Plan, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setMinWidth(80);
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		TableView<Plan> portTable = new TableView<>(portList);

		portTable.getColumns().addAll(idColumn,phoneColumn, carrierColumn, simColumn, PUKColumn, accountColumn,pinColumn ,planTypeColumn, portdateColumn,statusColumn);
		
		return portTable;
	}
	
	@SuppressWarnings("unchecked")
	public TableView<Bill> getBillTable(ObservableList<Bill> billList) {
		TableColumn<Bill, Integer> idColumn = new TableColumn<>("Bill ID");
		idColumn.setPrefWidth(30);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("billID"));
		
		TableColumn<Bill, String> carrierColumn = new TableColumn<>("Carrier");
		carrierColumn.setPrefWidth(80);
		carrierColumn.setCellValueFactory(new PropertyValueFactory<>("billCarrier"));
		
		TableColumn<Bill, String> accountColumn = new TableColumn<>("Account");
		accountColumn.setPrefWidth(100);
		accountColumn.setCellValueFactory(new PropertyValueFactory<>("billingAccount"));
		
		TableColumn<Bill, Double> balanceColumn = new TableColumn<>("Balance");
		balanceColumn.setPrefWidth(80);
		balanceColumn.setCellValueFactory(new PropertyValueFactory<>("billAmount"));
		
		TableColumn<Bill, Double> feeColumn = new TableColumn<>("Fee");
		feeColumn.setMinWidth(50);
		feeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceFess"));
		
		TableColumn<Bill, String> contactInfoColumn = new TableColumn<>("ContactInfo");
		contactInfoColumn.setMinWidth(100);
		contactInfoColumn.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
		
		TableColumn<Bill, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setMinWidth(80);
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		TableView<Bill> billTable = new TableView<>(billList);

		billTable.getColumns().addAll(idColumn,carrierColumn, accountColumn,balanceColumn ,feeColumn, contactInfoColumn,statusColumn);
		
		return billTable;
	}
	
	@SuppressWarnings("unchecked")
	public TableView<Service> getServiceTable(ObservableList<Service> serviceList) {
		TableColumn<Service, Double> idColumn = new TableColumn<>("Serive ID");
		idColumn.setPrefWidth(30);
		idColumn.setCellValueFactory(new PropertyValueFactory<>("serviceID"));
		
		TableColumn<Service, Double> deviceColumn = new TableColumn<>("Device");
		deviceColumn.setPrefWidth(80);
		deviceColumn.setCellValueFactory(new PropertyValueFactory<>("device"));
		
		TableColumn<Service, Double> serviceTypeColumn = new TableColumn<>("ServiceType");
		serviceTypeColumn.setPrefWidth(80);
		serviceTypeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceType"));
		
		TableColumn<Service, Integer> acceptDateColumn = new TableColumn<>("AcceptBy");
		acceptDateColumn.setPrefWidth(80);
		acceptDateColumn.setCellValueFactory(new PropertyValueFactory<>("acceptDate"));

		TableColumn<Service, Integer> completeDateColumn = new TableColumn<>("CompleteBy");
		completeDateColumn.setPrefWidth(80);
		completeDateColumn.setCellValueFactory(new PropertyValueFactory<>("completeDate"));
		
		TableColumn<Service, String> feeColumn = new TableColumn<>("Fee");
		feeColumn.setMinWidth(50);
		feeColumn.setCellValueFactory(new PropertyValueFactory<>("serviceFess"));
		
		TableColumn<Service, String> contactInfoColumn = new TableColumn<>("ContactInfo");
		contactInfoColumn.setMinWidth(100);
		contactInfoColumn.setCellValueFactory(new PropertyValueFactory<>("contactInfo"));
		
		TableColumn<Service, String> statusColumn = new TableColumn<>("Status");
		statusColumn.setMinWidth(80);
		statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
		
		TableView<Service> serviceTable = new TableView<>(serviceList);

		serviceTable.getColumns().addAll(idColumn,deviceColumn, serviceTypeColumn, statusColumn,acceptDateColumn, completeDateColumn ,feeColumn, contactInfoColumn);
		
		return serviceTable;
	}
	
	
	@SuppressWarnings("unchecked")
	public TableView<CustomerPropertyTable> getCustomerPropertyTable(ObservableList<Customer> customersList){
		CustomerPropertyTable customerPropertyTable;
		TableColumn<CustomerPropertyTable, String> phoneColumn = new TableColumn<>("Phone");
		phoneColumn.setPrefWidth(80);
		phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
		
		TableColumn<CustomerPropertyTable, String> actionColumn = new TableColumn<>("Action");
		actionColumn.setMinWidth(80);
		actionColumn.setCellValueFactory(new PropertyValueFactory<>("action"));
		
		TableColumn<CustomerPropertyTable, Double> oweAmountColumn = new TableColumn<>("Owe");
		oweAmountColumn.setMinWidth(50);
		oweAmountColumn.setCellValueFactory(new PropertyValueFactory<>("oweAmount"));
		
		TableColumn<CustomerPropertyTable, Double> newPlanColumn = new TableColumn<>("newPlan");
		newPlanColumn.setMinWidth(50);
		newPlanColumn.setCellValueFactory(new PropertyValueFactory<>("newPlan"));
		
		TableColumn<CustomerPropertyTable, Double> newCarrierColumn = new TableColumn<>("NewCarrier");
		newCarrierColumn.setMinWidth(50);
		newCarrierColumn.setCellValueFactory(new PropertyValueFactory<>("newCarrier"));
		
		
		ObservableList<CustomerPropertyTable>customersPropertyList = FXCollections.observableArrayList();
		for(Customer customer: customersList) {
			customerPropertyTable = new CustomerPropertyTable();
			
			System.out.println(customer.getAction());

			customerPropertyTable.setOweAmount(customer.getOweAmount());
			customerPropertyTable.setAction(customer.getAction());
			customerPropertyTable.setPhoneNumber(customer.getPhoneNumber());
			customerPropertyTable.setGroupTitle(customer.getGroupTitle());
			customerPropertyTable.setGroupNumber(customer.getGroupNumber().toString());
			customerPropertyTable.setStatus(customer.getStatus());
			customerPropertyTable.setCustomerCredit(customer.getCustomerCredit());
			if(customer.getExpireDate()!=null)
				customerPropertyTable.setExpireDate(customer.getExpireDate().toString());
			if(customer.getNewPlan().getCarrier()!=null)
				customerPropertyTable.setNewCarrier(customer.getNewPlan().getCarrier());
			if(customer.getNewPlan().getPlanType()!=null)
				customerPropertyTable.setNewPlan(customer.getNewPlan().getPlanType());
			if(customer.getNewPlan().getSim()!=null)
				customerPropertyTable.setSim(customer.getNewPlan().getSim());
			if(customer.getNewPlan().getPUK()!=null)
				customerPropertyTable.setPUK(customer.getNewPlan().getPUK());
			
			if(customer.getCurrentPlan()!=null) {
			if(customer.getCurrentPlan().getCarrier()!=null)
				customerPropertyTable.setCurrentCarrier(customer.getCurrentPlan().getCarrier());
			if(customer.getCurrentPlan().getPlanType()!=null)
				customerPropertyTable.setCurrentPlan(customer.getCurrentPlan().getPlanType());
			if(customer.getCurrentPlan().getAccount()!=null)
				customerPropertyTable.setCurrentPlan(customer.getCurrentPlan().getAccount());

				customerPropertyTable.setPin(customer.getCurrentPlan().getPin());
			}
			if(customer.getPrePlan()!=null)
			if(customer.getPrePlan().getCarrier()!=null)
				customerPropertyTable.setPreCarrier(customer.getPrePlan().getCarrier());
			if(customer.getOfferPrice()!=null)
				customerPropertyTable.setOfferPrice(customer.getOfferPrice());
			if(customer.getComment()!=null)
				customerPropertyTable.setComment(customer.getComment());
			
			customersPropertyList.add(customerPropertyTable);
		}	
		
		TableView<CustomerPropertyTable> customerTable = new TableView<>(customersPropertyList);
		customerTable.getColumns().addAll( phoneColumn, actionColumn, newPlanColumn ,newCarrierColumn);
		
		return customerTable;
	}
}
