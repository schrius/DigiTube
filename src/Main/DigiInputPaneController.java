package Main;
/*
 * Cash amount input pane controller
 */
import java.math.BigDecimal;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DigiInputPaneController {
	StringProperty input;
	
	@FXML
	TextField inputField;
	@FXML
	Label centerLabel;
	@FXML
	Button eraseButton;
	@FXML
	Button backButton;
	@FXML
	Button button1;
	@FXML
	Button button2;
	@FXML
	Button button3;
	@FXML
	Button button4;
	@FXML
	Button button5;
	@FXML
	Button button6;
	@FXML
	Button button7;
	@FXML
	Button button8;
	@FXML
	Button button9;
	@FXML
	Button button0;
	@FXML
	Button dotButton;
	@FXML
	Button enterButton;
	
	@FXML
	public void initialize() {
		input = new SimpleStringProperty();
		inputField.textProperty().bind(input);
	}
	
	public void eraseButtonListener() {
		inputField.clear();
	}
	public void backButtonListener() {
		
	}
	public void button1Listener() {
		if(input.getValue() != null)
		input.setValue(input.getValue() + "1");
		else 
		input.setValue("1");
	}
	public void button2Listener() {
		if(input.getValue() != null)
		input.setValue(input.getValue() + "2");
		else 
		input.setValue("2");
	}
	public void button3Listener() {
		if(input.getValue() != null)
		input.setValue(input.getValue() + "3");
		else 
		input.setValue("3");
	}
	public void button4Listener() {
		if(input.getValue() != null)
		input.setValue(input.getValue() + "4");
		else 
		input.setValue("4");
	}
	public void button5Listener() {
		if(input.getValue() != null)
		input.setValue(input.getValue() + "5");
		else 
		input.setValue("5");
	}
	public void button6Listener() {
		if(input.getValue() != null)
		input.setValue(input.getValue() + "6");
		else 
		input.setValue("6");
	}
	public void button7Listener() {
		if(input.getValue() != null)
		input.setValue(input.getValue() + "7");
		else 
		input.setValue("7");
	}
	public void button8Listener() {
		if(input.getValue() != null)
		input.setValue(input.getValue() + "8");
		else 
		input.setValue("8");
	}
	public void button9Listener() {
		if(input.getValue() != null)
		input.setValue(input.getValue() + "9");
		else 
		input.setValue("9");
	}
	public void button0Listener() {
		if(input.getValue() != null)
		input.setValue(input.getValue() + "0");
		else 
		input.setValue("0");
	}
	public void dotButtonListener() {
		if(input.getValue() != null)
		input.setValue(input.getValue() + ".");
		else 
		input.setValue(".");
	}
	public void enterButtonListener() {
		if(inputField.getText()!=null)
		MainController.getOrderController().receiveCash(new BigDecimal(inputField.getText()));
		Stage stage = (Stage) enterButton.getScene().getWindow();
		stage.close();
	}
	public String getinputValue() {
		return inputField.getText();
	}
}
