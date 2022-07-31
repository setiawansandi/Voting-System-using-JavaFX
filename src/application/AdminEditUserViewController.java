package application;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Voter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdminEditUserViewController implements Initializable{
    @FXML
    private TableView<Voter> table_user;

    @FXML
    private TableColumn<Voter, String> col_email;

    @FXML
    private TableColumn<Voter, String> col_fn;

    @FXML
    private TableColumn<Voter, String> col_ln;
    
    @FXML
    private TableColumn<Voter, String> col_hasVoted;

    @FXML
    private TableColumn<Voter, String> col_lastLog;

    @FXML
    private TableColumn<Voter, String> col_userid;

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
		
		
	}
	
    private void iniTable() {
//    	col_name.setCellValueFactory(new PropertyValueFactory<Student,String>("id"));
//		col_isCandidate.setCellValueFactory(new PropertyValueFactory<Student,Boolean>("isCandidate"));
//		col_hasVoted.setCellValueFactory(new PropertyValueFactory<Student,Boolean>("hasVoted"));
//		col_voteCount.setCellValueFactory(new PropertyValueFactory<Student,Integer>("voteCount"));
    }
}
