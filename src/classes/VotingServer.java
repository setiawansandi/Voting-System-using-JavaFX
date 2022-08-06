package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VotingServer {
	//private LocalDate startDate;
	//private LocalDate endDate;
	//private List<String> candidateList = new ArrayList<>();
	private boolean isVotingResultShown = false;
	// store list of votes
	
	public VotingServer(){
		
	}
	
	void showResult() {
		isVotingResultShown = true;
	}
	
	public int getTotalVoted() {
		PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	String userQuery = "SELECT COUNT(hasVoted) From UserAccount WHERE hasVoted = \"1\"";

    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery);
   		
    		rs = preparedStatement.executeQuery();
    		if (rs.next()) {
    			return rs.getInt(1);
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
		return 0;
	}
	
	public int getTotalNotVoted() {
		PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	String userQuery = "SELECT COUNT(hasVoted) From UserAccount WHERE hasVoted = \"0\"";

    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery);
   		
    		rs = preparedStatement.executeQuery();
    		if (rs.next()) {
    			return rs.getInt(1);
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
		return 0;
	}
	
	public int getTotalCandidate() {
		PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	String userQuery = "SELECT COUNT(*) FROM Candidate;";

    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery);
   		
    		rs = preparedStatement.executeQuery();
    		if (rs.next()) {
    			return rs.getInt(1);
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	return 0;
	}
	
	
	
	public ObservableList<Candidate> getCandidateData(){
		ImageView photo;
		ObservableList<Candidate> list = FXCollections.observableArrayList();
		PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
    	String userQuery = "SELECT * FROM Candidate;";
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery);

    		rs = preparedStatement.executeQuery();
    		while (rs.next()) {
    			photo = new ImageView();
    	        photo.setImage(new Image(this.getClass().getResourceAsStream(rs.getString("img"))));
    	        photo.setFitWidth(100);
    	        photo.setPreserveRatio(true);
    	        photo.setSmooth(true);
    	        photo.setCache(true);
    	        
    			list.add(new Candidate(
    					photo,
    					rs.getString("candID"),
    					rs.getString("fname"),
    					rs.getString("lname"),
    					rs.getInt("totalVotes"),
    					rs.getString("email")
	    				)
    				);
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	return list;
	}
	
	
	public ObservableList<VoterStruct> getVoterData(){
		ObservableList<VoterStruct> list = FXCollections.observableArrayList();
		PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
    	String userQuery = "SELECT * FROM UserAccount;";
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery);

    		rs = preparedStatement.executeQuery();
    		while (rs.next()) {
    			list.add(new VoterStruct(
    					rs.getString("userID"),
    					rs.getString("fname"),
    					rs.getString("lname"),
    					rs.getString("email"),
    					rs.getString("hasVoted")));
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	return list;
	}
	
	public void addUser(String fn, String ln, String email, String pw) {
		String ID = "";
		
		PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	String userQuery = "SELECT COUNT(*) FROM Candidate;";

    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery);
   		
    		rs = preparedStatement.executeQuery();
    		if (rs.next()) {
    			for(int i = 0; i < (7 - rs.getString(1).length()); i++) {
    				ID = ID + "0";
    			}
    			ID = "V" + ID + rs.getString(1);
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
				
//    	String userQuery2 = "INSERT INTO UserAccount VALUES (?, ?, ?, ?, ?, ?);";
//
//    	try {
//    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery);
//   		
//    		rs = preparedStatement.executeQuery();
//    		if (rs.next()) {
//    			return rs.getInt(1);
//    		}
//    		
//    	} catch (Exception e) {
//    		System.out.println(e);
//    	}
//    	return 0;
	}
	
}
