package controller;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;

public class NonAdminController extends Controller implements Initializable {
	
	private ListView<String> list;
	private ObservableList<String> obsList;
	private Text album_name;
	private ImageView imageView;
	
	
	public void start(Stage mainStage) {

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	public static User deSerialize(String file_name) {
		User u = null;
	    try {
	    	FileInputStream fileIn = new FileInputStream("data/" + file_name);
	        ObjectInputStream in = new ObjectInputStream(fileIn);
	        u = (User) in.readObject();
	        in.close();
	        fileIn.close();
	    } catch(Exception e) {
	         System.out.println("Invalid deserialization.");
	         return null;
	    } 
	    return u;
	}
}
