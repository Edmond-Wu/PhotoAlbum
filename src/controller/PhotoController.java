package controller;


import java.io.File;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;

import app.PhotoAlbum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Photo;

/**
 * @author Edmond Wu & Vincent Xie
 */
public class PhotoController extends Controller{
	
	@FXML
	private ImageView photo;
	
	@FXML
	private Text caption;
	
	@FXML
	private Text date;
	
	public void start(Stage mainStage) {
		File file1 = PhotoAlbum.photo.getFile();
		Image image1 = new Image(file1.toURI().toString());
		photo.setImage(image1);	
		photo.setPreserveRatio(true);
		centerImage(photo);
		caption.setText("Caption: " + PhotoAlbum.photo.getCaption());
		date.setText(PhotoAlbum.photo.getDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
	}
	
	/**
	 * Goes to the previous page.
	 * @param e
	 */
	public void back(ActionEvent e){
		 segue("/view/Album.fxml");
	}
	
	/**
	 * Deletes the photo
	 * @param e
	 */
	public void delete(ActionEvent e){
		ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
		ButtonType cancel = new ButtonType("Cancel", ButtonData.NO);
		Dialog<ButtonType> dialog = new Dialog<ButtonType>();
		dialog.getDialogPane().getButtonTypes().add(ok);
		dialog.getDialogPane().getButtonTypes().add(cancel);
		dialog.setHeaderText("Confirm.");
		dialog.setContentText("Are you sure you want to delete this photo?");
		dialog.showAndWait().ifPresent(response -> {
			if (response == ok) {
				ArrayList<Photo> photos = PhotoAlbum.album.getPhotos();
				for(int i = 0; i < photos.size(); i++){
					if(photos.get(i).equals(PhotoAlbum.photo)){
						photos.remove(i);
						PhotoAlbum.regular_user.serialize();
						segue("/view/Album.fxml");
						return;
					}
				}
			}
		});
	}
	
	/**
	 * Centers image in image view.
	 * @param ImageView photo
	 */
	public void centerImage(ImageView photo) {
        Image image = photo.getImage();
        if (image != null) {
            double ratioX = photo.getFitWidth() / image.getWidth();
            double ratioY = photo.getFitHeight() / image.getHeight();
            double ratio = (ratioX >= ratioY) ? ratioY : ratioX;

            photo.setX((photo.getFitWidth() - image.getWidth() * ratio) / 2);
            photo.setY((photo.getFitHeight() - image.getHeight() * ratio) / 2);

        }
    }
	
	public void edit(ActionEvent e) {
		
	}
}
