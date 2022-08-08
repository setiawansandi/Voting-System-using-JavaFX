package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Admin;
import models.Candidate;
import models.VotingServer;

public class AdminEditVoteViewController implements Initializable{
	
	VotingServer votingServer = new VotingServer();
	private boolean isSettingOpen = false;
	
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
    
    @FXML
    private DatePicker datepicker_end;

    @FXML
    private DatePicker datepicker_start;
    
    @FXML
    private CheckBox checkbox_result;
    
    @FXML
    private VBox mainVbox;
    
    @FXML
    private VBox settingMenu;
    
    @FXML
    private HBox hbox_setting;
	
	@FXML
    void dateSave(ActionEvent event) {
		LocalDate start = datepicker_start.getValue();
		LocalDate end = datepicker_end.getValue();
		Admin.getInstance().getVotingServer().saveDate(start, end);
    }

    @FXML
    void showResultSave(ActionEvent event) {
    	Admin.getInstance().getVotingServer().saveShowResult(checkbox_result.isSelected());
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
	
	void initSettings() {
		datepicker_start.setValue(VotingServer.getStartDate());
		datepicker_end.setValue(VotingServer.getEndDate());
		
		if(VotingServer.isVotingResultShown()) {
			checkbox_result.setSelected(true);
		} else checkbox_result.setSelected(false);
	}
	
	void initHandlers() {
		initSettingHandler();
		initVboxHandler();
	}
	
	void initSettingHandler() {
		hbox_setting.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 // update menu visibility
		    	 initSettings();
		    	 settingMenu.setVisible(!isSettingOpen);
		    	 isSettingOpen = !isSettingOpen;
		         event.consume();
		     }
		});
	}
	
	void initVboxHandler() {
		mainVbox.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		     @Override
		     public void handle(MouseEvent event) {
		    	 settingMenu.setVisible(false);
		         event.consume();
		     }
		});
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initTable();
		initSettings();
		initHandlers();
	}
	
}
