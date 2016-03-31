package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;

/**
 * @author Edmond Wu & Vincent Xie
 */
public class AlbumController extends Controller implements Initializable{
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private ImageView photo1;
	
	@FXML
	private Text name1;
	
	@FXML
	private ImageView photo2;
	
	@FXML
	private Text name2;
	
	@FXML
	private GridPane grid;
	
	public void start(Stage mainStage) {                
	    
	}
	
	public void back(ActionEvent e){
		segue("/view/Albums.fxml");
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		File file1 = new File("src/assets/test.jpeg");
		Image image1 = new Image(file1.toURI().toString());
		photo1.setImage(image1);		
		
		Image image2 = new Image(file1.toURI().toString());
		photo2.setImage(image2);
		
		photo1.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("test"));
		name1.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("test"));
		photo2.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("test"));
		name2.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("test"));
	}
}
