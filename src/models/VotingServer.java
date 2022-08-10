package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class VotingServer {
	private static LocalDate startDate;
	private static LocalDate endDate;
	private static List<Candidate> votingResult = new ArrayList<>();
	private static boolean isVotingResultShown = false;
	
	public VotingServer(){
		
	}
	
	public static void initializeDB() {
		PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
    	
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
	
	public static List<Candidate> getVotingResult() {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		String query = "SELECT fname, lname, img, totalVotes FROM Candidate ORDER BY totalvotes DESC;";
		
		try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(query);
    		rs = preparedStatement.executeQuery();
    		while (rs.next()) {
    			
    			votingResult.add(new Candidate(
		    					rs.getString("img"),
		    					rs.getString("fname"),
		    					rs.getString("lname"),
		    					rs.getInt("totalVotes")
								));
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e + "2");
    	}
		
		return votingResult;
	}
	
	public static int getSumOfAllVotes() {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int sum = 0;
		
		String query = "SELECT SUM(totalVotes) as sum_totalVotes FROM Candidate; ";
		
		try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(query);
    		rs = preparedStatement.executeQuery();
    		if (rs.next()) {
    			sum = rs.getInt("sum_totalVotes");
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e + "2");
    	}
		
		return sum;
	}
		
	
	//===========================================================================================
	
	public void saveDate(LocalDate start, LocalDate end) {
		
		PreparedStatement preparedStatement = null;
    	
    	String updateStartDateQuery = "UPDATE VotingServer SET startDate = ? WHERE votingMachine = 0; ";
    	String updateEndDateQuery = "UPDATE VotingServer SET endDate = ? WHERE votingMachine = 0; ";
    	
    	try {
    		
    		String strStartDate = start.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
    		
    		preparedStatement = SQLdatabase.conn.prepareStatement(updateStartDateQuery);
    		preparedStatement.setString(1, strStartDate);
    		preparedStatement.execute();
    		startDate = start;
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	
    	try {
  
    		String strEndDate = end.format(DateTimeFormatter.ofPattern("d/MM/yyyy"));
    		
    		preparedStatement = SQLdatabase.conn.prepareStatement(updateEndDateQuery);
    		preparedStatement.setString(1, strEndDate);
    		preparedStatement.execute();
      		endDate = end;
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	
	}
	
	public void saveShowResult(boolean checkbox) {
		PreparedStatement preparedStatement = null;
		int converted;
		
		if(checkbox) converted = 1;
		else converted = 0;
		
		String query = "UPDATE VotingServer SET showResult = ? WHERE votingMachine = 0; ";
		
		try {
			
    		preparedStatement = SQLdatabase.conn.prepareStatement(query);
    		preparedStatement.setInt(1, converted);
    		preparedStatement.execute();
			isVotingResultShown = checkbox;
    		
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
		
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
    	        
    	        try {
    	        	//photo.setImage(new Image(this.getClass().getResourceAsStream(rs.getString("img"))));
    	    		photo.setImage(new Image(rs.getString("img")));
    	        } catch (Exception e) {
    	        	System.out.println(e);
    	        }
    	        
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
    	String userQuery = "SELECT COUNT(*) as totalRow FROM UserAccount;";

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
    		System.out.println(e + "1");
    	}
		
    	System.out.println(ID);
    	String userQuery2 = "INSERT INTO main.UserAccount VALUES (?, ?, ?, ?, ?, ?);";
    	//('V0000006', '123456', 'John', 'Cena', '0', 'johncn@invis.com');
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery2);
    		preparedStatement.setString(1, ID);
    		preparedStatement.setString(2, pw);
    		preparedStatement.setString(3, fn);
    		preparedStatement.setString(4, ln);
    		preparedStatement.setString(5, "0");
    		preparedStatement.setString(6, email);
    		
    		preparedStatement.execute();
    		
    		
    	} catch (Exception e) {
    		System.out.println(e + "2");
    	}
	}
	
	public void addCandidate(String fn, String ln, String email, String img) {
		String ID = "";
		
		PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	String userQuery = "SELECT COUNT(*) as totalRow FROM Candidate;";

    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery);
   		
    		rs = preparedStatement.executeQuery();
    		// V + 000.. + ID
    		if (rs.next()) {
    			for(int i = 0; i < (7 - rs.getString(1).length()); i++) {
    				ID = ID + "0";
    			}
    			ID = "C" + ID + rs.getString(1);
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e + "1");
    	}
		
    	System.out.println(ID);
    	String userQuery2 = "INSERT INTO main.Candidate VALUES (?, ?, ?, ?, ?, ?);";
    	//('V0000006', '123456', 'John', 'Cena', '0', 'johncn@invis.com');
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery2);
    		preparedStatement.setString(1, ID);
    		preparedStatement.setString(2, fn);
    		preparedStatement.setString(3, ln);
    		preparedStatement.setString(4, img);
    		preparedStatement.setInt(5, 0);
    		preparedStatement.setString(6, email);
    		
    		preparedStatement.execute();
    		
    		
    	} catch (Exception e) {
    		System.out.println(e + "2");
    	}
	}
	
	public void deleteUserData(String userID) {
		//DELETE FROM UserAccount WHERE userID = "V0000011";
		PreparedStatement preparedStatement = null;
    	String deleteQuery = "DELETE FROM UserAccount WHERE userID = ?;";
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(deleteQuery);
    		preparedStatement.setString(1, userID);
    		preparedStatement.execute();
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
	}
	
	public void deleteCandData(String candID) {
		//DELETE FROM UserAccount WHERE userID = "V0000011";
		PreparedStatement preparedStatement = null;
    	String deleteQuery = "DELETE FROM Candidate WHERE candID = ?;";
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(deleteQuery);
    		preparedStatement.setString(1, candID);
    		preparedStatement.execute();
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
	}
	
	public void deleteAllCandData() {
		//DELETE FROM UserAccount WHERE userID = "V0000011";
		PreparedStatement preparedStatement = null;
    	String deleteQuery = "DELETE FROM Candidate";
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(deleteQuery);
    		preparedStatement.execute();
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
	}
	
}
