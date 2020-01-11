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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Utilisateur;

public class UtilisateurController implements Initializable{

	
	    @FXML private TabPane tabPane;
	    @FXML private Tab infoTab;
	    @FXML private Tab userTab;
	    @FXML private Tab modifTab;

	    @FXML private TableView<?> tableView;
	    @FXML private TableColumn<Utilisateur,String> loginCol;
	    @FXML private TableColumn<Utilisateur,String> nomCol;
	    @FXML private TableColumn<Utilisateur,String> prenomCol;
	    @FXML private TableColumn<Utilisateur,String> adminCol;

	    

	    @FXML private TextField loginField;
	    @FXML private TextField nomField;
	    @FXML private TextField prenomField;
	    @FXML private TextField passwordField;
	    
	    
	    
	    
	    
	    
	    
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	
	
	
	
	
	
	
     public void goBackToHome(ActionEvent event) throws IOException {
		
		AnchorPane pane=FXMLLoader.load(getClass().getResource("home.fxml"));
		Scene scene=new Scene(pane);
		Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
		}
	
	public void logout(ActionEvent event) throws IOException {
		AnchorPane pane=FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene scene=new Scene(pane);
		Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
		
		}

	
	  @FXML
	    void goToUpdate(ActionEvent event) {
		  tabPane.getSelectionModel().select(modifTab);
	    }

	  
	  @FXML
	    void update(ActionEvent event) {

	    }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
}
