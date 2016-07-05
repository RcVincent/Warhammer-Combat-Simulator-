package controller;
import DBpersist.DatabaseProvider;
import DBpersist.DerbyDatabase;
import DBpersist.IDatabase;

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
}
