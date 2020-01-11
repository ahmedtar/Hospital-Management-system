package application;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Patient;

public class LitPanelController {
	
	  
	
	
	
	

	//UpdatePane Input List
	   @FXML public TextField idField;
	   @FXML  public TextField litField;
	   @FXML public TextField cneField;
	   @FXML  public TextField nomField;
	   @FXML public TextField prenomField;
	   @FXML public RadioButton hommeRadio;
	   @FXML public RadioButton femmeRadio;
	   @FXML public TextField ageField;
	   @FXML public TextField teleField;
	   @FXML  public TextField adressField;
	   
	   @FXML public DatePicker datePickerE;
	   @FXML public DatePicker datePickerS;
	   
	   @FXML public TextField maladieField;
	   @FXML public TextField medecinField;
	   
	   
	   public void setLit(int litId) {
		   litField.setText(""+litId);
	   }
	   
	
	   public void setUpdateInputList(Patient p) {
		    
			idField.setText(""+p.getId());
			
		    litField.setText(""+p.getLit().getId());
			cneField.setText(p.getCne());
			nomField.setText(p.getNom());
			prenomField.setText(p.getPrenom());
			String sexe = p.getSexe();
			if(sexe.equals("Homme")) {
				hommeRadio.setSelected(true);
			}
			else if(sexe.equals("Femme")) {
				femmeRadio.setSelected(true);
			}
			else {
				hommeRadio.setSelected(false);
				femmeRadio.setSelected(false);
			}
			
			ageField.setText(""+p.getAge());
			teleField.setText(p.getNumTel());
			adressField.setText(p.getAdresse());
//			maladieField.setText(p.getMaladie());
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			datePickerE.setValue(LocalDate.parse(p.getDateEntree(), formatter));
			datePickerS.setValue(LocalDate.parse(p.getDateSortie(), formatter));
			
		}
	   
	  
}
