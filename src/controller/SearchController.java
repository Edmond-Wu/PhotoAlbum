package controller;

import java.io.*;
import java.util.ArrayList;

import app.PhotoAlbum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.ListView;
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

/**
 * @author Edmond Wu & Vincent Xie
 */
public class SearchController extends Controller {
	
	
	public static boolean albums;
	
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
	
	
	@FXML
	private ListView<String> list;
	
	public void start(Stage mainStage) {   
		File file = new File("src/assets/results.png");
		Image image = new Image(file.toURI().toString());
		imageView.setImage(image);	
		displayAlbums();
	}
	
	/**
	 * Goes to the previous page.
	 * @param e
	 */
	public void back(ActionEvent e){
		if(albums){
			segue("/view/Albums.fxml");
		} else {
			segue("/view/Album.fxml");
		}
	}
	
	/**
	 * Displays albums.
	 */
	public void displayAlbums(){
		grid.getChildren().clear();
		grid.getRowConstraints().clear();
		File file1 = new File("src/assets/test.jpeg");
		Image image1 = new Image(file1.toURI().toString());
		ArrayList<Photo> albums = PhotoAlbum.search;
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
						PhotoAlbum.photo = PhotoAlbum.search.get(
										2 * GridPane.getRowIndex(cover) + GridPane.getColumnIndex(cover));
						segue("/view/Photo.fxml");
					});
				}
			}
		}
	}
}
