package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

public class AdminNavigationController{
	
	@FXML
    private Button btn_editUser;

    @FXML
    private Button btn_editVote;

    @FXML
    private Button btn_home;

    @FXML
    private BorderPane mainBorderPane;
    
    
    @FXML
    void goToEditUser(ActionEvent event) {
    	loadFXML(getClass().getResource("/fxml/adminEditUserView.fxml"));
    	btn_editUser.setTextFill(Color.valueOf("#ffffff"));
    	btn_editVote.setTextFill(Color.valueOf("#bbbbbb"));
    	btn_home.setTextFill(Color.valueOf("#bbbbbb"));
    }

    @FXML
    void goToEditVote(ActionEvent event) {
    	loadFXML(getClass().getResource("/fxml/adminEditVoteView.fxml"));
    	btn_editUser.setTextFill(Color.valueOf("#bbbbbb"));
    	btn_editVote.setTextFill(Color.valueOf("#ffffff"));
    	btn_home.setTextFill(Color.valueOf("#bbbbbb"));
    }

    @FXML
    void goToHome(ActionEvent event) {
    	loadFXML(getClass().getResource("/fxml/adminHomeView.fxml"));
    	btn_editUser.setTextFill(Color.valueOf("#bbbbbb"));
    	btn_editVote.setTextFill(Color.valueOf("#bbbbbb"));
    	btn_home.setTextFill(Color.valueOf("#ffffff"));
    }
    
    @FXML
    void logout(ActionEvent event) {
    	
    }
    
    private void loadFXML(URL url) {
        try {
            FXMLLoader loader = new FXMLLoader(url);
            mainBorderPane.setCenter(loader.load());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
