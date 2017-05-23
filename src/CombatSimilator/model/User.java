package CombatSimilator.model;

import java.util.Random;

public class User {
	private String username;
	private String password;
	private String email;
	private String Fname;
	private String Lname;
	private String type;
	private String isAdmin;
	private String sessionid;
	
	//acting as a placeholder for logging in
	private boolean LoginStatus = false;
	
	
	public User(){
		//empty constructor for now 
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return Fname;
	}

	public void setFname(String fname) {
		Fname = fname;
	}

	public String getLname() {
		return Lname;
	}

	public void setLname(String lname) {
		Lname = lname;
	}
	
	public boolean logIn(String username, String password) {
		if(!LoginStatus)
			LoginStatus = true;
		return LoginStatus;
	}

	public void setUserAccountInformation(String fn, String ln, String email) {
		this.Fname = fn;
		this.Lname = ln;
		this.email = email;
	}


	public boolean authenticate(String Username, String  password) {
		//right now use this as a placeholder. 
		return false;
	}

	public String getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getSessionid() {
		return sessionid;
	}

	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
}
