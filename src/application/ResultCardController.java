package application;

import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import models.Candidate;

public class ResultCardController{
	@FXML
    private Circle iv_profile;

    @FXML
    private ProgressBar pb_bar;

    @FXML
    private Text tv_name;

    @FXML
    private Text tv_percent;
    
    private Candidate candidate;
    
    public void setData(Candidate candidate, int sum_totalVotes) {
    	this.candidate = candidate;
    	
    	tv_name.setText(candidate.getFname() + " " + candidate.getLname());
    	
    	//Image im = new Image(this.getClass().getResourceAsStream(candidate.getImage()));
    	Image im = new Image(candidate.getImage());
    	iv_profile.setFill(new ImagePattern(im));
    	
    	// twodp = Math.round(input * 100.0) / 100.0;
    	double decimal = ((double)candidate.getTotalVote()) / sum_totalVotes;
    	int percent = (int)(decimal * 100);
    	
    	tv_percent.setText(Integer.toString(percent) + "%");
    	pb_bar.setProgress(decimal);


    }
}
