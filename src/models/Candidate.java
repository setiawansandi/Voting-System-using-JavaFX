package models;

import javafx.scene.image.ImageView;

public class Candidate {
	private ImageView photo;
	private String fname;
	private String lname;
	private String email;
	private String candID;
	private String image;
	private int totalVote = 0;
	
	public ImageView getPhoto(){
		return photo;
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

	public String getCandID() {
		return candID;
	}

	public void setCandID(String candID) {
		this.candID = candID;
	}

	public int getTotalVote() {
		return totalVote;
	}

	public void setTotalVote(int totalVote) {
		this.totalVote = totalVote;
	}

	public Candidate(ImageView photo, String candID, String fname, String lname, int totalVote, String email) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.candID = candID;
		this.totalVote = totalVote;
		this.photo = photo;
	}
	
	public Candidate(String image, String fname, String lname, String candID) {
		this.image = image;
		this.fname = fname;
		this.lname = lname;
		this.candID = candID;
	}
	
	public Candidate(String image, String fname, String lname, int totalVote) {
		this.image = image;
		this.fname = fname;
		this.lname = lname;
		this.totalVote = totalVote;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
