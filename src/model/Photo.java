package model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import app.PhotoAlbum;

/**
* @author Edmond Wu & Vincent Xie
*/
public class Photo implements Serializable {
	
	private LocalDateTime date;
	private File file_name;
	private HashMap<String, String> tags;
	private ArrayList<User> likers;
	private String caption;
	
	
	/**
	 * Constructor with file name
	 * @param f name of the photo file
	 */
	public Photo(File f) {
		file_name = f;
		tags = new HashMap<String, String>();
		likers = new ArrayList<User>();
		caption = "";
	}
	
	/**
	 * Constructor if the caption is known
	 * @param f name of photo file
	 * @param c caption
	 * @param people String containing all the people tags
	 * @paramu places String containing all the place tags
	 */
	public Photo(File f, LocalDateTime d, String c, String t) {
		this(f);
		date = d;
		caption = c;
	}
	
	/**
	 * Gets the date-time of the photo (when last modified)
	 * @return the date-time of the picture
	 */
	public LocalDateTime getDate() {
		return date;
	}
	
	/**
	 * Gets the file name of the photo (when last modified)
	 * @return the file name of the picture
	 */
	public File getFile() {
		return file_name;
	}
	
	/**
	 * Gets the list of tags of the photo (when last modified)
	 * @return the tags list of the picture
	 */
	public HashMap<String, String> getTags() {
		return tags;
	}
	
	/**
	 * Gets the caption of the photo (when last modified)
	 * @return the caption of the picture
	 */
	public String getCaption() {
		return caption;
	}
	
	/**
	 * Returns the number of likes on a photo
	 * @return the number of likes
	 */
	public int getLikes() {
		for(int i = 0; i < likers.size(); i++){
			if(!PhotoAlbum.admin.getUserList().contains(likers.get(i))){
				likers.remove(i);
				i--;
			}
		}
		return likers.size();
	}
	
	/**
	 * Gets if the user has liked the image already
	 */
	public boolean liked(){
		return likers.contains(PhotoAlbum.logged_in);
	}
	
	/**
	 * Adds current user to likers.
	 */
	public void addUserToLikers(){
		likers.add(PhotoAlbum.logged_in);
	}
	
	/**
	 * Removes current user from likers.
	 */
	public void removeUserFromLikers(){
		while(likers.remove(PhotoAlbum.logged_in));
	}
	
	/**
	 * Sets caption.
	 * @param caption new caption
	 */
	public void setCaption(String caption){
		this.caption = caption;
	}
}
