package application;

import java.net.URL;
import java.util.ResourceBundle;

import classes.Admin;
import classes.Candidate;
import classes.VotingServer;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class AdminEditVoteViewController implements Initializable{
	
	VotingServer votingServer = new VotingServer();
	
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
    private TableColumn<Candidate, String> col_totalVote;

    @FXML
    private TableColumn<Candidate, String> col_ln;
    
    @FXML
    private TableColumn<Candidate, String> col_img;

    @FXML
    private TableView<Candidate> table_cand;

    @FXML
    private TextField tv_search;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTable();
	}
	
	void initTable() {
		ObservableList<Candidate> list;
		//col_img.setPrefWidth(80); 
        col_img.setCellValueFactory(new PropertyValueFactory<>("photo"));
		col_candid.setCellValueFactory(new PropertyValueFactory<Candidate, String>("candID"));
		col_fn.setCellValueFactory(new PropertyValueFactory<Candidate, String>("fname"));
		col_ln.setCellValueFactory(new PropertyValueFactory<Candidate, String>("lname"));
		col_totalVote.setCellValueFactory(new PropertyValueFactory<Candidate, String>("totalVote"));
		col_email.setCellValueFactory(new PropertyValueFactory<Candidate, String>("email"));
		
		list = Admin.getInstance().getVotingServer().getCandidateData();
		
		table_cand.setItems(list);
	}
}
