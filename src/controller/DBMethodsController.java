package controller;
import java.util.ArrayList;
import java.util.List;

import DBpersist.DatabaseProvider;
import DBpersist.DerbyDatabase;
import DBpersist.IDatabase;
import model.User;

public class DBMethodsController {
	private IDatabase db = null;
	
	DBMethodsController() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	public void changeUsername(String name, String newname, String password) {
		db.changeUsername(name, newname, password);
	}
	
	public void DeleteUser(String username, String Password) {
		db.DeleteUserFromDatabase(username, Password);
	}
	
	public ArrayList<User> matchUser(String name){
		List<User> userList = db.matchUsernameWithPassword(name);
		ArrayList<User> users = null;
		users = new ArrayList<User>();
		for (User user : userList) {
			users.add(user);
		}			

	return users;
	}
	
	
}
