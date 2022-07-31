package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class VoteManager {
	private LocalDate startDate;
	private LocalDate endDate;
	private List<String> candidateList = new ArrayList<>();
	private boolean isVotingResultShown = false;
	
	void showResult() {
		isVotingResultShown = true;
	}
	
	public static int getTotalVoted() {
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
	
	public static int getTotalNotVoted() {
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
}
