package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VotingServer {
	private static LocalDate startDate;
	private static LocalDate endDate;
	//private List<String> candidateList = new ArrayList<>();
	private static boolean isVotingResultShown = false;
	
	public VotingServer(){
		
	}
	
	public static void initializeDB() {
		PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/d/yyyy");
    	
    	String Query = "SELECT * FROM VotingServer WHERE votingMachine = 0;";
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(Query);
   		
    		rs = preparedStatement.executeQuery();
    		if (rs.next()) {
    			 startDate = LocalDate.parse(rs.getString("startDate"), formatter);
    			 endDate = LocalDate.parse(rs.getString("endDate"), formatter);
    			 if(rs.getInt("showResult") == 1) {
    				 isVotingResultShown = true;
    			 }
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
	}
	
	
	public static int getTotalCandidate() {
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
	
	
	public static List<Candidate> getCandidateDataForUser() {
		List<Candidate> list = new ArrayList<>();
		PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	
    	String userQuery = "SELECT * FROM Candidate;";
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery);

    		rs = preparedStatement.executeQuery();
    		while (rs.next()) {
    	        
    			list.add(new Candidate(
    					rs.getString("img"),
    					rs.getString("fname"),
    					rs.getString("lname"),
    					rs.getString("candId")
	    				)
    				);
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	return list;
	}
	
	public static void setVoted(String userID, String candID) {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int total = 0;
		
		// set hasVoted in user to "1"
    	String setVotedQuery = "UPDATE UserAccount SET hasVoted = \"1\" WHERE userID = ?;";
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(setVotedQuery);
    		preparedStatement.setString(1, userID);
    		preparedStatement.execute();
    		
    		Voter.getInstance().setHasVoted("1"); // 1 means user has voted
    	} catch (Exception e) {
    		System.out.println(e + "1");
    	}
    	
    	// get candidate's total vote
    	String getTotalVoteQuery = "SELECT totalVotes FROM Candidate WHERE candID = ?;";
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(getTotalVoteQuery);
    		preparedStatement.setString(1, candID);
    		rs = preparedStatement.executeQuery();
    		if (rs.next()) {
    			total = rs.getInt(1);
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e + "2");
    	}
    	
    	// update candidade's total vote
    	String updateTotalVoteQuery = "UPDATE Candidate SET totalVotes = ? WHERE candID = ?;";
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(updateTotalVoteQuery);
    		preparedStatement.setInt(1, total + 1);
    		preparedStatement.setString(2, candID);
    		preparedStatement.execute();
    		
    	} catch (Exception e) {
    		System.out.println(e + "3");
    	}
	}
	
	public static LocalDate getStartDate() {
		return startDate;
	}
	
	public static LocalDate getEndDate() {
		return endDate;
	}
	
	public static boolean isVotingResultShown() {
		return isVotingResultShown;
	}
	
	
	
	//===========================================================================================
	public void saveDate(LocalDate start, LocalDate end) {
		startDate = start;
		endDate = end;
	}
	
	public void saveShowResult() {
		
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
    		// V + 000.. + ID
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
