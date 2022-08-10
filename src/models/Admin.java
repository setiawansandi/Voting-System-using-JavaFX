package models;

public class Admin extends User{
	private static Admin instance = null;
	private static VotingServer voteServer = null;
	
	private Admin(String userID, String fname, String lname, String email) {
		super(userID, fname, lname, email);
	}
	
	synchronized public static void setInstance(String userID, String fname, String lname, String email) {
		if (instance == null) {
			instance = new Admin(userID, fname, lname, email);
		}
	}
	
	public static Admin getInstance() {
		return instance;
	}
	
	public void setVotingServer(VotingServer vs) {
		if (voteServer == null) {
			voteServer = vs;
		}
	}
	
	public VotingServer getVotingServer() {
		return voteServer;
	}
	
	public static void deleteInstance() {
		instance  = null;
	}
}
