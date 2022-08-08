package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import models.Admin;
import models.VotingServer;

public class AdminHomeViewController implements Initializable{
	VotingServer votingServer = new VotingServer();
	
    @FXML
    private Text dash_candidate;

    @FXML
    private Text dash_day;

    @FXML
    private Text dash_left;

    @FXML
    private Text dash_voted;

    @FXML
    private Text tv_admin_name;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tv_admin_name.setText(Admin.getInstance().getFname());
		
		Admin.getInstance().setVotingServer(votingServer);
		dash_candidate.setText(Integer.toString(VotingServer.getTotalCandidate()));	// method is static
		dash_voted.setText(Integer.toString(Admin.getInstance().getVotingServer().getTotalVoted()));
		dash_left.setText(Integer.toString(Admin.getInstance().getVotingServer().getTotalNotVoted()));
	}
    
    
}
