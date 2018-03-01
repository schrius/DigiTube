package Order;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderDetail {
		IntegerProperty itemID = new SimpleIntegerProperty();
		IntegerProperty orderID = new SimpleIntegerProperty();
		IntegerProperty productID = new SimpleIntegerProperty();
		IntegerProperty customerID = new SimpleIntegerProperty();
		IntegerProperty employeeID = new SimpleIntegerProperty();
		IntegerProperty quantity = new SimpleIntegerProperty();
		DoubleProperty orignalPrice = new SimpleDoubleProperty();
		DoubleProperty discountPrice = new SimpleDoubleProperty();
		StringProperty customerPhone = new SimpleStringProperty();
		StringProperty categories = new SimpleStringProperty();
		StringProperty description = new SimpleStringProperty();
		StringProperty orderDate = new SimpleStringProperty();

}
