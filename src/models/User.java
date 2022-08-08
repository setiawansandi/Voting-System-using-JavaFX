package models;

abstract class User {
	private String userID = "";
	private String fname = "";
	private String lname = "";
	private String email = "";
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
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
	
	public User(String userID, String fname, String lname, String email) {
		this.userID = userID;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
	}
	
	public User() {
		
	}
	
}
