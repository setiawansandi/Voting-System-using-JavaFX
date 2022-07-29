package application;

import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import classes.LoginManager;
import interfaces.WindowClose;
import interfaces.WindowLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class LoginController implements WindowClose, WindowLoad{

	@FXML
    private Button btn_login;

    @FXML
    private PasswordField tv_password;

    @FXML
    private TextField tv_userid;
    
    
    @FXML
    void onLoginClick(ActionEvent event) throws SQLException {
    	String fxml;
    	
    	if(LoginManager.checkLogin(tv_userid.getText().toUpperCase(), tv_password.getText()) == "user") {
    		//System.out.println("user");
    		fxml = "/fxml/" + "userHome.fxml";
    	} else if(LoginManager.checkLogin(tv_userid.getText().toUpperCase(), tv_password.getText()) == "admin") {
    		//System.out.println("admin");
    		fxml = "/fxml/" + "adminHome.fxml";
    	} else {
    		JOptionPane.showMessageDialog(null, "Please check your login details","Login failed", JOptionPane.INFORMATION_MESSAGE);
    		return;
    	}
    	
    	windowLoad(fxml);
    	windowClose(event);
    }

	@Override
	public void windowLoad(String fxml) {
		try {
    		HBox root = (HBox)FXMLLoader.load(getClass().getResource(fxml));
			Scene scene = new Scene(root,1120, 630); //*70
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			Stage adminWindow = new Stage();
			adminWindow.setTitle("Admin Panel");
			adminWindow.setScene(scene);
			adminWindow.show();
    	} catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
	}

	@Override
	public void windowClose(ActionEvent event) {
		Stage previousWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
    	previousWindow.close();
	}
    
    

}