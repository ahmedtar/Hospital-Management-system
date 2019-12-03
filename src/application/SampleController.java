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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class SampleController implements Initializable{ 
	
    @FXML
	
	public void changeScene(ActionEvent event) throws IOException {
		AnchorPane pane=FXMLLoader.load(getClass().getResource("home.fxml"));
		Scene scene=new Scene(pane);
		Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}
    
    @FXML
	private MediaView mediaView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		Media media=new Media("file:///C:/Users/asus/Desktop/HMS1/hospital-management-systeme/src/application/video/backVedio.mp4");
		MediaPlayer player=new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0);
        player.play();
	}
	
}
