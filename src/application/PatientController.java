package application;

//JAVA Import
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;

import com.mysql.cj.result.LocalDateValueFactory;

//Classes Import
import dao.PatientDao;
import model.Patient;
import daoImpl.PatientDaoImpl;
import model.Lit;


//javaFX Import
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;
import javafx.scene.control.Labeled;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Label;


//Modal Import
import model.Lit;
import model.Medecin;
import model.Patient;

public class PatientController implements Initializable{ 
	
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		populateTableView();
		updateBtn.setVisible(false);
		datePickerE.setValue(LocalDate.now());
		datePickerS.setValue(LocalDate.now());
		
		
		
		
	}
	
	
	
	public ObservableList<Patient> list = FXCollections.observableArrayList();	
      

	// TableView & Columns
	  @FXML public TableView<Patient> tableView;
	  @FXML public TableColumn<Patient,Integer> idCol;
	  @FXML public TableColumn<Patient, Lit> litCol;	   
	  @FXML public TableColumn<Patient,String> cneCol;
	  @FXML public TableColumn<Patient,String> nomCol;
	  @FXML public TableColumn<Patient,String> prenomCol;
	  @FXML public TableColumn<Patient,String> sexeCol;
	  @FXML public TableColumn<Patient,Integer> ageCol;
	  @FXML public TableColumn<Patient,String> teleCol;
	  @FXML public TableColumn<Patient,String> adressCol;
	  @FXML public TableColumn<Patient,String> maladieCol;
	  @FXML public TableColumn<Patient,Date> entreeCOl;
	  @FXML public TableColumn<Patient,Date> sortieCol;
	  @FXML public TableColumn<Patient,Medecin> medecinCol;
	  
	  @FXML public TableColumn<Patient,String> editCol;
	  
	  
    //Label in javafx
    @FXML public Label idLabel = new Label(); 



	@FXML private TextField searchField;
	  
// Buttons
	  @FXML private Button insertBtn;
	  
	  @FXML private Button searchBtn;
	  @FXML private Button clearBtn;
	  
// TabPane
	 @FXML public TabPane tabPane = new TabPane(); 
	 @FXML public Tab PatientTabPane = new Tab();
	 @FXML public Tab LitTabPane = new Tab();
	 @FXML public Tab UpdateTabPane = new Tab();
	
	
   @FXML
	public void goBackToHome(ActionEvent event) throws IOException {
	AnchorPane pane=FXMLLoader.load(getClass().getResource("home.fxml"));
	Scene scene=new Scene(pane);
	Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.setScene(scene);
	stage.show();
	}
   
	
private void populateTableView() {
		
		// Initialising the main Column
	    idCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
	    litCol.setCellValueFactory(new PropertyValueFactory<Patient, Lit>("lit"));
		cneCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("cne"));
		nomCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("prenom"));
		sexeCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("sexe"));
		ageCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("age"));
		teleCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("numTel"));
		adressCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("adresse"));
		maladieCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("maladie"));
		entreeCOl.setCellValueFactory(new PropertyValueFactory<Patient, Date>("dateEntree"));
		sortieCol.setCellValueFactory(new PropertyValueFactory<Patient, Date>("dateSortie"));
		medecinCol.setCellValueFactory(new PropertyValueFactory<Patient, Medecin>("medecin"));
		
		//Adding the Special cells
        Callback<TableColumn<Patient, String>, TableCell<Patient, String>> cellFactory=(param) -> {
			
            	//Make the TableCell Containing the Button
			final TableCell<Patient, String> cell = new TableCell<Patient , String>(){
				 
				
				@SuppressWarnings("deprecation")
				@Override
			    public void updateItem( String item , boolean empty) {
			    	   super.updateItem(item , empty);
			    	   
			    	 //ensure : cell created only on non-empty rows
			    	   if(empty) {
			    		   setGraphic(null);
			    		   setText(null);
			    	   }
			    	   else {
			    		   
			    		   //Create the Action Button
			    		   final Button delete = new Button("X");
			    		   final Button edit = new Button("Edit");
			    		   
	  // Edit Button Event
			    		   edit.setOnAction(e -> {
			    			   
			    			   PatientDaoImpl dao = new PatientDaoImpl();
								
								Patient p = getTableView().getItems().get(getIndex());
								
								
			    			   
								
							   idLabel.setText(""+p.getId());
								
							    litField.setText(""+p.getLit());
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
								maladieField.setText(p.getMaladie());
								
								DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
								datePickerE.setValue(LocalDate.parse(p.getDateEntree(), formatter));
								datePickerS.setValue(LocalDate.parse(p.getDateSortie(), formatter));
								
//								medecinField.setText(""+p.getMedecin());
			    		       
//								dao.updatePatient(p);	
			    			    	
			    					   
								updateBtn.setVisible(true);
								tabPane.getSelectionModel().select(UpdateTabPane);
			    			    
			    			   
			    		   });
			    		   
      //Delete Button Event
			    		   delete.setOnAction(e -> {
			    			   try {
								PatientDaoImpl dao = new PatientDaoImpl();
								
								Patient p = getTableView().getItems().get(getIndex());
								dao.deletePatient(p.getId());
								list.remove(p);
								
								
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
			    			   
			    		   });
			    		   
			    		   HBox editCell = new HBox(2);
			    		   editCell.getChildren().addAll(edit , delete);
			    		   
			    		   //set the created Button to cell
			    		   setGraphic(editCell);
			    		   setText(null);
			    	   }
			    }
			};

		 return cell;
		};
	  editCol.setCellFactory(cellFactory);
	  
	  try {
			this.LoadTable();
			System.out.println("Table Loaded Successufuly");
		} catch (Exception e) {
          System.out.println("Table Loading Error");
			e.printStackTrace();
		}
	
	}


@FXML
public void LoadTable() throws Exception {
	PatientDaoImpl dao = new PatientDaoImpl();
	this.list = dao.searchPatient("");		
	this.tableView.setItems(list);
}


@FXML
public void searchPatient() throws Exception {
	PatientDaoImpl dao = new PatientDaoImpl();
	list = dao.searchPatient(searchField.getText());		
	tableView.setItems(list);
}

@FXML
public void clearSearchField()throws Exception {
	searchField.setText("");
	LoadTable();
}

@FXML
public void insertLoad(Patient patient) throws Exception {
	list.add(patient);
	tableView.setItems(null);
	tableView.setItems(list);
	System.out.println("check 1111");
	LoadTable();
    tableView.refresh();
	System.out.println("check 22222");
}
	





// +++++++++++++++++++++++++++++++++++++++++++++++++++++  UPDATE TABPANE +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++


   //UpdatePane Input List
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
   
   
   
   
   
   
   @FXML private Button updateBtn;
   
   private PatientDaoImpl patientDao;


public Patient patientToEdit;
   
   
   
    
   @FXML
   public void inserePatient(ActionEvent e) throws Exception {
   	patientDao=new PatientDaoImpl();
//   	pCtrl = new PatientController();
   	
//   	int id = Integer.parseInt(idLabel.getText());
   	
   	//Get the values of UpdatePane Input List
   	String lit=litField.getText();
   	String cne=cneField.getText();
   	String nom=nomField.getText();
   	String prenom=prenomField.getText();
   	
   	String sexe="";
   	if(hommeRadio.isSelected()) sexe = "Homme";
   	else if(femmeRadio.isSelected()) sexe = "Femme";
   	
   	String ageStr=ageField.getText();
   	int age;
   	if(ageStr.equals("")) age=0;
   	else age = Integer.parseInt(ageStr);
   	
   	String datePickerE_str = datePickerE.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
   	String datePickerS_str ="";
   	if(datePickerE.getValue()!=null) datePickerS_str = datePickerS.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
   	else {
   		datePickerS_str = datePickerE_str;
   		datePickerS.setValue(datePickerE.getValue());
   	}
   	String numTel=teleField.getText();
   	String adress=adressField.getText();
   	String maladie=maladieField.getText();
   	
   	
 // ************Foreign Key Problem**************
   	
   	//String lit=litField.getText();
   	//int idLit=Integer.parseInt(lit);
   	//String medecin=medecinField.getText();
   	//int idMedcin=Integer.parseInt(medecin);
   	
   	//Create and Insert New Patient
   	Patient patient=new Patient( nom, prenom, cne , sexe, age, numTel, adress, maladie, datePickerE_str);
   	  // ************Foreign Key Problem**************
//   	patient.setLit(new Lit(Integer.parseInt(lit)));
//   	patient.setMedecin(null);
    	patient.setDateSortie(datePickerS_str);
//   	patient.setId(id);
   	int status =patientDao.addPatient(patient);
   	
   	if(status>0) {
   		
   		
   		Alert alert=new Alert(AlertType.INFORMATION);
   		alert.setTitle("Ajouter Patient ");
   		alert.setHeaderText("information");
   		alert.setContentText("Le Patient est ajout� avec succ�s");
   		this.insertLoad(patient);
   		tabPane.getSelectionModel().select(PatientTabPane);
   		alert.showAndWait();
   		
   		
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
   
   
    
   @FXML
   public void updatePatient(ActionEvent e) throws Exception {
	   patientDao=new PatientDaoImpl();
//  	pCtrl = new PatientController();
  	
//  	int id = Integer.parseInt(idLabel.getText());
  	
  	//Get the values of UpdatePane Input List
  	String lit=litField.getText();
  	String cne=cneField.getText();
  	String nom=nomField.getText();
  	String prenom=prenomField.getText();
  	
  	String sexe="";
  	if(hommeRadio.isSelected()) sexe = "Homme";
  	else if(femmeRadio.isSelected()) sexe = "Femme";
  	
  	String ageStr=ageField.getText();
  	int age;
  	if(ageStr.equals("")) age=0;
  	else age = Integer.parseInt(ageStr);
  	
  	String datePickerE_str = datePickerE.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  	String datePickerS_str = datePickerS.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
  	String numTel=teleField.getText();
  	String adress=adressField.getText();
  	String maladie=maladieField.getText();
  	
  	
// ************Foreign Key Problem**************
  	
  	//String lit=litField.getText();
  	//int idLit=Integer.parseInt(lit);
  	//String medecin=medecinField.getText();
  	//int idMedcin=Integer.parseInt(medecin);
  	
  	//Create and Insert New Patient
  	Patient patient=new Patient( nom, prenom, cne , sexe, age, numTel, adress, maladie, datePickerE_str);
  	  // ************Foreign Key Problem**************
//  	patient.setLit(new Lit(Integer.parseInt(lit)));
//  	patient.setMedecin(null);
   	patient.setDateSortie(datePickerS_str);
  	patient.setId(Integer.parseInt(idLabel.getText()));
  	int status =patientDao.updatePatient(patient);
   	
   	if(status!=0) {
   		
   		
   		Alert alert=new Alert(AlertType.INFORMATION);
   		alert.setTitle("Modifier Patient ");
   		alert.setHeaderText("information");
   		alert.setContentText("Patient Modifi� ");
   		LoadTable();
   		tabPane.getSelectionModel().select(PatientTabPane);
   		updateBtn.setVisible(false);
   		insertBtn.setVisible(true);
   		alert.showAndWait();
   		
   		
   		
//   		
   	}
   	else {
   		Alert alert=new Alert(AlertType.ERROR);
   		alert.setTitle("Modifier Patient ");
   		alert.setHeaderText("information");
   		alert.setContentText("Patient n'est pas modifi� correctement");
   		alert.showAndWait();
   	}
   	
   	System.out.println("done");
   	
   	
   	
   }



}
