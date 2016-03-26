package model;

public abstract class User {
	private String username;
	private String password;
	
	public User(String n, String p) {
		username = n;
		password = p;
	}
	
	/**
	 * Gets the username
	 * @return username of the user
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Gets the password
	 * @return password of the user
	 */
	public String getPassword() {
		return password;
	}
}
