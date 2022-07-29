package classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class SQLdatabase {
	public static Connection conn = null;
    
    public static Connection ConnectDb() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = (Connection) DriverManager.getConnection("jdbc:sqlite:votingSystem.db");
            JOptionPane.showMessageDialog(null, "Connection Established");
            return conn;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    public static boolean isDbConnected() {
		try {
			return !conn.isClosed();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
    }
}
