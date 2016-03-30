package controller;

import java.io.IOException;
import app.PhotoAlbum;
import java.io.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.NonAdminUser;
import model.User;

/**
 * @author Edmond Wu & Vincent Xie
 */
public class LoginController extends Controller {
	
	@FXML
	private TextField Username;
	
	@FXML
	private PasswordField Password;
	
	@FXML
	private Button Login;
	
	@FXML
	private Text userwrong;
	
	@FXML
	private Text passwrong;
	
	public void start(Stage mainStage) {                
	    Login.setDefaultButton(true);
	}
	
	/**
	 * Updates the user list
	 */
	public void updateUserList() {
		File dir = new File("data");
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				//Do something with child
		    }
		} else {
		    // Handle the case where dir is not really a directory.
		    // Checking dir.isDirectory() above would not be sufficient
		    // to avoid race conditions with another process that deletes
		    // directories.
		}
	}
	
	/**
	 * Logs the user in.
	 * @param e Action event.
	 * @throws IOException 
	 */
	public void login(ActionEvent e) throws IOException{
		String username = Username.getText().toLowerCase();
		String password = Password.getText();
		if(username.equalsIgnoreCase("admin")){
			if (password.equals(PhotoAlbum.admin.getPassword())) {
				FXMLLoader loader = new FXMLLoader();   
				loader.setLocation(getClass().getResource("/view/Admin.fxml"));
			    AnchorPane root = (AnchorPane)loader.load();
			    AdminController Admin = loader.getController();
			    Admin.start(PhotoAlbum.stage);
			    
			    Scene scene = new Scene(root, 800, 600);
			    PhotoAlbum.stage.setScene(scene);
			    PhotoAlbum.stage.setResizable(false);
			    PhotoAlbum.stage.show(); 
				return;
			}
			else {
				passwrong.setStyle("-fx-opacity: 1;");
				Password.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");
				return;
			}
		} 
		//search through list
		for(int i = 0; i < PhotoAlbum.admin.getUserList().size(); i++){
			User user = PhotoAlbum.admin.getUserList().get(i);
			if(user.getUsername().equals(username)){
				if(user.getPassword().equals(password)){
					Username.setStyle("-fx-text-box-border: white; -fx-focus-color: #008ED6;");
					userwrong.setStyle("-fx-opacity: 0;");
					Password.setStyle("-fx-text-box-border: white; -fx-focus-color: #008ED6;");
					passwrong.setStyle("-fx-opacity: 0;");
					System.out.println("Login successful.");
					return;
				} else {
					passwrong.setStyle("-fx-opacity: 1;");
					Password.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");
					return;
				}
			}
		}
		//if not found
		userwrong.setStyle("-fx-opacity: 1;");
		Username.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");
		Password.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");
	}
	
	/**
	 * Reset default colors and clears username field.
	 * @param e Mouse event.
	 */
	public void resetUsername(MouseEvent e){
		Username.setStyle("-fx-text-box-border: white; -fx-focus-color: #008ED6;");
		userwrong.setStyle("-fx-opacity: 0;");
	}
	
	/**
	 * Reset default colors and clears password field.
	 * @param e Mouse event.
	 */
	public void resetPassword(MouseEvent e){
		Password.setStyle("-fx-text-box-border: white; -fx-focus-color: #008ED6;");
		Password.clear();
		passwrong.setStyle("-fx-opacity: 0;");
	}
}
