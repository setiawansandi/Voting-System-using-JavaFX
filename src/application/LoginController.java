package application;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoginController {

	@FXML
    private Button btn_login;

    @FXML
    private PasswordField tv_password;

    @FXML
    private TextField tv_userid;
    
    @FXML
    void onLoginClick(ActionEvent event) throws SQLException {
    	if(LoginManager.isLogin(tv_userid.getText(), tv_password.getText())) {
    		System.out.println("is connected");
    	} else System.out.println("L bozo");
    	// check id and pw
    	// next window
    	
    	//switch change file name
    	
//    	try {
//            HBox root = (HBox)FXMLLoader.load(getClass().getResource("adminHome.fxml"));
//            Scene scene2 = new Scene(root,1120, 630);
//            scene2.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//            Stage Window2 = new Stage();
//            Window2.initModality(Modality.APPLICATION_MODAL);
//            Window2.setScene(scene2);
//            Window2.show();
//        }
//        catch(IOException e){
//            System.out.println(e.getMessage());
//        }
//
//        Stage window2 = (Stage)((Node)event.getSource()).getScene().getWindow();
//        window2.close();
    }
    
    
    

}