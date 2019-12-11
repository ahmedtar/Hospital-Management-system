package application;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import daoImpl.UtilisateurDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class SampleController implements Initializable{ 
	
	 @FXML private TextField nomUtilisateur;

	 @FXML private PasswordField passUtilisateur;
	 
	 @FXML private Label label;
	
	 private UtilisateurDaoImpl userDao;
	 
    @FXML
	public void changeScene(ActionEvent event) throws IOException {
    	userDao=new UtilisateurDaoImpl();
    	
    	String log=nomUtilisateur.getText();
    	String pass=passUtilisateur.getText();
    	int status=userDao.login(log, pass);
    	
    	
    	if(status>0) {
    		AnchorPane pane=FXMLLoader.load(getClass().getResource("home.fxml"));
    		Scene scene=new Scene(pane);
    		Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
    		Image image=new Image("/application/imgs/logo.png");
    		stage.getIcons().add(image);
    		stage.setTitle("Hospital Management System");
    		stage.setScene(scene);
    		stage.show();
    	}
    	else {
    		
    		label.setText("Le mot de passe ou le nom entré est incorrect !!");
    	}
    	
    	
    	
    	
		
	}
    
    //background video --------------------------------------------------------
    @FXML
	private MediaView mediaView;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
	/*	Media media=new Media("file:///C:/Users/asus/Desktop/HMS2/hospital-management-systeme/src/application/video/backVedio.mp4");
		MediaPlayer player=new MediaPlayer(media);
        mediaView.setMediaPlayer(player);
        player.setVolume(0);
        player.play();*/
	}
	
}
