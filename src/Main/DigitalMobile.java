package Main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DigitalMobile extends Application{
		Stage window;
		@Override
		public void start(Stage stage){
			window = stage;
			Parent parent;
			try {
				
				parent = FXMLLoader.load(getClass().getResource("EmployeeLoginFX.fxml"));
				Scene scene = new Scene(parent);

				window.setTitle("Digital Mobile");
				window.setScene(scene);
				window.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public static void main(String[] args) {
			launch(args);
		}

}
