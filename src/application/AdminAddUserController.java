package application;

import interfaces.WindowClose;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Admin;
import models.VotingServer;

public class AdminAddUserController implements WindowClose{
	VotingServer votingServer = new VotingServer();
	
    @FXML
    private Button btn_add;

    @FXML
    private Button btn_cancel;

    @FXML
    private TextField tv_email;

    @FXML
    private TextField tv_fn;

    @FXML
    private TextField tv_ln;

    @FXML
    private TextField tv_pw;
    
    
    @FXML
    void addUser(ActionEvent event) {
    	String fn = tv_fn.getText();
    	String ln = tv_ln.getText();
    	String email = tv_email.getText();
    	String pw = tv_pw.getText();
    	
    	if (!(fn.isBlank() || ln.isBlank() || email.isBlank() || pw.isBlank())) {
    		Admin.getInstance().setVotingServer(votingServer);
			Admin.getInstance().getVotingServer().addUser(fn, ln, email, pw);
			
			windowClose(event);
    	}
    	
//    	FXMLLoader loader = new FXMLLoader(getClass().getResource("adminEditUserView.fxml"));
//    	AdminEditUserViewController controller = loader.getController();
//    	controller.refreshTable();
			
    }

    @FXML
    void cancel(ActionEvent event) {
    	windowClose(event);
    }

	@Override
	public void windowClose(ActionEvent event) {
		Stage previousWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
    	previousWindow.close();
	}
    
}
