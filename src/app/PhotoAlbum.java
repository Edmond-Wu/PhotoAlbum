package app;

import java.util.ArrayList;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.NonAdminUser;

/**
 * @author Edmond Wu & Vincent Xie
 */
public class PhotoAlbum extends Application {
	
	public static ArrayList<NonAdminUser> userArrayList = new ArrayList<NonAdminUser>();
	
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
	
	public static void main(String[] args) {
		launch(args);
		/*
		User u = new Admin("admin", "admin");
		u.serialize();
		*/
	}
}