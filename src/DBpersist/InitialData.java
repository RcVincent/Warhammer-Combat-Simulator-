package DBpersist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Infantry;
import model.User;
import model.Weapon;

public class InitialData {

	//user db will be for authentication and hold a primary key for users and admins
	public static List<User> getUsers() throws IOException {
		List<User> UserList = new ArrayList<User>();
		ReadCSV readUser = new ReadCSV("users.csv");
		try {
			// auto-generated primary key for table User
			Integer UserId = 1;
			while (true) {
				List<Integer> tuple = readUser.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				User User = new User();

				// read User ID from CSV file, but don't use it
				// auto-generate User ID, instead
				User.setUserID(UserId++);				
				User.setUsername(i.next());
				User.setPassword(i.next());
				User.setEmail(i.next());
				User.setAccountType(i.next());
				User.setFname(i.next());
				User.setLname(i.next());
				UserList.add(User);
			}
			System.out.println("UserList loaded from CSV file");			
			return UserList;
		} finally {
			readUser.close();
		}
	}
	
	public static List<Infantry> getInfantry() throws IOException {
		List<Infantry> InfantryList = new ArrayList<Infantry>();
		ReadCSV readInfantry = new ReadCSV("Infantry.csv");
		
		try {
			Integer InfantryId = 1;
			while(true){		
				List<Integer> tuple = readInfantry.next();
				if(tuple == null) {
					break;
				}
				Iterator<Integer> i = tuple.iterator();
				Infantry infantry = new Infantry();
				infantry.setWS(i.next());
				infantry.setBS(i.next());
				infantry.setStrength(i.next());
				infantry.setToughness(i.next());
				infantry.setWounds(i.next());
				infantry.setInitiaitve(i.next());
				infantry.setAttacks(i.next());
				infantry.setLeadership(i.next());
				infantry.setSave(i.next());
			}
			System.out.println("InfantryList loaded from CSV file");
			return InfantryList;
			}
		finally {
			readInfantry.close();
		}
	}
	
	public static List<Weapon> getWeapon() throws IOException {
		List<Weapon> WeaponList = new ArrayList<Weapon>();
		ReadCSV readWeapons = new ReadCSV("Weapons.csv");
		
		try {
			Integer WeaponId = 1;
			while(true) {
				List<Integer> tuple = readWeapons.next();
				if(tuple == null) {
					break;
				}
				
				Iterator<Integer> i = tuple.iterator();
				Weapon w = new Weapon();
				w.setStrength(i.next());
				w.setAP(i.next());
			}
			System.out.println(); 
			return WeaponList;
		}
		finally {
			readWeapons.close();
		}
	}
	
}
