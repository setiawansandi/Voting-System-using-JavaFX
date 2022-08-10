package models;

public class Voter extends User{
	private String hasVoted;

	private static Voter instance = null;
	
	private Voter(String userID, String fname, String lname, String email, String hasVoted) {
		super(userID, fname, lname, email);
		this.hasVoted = hasVoted;
	}
	
	synchronized public static Voter setInstance(String userID, String fname, String lname, String email, String hasVoted) {
		if (instance == null) {
			instance = new Voter(userID, fname, lname, email, hasVoted);
		}
		return instance;
	}
	
	public static Voter getInstance() {
		return instance;
	}
	
	public static void deleteInstance() {
		instance  = null;
	}
	
	public String getHasVoted() {
		return hasVoted;
	}

	public void setHasVoted(String hasVoted) {
		this.hasVoted = hasVoted;
	}
	
}
