package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PatientController implements Initializable{ 
	
	
   @FXML
	
	public void goBackToHome(ActionEvent event) throws IOException {
	AnchorPane pane=FXMLLoader.load(getClass().getResource("home.fxml"));
	Scene scene=new Scene(pane);
	Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.setScene(scene);
	stage.show();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}

}
