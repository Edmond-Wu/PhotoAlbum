//Edmond Wu & Vincent Xie

package view;

import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class AddPhotoDialog extends Dialog<ButtonType> {

    private ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
    private ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    private ButtonType browse = new ButtonType("Browse...", ButtonData.APPLY);
    private TextField photoText;
    private TextField dateTime;
    private TextField caption;
    private TextField tags;

    /**
     * Creates an add user dialog box.
     */
    public AddPhotoDialog() {
        setTitle("Add a photo");
        setHeaderText(null);

        GridPane dPane = new GridPane();
        Label photo = new Label("Photo name: ");
        Label date = new Label("Date of photo: ");
        Label cap = new Label("Caption: ");
        Label tag = new Label("Tags: ");
        photoText = new TextField();
        caption = new TextField();
        dateTime = new TextField();
        tags = new TextField();

        dPane.setHgap(7D);
        dPane.setVgap(8D);

        GridPane.setConstraints(photo, 0, 0);
        GridPane.setConstraints(date, 0, 1);
        GridPane.setConstraints(cap, 0, 2);
        GridPane.setConstraints(tag, 0, 3);
        GridPane.setConstraints(photoText, 1, 0);
        GridPane.setConstraints(dateTime, 1, 1);
        GridPane.setConstraints(caption, 1, 2);
        GridPane.setConstraints(tags, 1, 3);
        
        /*
        GridPane.setConstraints(song, 0, 0);
        GridPane.setConstraints(artist, 0, 1);
        GridPane.setConstraints(album, 0, 2);
        GridPane.setConstraints(year, 0, 3);
        GridPane.setConstraints(songText, 1, 0);
        GridPane.setConstraints(artistText, 1, 1);
        GridPane.setConstraints(albumText, 1, 2);
        GridPane.setConstraints(yearText, 1, 3);
		*/
        
        dPane.getChildren().addAll(photo, date, cap, tag, photoText, dateTime, caption, tags);
        getDialogPane().getButtonTypes().addAll(ok, cancel, browse);
        getDialogPane().setContent(dPane);
    }
    
    /**
     * Extracts photo file name from dialog box.
     * @return photo name
     */
    public String getPhotoName() {
    	return photoText.getText();
    }
    
    /**
     * Extracts date from the dialog box.
     * @return date of photo
     */
    public String getDate() {
    	return dateTime.getText();
    }
    
    /**
     * Extracts the caption from the dialog box.
     * @return caption of photo
     */
    public String getCaption() {
    	return caption.getText();
    }
    
    /**
     * Extracts tags string from the dialog box.
     * @return tags string
     */
    public String getTags() {
    	return tags.getText();
    }
}