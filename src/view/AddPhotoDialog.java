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
        photoText = new TextField();
        caption = new TextField();
        dateTime = new TextField();
        tags = new TextField();

        dPane.setHgap(7D);
        dPane.setVgap(8D);

        GridPane.setConstraints(photo, 0, 0);
        GridPane.setConstraints(photoText, 1, 0);
        GridPane.setConstraints(dateTime, 2, 0);
        GridPane.setConstraints(caption, 3, 0);
        GridPane.setConstraints(tags, 4, 0);
        
        dPane.getChildren().addAll(photoText);
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
}