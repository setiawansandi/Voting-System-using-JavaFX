package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import models.SQLdatabase;
import models.VotingServer;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
		try {
			HBox root = (HBox)FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
			Scene scene = new Scene(root,1120, 630); //*70
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			primaryStage.setTitle("Voting System");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SQLdatabase.ConnectDb();
		VotingServer.initializeDB();
		launch(args);
	}
}
