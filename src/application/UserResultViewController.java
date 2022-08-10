package application;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import models.Candidate;
import models.VotingServer;

public class UserResultViewController implements Initializable{

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;
    
    @FXML
    private HBox hb_alert;
    
    @FXML
    private HBox hb_resultBox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		if(VotingServer.isVotingResultShown()) {
			
			hb_alert.setVisible(false);
			hb_alert.setManaged(false);
			hb_resultBox.setVisible(true);
			hb_resultBox.setManaged(true);
			initResult();
			
		} else {
			hb_alert.setVisible(true);
			hb_alert.setManaged(true);
			hb_resultBox.setVisible(false);
			hb_resultBox.setManaged(false);
		}
		
	}
	
	void initResult() {
		List<Candidate> votingResult = VotingServer.getVotingResult();
		int sum_totalVotes = VotingServer.getSumOfAllVotes();
		
		System.out.println(votingResult.size());
		
		try {
			for(int i = 0; i < VotingServer.getTotalCandidate(); ++i) {
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/resultCard.fxml"));
				
				GridPane cardGP = fxmlLoader.load();
				
				// get each candidate data from the list
				Candidate candidate = votingResult.get(i);
				
				ResultCardController resultCardController = fxmlLoader.getController();
				resultCardController.setData(candidate, sum_totalVotes);
				
				
				grid.add(cardGP, 0, i+1); // (child, column, row) 
				
				GridPane.setMargin(cardGP, new Insets(0, 0, 35, 0));
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
