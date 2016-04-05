//Edmond Wu & Vincent Xie

package view;

import java.io.File;
import java.time.LocalDate;

import app.PhotoAlbum;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;

public class AddPhotoDialog extends Dialog<ButtonType> {

    private ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
    private ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    private Button browse = new Button("Browse...");
    private DatePicker dateTime;
    private TextField caption;
    private TextField tags;
    private FileChooser browser;

    /**
     * Creates an add user dialog box.
     */
    public AddPhotoDialog() {
        setTitle("Add a photo");
        setHeaderText(null);

        GridPane dPane = new GridPane();
        Label photo = new Label("Photo: ");
        Label date = new Label("Date of photo: ");
        Label cap = new Label("Caption: ");
        Label tag = new Label("Tags: ");
        caption = new TextField();
        dateTime = new DatePicker();
        tags = new TextField();

        dPane.setHgap(7D);
        dPane.setVgap(8D);

        GridPane.setConstraints(photo, 0, 0);
        GridPane.setConstraints(date, 0, 1);
        GridPane.setConstraints(cap, 0, 2);
        GridPane.setConstraints(tag, 0, 3);
        GridPane.setConstraints(browse, 1, 0);
        GridPane.setConstraints(dateTime, 1, 1);
        GridPane.setConstraints(caption, 1, 2);
        GridPane.setConstraints(tags, 1, 3);
        
        browser = new FileChooser();
        
        browse.setOnAction(
                e -> {
                        File file = browser.showOpenDialog(PhotoAlbum.stage);
                        this.show();
                        if (file != null) {
                            System.out.println(file.getName());
                        }
                });
        
        dPane.getChildren().addAll(photo, date, cap, tag, browse, dateTime, caption, tags);
        getDialogPane().getButtonTypes().addAll(ok, cancel);
        getDialogPane().setContent(dPane);
    }
    
    /**
     * Extracts photo file name from dialog box.
     * @return photo name
     */
    public String getPhotoName() {
    	return ""; //change this
    }
    
    /**
     * Extracts date from the dialog box.
     * @return date of photo
     */
    public LocalDate getDate() {
    	return dateTime.getValue();
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