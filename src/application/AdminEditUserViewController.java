package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import interfaces.WindowLoad;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Admin;
import models.VoterStruct;
import models.VotingServer;

public class AdminEditUserViewController implements Initializable, WindowLoad{
	Admin admin = Admin.getInstance();
	VotingServer votingServer = new VotingServer();
	
    @FXML
    private TableView<VoterStruct> table_user;

    @FXML
    private TableColumn<VoterStruct, String> col_email;

    @FXML
    private TableColumn<VoterStruct, String> col_fn;

    @FXML
    private TableColumn<VoterStruct, String> col_ln;
    
    @FXML
    private TableColumn<VoterStruct, String> col_hasVoted;

    @FXML
    private TableColumn<VoterStruct, String> col_userid;

    @FXML
    private TextField tv_search;
    
	@FXML
    private Button btn_search;
	
    @FXML
    private Button btn_addUser;

    @FXML
    private Button btn_delete;
	

    @FXML
    private ChoiceBox<?> cb_category;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTable();
	}
	
    private void initTable() {
    	ObservableList<VoterStruct> list;
    	
		col_userid.setCellValueFactory(new PropertyValueFactory<VoterStruct,String>("userID"));
		col_fn.setCellValueFactory(new PropertyValueFactory<VoterStruct,String>("fname"));
		col_ln.setCellValueFactory(new PropertyValueFactory<VoterStruct,String>("lname"));
		col_hasVoted.setCellValueFactory(new PropertyValueFactory<VoterStruct,String>("hasVoted"));
		col_email.setCellValueFactory(new PropertyValueFactory<VoterStruct,String>("email"));
		
    	Admin.getInstance().setVotingServer(votingServer);
		
		list = Admin.getInstance().getVotingServer().getVoterData();
		
		table_user.setItems(list);
    }
    
    @FXML
    private void addUser() {
    	windowLoad("adminAddUser.fxml", "Add User");
    }

	@Override
	public void windowLoad(String fxml, String windowTitle) {
		try {
    		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/fxml/"+ fxml));
			Scene scene = new Scene(root,400, 350); //*70
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			Stage adminWindow = new Stage();
			adminWindow.setTitle(windowTitle);
			adminWindow.setScene(scene);
			adminWindow.show();
    	} catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
		
	}
}
