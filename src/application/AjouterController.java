package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import daoImpl.PatientDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Patient;

public class AjouterController implements Initializable{
	
	     @FXML  private TextField litField;

	    @FXML private TextField cneField;

	    @FXML  private TextField nomField;

	    @FXML private TextField sexeField;

	    @FXML private TextField ageField;

	    @FXML private TextField teleField;

	    @FXML  private TextField adressField;

	    @FXML private TextField entreField;

	    @FXML private TextField sortieField;
	    
	    @FXML private TextField maladieField;

	    @FXML private TextField medecinField;

	    @FXML private TextField prenomField;

	    @FXML private Button insertBtn;
	    
	    private PatientDaoImpl patientDao;
	    
	    //add Patient --------------------------------------------
	    
	    @FXML
	    public void inserePatient(ActionEvent e) throws SQLException {
	    	patientDao=new PatientDaoImpl();
	    	
	    	String cne=cneField.getText();
	    	String nom=nomField.getText();
	    	String Prenom=prenomField.getText();
	    	String sexe=sexeField.getText();
	    	String ageStr=ageField.getText();
	    	int age=Integer.parseInt(ageStr);
	    	String dateEntre=entreField.getText();
	    	String dateSortie=sortieField.getText();
	    	String numeTele=teleField.getText();
	    	String adress=adressField.getText();
	    	String maladie=maladieField.getText();
	    	
	    	//String lit=litField.getText();
	    	//int idLit=Integer.parseInt(lit);
	    	//String medecin=medecinField.getText();
	    	//int idMedcin=Integer.parseInt(medecin);
	    	
	    	
	    	Patient patient=new Patient(nom,Prenom,cne,sexe,age,numeTele,adress,maladie,dateEntre);
	    	patient.setLit(null);
	    	patient.setMedecin(null);
	    	patient.setDateSortie(dateSortie);
	    	int status =patientDao.addPatient(patient);
	    	
	    	if(status>0) {
	    		Alert alert=new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Ajouter Patient ");
	    		alert.setHeaderText("information");
	    		alert.setContentText("Patient est ajaute ");
	    		alert.showAndWait();
	    	}
	    	else {
	    		Alert alert=new Alert(AlertType.ERROR);
	    		alert.setTitle("Ajouter Patient ");
	    		alert.setHeaderText("information");
	    		alert.setContentText("Patient n'est pas ajoute correctement");
	    		alert.showAndWait();
	    	}
	    	
	    	System.out.println("done");
	    	
	    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

}
