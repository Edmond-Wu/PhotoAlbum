package controller;

import java.io.*;
import java.util.*;
import java.net.URL;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Album;
import view.AddAlbumDialog;

/**
 * @author Edmond Wu & Vincent Xie
 */
public class AlbumsController extends Controller implements Initializable{
	
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private GridPane grid;
	
	private ObservableList<String> obsList;
	
	@FXML
	private ListView<String> list;
	
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File file = new File("src/assets/Albums.png");
		Image image = new Image(file.toURI().toString());
		imageView.setImage(image);	
		
		File file1 = new File("src/assets/test.jpeg");
		Image image1 = new Image(file1.toURI().toString());	
		for(int i = 0; i < 3; i++){
			for(int j = 0; j < 2; j++){
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
		     	test1.setText("Album name");
		     	test1.setWrappingWidth(366);
		     	grid.add(test1, j, i);
		        GridPane.setHalignment(test1, HPos.CENTER);
		        GridPane.setValignment(test1, VPos.BOTTOM);
		        GridPane.setMargin(test1, new Insets(0, 0, 10, 0));
		        test1.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> { segue("/view/Album.fxml"); });
			}
		}
	}
	
	/**
	 * Adds an album to the user's album list
	 * @param e
	 */
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
			//list.getSelectionModel().select(obsList.size() - 1);
		}
	}
	
	/**
	 * Removes from the list of albums
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
				}
			});
		}
	}
	
	/**
	 * Allows editing of albums.
	 * @param e
	 */
	public void edit(ActionEvent e) {
		System.out.println("edit");
	}
	
	/**
	 * Allows searching of albums.
	 * @param e
	 */
	public void search(ActionEvent e) {
		SearchController.albums = true;
		segue("/view/Search.fxml");
	}
	
}
