package models;

public class VoterStruct {
	private String userID;
	private String fname;
	private String lname;
	private String email;
	private String hasVoted;
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHasVoted() {
		return hasVoted;
	}

	public void setHasVoted(String hasVoted) {
		this.hasVoted = hasVoted;
	}

	VoterStruct(String userID, String fname, String lname, String email, String hasVoted) {
		this.userID = userID;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.hasVoted = hasVoted;
	}
}
