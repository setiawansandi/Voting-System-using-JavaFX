package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginManager{
	
	public static String checkLogin(String user, String password) throws SQLException {
    	//System.out.println(SQLdatabase.isDbConnected());
    	PreparedStatement preparedStatement = null;
    	ResultSet resultSet = null;
    	String userQuery = "select * from UserAccount where userID = ? and password = ?";
    	String adminQuery = "select * from AdminAccount where userID = ? and password = ?";
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery);
    		preparedStatement.setString(1, user);
    		preparedStatement.setString(2, password);
    		
    		resultSet = preparedStatement.executeQuery();
    		if (resultSet.next()) {
    			return "user";
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(adminQuery);
    		preparedStatement.setString(1, user);
    		preparedStatement.setString(2, password);
    		
    		resultSet = preparedStatement.executeQuery();
    		if (resultSet.next()) {
    			return "admin";
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
    	preparedStatement.close();
    	resultSet.close();
    	
		return "fail";
    	
    }
	
	
	
	public static void setAdmin(String id) throws SQLException {
		PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	String userQuery = "select * from AdminAccount where userID = ?";
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery);
    		preparedStatement.setString(1, id);
    		
    		rs = preparedStatement.executeQuery();
    		if (rs.next()) {
    			Admin.setInstance(rs.getString("userID"), rs.getString("fname"), rs.getString("lname"), rs.getString("email"));
    			//System.out.println(Admin.getInstance().getUserID() + " " + Admin.getInstance().getFname());
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
	}
	
	
	
	public static void setVoter(String id) {
		PreparedStatement preparedStatement = null;
    	ResultSet rs = null;
    	String userQuery = "select * from UserAccount where userID = ?";
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(userQuery);
    		preparedStatement.setString(1, id);
    		
    		rs = preparedStatement.executeQuery();
    		if (rs.next()) {
    			Voter.setInstance(rs.getString("userID"), rs.getString("fname"), rs.getString("lname"), rs.getString("email"), rs.getString("hasVoted"));
    		}
    		
    	} catch (Exception e) {
    		System.out.println(e);
    	}
	}
}
