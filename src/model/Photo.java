package model;

import java.util.*;

/**
* @author Edmond Wu & Vincent Xie
*/
public class Photo {
	
	private Calendar date_time;
	private String file_name;
	private ArrayList<String> tags;
	private String caption;
	
	/**
	 * Constructor with file name
	 * @param n name of the photo file
	 */
	public Photo(String n) {
		date_time = new GregorianCalendar();
		file_name = n;
		tags = new ArrayList<String>();
		caption = "";
	}
	
	/**
	 * Constructor if the caption is known
	 * @param n name of photo file
	 * @param c caption
	 */
	public Photo(String n, String c) {
		this(n);
		caption = c;
	}
	
	/**
	 * Gets the date-time of the photo (when last modified)
	 * @return the date-time of the picture
	 */
	public Calendar getDate() {
		return date_time;
	}
	
	/**
	 * Gets the file name of the photo (when last modified)
	 * @return the file name of the picture
	 */
	public String getFileName() {
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
}
