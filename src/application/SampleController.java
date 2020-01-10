package application;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import daoImpl.UtilisateurDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class SampleController implements Initializable{ 
	
	@FXML
    private JFXPasswordField pass;

    @FXML
    private Label label;

    @FXML
    private JFXTextField user;
	
	 private UtilisateurDaoImpl userDao;
	 
    @FXML
	public void changeScene(ActionEvent event) throws IOException {
    	userDao=new UtilisateurDaoImpl();
    	String login=user.getText();
    	String password=pass.getText();
    	int status=0;
		try {
			status = userDao.login(login, password);
		} catch (SQLException e) {
			Alert alert = new Alert(AlertType.ERROR);
		    alert.setTitle("DataBase connection");
		    String s = "error establishing a database connection";
		    alert.setContentText(s);
		    alert.showAndWait();
		}
    	
    	if(status>0) {
    		
    		AnchorPane pane=FXMLLoader.load(getClass().getResource("home.fxml"));
    		Scene scene=new Scene(pane);
    		Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
    		Image image=new Image("/application/imgs/logo.png");
    		
    		stage.getIcons().add(image);
    		stage.setTitle("Hospital Management System");
    		//stage.initStyle(StageStyle.UNDECORATED);
    		stage.setScene(scene);
    		stage.show();
    	}
    	else {
    		pass.setText("");
    		label.setText("invalid informations ");
    	}  	
		
	}
    
    
    //background video --------------------------------------------------------
    @FXML
	private MediaView mediaView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		Media media=new Media(getClass().getResource("video/backVedio.mp4").toExternalForm());
		MediaPlayer player=new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setCycleCount(MediaPlayer.INDEFINITE);
        player.setVolume(0);
        player.play();
		
	
	}
	
}
