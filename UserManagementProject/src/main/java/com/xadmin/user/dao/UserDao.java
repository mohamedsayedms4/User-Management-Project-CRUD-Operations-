package com.xadmin.user.dao;

public class UserDao {

	private String url      = "jdbc:sqlserver://localhost:1433;databaseName=userMangment" ;
	private String user     = "test";
	private String password = "0000";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, email, country) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id =?";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

	
}
