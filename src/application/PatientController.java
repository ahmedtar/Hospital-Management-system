package application;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Lit;
import model.Medecin;
import model.Patient;

public class PatientController implements Initializable{ 
	
	
	  @FXML
	    private Button insertBtn;

	    @FXML
	    private TextField search;

	    @FXML
	    private Button searchBtn;

	    @FXML
	    private TableView<Patient> tableView;

	    @FXML
	    private TableColumn<Patient, Lit> litCol;
	    
	    @FXML
	    private TableColumn<Patient,String> cneCol;

	    @FXML
	    private TableColumn<Patient,String> nomCol;

	    @FXML
	    private TableColumn<Patient,String> prenomCol;

	    @FXML
	    private TableColumn<Patient,String> sexeCol;

	    @FXML
	    private TableColumn<Patient,Integer> ageCol;

	    @FXML
	    private TableColumn<Patient,String> teleCol;

	    @FXML
	    private TableColumn<Patient,String> adressCol;

	    @FXML
	    private TableColumn<Patient,String> maladieCol;

	    @FXML
	    private TableColumn<Patient,Date> entreCOl;

	    @FXML
	    private TableColumn<Patient,Date> sortieCol;

	    @FXML
	    private TableColumn<Patient,Medecin> medcinCol;

	    @FXML
	    private Button allBtn;
	
	
   @FXML
	public void goBackToHome(ActionEvent event) throws IOException {
	AnchorPane pane=FXMLLoader.load(getClass().getResource("home.fxml"));
	Scene scene=new Scene(pane);
	Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.setScene(scene);
	stage.show();
	}
   
   @FXML
	
  	public void goAdd(ActionEvent event) throws IOException {
	   Stage stage=new Stage();
  	Parent pane=FXMLLoader.load(getClass().getResource("Ajouter.fxml"));
  	Scene scene=new Scene(pane);
  	stage.setScene(scene);
  	stage.show();
  	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	

}
