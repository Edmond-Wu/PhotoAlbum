package model;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
* @author Edmond Wu & Vincent Xie
*/
public class Photo implements Serializable {
	
	private LocalDate date;
	private File file_name;
	private HashMap<String, String> tags;
	private int likes;
	/*
	private ArrayList<String> places;
	private ArrayList<String> people;
	*/
	private String caption;
	
	
	/**
	 * Constructor with file name
	 * @param f name of the photo file
	 */
	public Photo(File f) {
		file_name = f;
		tags = new HashMap<String, String>();
		caption = "";
		likes = 0;
	}
	
	/**
	 * Constructor if the caption is known
	 * @param f name of photo file
	 * @param c caption
	 * @param people String containing all the people tags
	 * @paramu places String containing all the place tags
	 */
	public Photo(File f, LocalDate d, String c, String people, String places) {
		this(f);
		date = d;
		caption = c;
		String[] people_split = people.split("\\s*,\\s*");
		String[] places_split = people.split("\\s*,\\s*");
		for (int i = 0; i < people_split.length; i++) {
			if (people_split[i].length() > 0) {
				tags.put("Person " + i, people_split[i]);
			}
		}
		for (int j = 0; j < places_split.length; j++) {
			if (places_split[j].length() > 0) {
				tags.put("Place " + j, places_split[j]);
			}
		}
	}
	
	/**
	 * Gets the date-time of the photo (when last modified)
	 * @return the date-time of the picture
	 */
	public LocalDate getDate() {
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
		return likes;
	}
	
	/**
	 * Increases the amount of likes on a photo by 1
	 */
	public void like() {
		likes++;
	}
}
