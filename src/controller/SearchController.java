package controller;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

import app.PhotoAlbum;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Edmond Wu & Vincent Xie
 */
public class SearchController extends Controller implements Initializable{
	
	
	public static boolean albums;
	
	@FXML
	private ImageView imageView;
	
	@FXML
	private ImageView album1;
	
	@FXML
	private Text name1;
	
	@FXML
	private ImageView album2;
	
	@FXML
	private Text name2;
	
	@FXML
	private GridPane grid;
	
	private ObservableList<String> obsList;
	
	@FXML
	private ListView<String> list;
	
	public void start(Stage mainStage) {   
		obsList = FXCollections.observableArrayList();
		for (int i = 0; i < PhotoAlbum.regular_user.getAlbums().size(); i++) {
			obsList.add(i, PhotoAlbum.regular_user.getAlbums().get(i).getName());
		}
		/*
		for (Album a : PhotoAlbum.regular_user.getAlbums()) {
			System.out.println(a.getName());
		}
		*/
		/*
		list.setItems(obsList); 
		list.getSelectionModel().select(0);
		showInfo();
		list.setOnMouseClicked((e) -> showInfo());
		*/
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		File file = new File("src/assets/results.png");
		Image image = new Image(file.toURI().toString());
		imageView.setImage(image);	
		
		File file1 = new File("src/assets/test.jpeg");
		Image image1 = new Image(file1.toURI().toString());
		album1.setImage(image1);		
		
		Image image2 = new Image(file1.toURI().toString());
		album2.setImage(image2);
		
		album1.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> { segue("/view/Album.fxml"); });
		name1.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("test"));
		album2.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("test"));
		name2.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> System.out.println("test"));
	}
	
	public void back(ActionEvent e){
		if(albums){
			segue("/view/Albums.fxml");
		} else {
			segue("/view/Album.fxml");
		}
	}
}
