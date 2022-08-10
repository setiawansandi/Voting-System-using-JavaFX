package application;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import interfaces.WindowLoad;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import models.Admin;
import models.Candidate;
import models.Voter;
import models.VotingServer;

public class AdminEditVoteViewController implements Initializable, WindowLoad{
	
	VotingServer votingServer = new VotingServer();
	private boolean isSettingOpen = false;
	
	private int index = -1;

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
    private ImageView iv_settingIcon;
    
	
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
		    	 // placeholder (refresh table)
		    	 initTable();
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
	
    @FXML
    void addCandidate(ActionEvent event) {
    	windowLoad("adminAddCandidate.fxml", "Add Candidate");
    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		iv_settingIcon.setImage(new Image(this.getClass().getResourceAsStream("/img/setting_214.png")));
		
		initTable();
		initSettings();
		initHandlers();
	}

	@Override
	public void windowLoad(String fxml, String windowTitle) {
		try {
    		Parent root = (Parent)FXMLLoader.load(getClass().getResource("/fxml/"+ fxml));
			Scene scene = new Scene(root,400, 500);
			scene.getStylesheets().add(getClass().getResource("/css/application.css").toExternalForm());
			Stage adminWindow = new Stage();
			adminWindow.setTitle(windowTitle);
			adminWindow.setScene(scene);
			adminWindow.show();
    	} catch (IOException e) {
    		System.out.println(e.getMessage());
    	}
	}
	
	@FXML
    void getSelected(MouseEvent event) {
    	index = table_cand.getSelectionModel().getSelectedIndex();    	
    }

    @FXML
    void deleteAllCandidate(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setContentText("Are you sure you want to DELETE all candidate?\n");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		Admin.getInstance().getVotingServer().deleteAllCandData();
        	
    	} else {
    	    // user chose CANCEL or closed the dialog
        	System.out.println("L");
    	}
    	initTable();
    }

    @FXML
    void deleteCandidate(ActionEvent event) {
    	if (index <= -1) {
    		return;
    	}
    	String id = col_candid.getCellData(index).toString();
    	Admin.getInstance().getVotingServer().deleteCandData(id);
    	
    	initTable();
    }
	
}
