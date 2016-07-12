package DBpersist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DBpersist.DBUtil;
import DBpersist.DerbyDatabase.Transaction;
import DBpersist.PersistenceException;

import model.Armory;
import model.Faction;
import model.Favorites;
import model.User;
import model.Weapon;

public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}

	public interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 100;
	
		//********************
		//adding a user to the database
		//********************
		@Override
		public List<User> addUserToDatabase(final String name, final String pswd, final String email, final String type, final String first,
				final String last) {
			return executeTransaction(new Transaction<List<User>>() {
				@Override
				public List<User> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					PreparedStatement stmt2 = null;
					ResultSet resultSet = null;

					try {
						stmt = conn.prepareStatement(
								"insert into users(user_userName, user_passWord, user_email, user_accountType, user_firstName, user_lastName) " +
										" values(?, ?, ?, ?, ?, ?) "
								);
						stmt.setString(1, name);
						stmt.setString(2, pswd);
						stmt.setString(3, email);
						stmt.setString(4, type);
						stmt.setString(5, first);
						stmt.setString(6, last);
						stmt.executeUpdate();
						
						stmt2 = conn.prepareStatement(
								"select * " +
										" from users " +
										" where user_userName = ?"
								);
						stmt2.setString(1, name);
						
						resultSet = stmt2.executeQuery();

						// for testing that a result was returned
						Boolean found = false;
						List<User> result = new ArrayList<User>();
						while (resultSet.next()) {
							found = true;
							User u = new User();
							loadUser(u, resultSet, 1);
							result.add(u);
						}

						// check if the title was found
						if (!found) {
							System.out.println("<" + name + "> was not found in the users table");
						}

						return result;


					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(stmt2);
					}
				}
			});
		}
		
		//*************************************************
		//match user name with password for authentication
		//*************************************************
		@Override
		public List<User> matchUsernameWithPassword(final String name) {
			
			return executeTransaction(new Transaction<List<User>>() {
				@Override
				public List<User> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;

					try {


						stmt = conn.prepareStatement(
								"select * from Users " +
										" where user_userName = ? "
								);
						stmt.setString(1, name);
						List<User> result = new ArrayList<User>();
						resultSet = stmt.executeQuery();

						// for testing that a result was returned
						Boolean found = false;

						while (resultSet.next()) {
							found = true;

							User u = new User();
							loadUser(u, resultSet, 1);
							result.add(u);
						}

						// check if the title was found
						if (!found) {
							System.out.println("<" + name + "> was not found in the Users table");
						}

						return result;


					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});
		}


		//********************************
		//Delete a user from the database
		//********************************
		@Override
		public List<User> DeleteUserFromDatabase(final String name, final String pswd) {
			return executeTransaction(new Transaction<List<User>>() {
				@Override
				public List<User> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					PreparedStatement stmt2 = null; 
					ResultSet resultSet = null;

					try {


						stmt = conn.prepareStatement(
								"delete from users " +
										" where user_userName = ? " +
										" and user_passWord = ? "
								);
						stmt.setString(1, name);
						stmt.setString(2, pswd);
						stmt.executeUpdate();

						// return all users and see that the one entered was deleted
						
						stmt2 = conn.prepareStatement(
								"select * from users " 		
								);
						resultSet = stmt2.executeQuery();
						List<User> result = new ArrayList<User>();
						
						Boolean found = false;

						while (resultSet.next()) {
							found = true;

							User u = new User();
							loadUser(u, resultSet, 1);
							result.add(u);
						}

						// check if the title was found
						if (!found) {
							System.out.println("<" + name + "> users list is empty");
						}

						return result;


					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(stmt2);
					}
				}
			});
		}
		//************************
		//Change username
		//***********************
		@Override
		public List<User> changeUsername(final String name, final String newName, final String pswd) {
			return executeTransaction(new Transaction<List<User>>() {
				@Override
				public List<User> execute(Connection conn) throws SQLException {
					
					PreparedStatement stmt = null;
					PreparedStatement stmt2 = null;
					
					ResultSet resultSet2 = null;
					
					try {
						
						stmt = conn.prepareStatement(
								"update users " +
										" set user_userName = ? " +
										" where user_userName = ? " +
										" and user_passWord = ? "
								);

						stmt.setString(1, newName);
						stmt.setString(2, name);
						stmt.setString(3, pswd);
						stmt.executeUpdate();
						System.out.printf("Querry Completed: Update user's name");

						// return all users and see that the one entered was deleted
						
						stmt2 = conn.prepareStatement(
								"select * from users " 	+
										" where user_userName = ? "
								);
						//ensure new userName is in database
						stmt2.setString(1, newName);

						resultSet2 = stmt2.executeQuery();
						System.out.printf("Where does the query die?");

						List<User> result = new ArrayList<User>();
						
						Boolean found = false;

						while (resultSet2.next()) {
							found = true;

							User u = new User();
							loadUser(u, resultSet2, 1);
							result.add(u);
						}
						
						// check if the title was found
						if (!found) {
							System.out.println("<" + name + "> was not in users list");
						}

						return result;


					} finally {
						
						DBUtil.closeQuietly(resultSet2);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(stmt2);
					}
				}
			});
		}

		@Override
		public List<User> getAccountInfo(final String name) {
			
			return executeTransaction(new Transaction<List<User>>() {
				@Override
				public List<User> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					try {
						stmt = conn.prepareStatement(
								"select * from Users " +
										" where user_userName = ? "
								);
						stmt.setString(1, name);
						List<User> result = new ArrayList<User>();
						resultSet = stmt.executeQuery();
						Boolean found = false;
						while (resultSet.next()) {
							found = true;

							User u = new User();
							loadUser(u, resultSet, 1);
							result.add(u);
						}

						// check if the title was found
						if (!found) {
							System.out.println("<" + name + "> was not found in the Users table");
						}

						return result;


					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});
		}
		
		@Override
		public List<Favorites> getFromInfantryFavorites(final Integer userId) {
			return executeTransaction(new Transaction<List<Favorites>>() {
				@Override
				public List<Favorites> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					PreparedStatement stmt2 = null;
					ResultSet resultSet = null;
					
					try {
						stmt2 = conn.prepareStatement(
								"select * " +
										" from favoriteInfantry " +
										" where patron_id = ?"
								);
						stmt2.setInt(1, userId);
						
						resultSet = stmt2.executeQuery();

						// for testing that a result was returned
						Boolean found = false;
						List<Favorites> result = new ArrayList<Favorites>();
						while (resultSet.next()) {
							found = true;
							Favorites u = new Favorites();
							loadFavorite(u, resultSet, 1);
							result.add(u);
						}

						// check if the title was found
						if (!found) {
							System.out.println("<> was not found in the Restaurants table");
						}

						return result;


					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(stmt2);
					}
				}
			});
		}
		@Override
		public List<Weapon> addWeaponToArmory(final String weapon, final String Faction_name) {
			return executeTransaction(new Transaction<List<Weapon>>() {
				@Override
				public List<Weapon> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					PreparedStatement stmt2 = null;
					PreparedStatement stmt3 = null;
					ResultSet resultSet = null;
					ResultSet resultSet2 = null;


					try {
						stmt = conn.prepareStatement(
								"select Faction_ID from factions " +
										" where faction_name = ? "
								);
						stmt.setString(1, Faction_name);
						resultSet = stmt.executeQuery();
						int Faction_ID = 0;
						if(resultSet.next()) {
							Faction_ID = resultSet.getInt(1);
						}
						stmt2 = conn.prepareStatement(
								"insert into armory(Faction_ID, Armory_weapon) " +
										" values(?, ?) "
								);
						stmt2.setInt(1, Faction_ID);
						stmt2.setString(2, weapon);
						stmt2.executeUpdate();
						
						stmt3 = conn.prepareStatement(
								"select * " +
										" from Armory " +
										" where Armory_weapon = ?"
								);
						stmt3.setString(1, weapon);
						
						resultSet2 = stmt3.executeQuery();

						// for testing that a result was returned
						Boolean found = false;
						List<Weapon> result = new ArrayList<Weapon>();
						while (resultSet2.next()) {
							found = true;
							Weapon w = new Weapon();
							loadWeapon(w, resultSet2, 1);
							result.add(w);
						}

						// check if the title was found
						if (!found) {
							System.out.println("<" + weapon + "> was not found in the armory");
						}

						return result;


					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(stmt2);
						DBUtil.closeQuietly(stmt3);
					}
				}
			});
		}
		
		public Weapon DeleteFromArmory(final String weaponName) {
			return executeTransaction(new Transaction<Weapon>() {
				@Override
				public Weapon execute(Connection conn) throws SQLException {
//					
					PreparedStatement stmt1 = null;
					PreparedStatement stmt2 = null;
					ResultSet resultSet = null;
					ResultSet resultSet2 = null;

					try {
						
						
						stmt1 = conn.prepareStatement(
								" delete from Armory " +
										" where Armory_weapon = ?"
								);
						stmt1.setString(1, weaponName);
						
						stmt1.executeUpdate();
						
						stmt2 = conn.prepareStatement(
								"select * " +
										" from Armory " +
										" where Armory_item = ?"
								);
						stmt2.setString(1, weaponName);
						
						resultSet2 = stmt2.executeQuery();

						// for testing that a result was returned
						Boolean found = false;
						Weapon result = new Weapon();
						while (resultSet2.next()) {
							found = true;
							Weapon w = new Weapon();
							loadWeapon(w, resultSet, 1);
							result = w;
						}

						// check if the title was found
						if (!found) {
							System.out.println("<" + weaponName + "> was not found in the menu table");
						}

						return result;


					} finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(resultSet2);
						DBUtil.closeQuietly(stmt1);
						DBUtil.closeQuietly(stmt2);
					}
				}
			});
			
		}
		
		public List<Armory> armoryByFactionName(final String factionName) {
			return executeTransaction(new Transaction<List<Armory>>(){
				
				public List<Armory> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try {
						stmt = conn.prepareStatement(
								"select armory.* " +
										"from armory, factions " + 
										"and armory.faction_id = faction.faction_id "
								);
						stmt.setString(1, factionName);
						List<Armory> result = new ArrayList<Armory>();
						resultSet = stmt.executeQuery();
						Boolean found = false;
						
						while (resultSet.next()) {
							found = true;
							
							Armory A = new Armory(); 
							loadArmory(A, resultSet, 1);
							result.add(A);
						}
						
						if(!found) {
							System.out.println("<" + factionName + "> was not found");
						}
						return result;
					}
					
					finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
			});
		}
		
		public List<Faction> createFaction(int faction_id, String faction_name) {
			return  executeTransaction(new Transaction<List<Faction>>() {

				@Override
				public List<Faction> execute(Connection conn) throws SQLException {
					
					PreparedStatement stmt = null;
					PreparedStatement stmt2 = null; 
					ResultSet resultSet = null;
					
					try {
						stmt = conn.prepareStatement(
								"insert into factions(faction_faction_id, faction_faction_name)" +
									"values(?,?)"
								);
						stmt.setInt(1, faction_id);
						stmt.setString(2, faction_name);
						stmt.executeUpdate();
						
						stmt2 = conn.prepareStatement(
								"select *" + 
										"from factions" + 
										"where faction_faction_name = ? "
								);
						stmt2.setString(1, faction_name);
						resultSet = stmt2.executeQuery();
						
						Boolean found = false;
						List<Faction> result = new ArrayList<Faction>();
						while(resultSet.next()) {
							found = true;
							Faction f = new Faction();
							loadFaction(f, resultSet, 1);
							result.add(f);
						}
						
						if(!found) {
							System.out.println("<" + faction_name + "was not found in our records. It can be assumed to be destroyed in the name of the emporer.");
						}
						
						return result;
					}
					
					finally {
						DBUtil.closeQuietly(conn);
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
						DBUtil.closeQuietly(stmt2);
					}
				}
				
			});
		}
		
		public List<Faction> searchByFactionName(String faction_name) {
			return executeTransaction(new Transaction<List<Faction>>(){

				@Override
				public List<Faction> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try{
						stmt = conn.prepareStatement(
								"select * from factions" + 
										"where faction_name = ?"								
							);
						stmt.setString(1, faction_name);
						resultSet = stmt.executeQuery();
						Boolean found = true;
						List<Faction> result = new ArrayList<Faction>();
						
						while(resultSet.next()) {
							found = true;
							
							Faction f = new Faction();
							loadFaction(f, resultSet, 1);
							result.add(f);
						}
						
						if(!found) {
							System.out.println("<" + faction_name + "was not found in our records. It can be assumed to be destroyed in the name of the emporer.");
						}
						return result;
						
					}
					finally{
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
					
				}
				
				
				});
		}
		
		public List<Faction> searhcByArmoryID(int armory_id) {
			return executeTransaction(new Transaction<List<Faction>>(){
				
				public List<Faction> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try {
						stmt = conn.prepareStatement(
									"select factions.* from factions, armories" +
									"where armories.armory_id = ?" +
									"and factions.armory_id = armories.ArmoryID"		
								);
						stmt.setInt(1, armory_id);
						resultSet = stmt.executeQuery();
						
						List<Faction> result = new ArrayList<Faction>();
						Boolean found = false;
						
						while(resultSet.next()) {
							found = true;
							Faction f = new Faction();
							
							loadFaction(f, resultSet, 1);
							result.add(f);
						}
						return result;
					}
					
					finally {
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				
				}	
			});
		}
		
		
		public List<Armory> findArmoryByFactionName(String faction_name) {
			return executeTransaction(new Transaction<List<Armory>>() {

				@Override
				public List<Armory> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try {
						stmt = conn.prepareStatement(
								"select armories.* from armories, factions" +
								"where factions.faction_name = ?" +
								"and armories.FactionID = factions.faction_id"
								);
						stmt.setString(1, faction_name);
						resultSet = stmt.executeQuery();
						
						List<Armory> result = new ArrayList<Armory>();
						
						Boolean found = false;
						while(resultSet.next()) {
							found = true;
							
							Armory a = new Armory();
							loadArmory(a, resultSet, 1);
							result.add(a);
						}
						
						return result;
					}
					
					finally{
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
					
			});
		}
		
		public List<Armory> findArmoryByFactionID(int faction_id) {
			return executeTransaction(new Transaction<List<Armory>>() {

				@Override
				public List<Armory> execute(Connection conn) throws SQLException {
					PreparedStatement stmt = null;
					ResultSet resultSet = null;
					
					try {
						stmt = conn.prepareStatement(
								"select armories.* from armories, factions" +
								"where factions.faction_id = ?" +
								"and armories.FactionID = factions.faction_id"
								);
						
						stmt.setInt(1, faction_id);
						resultSet = stmt.executeQuery();
						
						List<Armory> result = new ArrayList<Armory>();
						
						Boolean found = false;
						while(resultSet.next()) {
							found = true;
							
							Armory a = new Armory();
							loadArmory(a, resultSet, 1);
							result.add(a);
						}
						
						return result;
					}
					
					finally{
						DBUtil.closeQuietly(resultSet);
						DBUtil.closeQuietly(stmt);
					}
				}
					
			});
			
		}
		
		public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
			try {
				return doExecuteTransaction(txn);
			} catch (SQLException e) {
				throw new PersistenceException("Transaction failed", e);
			}
		}

		public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
			Connection conn = connect();

			try {
				int numAttempts = 0;
				boolean success = false;
				ResultType result = null;

				while (!success && numAttempts < MAX_ATTEMPTS) {
					try {
						result = txn.execute(conn);
						conn.commit();
						success = true;
					} catch (SQLException e) {
						if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
							// Deadlock: retry (unless max retry count has been reached)
							numAttempts++;
						} else {
							// Some other kind of SQLException
							throw e;
						}
					}
				}

				if (!success) {
					throw new SQLException("Transaction failed (too many retries)");
				}

				// Success!
				return result;
			} finally {
				DBUtil.closeQuietly(conn);
			}
		}

		private Connection connect() throws SQLException {
			Connection conn = DriverManager.getConnection("jdbc:derby:H:/workspace.newDBarea;create=true");

			// Set autocommit to false to allow multiple the execution of
			// multiple queries/statements as part of the same transaction.
			conn.setAutoCommit(false);

			return conn;
		}
		
		//these build the collections to return to the servlets, controlles
		private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
			user.setUserID(resultSet.getInt(index++));
			user.setUsername(resultSet.getString(index++));
			user.setPassword(resultSet.getString(index++));
			user.setEmail(resultSet.getString(index++));
			user.setAccountType(resultSet.getString(index++));
			user.setFname(resultSet.getString(index++));
			user.setLname(resultSet.getString(index++));
		}
		
		private void loadFavorite(Favorites fav, ResultSet resultSet, int index) throws SQLException {
			fav.setFavID(resultSet.getInt(index++));;
			fav.setUserID(resultSet.getInt(index++));
			fav.setName(resultSet.getString(index++));
		}
		
		private void loadArmory(Armory a, ResultSet resultSet, int index) throws SQLException {
			a.setFactionID(resultSet.getInt(index++));
			a.setArmoryID(resultSet.getInt(index++));
			
			
		}
		private void loadWeapon(Weapon w, ResultSet resultSet, int index) throws SQLException {
			w.setName(resultSet.getString(index++));
			w.setStrength(resultSet.getInt(index++));
			w.setAP(resultSet.getInt(index++));
			w.setArmory_id(resultSet.getInt(index++));
		}
		
		private void loadFaction(Faction f, ResultSet resultSet, int index) throws SQLException {
			f.setFaction_id(resultSet.getInt(index++));
			f.setFaction_name(resultSet.getString(index++));
		}
		
		//creating the tables
		public void createTables() {
			executeTransaction(new Transaction<Boolean>() {
				@Override
				public Boolean execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					PreparedStatement stmt2 = null;
					PreparedStatement stmt3 = null;
					PreparedStatement stmt4 = null;
					try {
						stmt1 = conn.prepareStatement(
								"create table users (" +
										"	user_id integer primary key " +
										"		generated always as identity (start with 1, increment by 1), " +									
										"	user_userName varchar(40),"     +
										"	user_passWord varchar(40), "     +
										"   user_email varchar(40), "        +
										"   user_accountType varchar(30), " +
										"    user_firstName varchar(50), "  +
										"    user_lastNAme varchar(50) "    +
										")"
								);	
						stmt1.executeUpdate();
						
						//Create the favorites Table
						stmt2 = conn.prepareStatement(
								" create table favoriteInfantry (" +
										" favInfantryId integer primary key " +
										" 		generated always as identity (start with 1, increment by 1), " +
										" user_id integer, "   +
										" infantryname varchar(40)" +
										")"
								);
						
						stmt2.executeUpdate();
						
						//Create the Armory 
						stmt3 = conn.prepareStatement(
								" create table armories (" +
										" armoryID integer primary key " +
										" 		generated always as identity (start with 1, increment by 1), " +
										" faction_id integer, "   +
										" armory_item varchar(40), "      +
										")"
								);
						
						stmt3.executeUpdate();
						
						stmt4 = conn.prepareStatement(
								"create table factions (" +
										"faction_id integer primary key" +
										"	generated always as identity (start with 1, incremented by 1), " +
										"faction_name varchar(40)," +
										"armory_id integer" +
										")"
								);
						stmt4.executeUpdate();
						
						return true;
					} finally {
						DBUtil.closeQuietly(stmt1);
						DBUtil.closeQuietly(stmt2);
						DBUtil.closeQuietly(stmt3);
						DBUtil.closeQuietly(stmt4);
					}
				}
			});
		}
		

		//loading initial data for basic website navigation
		public void loadInitialData() {
			executeTransaction(new Transaction<Boolean>() {
				@Override
				public Boolean execute(Connection conn) throws SQLException {
					
					List<User> userList;
					
					//initial data for order will be built when an order is made
					try {
						
						userList = InitialData.getUsers();
						
					} catch (IOException e) {
						throw new SQLException("Couldn't read initial data", e);
					}

					PreparedStatement insertRestaurants = null;
					PreparedStatement insertUsers = null;
					PreparedStatement insertMenus = null;
					try {


						insertUsers = conn.prepareStatement("insert into users (user_userName, user_passWord, user_email, user_accountType, user_firstName, user_Lastname) "
								+ "		values (?, ?, ?, ?, ?, ?)");
						for (User u : userList) {
							insertUsers.setString(1, u.getUsername());
							insertUsers.setString(2, u.getPassword());
							insertUsers.setString(3, u.getEmail());
							insertUsers.setString(4, u.getAccountType());
							insertUsers.setString(5, u.getFname());
							insertUsers.setString(6, u.getLname());
							insertUsers.addBatch();
						}
						insertUsers.executeBatch();
						System.out.println("Users table populated");


						return true;
					} finally {
						DBUtil.closeQuietly(insertUsers);
						
						
					}
				}
			});
		}
		
		// The main method creates the database tables and loads the initial data.
		public static void main(String[] args) throws IOException {
			System.out.println("Creating tables...");
			DerbyDatabase db = new DerbyDatabase();
			db.createTables();
			System.out.println("Loading initial data...");
			db.loadInitialData();
			System.out.println("loaded intial data");
			System.out.println("austin got it the first time, you never will");
			System.out.println("you got it this time, dont get cocky");
		}
		
}
