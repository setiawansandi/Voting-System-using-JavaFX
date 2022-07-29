package classes;

abstract class User {
	private String userID = "";
	private String fname = "";
	private String lname = "";
	private String email = "";
	private String loginStatus = "";
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLoginStatus() {
		return loginStatus;
	}
	
	public void setLoginStatus(String loginStatus) {
		this.loginStatus = loginStatus;
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
	
}
