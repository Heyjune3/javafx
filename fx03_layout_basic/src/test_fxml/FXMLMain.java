package test_fxml;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;	// Module-info.java에 requires javafx.fxml; 입력
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class FXMLMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		
		try {
			java.net.URL url = getClass().getResource("Test.fxml"); 
			Parent root = FXMLLoader.load(url);
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
