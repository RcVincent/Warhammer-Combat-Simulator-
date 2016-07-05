package controller;
import DBpersist.DatabaseProvider;
import DBpersist.DerbyDatabase;
import DBpersist.IDatabase;


public class ChangeUsername {
	private IDatabase db = null;
	public ChangeUsername(){
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();
	}
	
	public void changeUsername(String name, String newname, String password) {
		db.changeUsername(name, newname, password);
	}
}
