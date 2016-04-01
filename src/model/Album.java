package model;

import java.io.*;
import java.util.*;

/**
 * @author Edmond Wu & Vincent Xie
 */
public class Album implements Serializable {
	
	private String name;
	private ArrayList<Photo> photos;
	
	/**
	 * Constructor with album name
	 * @param s name of the photo album
	 */
	public Album(String s) {
		name = s;
		photos = new ArrayList<Photo>();
	}
	
	/**
	 * Returns the name of the album
	 * @return name of album
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Get a list of the photos in the album
	 * @return ArrayList of photos
	 */
	public ArrayList<Photo> getPhotos() {
		return photos;
	}
	
	/**
	 * Add a photo to the album
	 * @param p photo to add
	 */
	public void addPhoto(Photo p) {
		photos.add(p);
	}
	
	/**
	 * Remove a photo from the album if it's contained
	 * @param p photo to be removed
	 */
	public void removePhoto(Photo p) {
		try {
			photos.remove(p);
		} catch(Exception e) {
			System.out.println("The photo you're trying to remove doesn't exist!");
		}
	}
	
	/**
	 * Changes the album name
	 * @param n New name of the album
	 */
	public void changeName(String n) {
		name = n;
	}
}
