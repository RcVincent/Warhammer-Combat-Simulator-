package DBpersist;
import java.util.List;

import model.Armory;
import model.Faction;
import model.Favorites;
import model.User;
import model.Weapon;


public interface IDatabase {
	List<User> matchUsernameWithPassword(String name);
	
    List<User> getAccountInfo(String name);

	List<User> addUserToDatabase(String name, String pswd, String email, String type, String first, String last);

	List<User> DeleteUserFromDatabase(String name, String pswd);

	List<User> changeUsername(String name, String newName, String pswd);
	
	List<Weapon> addWeaponToArmory(String weapon, String Faction_name);
	
	Weapon DeleteFromArmory(String weaponName) ;
	
	List<Armory> findArmoryByFactionName(String faction_name);
	
	List<Armory> findArmoryByFactionID(int faction_id);
	
	List<Armory> deleteArmory(int armory_id);
	
	List<Faction> createFaction(int faction_id, String faction_name);
	
	List<Faction> removeFaction(String faction_name, int faction_id);
	
	List<Faction> searchByFactionName(String faction_name);
	
	List<Faction> searchFactionByArmoryID(int armory_id);
	
	List<Favorites> addFactionToFavorites(String faction_name, int faction_id, int user_id);	
	
	List<Favorites> getFromInfantryFavorites(Integer userId);
	
	List<Favorites> removeFromFavorites(String faction_name, int user_id);
}
