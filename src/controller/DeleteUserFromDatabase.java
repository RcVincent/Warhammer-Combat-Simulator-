package controller;
import DBpersist.DatabaseProvider;
import DBpersist.DerbyDatabase;
import DBpersist.IDatabase;

public class DeleteUserFromDatabase {
	private IDatabase db = null;
	
	public DeleteUserFromDatabase() {
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	public void DeleteUser(String username, String Password) {
		db.DeleteUserFromDatabase(username, Password);
	}
}
