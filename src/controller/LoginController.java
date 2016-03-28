package controller;

import java.io.IOException;

import app.PhotoAlbum;
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
		setController(mainStage);
	    Login.setDefaultButton(true);
	}
	
	/**
	 * Logs the user in.
	 * @param e Action event.
	 * @throws IOException 
	 */
	public void login(ActionEvent e) throws IOException{
		String username = Username.getText();
		String password = Password.getText();
		if(username.equalsIgnoreCase("admin")){
			FXMLLoader loader = new FXMLLoader();   
			loader.setLocation(getClass().getResource("/view/Admin.fxml"));
		    AnchorPane root = (AnchorPane)loader.load();
		    AdminController Admin = loader.getController();
		    Admin.start(stage);
		    
		    Scene scene = new Scene(root, 800, 600);
		    stage.setScene(scene);
		    stage.setResizable(false);
		    stage.show(); 
			return;
		} 
		//search through list
		for(int i = 0; i < PhotoAlbum.userArrayList.size(); i++){
			NonAdminUser user = PhotoAlbum.userArrayList.get(i);
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
