package DBpersist;
import java.util.List;

import model.User;


public interface IDatabase {
List<User> matchUsernameWithPassword(String name);
	
    List<User> getAccountInfo(String name);

	List<User> addUserToDatabase(String name, String pswd, String email, String type, String first, String last);

	List<User> DeleteUserFromDatabase(String name, String pswd);

	List<User> changeUsername(String name, String newName, String pswd);
}
