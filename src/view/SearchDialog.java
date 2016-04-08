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
    private TextField ppl_tags;
    private TextField pl_tags;
    
    public SearchDialog() {
    	DialogPane dialogPane = this.getDialogPane();
    	setTitle("Search for photos");
        setHeaderText(null);
        
        GridPane dPane = new GridPane();
        dPane.setPrefWidth(500);
        dPane.setMinHeight(200);
        Text date = new Text("Date: ");
        Text people_tags = new Text("Tag people: ");
        Text places_tags = new Text("Tag places: ");
                
        ppl_tags = new TextField();
        pl_tags = new TextField();
        dateTime = new DatePicker();
        dateTime.setPromptText("Optional");
        ppl_tags.setPromptText("Optional");
        pl_tags.setPromptText("Optional");

        dPane.setHgap(7D);
        dPane.setVgap(8D);

        GridPane.setConstraints(date, 0, 0);
        GridPane.setConstraints(people_tags, 0, 1);
        GridPane.setConstraints(places_tags, 0, 2);
        GridPane.setConstraints(dateTime, 1, 0);
        GridPane.setConstraints(ppl_tags, 1, 1);
        GridPane.setConstraints(pl_tags, 1, 2);

        
        dPane.getChildren().addAll(date, people_tags, places_tags, dateTime, ppl_tags, pl_tags);
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
     * Extracts people-related tags string from the dialog box.
     * @return tags string
     */
    public String getPeopleTags() {
    	return ppl_tags.getText();
    }
    
    /**
     * Extracts places-related tags string from the dialog box.
     * @return tags string
     */
    public String getPlaceTags() {
    	return pl_tags.getText();
    }
}
