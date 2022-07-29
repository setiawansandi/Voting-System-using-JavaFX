package classes;

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
}
