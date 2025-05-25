package com.xadmin.usermanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xadmin.usermanagement.model.User;

/**
 * Data Access Object (DAO) class for handling CRUD operations on User entity.
 * It manages database connections and SQL queries to insert, select, update, and delete users.
 * 
 * @author Mohamed Sayed
 */
public class USerDAO {
	
    // JDBC URL, username and password of SQL Server database
    private String jdbcURL = "jdbc:sqlserver://localhost:1433;databaseName=userMangment;encrypt=false";
    private String jdbcUsername = "test"; 
    private String jdbcPassword = "0000";

    // SQL query to insert a new user into the users table
	private static final String INSERT_USERS_SQL = "INSERT INTO users (name, email, country) VALUES (?, ?, ?);";

    // SQL query to select a user by their ID
	private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";

    // SQL query to select all users
	private static final String SELECT_ALL_USERS = "select * from users";

    // SQL query to delete a user by their ID
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";

    // SQL query to update a user record by ID
	private static final String UPDATE_USERS_SQL = "update users set name = ?, email= ?, country =? where id = ?;";

    /**
     * Default constructor
     */
	public USerDAO() {
	}

    /**
     * Establishes and returns a connection to the database.
     * Loads the JDBC driver class and connects using credentials.
     * 
     * @return Connection object to interact with the database
     */
	protected Connection getConnection() {
		Connection connection = null;
		try {
			// Load the SQL Server JDBC driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// Establish connection with database
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return connection;
	}

    /**
     * Inserts a new user into the database.
     * Uses a PreparedStatement to safely insert data.
     * 
     * @param user User object to be inserted
     * @throws SQLException if any SQL error occurs
     */
	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}

    /**
     * Selects a user from the database by their ID.
     * Returns the User object if found, else null.
     * 
     * @param id The ID of the user to fetch
     * @return User object with data from database or null if not found
     */
	public User selectUser(int id) {
		User user = null;
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new User(id, name, email, country);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return user;
	}

    /**
     * Retrieves all users from the database.
     * Returns a List of User objects.
     * 
     * @return List<User> list of all users
     */
	public List<User> selectAllUsers() {
		List<User> users = new ArrayList<>();
		try (Connection connection = getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				users.add(new User(id, name, email, country));
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return users;
	}

    /**
     * Deletes a user by ID from the database.
     * 
     * @param id The ID of the user to delete
     * @return true if a user was deleted, false otherwise
     * @throws SQLException if a database error occurs
     */
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
			 PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

    /**
     * Updates user information in the database.
     * 
     * @param user User object containing updated data
     * @return true if update was successful, false otherwise
     * @throws SQLException if a database error occurs
     */
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = getConnection();
			 PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {
			System.out.println("updated User: " + statement);
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getCountry());
			statement.setInt(4, user.getId());

			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

    /**
     * Helper method to print detailed information about SQL exceptions.
     * 
     * @param ex SQLException instance to print details for
     */
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
