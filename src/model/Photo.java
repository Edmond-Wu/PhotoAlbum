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
	private ArrayList<String> tags;
	/*
	private ArrayList<String> places;
	private ArrayList<String> people;
	*/
	private String caption;
	
	
	/**
	 * Constructor with file name
	 * @param n name of the photo file
	 */
	public Photo(File f) {
		file_name = f;
		tags = new ArrayList<String>();
		caption = "";
	}
	
	/**
	 * Constructor if the caption is known
	 * @param n name of photo file
	 * @param c caption
	 * @param t String containing all the tags
	 */
	public Photo(File f, LocalDate d, String c, String t) {
		this(f);
		date = d;
		caption = c;
		String[] split = t.split("\\s*,\\s*");
		for (int i = 0; i < split.length; i++) {
			if (split[i].length() > 0) {
				tags.add(split[i]);
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
	public ArrayList<String> getTags() {
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
	 * Prints out the list of tags
	 */
	public void printTags() {
		for (String t : tags) {
			System.out.println(t);
		}
	}
}
