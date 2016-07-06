package database;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import DBpersist.DatabaseProvider;
import DBpersist.DerbyDatabase;
import DBpersist.IDatabase;
import model.User;
import model.Weapon;
import model.Faction;
import model.Favorites;
import model.Search;
import model.Armory;

public class DatabaseTests {
	
	private IDatabase db = null;
	
	List<Armory> armoryList = null;
	List<User> userList = null;
	List<User> users = null;
	List<Weapon> weaponList = null;
	List<Favorites> favList = null;
	
	Armory A = null;
	
	public static void setUpBeforeClass() throws Exception {	
	}
	
	public static void tearDownAfterClass() throws Exception {		
	}
	
	@Before
	public void setUp() throws Exception {
		DatabaseProvider.setInstance(new DerbyDatabase());	
		db = DatabaseProvider.getInstance();
	}
	
	@After
	public void tearDown() throws Exception {
	}
	
}
