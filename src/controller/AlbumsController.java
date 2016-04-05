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
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import model.Album;
import model.NonAdminUser;
import view.AddAlbumDialog;

/**
 * @author Edmond Wu & Vincent Xie
 */
public class AlbumsController extends Controller{

	@FXML
	private ImageView imageView;

	@FXML
	private GridPane grid;
	
	@FXML
	private Button edit;
	
	@FXML
	private Button delete;
	
	@FXML
	private Button logout;
	
	@FXML
	private Button done;
	
	@FXML
	private Button search;
	
	@FXML
	private Button add;
	
	private ArrayList<Integer> selected;

	public void start(Stage mainStage) {   
		File file = new File("src/assets/Albums.png");
		Image image = new Image(file.toURI().toString());
		imageView.setImage(image);
		hideButton(done);
		hideButton(delete);
		displayAlbums();
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
		}
		displayAlbums();
	}

	/**
	 * Removes from the list of albums
	 * @param e
	 */
	public void delete(ActionEvent e){
		ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
		ButtonType cancel = new ButtonType("Cancel", ButtonData.NO);
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.getDialogPane().getButtonTypes().add(ok);
		dialog.getDialogPane().getButtonTypes().add(cancel);
		dialog.setHeaderText("Confirm.");
		dialog.setContentText("Are you sure you would like to delete the selected albums?");
		dialog.showAndWait().ifPresent(response -> {
			if (response == ok) {
				ArrayList<Album> albums = PhotoAlbum.regular_user.getAlbums();
				for(Integer i: selected){
					albums.set(i, null);
				}
				while(albums.remove(null));
			}
		});
		selected.clear();
		displayAlbumsEdit();
	}

	/**
	 * Allows editing of albums.
	 * @param e
	 */
	public void edit(ActionEvent e) {
		selected = new ArrayList<Integer>();
		hideButton(edit);
		hideButton(logout);
		hideButton(search);
		hideButton(add);
		showButton(done);
		showButton(delete);
		displayAlbumsEdit();
	}
	
	/**
	 * Finish editing.
	 * @param e
	 */
	public void done(ActionEvent e) {
		showButton(edit);
		showButton(logout);
		showButton(search);
		showButton(add);
		hideButton(done);
		hideButton(delete);
		ObservableList<Node> list = grid.getChildren();
		ArrayList<Album> albums = PhotoAlbum.regular_user.getAlbums();
		for(int i = 0; i < list.size(); i++){
			Node node = list.get(i);
			if(node instanceof TextField){
				albums.get((i - 1) / 2).changeName(((TextField)node).getText());
			}
		}
		displayAlbums();
	}
	
	/**
	 * Allows searching of albums.
	 * @param e
	 */
	public void search(ActionEvent e) {
		SearchController.albums = true;
		segue("/view/Search.fxml");
	}

	/**
	 * Displays albums.
	 */
	public void displayAlbums(){
		grid.getChildren().clear();
		grid.getRowConstraints().clear();
		File file1 = new File("src/assets/test.jpeg");
		Image image1 = new Image(file1.toURI().toString());
		ArrayList<Album> albums = PhotoAlbum.regular_user.getAlbums();
		grid.setPrefHeight(70 + (int)((albums.size() + 1) / 2) * 211);
		if(albums.size() <= 2){
			grid.setPrefHeight(240);
		} else if(albums.size() <= 4){
			grid.setPrefHeight(468);
		}
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
					ImageView cover = new ImageView();
					cover.setFitHeight(190);
					cover.setFitWidth(320);
					cover.setPreserveRatio(true);
					cover.setPickOnBounds(true);
					cover.setImage(image1);
					grid.add(cover, j, i);
					GridPane.setHalignment(cover, HPos.CENTER);
					GridPane.setValignment(cover, VPos.CENTER);
					GridPane.setMargin(cover, new Insets(0, 0, 10, 0));
					cover.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { 
						PhotoAlbum.album = PhotoAlbum.regular_user
								.getAlbums().get(
										2 * GridPane.getRowIndex(cover) + GridPane.getColumnIndex(cover));
						segue("/view/Album.fxml");
					});
					Text name = new Text();
					name.setText(albums.get(2 * i + j).getName());
					name.setWrappingWidth(366);
					grid.add(name, j, i);
					GridPane.setHalignment(name, HPos.CENTER);
					GridPane.setValignment(name, VPos.BOTTOM);
					name.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { 
						PhotoAlbum.album = PhotoAlbum.regular_user
								.getAlbums().get(
										2 * GridPane.getRowIndex(cover) + GridPane.getColumnIndex(cover));
						segue("/view/Album.fxml");
					});
				}
			}
		}
	}
	
	/**
	 * Displays albums for editing.
	 */
	public void displayAlbumsEdit(){
		grid.getChildren().clear();
		grid.getRowConstraints().clear();
		File file1 = new File("src/assets/test.jpeg");
		Image image1 = new Image(file1.toURI().toString());
		ArrayList<Album> albums = PhotoAlbum.regular_user.getAlbums();
		grid.setPrefHeight(70 + (int)((albums.size() + 1) / 2) * 211);
		if(albums.size() <= 2){
			grid.setPrefHeight(240);
		} else if(albums.size() <= 4){
			grid.setPrefHeight(468);
		}
		for(int i = 0; i < Math.ceil(albums.size() / 2.0); i++){
			RowConstraints row = new RowConstraints();
			row.setMinHeight(10);
			row.setPrefHeight(30);
			row.setVgrow(Priority.SOMETIMES);
			grid.getRowConstraints().add(row);
		}
		for(int i = 0; i <= albums.size() / 2; i++){
			for(int j = 0; j < 2; j++){
				if(2 * i + j < albums.size()){
					ImageView cover = new ImageView();
					cover.setFitHeight(190);
					cover.setFitWidth(320);
					cover.setPreserveRatio(true);
					cover.setPickOnBounds(true);
					cover.setImage(image1);
					cover.setOpacity(0.5);
					grid.add(cover, j, i);
					GridPane.setHalignment(cover, HPos.CENTER);
					GridPane.setValignment(cover, VPos.CENTER);
					GridPane.setMargin(cover, new Insets(0, 0, 10, 0));
					cover.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { 
						if(cover.getOpacity() == 0.5){
							cover.setOpacity(1);
							selected.add(2 * GridPane.getRowIndex(cover) + GridPane.getColumnIndex(cover));
						} else {
							cover.setOpacity(0.5);
							selected.remove(new Integer(2 * GridPane.getRowIndex(cover) + GridPane.getColumnIndex(cover)));
						}
					});
					TextField name = new TextField();
					name.setText(albums.get(2 * i + j).getName());
					name.setPrefWidth(250);
					name.setMaxWidth(250);
					name.setAlignment(Pos.CENTER);
					grid.add(name, j, i);
					GridPane.setHalignment(name, HPos.CENTER);
					GridPane.setValignment(name, VPos.BOTTOM);
					name.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { 
						PhotoAlbum.album = PhotoAlbum.regular_user
								.getAlbums().get(
										2 * GridPane.getRowIndex(cover) + GridPane.getColumnIndex(cover));
					});
				}
			}
		}
	}
}
