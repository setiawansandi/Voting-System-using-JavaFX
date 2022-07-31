package application;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Admin;
import classes.VoteManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

public class AdminHomeViewController implements Initializable{
	
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
		dash_candidate.setText(Integer.toString(VoteManager.getTotalCandidate()));
		dash_voted.setText(Integer.toString(VoteManager.getTotalVoted()));
		dash_left.setText(Integer.toString(VoteManager.getTotalNotVoted()));
	}
    
    
}
