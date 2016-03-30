package controller;

import java.io.IOException;

import app.PhotoAlbum;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Edmond Wu & Vincent Xie
 */
public abstract class Controller {
	
	/**
	 * Logs a user out.
	 * @throws IOException
	 */
	public void logout() throws IOException{
		ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
		ButtonType cancel = new ButtonType("Cancel", ButtonData.NO);
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.getDialogPane().getButtonTypes().add(ok);
		dialog.getDialogPane().getButtonTypes().add(cancel);
		dialog.setHeaderText("Confirm.");
		dialog.setContentText("Are you sure you want to logout?");
		dialog.showAndWait().ifPresent(response -> {
			FXMLLoader loader = new FXMLLoader();   
			loader.setLocation(getClass().getResource("/view/Login.fxml"));
			AnchorPane root;
			try {
				root = (AnchorPane)loader.load();
				LoginController login = loader.getController();
				login.start(PhotoAlbum.stage);

				Scene scene = new Scene(root, 800, 600);
				PhotoAlbum.stage.setScene(scene);
				PhotoAlbum.stage.setResizable(false);
				PhotoAlbum.stage.show(); 
			} catch (Exception e) {
				System.out.println("Error while logging out.");
			}
		});
	}
}
