package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
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
    private Text dash_time;

    @FXML
    private Text dash_left;

    @FXML
    private Text dash_voted;

    @FXML
    private Text tv_admin_name;
    
    @FXML
    private Text tv_unit;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		tv_admin_name.setText(Admin.getInstance().getFname());
		
		Admin.getInstance().setVotingServer(votingServer);
		dash_candidate.setText(Integer.toString(VotingServer.getTotalCandidate()));	// method is static
		dash_voted.setText(Integer.toString(Admin.getInstance().getVotingServer().getTotalVoted()));
		dash_left.setText(Integer.toString(Admin.getInstance().getVotingServer().getTotalNotVoted()));
		
		initDate();
	}
	
	private void initDate() {
		int endDay = VotingServer.getEndDate().getDayOfMonth();
		int endMonth = VotingServer.getEndDate().getMonthValue();
		int endYear = VotingServer.getEndDate().getYear();
		
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime endDate = LocalDateTime.of(endYear, endMonth, endDay, 0, 0, 0);
		
		if(ChronoUnit.DAYS.between(currentDate, endDate) != 0) {
			dash_time.setText(Long.toString(ChronoUnit.DAYS.between(currentDate, endDate)));
			tv_unit.setText("days");
			
		} else if (ChronoUnit.HOURS.between(currentDate, endDate) != 0) {
			dash_time.setText(Long.toString(ChronoUnit.HOURS.between(currentDate, endDate)));
			tv_unit.setText("hours");
			
		} else if (ChronoUnit.MINUTES.between(currentDate, endDate) != 0) {
			dash_time.setText(Long.toString(ChronoUnit.MINUTES.between(currentDate, endDate)));
			tv_unit.setText("minutes");
			
		} else {
			dash_time.setText(Long.toString(ChronoUnit.SECONDS.between(currentDate, endDate)));
			tv_unit.setText("seconds");
			
		}
	}
    
    
}
