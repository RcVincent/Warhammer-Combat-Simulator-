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


import model.User;

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
		
		public List<User> DeleteUserFromDatabase(final String name, final String pswd) {
			return executeTransaction(new Transaction<List<User>>() {
				
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
		
		//creating the tables
		public void createTables() {
			executeTransaction(new Transaction<Boolean>() {
				@Override
				public Boolean execute(Connection conn) throws SQLException {
					PreparedStatement stmt1 = null;
					PreparedStatement stmt2 = null;
					PreparedStatement stmt3 = null;
					PreparedStatement stmt4 = null;
					PreparedStatement stmt5 = null;

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

						//the users referenced in the user_id constraing are owners
						stmt2 = conn.prepareStatement(
								"create table restaurants (" +
										"	rest_id integer primary key " +
										"		generated always as identity (start with 1, increment by 1), " +
										"	user_id integer constraint user_id references users, " +
										"	rest_name varchar(40),"    +
										"	rest_address varchar(90)," +
										"   rest_city varchar(30),"	   +
										"   rest_zipcode varchar(10)"  +
										")"
								);
						stmt2.executeUpdate();
						//the following tables do not use a constraint to avoid overlapping foreign keys
						stmt3 = conn.prepareStatement(
								" create table menu (" +
										" menu_id integer primary key " +
										" 		generated always as identity (start with 1, increment by 1), " +
										" rest_id integer, "   +
										" menu_item varchar(40), "      +
										" menu_price varchar(10)"      +
										")"
								);
						stmt3.executeUpdate();
						//order is attached to a patron
						stmt4 = conn.prepareStatement(
								" create table orders (" +
										" order_id integer primary key " +
										" 		generated always as identity (start with 1, increment by 1), " +
										" patron_id integer, "    +
										" rest_name varchar(40)," +
										" order_number integer, " +
										" item varchar(40), "	  +
										" quantity integer,"	  +
										" price varchar(10),"		  +
										" status varchar(40)"     +
										")"
								);
						stmt4.executeUpdate();
						stmt5 = conn.prepareStatement(
								" create table favRests (" +
										" favRestId integer primary key " +
										" 		generated always as identity (start with 1, increment by 1), " +
										" patron_id integer, "   +
										" rest_name varchar(40)" +
										")"
								);
						stmt5.executeUpdate();

						return true;
					} finally {
						DBUtil.closeQuietly(stmt1);
						DBUtil.closeQuietly(stmt2);
						DBUtil.closeQuietly(stmt3);
						DBUtil.closeQuietly(stmt4);
						DBUtil.closeQuietly(stmt5);
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
