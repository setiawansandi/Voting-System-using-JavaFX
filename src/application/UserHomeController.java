package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserHomeController {
    @FXML
    private Hyperlink hl_home;

    @FXML
    private Hyperlink hl_result;

    @FXML
    private Hyperlink hl_vote;

    @FXML
    private Text tv_day;

    @FXML
    private Text tv_hour;

    @FXML
    private Text tv_minute;

    @FXML
    private Text tv_second;
    
    private Stage userScreen = new Stage();
    
    @FXML
    void goToResult(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/fxml/userResult.fxml"));
    	Scene scene = new Scene(root,1120, 630);

    	userScreen = (Stage)((Node) event.getSource()).getScene().getWindow();
    	userScreen.setScene(scene);
    	userScreen.show();
    }

    @FXML
    void goToVote(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/fxml/userVote.fxml"));
    	Scene scene = new Scene(root,1120, 630);

    	userScreen = (Stage)((Node) event.getSource()).getScene().getWindow();
    	userScreen.setScene(scene);
    	userScreen.show();
    }
}
