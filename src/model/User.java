package model;

import java.io.*;

/**
 * @author Edmond Wu & Vincent Xie
 */
public abstract class User implements Serializable {
	
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
	
	/**
	 * Serializes the user data
	 */
	public void serialize() {
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("data/" + username + ".ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	         System.out.printf("Serialized data is saved in data/" + username + ".ser");
	      } catch(IOException i) {
	          i.printStackTrace();
	      }
	}
}