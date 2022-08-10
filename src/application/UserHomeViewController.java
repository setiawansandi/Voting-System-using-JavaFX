package application;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import models.Voter;
import models.VotingServer;

public class UserHomeViewController implements Initializable{
	private static int endDay, endMonth, endYear;

    @FXML
    private Text tv_days;

    @FXML
    private Text tv_hours;

    @FXML
    private Text tv_mins;

    @FXML
    private Text tv_secs;
    
    @FXML
    private ImageView lbozo;
    
    @FXML
    private Text tv_fname;

    @FXML
    void goToVote(MouseEvent event) {
    	// implements later
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbozo.setImage(new Image(this.getClass().getResourceAsStream("/img/vote.png")));
		tv_fname.setText(Voter.getInstance().getFname());

		initCountdown();
	}
	
	void initCountdown() {
//		LocalDateTime tomorrowMidnight = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT).plusDays(1);        
//      Duration duration = Duration.between(LocalDateTime.now(), tomorrowMidnight);
		
		
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
//	        	System.out.println(ChronoUnit.DAYS.between(currentDate, endDate));
//	        	System.out.println(ChronoUnit.HOURS.between(currentDate, endDate)%24);
//	        	System.out.println(ChronoUnit.MINUTES.between(currentDate, endDate)%60); // 60 mins
//	        	System.out.println(ChronoUnit.SECONDS.between(currentDate, endDate)%60); // 60 secs
//	        	System.out.println("==================");
	        	
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