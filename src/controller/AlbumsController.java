package controller;

import java.io.File;
import java.util.*;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import app.PhotoAlbum;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Album;
import model.User;
import view.AddAlbumDialog;

/**
 * @author Edmond Wu & Vincent Xie
 */
public class AlbumsController extends Controller implements Initializable{
	
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private ImageView album1;
	
	@FXML
	private Text name1;
	
	@FXML
	private ImageView album2;
	
	@FXML
	private Text name2;
	
	@FXML
	private GridPane grid;
	
	private ObservableList<String> obsList;
	
	@FXML
	private ListView<String> list;
	
	public void start(Stage mainStage) {                
	    
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File file = new File("src/assets/Albums.png");
		Image image = new Image(file.toURI().toString());
		imageView.setImage(image);	
		
		File file1 = new File("src/assets/test.jpeg");
		Image image1 = new Image(file1.toURI().toString());
		album1.setImage(image1);		
		
		Image image2 = new Image(file1.toURI().toString());
		album2.setImage(image2);
		
		album1.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> { segue("/view/Album.fxml"); });
		name1.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("test"));
		album2.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("test"));
		name2.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("test"));
	}
	
	public void add(ActionEvent e) {
		AddAlbumDialog dialog = new AddAlbumDialog();
		Optional<ButtonType> result = dialog.showAndWait();
		
		String ok = ButtonType.OK.getText();
		String click = result.get().getText();
		
		if (click.equals(ok)) {
			String album_name = dialog.getAlbumName();
			if (album_name.isEmpty()) {
				Alert error = new Alert(AlertType.INFORMATION);
				error.setHeaderText("Error!");
				error.setContentText("Album name is required!");
				error.show();
				return;
			}
			
			for (int i = 0; i < PhotoAlbum.regular_user.getAlbums().size(); i++) {
				Album a = PhotoAlbum.regular_user.getAlbums().get(i);
				if (album_name.equalsIgnoreCase(a.getName())) {
					Alert error = new Alert(AlertType.INFORMATION);
					error.setHeaderText("Error!");
					error.setContentText("Album already exists!");
					error.show();
					return;
				}
			}
			Album added = new Album(album_name);
			PhotoAlbum.regular_user.getAlbums().add(added);
			obsList.add(album_name);
			list.getSelectionModel().select(obsList.size() - 1);
			showInfo();
		}
	}
	
	/**
	 * Removes from the list of users.
	 * @param e
	 */
	public void delete(ActionEvent e){
		int index = list.getSelectionModel().getSelectedIndex();
		if(index >= 0){
			ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
			ButtonType cancel = new ButtonType("Cancel", ButtonData.NO);
			Dialog<ButtonType> dialog = new Dialog<ButtonType>();
			dialog.getDialogPane().getButtonTypes().add(ok);
			dialog.getDialogPane().getButtonTypes().add(cancel);
			dialog.setHeaderText("Confirm.");
			dialog.setContentText("Are you sure you would like to delete album " + 
					PhotoAlbum.regular_user.getAlbums().get(index) + "?");
			dialog.showAndWait().ifPresent(response -> {
				if (response == ok) {
					obsList.remove(index);
					PhotoAlbum.regular_user.getAlbums().remove(index);
					showInfo();
				}
			});
		}
	}
	
	public void edit(ActionEvent e) {
		
	}
	
	public void showInfo() {
		
	}
}
