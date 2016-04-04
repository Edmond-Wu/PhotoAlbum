package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import app.PhotoAlbum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import model.User;
import view.AddPhotoDialog;

/**
 * @author Edmond Wu & Vincent Xie
 */
public class AlbumController extends Controller {
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private GridPane grid;
	
	@FXML
	private Text title;
	

	public void start(Stage mainStage) {                
		displayPhotos();
		if(PhotoAlbum.album != null){
			title.setText(PhotoAlbum.album.getName());
		} else {
			System.out.println("egesg");
		}
	}
	
	public void back(ActionEvent e){
		segue("/view/Albums.fxml");
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
			displayPhotos();
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
	
	/**
	 * Displays Photos.
	 */
	public void displayPhotos(){
		if(PhotoAlbum.album == null || PhotoAlbum.album.getPhotos() == null){
			return;
		}
		grid.getChildren().clear();
		File file1 = new File("src/assets/test.jpeg");
		Image image1 = new Image(file1.toURI().toString());
		ArrayList<Photo> albums = PhotoAlbum.album.getPhotos();
		grid.setPrefHeight(Math.max(100 + (int)((albums.size() - 1) / 2) * 300, 468));
		for(int i = grid.getRowConstraints().size(); i < Math.ceil(albums.size() / 2.0); i++){
			RowConstraints row = new RowConstraints();
			row.setMinHeight(10);
			row.setPrefHeight(30);
			row.setVgrow(Priority.SOMETIMES);
			grid.getRowConstraints().add(row);
		}
		for(int i = 0; i <= albums.size() / 2; i++){
			for(int j = 0; j < 2; j++){
				if(2 * i + j < albums.size()){
					ImageView test = new ImageView();
					test.setFitHeight(190);
					test.setFitWidth(320);
					test.setPreserveRatio(true);
					test.setPickOnBounds(true);
					test.setImage(image1);
					grid.add(test, j, i);
					GridPane.setHalignment(test, HPos.CENTER);
					GridPane.setValignment(test, VPos.CENTER);
					GridPane.setMargin(test, new Insets(0, 0, 10, 0));
					test.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> { segue("/view/Album.fxml"); });

					Text test1 = new Text();
					test1.setText(albums.get(2 * i + j).getFileName());
					test1.setWrappingWidth(366);
					grid.add(test1, j, i);
					GridPane.setHalignment(test1, HPos.CENTER);
					GridPane.setValignment(test1, VPos.BOTTOM);
					GridPane.setMargin(test1, new Insets(0, 0, 10, 0));
					test1.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> { segue("/view/Album.fxml"); });
				}
			}
		}
	}
}
