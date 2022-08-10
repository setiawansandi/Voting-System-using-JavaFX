package application;

import java.io.IOException;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Candidate;
import models.Voter;
import models.VotingServer;

public class CandidateCardController {
    @FXML
    private VBox vBox;
	
    @FXML
    private Text candName;

    @FXML
    private Circle candProfile;

    @FXML
    private Button candVote;
    
    private Candidate candidate;
    
    @FXML
    void vote(ActionEvent event) throws IOException {
    	// send conformation window
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("Are you sure you want to vote for " + candidate.getFname() + " " + candidate.getLname() + "?\n");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    	    // user chose OK
    		VotingServer.setVoted(Voter.getInstance().getUserID(), candidate.getCandID());
    		
    		// refresh scene
    		Parent root = FXMLLoader.load(getClass().getResource("/fxml/userNavigation.fxml")); 
    		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow(); 
    		Scene scene = new Scene(root);
    		stage.setScene(scene); 
    		stage.show();
        	
    	} else {
    	    // user chose CANCEL or closed the dialog
        	System.out.println("L");
    	}
    }
    
    public void setData(Candidate candidate) {
    	this.candidate = candidate;
    	candName.setText(candidate.getFname() + " " + candidate.getLname());
    	
    	//Image im = new Image(this.getClass().getResourceAsStream(candidate.getImage()));
    	Image im = new Image(candidate.getImage());
    	
    	candProfile.setFill(new ImagePattern(im));
    }
}

