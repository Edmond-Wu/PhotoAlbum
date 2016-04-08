package view;

import java.time.LocalDate;

import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class SearchDialog extends Dialog<ButtonType> {
	private ButtonType ok = new ButtonType("OK", ButtonData.OK_DONE);
    private ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    private DatePicker dateTime;
    private TextField tags;
    
    public SearchDialog() {
    	DialogPane dialogPane = this.getDialogPane();
    	setTitle("Search for photos");
        setHeaderText(null);
        
        GridPane dPane = new GridPane();
        dPane.setPrefWidth(500);
        dPane.setMinHeight(200);
        Text date = new Text("Date: ");
        Text tag = new Text("Tags: ");
                
        tags = new TextField();
        dateTime = new DatePicker();
        dateTime.setPromptText("Optional");
        tags = new TextField();
        tags.setPromptText("Optional");

        dPane.setHgap(7D);
        dPane.setVgap(8D);

        GridPane.setConstraints(date, 0, 0);
        GridPane.setConstraints(tag, 0, 1);
        GridPane.setConstraints(dateTime, 1, 0);
        GridPane.setConstraints(tags, 1, 0);

        
        dPane.getChildren().addAll(date, tag, dateTime, tags);
        dialogPane.getButtonTypes().addAll(ok, cancel);
        dialogPane.setContent(dPane);
    }
    
    /**
     * Extracts date from the dialog box.
     * @return date of photo
     */
    public LocalDate getDate() {
    	return dateTime.getValue();
    }
    
    /**
     * Extracts tags string from the dialog box.
     * @return tags string
     */
    public String getTags() {
    	return tags.getText();
    }
}
