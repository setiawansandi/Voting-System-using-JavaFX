package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class UserHomeViewController implements Initializable{

    @FXML
    private Text tv_days;

    @FXML
    private Text tv_hours;

    @FXML
    private Text tv_mins;

    @FXML
    private Text tv_secs;

    @FXML
    void goToVote(MouseEvent event) {
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

}