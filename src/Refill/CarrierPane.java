package Refill;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class CarrierPane {
	private GridPane carrierPane;
	private Button lycaMobileButton;
	private Button UltraMobileButton;
	private Button SimpleMobileButton;
	
	public CarrierPane() {
		carrierPane = new GridPane();
		lycaMobileButton = new Button();
		lycaMobileButton.setText("Lycamobile");
		lycaMobileButton.setOnAction( e -> {
			LycaPlanPane lyca = new LycaPlanPane();
			BorderPane orderPane = (BorderPane) lycaMobileButton.getScene().getRoot();
			orderPane.setRight(lyca.getCarrierPane());	
		});
		UltraMobileButton = new Button();
		UltraMobileButton.setText("Ultra Mobile");
		SimpleMobileButton = new Button();
		SimpleMobileButton.setText("Simple Mobile");
		carrierPane.setVgap(5);
		carrierPane.setHgap(5);
		GridPane.setConstraints(lycaMobileButton, 0, 0);
		GridPane.setConstraints(UltraMobileButton, 1, 0);
		GridPane.setConstraints(SimpleMobileButton, 2, 0);
		
		carrierPane.getChildren().addAll(lycaMobileButton,UltraMobileButton,SimpleMobileButton);
	}
	
	public GridPane getCarrierPane() {
		return carrierPane;
	}
	

}
