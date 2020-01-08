package application;

//JAVA Import
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;


//Classes Import
import dao.PatientDao;
import model.Patient;
import daoImpl.PatientDaoImpl;


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
import javafx.scene.control.Labeled;
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
	 @FXML public Tab paneTabPane = new Tab();
	
	
   @FXML
	public void goBackToHome(ActionEvent event) throws IOException {
	AnchorPane pane=FXMLLoader.load(getClass().getResource("home.fxml"));
	Scene scene=new Scene(pane);
	Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
	stage.setScene(scene);
	stage.show();
	}
   
   
   // AJouter Controller !!!!!!!!!!!!!!!!!!!!!!
   @FXML
  	public void goAdd(ActionEvent event) throws IOException {
	   Stage stage=new Stage();
  	Parent pane=FXMLLoader.load(getClass().getResource("Ajouter.fxml"));
  	Scene scene=new Scene(pane);
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
		teleCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("tele"));
		adressCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("adress"));
		maladieCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("maladie"));
		entreeCOl.setCellValueFactory(new PropertyValueFactory<Patient, Date>("dateEntree"));
		sortieCol.setCellValueFactory(new PropertyValueFactory<Patient, Date>("dateSortie"));
		medecinCol.setCellValueFactory(new PropertyValueFactory<Patient, Medecin>("medecin"));
		
		//Adding the Special cells
        Callback<TableColumn<Patient, String>, TableCell<Patient, String>> cellFactory=(param) -> {
			
            	//Make the TableCell Containing the Button
			final TableCell<Patient, String> cell = new TableCell<Patient , String>(){
				 
				
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
			    		   
			    		   
			    		   edit.setOnAction(e -> {
			    			   
			    			   PatientDaoImpl dao = new PatientDaoImpl();
								
								Patient p = getTableView().getItems().get(getIndex());
								
								
			    			   
								
							   idLabel.setText("ID : "+p.getId());
								
							    litField.setText(""+p.getLit());
								cneField.setText(p.getCne());
								nomField.setText(p.getNom());
								prenomField.setText(p.getPrenom());
								sexeField.setText(p.getSexe());
								ageField.setText(""+p.getAge());
								teleField.setText(p.getNumTel());
								adressField.setText(p.getAdresse());
								maladieField.setText(p.getMaladie());
//								entreeField.setText(p.getDateEntree());
//								sortieField.setText(p.getDateSortie());
//								medecinField.setText(""+p.getMedecin());
			    		       
//								dao.updatePatient(p);	
			    			    	
			    					   
								updateBtn.setVisible(true);
			    			    
			    			   
			    		   });
			    		   
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
			    		   
			    		   HBox editCell = new HBox();
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
	

// +++++++++++++++++++++++++++++++++++++++++++++++++++++  UPDATE TAB ++++++++++++++++++++++++++++++++

   @FXML  public TextField litField;
   @FXML public TextField cneField;
   @FXML  public TextField nomField;
   @FXML public TextField prenomField;
   @FXML public TextField sexeField;
   @FXML public TextField ageField;
   @FXML public TextField teleField;
   @FXML  public TextField adressField;
   @FXML public TextField entreeField;
   @FXML public TextField sortieField;
   @FXML public TextField maladieField;
   @FXML public TextField medecinField;
   
   
   
   
   @FXML private Button updateBtn;
   
   private PatientDaoImpl patientDao;


public Patient patientToEdit;
   
   
   
    
   @FXML
   public void inserePatient(ActionEvent e) throws Exception {
   	patientDao=new PatientDaoImpl();
//   	pCtrl = new PatientController();
   	
   	int id = Integer.parseInt(idLabel.getText());
   	String cne=cneField.getText();
   	String nom=nomField.getText();
   	String Prenom=prenomField.getText();
   	String sexe=sexeField.getText();
   	String ageStr=ageField.getText();
   	int age=Integer.parseInt(ageStr);
   	String dateEntre=entreeField.getText();
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
   	patient.setId(id);
   	int status =patientDao.addPatient(patient);
   	
   	if(status>0) {
   		
   		
   		Alert alert=new Alert(AlertType.INFORMATION);
   		alert.setTitle("Ajouter Patient ");
   		alert.setHeaderText("information");
   		alert.setContentText("Patient est ajaute ");
   		alert.showAndWait();
   		
   		this.insertLoad(patient);
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
//   	pCtrl = new PatientController();
   	
   	String cne=cneField.getText();
   	String nom=nomField.getText();
   	String Prenom=prenomField.getText();
   	String sexe=sexeField.getText();
   	String ageStr=ageField.getText();
   	int age=Integer.parseInt(ageStr);
   	String dateEntre=entreeField.getText();
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
   	int status =patientDao.updatePatient(patient);
   	
   	if(status>0) {
   		
   		
   		Alert alert=new Alert(AlertType.INFORMATION);
   		alert.setTitle("Modifier Patient ");
   		alert.setHeaderText("information");
   		alert.setContentText("Patient Modifié ");
   		alert.showAndWait();
   		
   		tableView.refresh();
//   		
   	}
   	else {
   		Alert alert=new Alert(AlertType.ERROR);
   		alert.setTitle("Modifier Patient ");
   		alert.setHeaderText("information");
   		alert.setContentText("Patient n'est pas modifié correctement");
   		alert.showAndWait();
   	}
   	
   	System.out.println("done");
   	
   	
   	
   }



}
