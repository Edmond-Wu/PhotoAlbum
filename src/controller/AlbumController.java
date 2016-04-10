package controller;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import app.PhotoAlbum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
import view.AddPhotoDialog;
import view.SearchDialog;

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
	
	@FXML
	private Button add;
	

	public void start(Stage mainStage) { 
		if(!PhotoAlbum.logged_in.equals(PhotoAlbum.regular_user)){
			hideButton(add);
		}
		displayPhotos();
		if(PhotoAlbum.album != null){
			title.setText(PhotoAlbum.album.getName());
		}
	}
	
	/**
	 * Reloads a previous page
	 * @param Mouse click event
	 */
	public void back(ActionEvent e){
		segue("/view/Albums.fxml");
	}
	
	/**
	 * Method for adding a photo
	 * @param e Mouse click event
	 */
	public void add(ActionEvent e) {
		AddPhotoDialog dialog = new AddPhotoDialog();
		Optional<ButtonType> result = dialog.showAndWait();
		
		String ok = ButtonType.OK.getText();
		String click = result.get().getText();
		File photo_file;
		if (click.equals(ok)) {
			photo_file = AddPhotoDialog.file;
			if (photo_file == null) {
				Alert error = new Alert(AlertType.INFORMATION);
				error.setHeaderText("Error!");
				error.setContentText("Photo path is required!");
				error.show();
				return;
			}
			for (int i = 0; i < PhotoAlbum.album.getPhotos().size(); i++) {
				Photo p = PhotoAlbum.album.getPhotos().get(i);
				if (p.getFile().equals(photo_file)) {
					Alert error = new Alert(AlertType.INFORMATION);
					error.setHeaderText("Error!");
					error.setContentText("Photo already exists in album!");
					error.show();
					return;
				}
			}
			Date d = new Date(photo_file.lastModified());
			LocalDateTime date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			String caption = dialog.getCaption();
			Photo added = new Photo(photo_file, date, caption, "");
			PhotoAlbum.album.getPhotos().add(added);
			displayPhotos();
		}
		PhotoAlbum.regular_user.serialize();
	}
	
	/**
	 * Allows searching of albums.
	 * @param e Mouse click event
	 */
	public void search(ActionEvent e) {
		SearchController.albums = false;
		SearchDialog dialog = new SearchDialog();
		Optional<ButtonType> result = dialog.showAndWait();

		String ok = ButtonType.OK.getText();
		String click = result.get().getText();
		if (click.equals(ok)) {
			PhotoAlbum.search = new ArrayList<Photo>();

			LocalDate startDate = dialog.getStartDate();
			LocalDate endDate = dialog.getEndDate();
			String key = dialog.getKey().toLowerCase();
			String value = dialog.getValue().toLowerCase();
			
			if (startDate == null && endDate == null && key.trim().length() == 0 && value.trim().length() == 0) {
				return;
			}
			
			ArrayList<Photo> photos = PhotoAlbum.album.getPhotos();
			for(int i = 0; i < photos.size(); i++){
				File file = photos.get(i).getFile();
				Date d = new Date(file.lastModified());
				LocalDate date = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				if (key.trim().length() > 0) {
					for (String k : photos.get(i).getTags().keySet()) {
						if (k.contains(key)) {
							if (!PhotoAlbum.search.contains(photos.get(i))) {
								PhotoAlbum.search.add(photos.get(i));
							}
						}
					}
				}
				if (value.trim().length() > 0) {
					for (String k : photos.get(i).getTags().keySet()) {
						for (int j = 0; j < photos.get(i).getTags().get(k).size(); j++) {
							if (photos.get(i).getTags().get(k).get(j).contains(value)) {
								PhotoAlbum.search.add(photos.get(i));
							}
						}
					}
				}
				if (startDate == null && endDate == null) {
					
				}
				if(startDate == null){
					if(date.compareTo(endDate) <= 0){
						PhotoAlbum.search.add(photos.get(i));
					}
				} else if (endDate == null){
					if(date.compareTo(startDate) >= 0){ 
						PhotoAlbum.search.add(photos.get(i));
					}
				}
				if(startDate != null && endDate != null){
					if(date.compareTo(startDate) >= 0 && date.compareTo(endDate) <= 0){
						PhotoAlbum.search.add(photos.get(i));
					}
				}	
			}
			segue("/view/Search.fxml");
		}
	}
	
	/**
	 * Displays Photos.
	 */
	public void displayPhotos(){
		if(PhotoAlbum.album == null || PhotoAlbum.album.getPhotos() == null){
			return;
		}
		grid.getChildren().clear();
		grid.getRowConstraints().clear();
		ArrayList<Photo> albums = PhotoAlbum.album.getPhotos();
		for(int i = 0; i < albums.size(); i++){
			File file = albums.get(i).getFile();
			if(!file.exists()){
				albums.set(i, null);
			}
		}
		while(albums.remove(null));
		PhotoAlbum.regular_user.serialize();
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
					File file = albums.get(2 * i + j).getFile();
					Image image = new Image(file.toURI().toString());
					ImageView cover = new ImageView();
					cover.setFitHeight(190);
					cover.setFitWidth(320);
					cover.setPreserveRatio(true);
					cover.setPickOnBounds(true);
					cover.setImage(image);
					grid.add(cover, j, i);
					GridPane.setHalignment(cover, HPos.CENTER);
					GridPane.setValignment(cover, VPos.CENTER);
					GridPane.setMargin(cover, new Insets(0, 0, 10, 0));
					cover.addEventFilter(MouseEvent.MOUSE_PRESSED, e -> { 
						PhotoAlbum.photo = PhotoAlbum.album.getPhotos().get(
										2 * GridPane.getRowIndex(cover) + GridPane.getColumnIndex(cover));
						PhotoController.search = false;
						segue("/view/Photo.fxml");
					});
				}
			}
		}
	}
}
