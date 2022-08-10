package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Voter;

public class UserNavigationController implements Initializable{
    @FXML
    private Button btn_home;

    @FXML
    private Button btn_result;

    @FXML
    private Button btn_vote;

    @FXML
    private BorderPane mainBorderPane;

    @FXML
    void goToHome(ActionEvent event) {
    	if(Voter.getInstance().getHasVoted().equals("1")) {
    		loadFXML(getClass().getResource("/fxml/UserHomeViewVoted.fxml"));
    	} else {
    		loadFXML(getClass().getResource("/fxml/UserHomeView.fxml"));
    	}
    	
    	btn_home.setTextFill(Color.valueOf("#ffffff"));
    	btn_result.setTextFill(Color.valueOf("#bbbbbb"));
    	btn_vote.setTextFill(Color.valueOf("#bbbbbb"));
    }

    @FXML
    void goToResult(ActionEvent event) {
    	loadFXML(getClass().getResource("/fxml/UserResultView.fxml"));
    	btn_result.setTextFill(Color.valueOf("#ffffff"));
    	btn_home.setTextFill(Color.valueOf("#bbbbbb"));
    	btn_vote.setTextFill(Color.valueOf("#bbbbbb"));
    }

    @FXML
    void goToVote(ActionEvent event) {
    	loadFXML(getClass().getResource("/fxml/UserVotingView.fxml"));
    	btn_vote.setTextFill(Color.valueOf("#ffffff"));
    	btn_home.setTextFill(Color.valueOf("#bbbbbb"));
    	btn_result.setTextFill(Color.valueOf("#bbbbbb"));
    }

    @FXML
    void logout(ActionEvent event) throws IOException {
    	Voter.deleteInstance();
    	
    	Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml")); 
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
		Scene scene = new Scene(root);
		stage.setScene(scene); 
		stage.show();
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(Voter.getInstance().getHasVoted().equals("1")) {
			// hide this tab if user has alr voted
			// this line will hide the element and automatically rearrange
			btn_vote.setVisible(false);
			btn_vote.setManaged(false);
			//btn_vote.managedProperty().bind(btn_vote.visibleProperty());
			
			loadFXML(getClass().getResource("/fxml/UserHomeViewVoted.fxml"));
		}
	}
}
