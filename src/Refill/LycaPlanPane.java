package Refill;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class LycaPlanPane {
		private GridPane lycaPlanPane;
		private Button lycaMobileButton;
		private Button UltraMobileButton;
		
		public LycaPlanPane() {
			lycaPlanPane = new GridPane();
			lycaMobileButton = new Button();
			lycaMobileButton.setText("Lycamobile");
			UltraMobileButton = new Button();
			UltraMobileButton.setText("Ultra Mobile");

			lycaPlanPane.setVgap(5);
			lycaPlanPane.setHgap(5);
			GridPane.setConstraints(lycaMobileButton, 0, 0);
			GridPane.setConstraints(UltraMobileButton, 1, 0);
			
			lycaPlanPane.getChildren().addAll(lycaMobileButton,UltraMobileButton);
		}
		
		public GridPane getCarrierPane() {
			return lycaPlanPane;
		}
		

}


