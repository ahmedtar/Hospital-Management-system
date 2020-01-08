package application;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import model.Patient;
import model.Patient;
import daoImpl.PatientDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import model.Patient;

public class AjouterController implements Initializable{
	
	    PatientController pCtrl = new PatientController();
	
	     @FXML  public TextField litField;

	    @FXML public TextField cneField;

	    @FXML  public TextField nomField;

	    @FXML public TextField sexeField;

	    @FXML public TextField ageField;

	    @FXML public TextField teleField;

	    @FXML  public TextField adressField;

	    @FXML public TextField entreField;

	    @FXML public TextField sortieField;
	    
	    @FXML public TextField maladieField;

	    @FXML public TextField medecinField;

	    @FXML public TextField prenomField;

	    @FXML private Button insertBtn;
	    
	    private PatientDaoImpl patientDao;
	    
	    @FXML public Label idLabel = new Label();
	    
	    //add Patient --------------------------------------------
	    
	    @FXML
	    public void inserePatient(ActionEvent e) throws Exception {
	    	patientDao=new PatientDaoImpl();
	    	pCtrl = new PatientController();
	    	
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
	    	patient.setDateSortie(dateEntre);
	    	int status =patientDao.addPatient(patient);
	    	
	    	if(status>0) {
	    		
	    		
	    		Alert alert=new Alert(AlertType.INFORMATION);
	    		alert.setTitle("Ajouter Patient ");
	    		alert.setHeaderText("information");
	    		alert.setContentText("Patient est ajaute ");
	    		alert.showAndWait();
	    		
	    		pCtrl.insertLoad(patient);
//	    		
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
	    
	    
	    @FXML public void updatePatient() {
	    	patientDao = new PatientDaoImpl();
	    	Patient p = pCtrl.patientToEdit;
	    	
	    	String lit=litField.getText();
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
	    	
	    	
			   
	    }
	    
	    @FXML
	    public void update(Patient p) throws Exception {
	    	nomField.setText(p.getNom());
	    	idLabel.setText(""+p.getId());
	    }
	    
	    

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}

}
