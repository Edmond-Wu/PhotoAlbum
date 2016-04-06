package controller;


import java.io.File;

import app.PhotoAlbum;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * @author Edmond Wu & Vincent Xie
 */
public class PhotoController extends Controller{
	
	@FXML
	private ImageView photo;
	
	public void start(Stage mainStage) {
		File file1 = PhotoAlbum.photo.getFile();
		Image image1 = new Image(file1.toURI().toString());
		photo.setImage(image1);	
		photo.setPreserveRatio(true);
		centerImage(photo);
	}
	
	public void back(ActionEvent e){
		 segue("/view/Album.fxml");
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
}
