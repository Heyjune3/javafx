package chat_client;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;

public class ClientMain extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Client.fxml"));
			Scene scene = new Scene(root);
			// Client.fxml 파일에
			// stylesheets="@https://fonts.googleapis.com/css2?family=Noto+Sans+KR&amp;display=swap, @application.css"
			// 부분에 @application.css로 밑의 식을 적용시킴
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("CHAT_CLIENT");
			primaryStage.setResizable(false);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
