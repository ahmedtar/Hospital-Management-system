package application;

//JAVA Import
import java.io.IOException;
import java.net.URL;
import java.rmi.server.LoaderHandler;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.ButtonModel;

import com.mysql.cj.result.LocalDateValueFactory;

//Classes Import
import dao.PatientDao;
import model.Patient;
import daoImpl.LitDaoImpl;
import daoImpl.PatientDaoImpl;
import model.Lit;


//javaFX Import
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.LocalDateStringConverter;
import javafx.scene.control.Labeled;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Label;


//Modal Import
import model.Lit;
import model.Medecin;
import model.Patient;

public class PatientController implements Initializable{ 
	
	
	Patient p;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		populateTableView();
		setLitIconColor();
		
		try {

					 populateLitMenuList();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		updateGridBtn.setVisible(false);
		datePickerE.setValue(LocalDate.now());
		datePickerS.setValue(LocalDate.now());
		
		
		
		
	}
	
	
	
	public ObservableList<Patient> list = FXCollections.observableArrayList();	
      

	// TableView & Columns
	  @FXML public TableView<Patient> tableView;
	  @FXML public TableColumn<Patient,Integer> idCol;
	  @FXML public TableColumn<Patient, String> litCol;	   
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
	  @FXML public TableColumn<Patient,String> medecinCol;
	  
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
//	    litCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("litId"));
	    idCol.setCellValueFactory(new PropertyValueFactory<Patient, Integer>("id"));
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
//		medecinCol.setCellValueFactory(new PropertyValueFactory<Patient, Medecin>("medecin"));
		
		
		litCol.setCellValueFactory(new Callback<CellDataFeatures<Patient,String> , ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Patient, String> param) {
                return new SimpleStringProperty(""+param.getValue().getLit().getId());
            }
        });
		
		medecinCol.setCellValueFactory(new Callback<CellDataFeatures<Patient,String> , ObservableValue<String>>(){

            @Override
            public ObservableValue<String> call(CellDataFeatures<Patient, String> param) {
                return new SimpleStringProperty(param.getValue().getMedecin().getNom());
            }
        });
		
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
								
								
			    			   
								
							  setUpdateInputList(p);
								
//								medecinField.setText(""+p.getMedecin());
			    		       
//								dao.updatePatient(p);	
			    			    	
			    					   
								updateGridBtn.setVisible(true);
								insertBtn.setVisible(false);
								UpdateTabPane.setText("Modifier");
								tabPane.getSelectionModel().select(UpdateTabPane);
			    			    
			    			   
			    		   });
			    		   
      //Delete Button Event
			    		   delete.setOnAction(e -> {
			    			   
			    			    Alert alert=new Alert(AlertType.CONFIRMATION);
			    		   		alert.setTitle("Suppression d'un Patient ");
			    		   		alert.setHeaderText("Un patient va être supprimé");
			    		   		alert.setContentText("Vous êtes sûr de vouloir supprimer ce Patient ?");
			    		   		
			    		   		Optional<ButtonType> result = alert.showAndWait();
			    		   		if (result.get() == ButtonType.OK){
			    		   			try {
										PatientDaoImpl dao = new PatientDaoImpl();
										
										Patient p = getTableView().getItems().get(getIndex());
										dao.deletePatient(p.getId());
										list.remove(p);
										
										
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
			    		   		} else {
			    		   		    alert.close();
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



private void setUpdateInputList(Patient p) {
	idLabel.setText(""+p.getId());
	
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
	maladieField.setText(p.getMaladie());
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	datePickerE.setValue(LocalDate.parse(p.getDateEntree(), formatter));
	datePickerS.setValue(LocalDate.parse(p.getDateSortie(), formatter));
	
	medecinField.setText(""+p.getMedecin().getNom());
	
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
   @FXML private Button anullerBtn;
   @FXML private GridPane updateGridBtn;
   
   @FXML private MenuButton litMenuBtn = new MenuButton("Lits");
   @FXML private ObservableList<MenuItem> litMenuList = FXCollections.observableArrayList();
   
   
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
   	    patient.setLit(new Lit(Integer.parseInt(lit)));
//   	patient.setMedecin(null);
    	patient.setDateSortie(datePickerS_str);
//   	patient.setId(id);
   	int status =patientDao.addPatient(patient);
   	
   	if(status>0) {
   		
   		
   		Alert alert=new Alert(AlertType.INFORMATION);
   		alert.setTitle("Ajouter Patient ");
   		alert.setHeaderText("information");
   		alert.setContentText("Le Patient est ajouté avec succès");
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
   	setLitIconColor();
   	
   	
   	
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
  	patient.setLit(new Lit(Integer.parseInt(lit)));
//  	patient.setMedecin(null);
   	patient.setDateSortie(datePickerS_str);
  	patient.setId(Integer.parseInt(idLabel.getText()));
  	
  	Alert confirmeAlert=new Alert(AlertType.CONFIRMATION);
		confirmeAlert.setTitle("Modification d'un Patient ");
		confirmeAlert.setHeaderText("Un patient va être modifié");
		confirmeAlert.setContentText("Vous confirmez cette modification ?");
		
		Optional<ButtonType> result = confirmeAlert.showAndWait();
		if (result.get() == ButtonType.OK){
			int status =patientDao.updatePatient(patient);
		   	
		   	if(status!=0) {
		   		
		   		
		   		Alert alert=new Alert(AlertType.INFORMATION);
		   		alert.setTitle("Modifier Patient ");
		   		alert.setHeaderText("information");
		   		alert.setContentText("Patient Modifié ");
		   		LoadTable();
		   		tabPane.getSelectionModel().select(PatientTabPane);
		   		updateGridBtn.setVisible(false); // Hide upate and Cancel Btns
		   		insertBtn.setVisible(true);
		   		UpdateTabPane.setText("Ajouter");
		   		alert.showAndWait();		
		   	}
		   	else {
		   		Alert alert=new Alert(AlertType.ERROR);
		   		alert.setTitle("Modifier Patient ");
		   		alert.setHeaderText("information");
		   		alert.setContentText("Patient n'est pas modifié correctement");
		   		alert.showAndWait();
		   	}
		} else {
		    confirmeAlert.close();
		}
  	
		setLitIconColor();
   }
   
   @FXML
   public void goToPatientTabPane() {

	   tabPane.getSelectionModel().select(PatientTabPane);
	   updateGridBtn.setVisible(false);
	   insertBtn.setVisible(true);
	   UpdateTabPane.setText("Ajouter");
   }

   
   public void populateLitMenuList() throws Exception{
	   
//	   LoadTable() ;
//	   int n = list.size();
//	   litMenuBtn.getItems().clear();
////	   Integer[] tabLits = new Integer[n];
//	   List<Integer> listLits = new ArrayList<Integer>();
//	   for(int i=0; i<n; i++) {
////		   MenuItem tempItem = new MenuItem();
//		   listLits.add(list.get(i).getLit().getId());
//	   }
	   litMenuBtn.getItems().clear();
	   for(int i=1; i<=24; i++) {
		       final int j =i;
			   MenuItem tempItem = new MenuItem();
			   tempItem.setText(i+"");
			   tempItem.setOnAction(e -> litMenuBtn.setText(""+j));
			   litMenuBtn.getItems().add(tempItem);
			   	   
	   }
	     
   }
   

   
   
   // +++++++++++++++++++++++++++++++++++++++ LIT TABPANE +++++++++++++++++++++++++++++++++++
   
   @FXML Button lit1 = new Button();
   @FXML Button lit2 = new Button();
   @FXML Button lit3 = new Button();
   @FXML Button lit4 = new Button();
   @FXML Button lit5 = new Button();
   @FXML Button lit6 = new Button();
   @FXML Button lit7 = new Button();
   @FXML Button lit8 = new Button();
   @FXML Button lit9 = new Button();
   @FXML Button lit10 = new Button();
   @FXML Button lit11 = new Button();
   @FXML Button lit12 = new Button();
   @FXML Button lit13 = new Button();
   @FXML Button lit14 = new Button();
   @FXML Button lit15 = new Button();
   @FXML Button lit16 = new Button();
   @FXML Button lit17 = new Button();
   @FXML Button lit18 = new Button();
   @FXML Button lit19 = new Button();
   @FXML Button lit20 = new Button();
   @FXML Button lit21 = new Button();
   @FXML Button lit22 = new Button();
   @FXML Button lit23 = new Button();
   @FXML Button lit24  = new Button();
   
   public Button litBtns[];
   
   @FXML 
   public void litBtnsAppend() {
	   // Append all btns in the list litBtns
	   Button templitBtns[] = {lit1 ,lit2 ,lit3 ,lit4 ,lit5 ,lit6 ,lit7 ,lit8 ,lit9 ,lit10 ,lit11 ,lit12
			   ,lit13 ,lit14 ,lit15 ,lit16 ,lit17 ,lit18 ,lit19 ,lit20 ,lit21 ,lit22 ,lit23 ,lit24};
	   litBtns = templitBtns;
   }
   
   ObservableList<Integer> LitExistedIds = FXCollections.observableArrayList();
   //put the existed Lit in a list LitExistedIds
 
   
   
   @FXML
   public void openLitPanel(ActionEvent event) throws Exception{
	    Button b = (Button) event.getSource();
		int litId = Integer.parseInt(b.getText());
		
		LitDaoImpl litDao = new LitDaoImpl();
		litDao.searchLitById(litId);
		
		PatientDaoImpl pDao = new PatientDaoImpl();
		
        p = pDao.searchPatientByLit(litId);
//		System.out.println("CLICK : "+p.getNom());
		
		
	    
		
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("LitPanel.fxml"));
//	    AnchorPane pane=FXMLLoader.load(getClass().getResource("LitPanel.fxml"));
		
		Parent pane = (Parent) loader.load();
		LitPanelController litPanel = loader.getController();
//		litPanel.setLit(p.getLit().getId());
		litPanel.setUpdateInputList(p);
		
		Scene scene=new Scene(pane);

		stage.setScene(scene);
		stage.setX(500);
		stage.setY(150);
		stage.show();
		
//		
//		
			
   }
   
   
   @FXML
   void exit(ActionEvent event) throws Exception {
	   AnchorPane pane=FXMLLoader.load(getClass().getResource("Sample.fxml"));
		Scene scene=new Scene(pane);
		Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
   }
   
   private void setLitIconColor() {
	   PatientDaoImpl dao = new PatientDaoImpl();
	   litBtnsAppend();
	   for(int i=0 ; i<24 ; i++) {
		   
		   try {
				dao.searchPatientByLit(i+1);
				litBtns[i].setDisable(false);
				litBtns[i].getStyleClass().add("bedReserved");
			}
		   catch (Exception e) {
			   litBtns[i].getStyleClass().add("bedNotReserved");
			   litBtns[i].setDisable(true);
			}
		   
	   }
	   
   }

// ------------------------------- FIN -------------------------------- 
}
