package model;

public class Favorites {
	private String name;
	private int userID;
	private int favID;
	
	public Favorites(){
		
		
	}
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name = name;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getFavID() {
		return favID;
	}
	public void setFavID(int favID) {
		this.favID = favID;
	}
	
}