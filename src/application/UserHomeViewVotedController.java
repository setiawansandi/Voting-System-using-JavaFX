package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import models.Voter;

public class UserHomeViewVotedController implements Initializable{

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
	}
}
