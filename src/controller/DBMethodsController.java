package controller;
import java.util.ArrayList;
import java.util.List;

import DBpersist.DatabaseProvider;
import DBpersist.DerbyDatabase;
import DBpersist.IDatabase;
import model.Armory;
import model.Favorites;
import model.User;

public class DBMethodsController {
	private IDatabase db = null;
	
	public DBMethodsController() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	//Add a user to the system
	public void AddUser(String username, String password, String email, String type, String firstname, String lastname){
		db.addUserToDatabase(username, password, email, type, firstname, lastname);
	}
	
	//Change a username 
	public void changeUsername(String name, String newname, String password) {
		db.changeUsername(name, newname, password);
	}
	
	//Remove a user from the system
	public void DeleteUser(String username, String Password) {
		db.DeleteUserFromDatabase(username, Password);
	}
	
	//Add a weapon to a factions armory
	public void AddWeapon(String weaponName, String Faction_name) {
		db.addWeaponToArmory(weaponName, Faction_name);
	}
	
	//Remove a weapon from an armory.
	public void RemoveWeapon(String weaponName) {
		db.DeleteFromArmory(weaponName);
	}
	
	public ArrayList<Armory> getArmory(String factionName) {
		List<Armory> armoryList = db.findArmoryByFactionName(factionName);
		ArrayList<Armory> armories = null;
		
		if(armoryList.isEmpty()) {
			System.out.println("No such armories exist, even in the webway");
			return null;
		}
		
		else {
			armories = new ArrayList<Armory>();
			for(Armory A: armoryList) {
				armories.add(A);
			}
			return armories;
		}
	}
	
	public void GetArmoryFromFacID(int faction_id) {
		db.findArmoryByFactionID(faction_id);
	}
	
	public void GetArmoryFromFacName(String faction_name) {
		db.findArmoryByFactionName(faction_name);
	}
	
	public void SearchForFaction(String faction_name) {
		db.searchByFactionName(faction_name);
	}
	
	public void SearchForFactionArmory(int armory_id) {
		db.searchFactionByArmoryID(armory_id);
	}
	
	public void addFactionToFavs(String faction_name, int faction_id, int user_id) {
		db.addFactionToFavorites(faction_name, faction_id, user_id);
	}
	
	public ArrayList<Favorites> GetFavorites(int user_id) {
		List<Favorites> favList = null;
		
		//Need to create a get favorites method before finishing this 
	}
	
	
	
	//Match users with their entered password 
	public ArrayList<User> matchUser(String name){
		List<User> userList = db.matchUsernameWithPassword(name);
		ArrayList<User> users = null;
		users = new ArrayList<User>();
		for (User user : userList) {
			users.add(user);
		}			

	return users;
	}
	
	//Return a users account information
	public ArrayList<User> getInfo(String name){
		List<User> userList = db.getAccountInfo(name);
		ArrayList<User> users = null;
		users = new ArrayList<User>();
		for (User user : userList) {
			users.add(user);
		}

	return users;
	}
	
	public boolean authenticate(User u, String password){
		boolean real = false;
		if(u.getPassword().equals(password)) {
			real = true;
		}
		return real;
	}
	
	
}
