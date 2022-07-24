package application;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginManager {
	
	static boolean isLogin(String user, String password) throws SQLException {
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
    			return true;
    		}
    		
    	} catch (Exception e) {
    		
    	}
    	
    	try {
    		preparedStatement = SQLdatabase.conn.prepareStatement(adminQuery);
    		preparedStatement.setString(1, user);
    		preparedStatement.setString(2, password);
    		
    		resultSet = preparedStatement.executeQuery();
    		if (resultSet.next()) {
    			return true;
    		}
    		
    	} catch (Exception e) {
    		
    	}
    	preparedStatement.close();
    	resultSet.close();
    	
		return false;
    	
    }
}
