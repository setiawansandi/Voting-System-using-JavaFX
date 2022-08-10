package application;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import models.Voter;
import models.VotingServer;

public class UserHomeViewVotedController implements Initializable{
	private static int endDay, endMonth, endYear;

    @FXML
    private ImageView lbozo;

    @FXML
    private Text tv_days;

    @FXML
    private Text tv_hours;

    @FXML
    private Text tv_mins;

    @FXML
    private Text tv_secs;
    
    @FXML
    private Text tv_fname;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbozo.setImage(new Image(this.getClass().getResourceAsStream("/img/checkmark.png")));
		tv_fname.setText(Voter.getInstance().getFname());
		
		initCountdown();
	}
	
	void initCountdown() {
		endDay = VotingServer.getEndDate().getDayOfMonth();
		endMonth = VotingServer.getEndDate().getMonthValue();
		endYear = VotingServer.getEndDate().getYear();
				
        LocalDateTime endDate = LocalDateTime.of(endYear, endMonth, endDay, 0, 0, 0);
		
		// schedule a task to run every one second
		Timer timer = new Timer();
        TimerTask task = new TimerTask() {
         
	        // run() method to carry out the action of the task
	        public void run() {
	        	LocalDateTime currentDate = LocalDateTime.now();
	        	if(currentDate == endDate) {
	        		timer.cancel();
	        		timer.purge(); // kill remaining task (?)
	        	}
	        	
	        	tv_days.setText(Long.toString(ChronoUnit.DAYS.between(currentDate, endDate)));
	        	tv_hours.setText(Long.toString(ChronoUnit.HOURS.between(currentDate, endDate) % 24));  // 24 hrs
	        	tv_mins.setText(Long.toString(ChronoUnit.MINUTES.between(currentDate, endDate) % 60)); // 60 mins
	        	tv_secs.setText(Long.toString(ChronoUnit.SECONDS.between(currentDate, endDate) % 60)); // 60 secs
	        };
        };
     

        // schedule() method to schedule the execution with start time (task, delay, waitTime)
	    timer.schedule(task, 0, 1000);
	}
}
