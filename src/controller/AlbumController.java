package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import app.PhotoAlbum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import model.User;
import view.AddPhotoDialog;

/**
 * @author Edmond Wu & Vincent Xie
 */
public class AlbumController extends Controller implements Initializable{
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private ImageView photo1;
	
	@FXML
	private Text name1;
	
	@FXML
	private ImageView photo2;
	
	@FXML
	private Text name2;
	
	@FXML
	private GridPane grid;
	
	private ObservableList<String> obsList;

	public void start(Stage mainStage) {                
		obsList = FXCollections.observableArrayList();
		for (int i = 0; i < PhotoAlbum.regular_user.getAlbums().size(); i++) {
			obsList.add(i, PhotoAlbum.regular_user.getAlbums().get(i).getName());
		}
		/*
		for (Album a : PhotoAlbum.regular_user.getAlbums()) {
			System.out.println(a.getName());
		}
		*/
		/*
		list.setItems(obsList); 
		list.getSelectionModel().select(0);
		showInfo();
		list.setOnMouseClicked((e) -> showInfo());
		*/
	}
	
	public void back(ActionEvent e){
		segue("/view/Albums.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		File file1 = new File("src/assets/test.jpeg");
		Image image1 = new Image(file1.toURI().toString());
		photo1.setImage(image1);		
		
		Image image2 = new Image(file1.toURI().toString());
		photo2.setImage(image2);
		
		photo1.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> segue("/view/Photo.fxml"));
		name1.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("test"));
		photo2.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("test"));
		name2.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("test"));
	}
	
	public void add(ActionEvent e) {
		AddPhotoDialog dialog = new AddPhotoDialog();
		Optional<ButtonType> result = dialog.showAndWait();
		
		String ok = ButtonType.OK.getText();
		String click = result.get().getText();
		
		if (click.equals(ok)) {
			String photo_name = dialog.getPhotoName();
			if (photo_name.isEmpty()) {
				Alert error = new Alert(AlertType.INFORMATION);
				error.setHeaderText("Error!");
				error.setContentText("Photo path is required!");
				error.show();
				return;
			}
			
			for (int i = 0; i < PhotoAlbum.regular_user.getAlbums().size(); i++) {
				Photo p = PhotoAlbum.album.getPhotos().get(i);
				if (photo_name.equalsIgnoreCase(p.getFileName())) {
					Alert error = new Alert(AlertType.INFORMATION);
					error.setHeaderText("Error!");
					error.setContentText("Photo already exists in album!");
					error.show();
					return;
				}
			}
			Photo added = new Photo(photo_name);
			PhotoAlbum.album.getPhotos().add(added);
			//PhotoAlbum.regular_user.getAlbums().add(added);
			obsList.add(photo_name);
			//list.getSelectionModel().select(obsList.size() - 1);
			//showInfo();
		}
	}
	
	/**
	 * Allows searching of albums.
	 * @param e
	 */
	public void search(ActionEvent e) {
		SearchController.albums = false;
		segue("/view/Search.fxml");
	}
}
