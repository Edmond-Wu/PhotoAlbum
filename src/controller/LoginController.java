package controller;

import java.awt.AWTException;
import java.awt.Robot;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;

public class LoginController implements Initializable {
	
	@FXML
	TextField Username;
	
	@FXML
	PasswordField Password;
	
	@FXML
	Button Login;
	
	public void start(Stage mainStage) {                
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
}
