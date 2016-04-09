package controller;


import java.io.File;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Optional;

import app.PhotoAlbum;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Album;
import model.Photo;
import view.AddTagDialog;

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

	@FXML
	private Text likes;

	@FXML
	private Button addTag;

	@FXML
	private Button done;

	@FXML
	private Button edit;

	@FXML
	private Button logout;

	@FXML
	private Button like;
	
	@FXML
	private AnchorPane pane;
	
	@FXML
	private Button delete;
	
	TextField newCaption;

	public void start(Stage mainStage) {
		if(!PhotoAlbum.logged_in.equals(PhotoAlbum.regular_user)){
			hideButton(edit);
			hideButton(delete);
		}
		hideButton(addTag);
		hideButton(done);
		File file1 = PhotoAlbum.photo.getFile();
		Image image1 = new Image(file1.toURI().toString());
		photo.setImage(image1);	
		photo.setPreserveRatio(true);
		centerImage(photo);
		caption.setText("Caption: " + PhotoAlbum.photo.getCaption());
		date.setText(PhotoAlbum.photo.getDate().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM)));
		setUpLikeButton();
	}

	/**
	 * Sets up like button and counter.
	 */
	public void setUpLikeButton(){
		Photo photo = PhotoAlbum.photo;
		likes.setText(photo.getLikes() + "");
		like.addEventHandler(MouseEvent.MOUSE_ENTERED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if(photo.liked()){
					likes.setText(photo.getLikes() + "   - Unlike this photo :(");
				} else {
					likes.setText(photo.getLikes() + "   - Like this photo!");
				}
			}
		});
		like.addEventHandler(MouseEvent.MOUSE_EXITED,
				new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				likes.setText(photo.getLikes() + "");
			}
		});
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

	/**
	 * Edit fields in the photo and allows adding of tags.
	 * @param e
	 */
	public void edit(ActionEvent e) {
		hideButton(edit);
		hideButton(logout);
		showButton(addTag);
		showButton(done);
		
		caption.setText("Caption: ");
		newCaption = new TextField();
		newCaption.setLayoutX(153);
		newCaption.setLayoutY(485);
		newCaption.setText(PhotoAlbum.photo.getCaption());
		newCaption.setPrefWidth(250);
		pane.getChildren().add(newCaption);
	}
	
	public void addTag(ActionEvent e) {
		AddTagDialog dialog = new AddTagDialog();
		Optional<ButtonType> result = dialog.showAndWait();
		
		String ok = ButtonType.OK.getText();
		String click = result.get().getText();
		
		if (click.equals(ok)) {
			String key = dialog.getKey();
			String value = dialog.getValue();
			if (key.isEmpty() || value.isEmpty()) {
				Alert error = new Alert(AlertType.INFORMATION);
				error.setHeaderText("Error!");
				error.setContentText("Both key and value are required!");
				error.show();
				return;
			}
			PhotoAlbum.photo.getTags().put(key, value);
		}
		//PhotoAlbum.photo.printTags();
		PhotoAlbum.regular_user.serialize();
	}

	/**
	 * Done editing.
	 * @param e
	 */
	public void done(ActionEvent e){
		hideButton(addTag);
		hideButton(done);
		showButton(edit);
		showButton(logout);
		PhotoAlbum.photo.setCaption(newCaption.getText());
		pane.getChildren().remove(newCaption);
		newCaption = new TextField();
		caption.setText("Caption: " + PhotoAlbum.photo.getCaption());
		PhotoAlbum.regular_user.serialize();
	}

	/**
	 * Likes image.
	 */
	public void like(ActionEvent e){
		Photo photo = PhotoAlbum.photo;
		if(photo.liked()){
			photo.removeUserFromLikers();
			likes.setText(photo.getLikes() + "");
		} else {
			photo.addUserToLikers();
			likes.setText(photo.getLikes() + "   - You like this photo!");
		}
		PhotoAlbum.regular_user.serialize();
	}
}
