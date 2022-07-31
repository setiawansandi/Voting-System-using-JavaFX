package classes;

public class Admin extends User{
	private static Admin instance = null;
	
	private Admin(String userID, String fname, String lname, String email) {
		super(userID, fname, lname, email);
	}
	
	synchronized public static Admin setInstance(String userID, String fname, String lname, String email) {
		if (instance == null) {
			instance = new Admin(userID, fname, lname, email);
		}
		return instance;
	}
	
	public static Admin getInstance() {
		return instance;
	}
}
