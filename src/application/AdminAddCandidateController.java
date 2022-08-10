package application;

import java.io.File;

import interfaces.WindowClose;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.Admin;
import models.VotingServer;

public class AdminAddCandidateController implements WindowClose{
	private String image_url = "";
	VotingServer votingServer = new VotingServer();

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_cancel;

    @FXML
    private ImageView iv_image;

    @FXML
    private TextField tv_email;

    @FXML
    private TextField tv_fn;

    @FXML
    private TextField tv_ln;
    
    final FileChooser fc = new FileChooser();

    @FXML
    void addUser(ActionEvent event) {
    	String fn = tv_fn.getText();
    	String ln = tv_ln.getText();
    	String email = tv_email.getText();
    	
    	if (!(fn.isBlank() || ln.isBlank() || email.isBlank() || image_url.isBlank())) {
    		Admin.getInstance().setVotingServer(votingServer);
			Admin.getInstance().getVotingServer().addCandidate(fn, ln, email, image_url);
			
			windowClose(event);
    	}
    }

    @FXML
    void cancel(ActionEvent event) {
    	windowClose(event);
    }

    @FXML
    void upload(ActionEvent event) {
    	fc.setTitle("Choose an Image");
    	
    	// Set the initial directory for the displayed file dialog 
    	// user.home refers to the path to the user directory 
    	fc.setInitialDirectory(new File(System.getProperty("user.home")));
    	
    	
    	
    	// Gets the extension filters used in the displayed file dialog. 
    	fc.getExtensionFilters ().clear(); // Removes all of the elements from this list 
    	fc.getExtensionFilters().add(new FileChooser. ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
    	//Set the selected file or null if no file has been selected 
    	File file = fc.showOpenDialog(null); // Shows a new file open dialog.
    	
    	if (file!= null) {
    		image_url = file.toURI().toString();
    		iv_image.setImage(new Image(image_url));
    		System.out.println(file.toURI().toString());
    	} else {
    		System.out.println("A file is invalid");
    	}
    	
    }

	@Override
	public void windowClose(ActionEvent event) {
		Stage previousWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
    	previousWindow.close();
	}

}
