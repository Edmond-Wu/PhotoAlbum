//Edmond Wu & Vincent Xie

package view;

import java.io.File;
import java.time.LocalDate;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class AddPhotoDialog extends Dialog<ButtonType> {

    private ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
    private ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    private Button browse = new Button("Browse...");
    private DatePicker dateTime;
    private TextField caption;
    private TextField tags;
    private FileChooser browser;
    public static File file;

    /**
     * Creates an add user dialog box.
     */
    public AddPhotoDialog() {
    	DialogPane dialogPane = this.getDialogPane();
    	dialogPane.getStylesheets().add(
    	   getClass().getResource("Dialog.css").toExternalForm());
    	
        setTitle("Add a photo");
        setHeaderText(null);
        
        GridPane dPane = new GridPane();
        dPane.setPrefWidth(500);
        dPane.setMinHeight(200);
        Text photo = new Text("Photo: ");
        Text name = new Text("");
        name.setFill(Color.ORANGE);
        Text date = new Text("Date of photo: ");
        Text datePrompt = new Text("Uses last modified date \nif not specified. ");
        Text cap = new Text("Caption: ");
        Text tag = new Text("Tags: ");
        
        name.setWrappingWidth(200);
        
        caption = new TextField();
        dateTime = new DatePicker();
        dateTime.setPromptText("Optional");
        tags = new TextField();

        dPane.setHgap(7D);
        dPane.setVgap(8D);

        GridPane.setConstraints(photo, 0, 0);
        GridPane.setConstraints(name, 3, 0);
        GridPane.setConstraints(date, 0, 1);
        GridPane.setConstraints(datePrompt, 3, 1);
        GridPane.setConstraints(cap, 0, 2);
        GridPane.setConstraints(tag, 0, 3);
        GridPane.setConstraints(browse, 1, 0);
        GridPane.setConstraints(dateTime, 1, 1);
        GridPane.setConstraints(caption, 1, 2);
        GridPane.setConstraints(tags, 1, 3);
        
        browser = new FileChooser();
        browser.setTitle("Open Photo");
        browser.getExtensionFilters().addAll(
                new ExtensionFilter("Image Files", "*.tif", "*.tiff"
                		, "*.gif", "*.jpeg", "*.jpg", "*.jif", "*.jfif"
                		, "*.jp2", "*.jpx", "*.j2k", "*.j2c"
                		, "*.fpx", "*.pcd", "*.png"
                ));
        browse.setOnAction(
                e -> {
                		Stage stage = new Stage();
                        File file = browser.showOpenDialog(stage);
                        if (file != null) {
                           	name.setText((file.getName()));
                        }
                        AddPhotoDialog.file = file;
                });
        
        dPane.getChildren().addAll(photo, name, date, datePrompt, cap, tag, browse, dateTime, caption, tags);
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