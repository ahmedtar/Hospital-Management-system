package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController implements Initializable{ 
	
	
	 @FXML
	    void goToUser(ActionEvent event) throws Exception {
		 AnchorPane pane=FXMLLoader.load(getClass().getResource("Utilisateur.fxml"));
			Scene scene=new Scene(pane);
			Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setX(180);
			stage.setY(50);
			stage.show();

	    }
	 
	 @FXML
		public void exit(ActionEvent event) throws IOException {
			AnchorPane pane=FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene=new Scene(pane);
			Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setX(400);
			stage.setY(50);
			stage.show();
		}
	 @FXML
		public void changeToPatient(ActionEvent event) throws IOException {
			AnchorPane pane=FXMLLoader.load(getClass().getResource("Patient.fxml"));
			Scene scene=new Scene(pane);
			Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setX(180);
			stage.setY(50);
			stage.show();
		}

		@FXML
		
		public void changeToDoctor(ActionEvent event) throws IOException {
			AnchorPane pane=FXMLLoader.load(getClass().getResource("Medecin.fxml"));
			Scene scene=new Scene(pane);
			Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setX(180);
			stage.setY(50);
			stage.show();
		}
	 
	 @FXML
	 private AnchorPane pane1,pane2,pane3,pane4;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	//	pane1.setStyle("-fx-background-image: url(\"/application/imgs/1.jpg\")" ); 
		pane2.setStyle("-fx-background-image: url(\"/application/imgs/7972.jpg\")" );
		
	}
	
}
