package application;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Candidate;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AdminEditVoteViewController implements Initializable{
	@FXML
    private Button btn_addUser;

    @FXML
    private Button btn_clear;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_search;

    @FXML
    private ChoiceBox<?> cb_category;

    @FXML
    private TableColumn<Candidate, String> col_candid;

    @FXML
    private TableColumn<Candidate, String> col_email;

    @FXML
    private TableColumn<Candidate, String> col_fn;

    @FXML
    private TableColumn<Candidate, String> col_lastLog;

    @FXML
    private TableColumn<Candidate, String> col_ln;
    
    @FXML
    private TableColumn<Candidate, String> col_img; // implement later

    @FXML
    private TableView<Candidate> table_user;

    @FXML
    private TextField tv_search;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
	}
}
