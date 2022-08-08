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
import javafx.scene.layout.VBox;
import models.Candidate;
import models.VotingServer;

public class UserVotingViewController implements Initializable{

    @FXML
    private GridPane grid;

    @FXML
    private ScrollPane scroll;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		int column = 0;
		int row = 1; // %3
		List<Candidate> candidateList = VotingServer.getCandidateDataForUser();
		
		try {
			for(int i = 0; i < VotingServer.getTotalCandidate(); ++i) {
				
				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(getClass().getResource("/fxml/candidateCard.fxml"));
				
				VBox vBox = fxmlLoader.load();
				
				// get each candidate from the list
				Candidate candidate = candidateList.get(i);
				
				CandidateCardController candidateCardController = fxmlLoader.getController();
				candidateCardController.setData(candidate);
				
				// max 3 columns per row
				if (column == 3) {
					column = 0;
					row++;
				}
				
				grid.add(vBox, column++, row); // (child, column, row)
				
				GridPane.setMargin(vBox, new Insets(0, 20, 40, 20));
				
			}
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}

}
