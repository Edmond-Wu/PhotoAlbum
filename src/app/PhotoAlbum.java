package app;


import java.io.FileInputStream;
import java.io.ObjectInputStream;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.Admin;
import model.NonAdminUser;
import model.User;

/**
 * @author Edmond Wu & Vincent Xie
 */
public class PhotoAlbum extends Application {
	
	public static Admin admin = new Admin("admin", "password");
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		FXMLLoader loader = new FXMLLoader();   
	    loader.setLocation(getClass().getResource("/view/Login.fxml"));
	    AnchorPane root = (AnchorPane)loader.load();
	    LoginController login = loader.getController();
	    login.start(primaryStage);
	    
	    Scene scene = new Scene(root, 800, 600);
	    primaryStage.setScene(scene);
	    primaryStage.setResizable(false);
	    primaryStage.show(); 
	}
	
	/*
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
	*/
	
	public static void main(String[] args) {
		launch(args);
		
		/*
		User u = new Admin("admin", "admin");
		u.serialize();
		
		User x = deSerialize(u.getUsername() + ".ser");
		System.out.println();
		System.out.println(x.getPassword());
		*/
	}
}