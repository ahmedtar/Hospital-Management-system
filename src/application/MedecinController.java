package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


import daoImpl.MedecinDAOimpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Medecin;
import model.Patient;

public class MedecinController implements Initializable{
	
	//les colonne de tableView----------------------------------------------
	    @FXML private TableView<Medecin> tableView;
	    @FXML private TableColumn<Medecin,String > cneCol;
	    @FXML  private TableColumn<Medecin,String> nomCol;
	    @FXML  private TableColumn<Medecin,String > prenomCol;
	    @FXML private TableColumn<Medecin,String > sexeCol;
	    @FXML  private TableColumn<Medecin,Integer > AgeCol;
	    @FXML  private TableColumn<Medecin,String > numCol;
	    @FXML private TableColumn<Medecin,String > adressCol;
	    @FXML private TableColumn<Medecin,String > specialCol;
	    @FXML private TableColumn<Medecin,Integer> departCol;
	    @FXML  private TableColumn<Medecin,Boolean > enServiceCol;
	    @FXML private TableColumn<Medecin,String> deleteCol;

	    
	    
	    ///les textField de tab modifier------------------------------------
	    @FXML private TextField cneField1;
	    @FXML private TextField nomField1;
	    @FXML private TextField prenomField1;
	    @FXML private TextField ageField1;
	    @FXML private TextField numField1;
	    @FXML private TextField adressField1;
	    @FXML private TextField specialisayionField1;
	    @FXML private RadioButton homme1;
	    @FXML private RadioButton femme1;
	    @FXML private RadioButton enService1;
	    @FXML private RadioButton horsService1;
	    
	    
	  ///les textField de tab ajouter------------------------------------

	    @FXML private TextField cneField2;
	    @FXML private TextField nomField2;
	    @FXML private TextField prenomField2;
	    @FXML private TextField ageField2;
	    @FXML private TextField numField2;
	    @FXML private TextField adressField2;
	    @FXML private TextField specialisationField2;
	    @FXML private RadioButton homme2;
	    @FXML private RadioButton femme2;
	    @FXML private RadioButton enService2;
	    @FXML private RadioButton horsService2;


	    @FXML private TabPane tabPane;
	    @FXML private Tab medecinTab;
	    @FXML private Tab modifierTab;
	    @FXML private Tab ajouterTab;

	    @FXML private TextField searchField;
	    private MedecinDAOimpl medecinDao;
	    
	    private ObservableList<Medecin> medecinlist;
	    private static int id0;
	    
	    
	    
	  
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		populateTableView();
	}
	
	public void populateTableView() {
		List<Medecin> list=new ArrayList<>();
		this.medecinDao =new MedecinDAOimpl();
		list=medecinDao.getAllMdcs();
		this.medecinlist=FXCollections.observableArrayList(list);
		
		cneCol.setCellValueFactory(new PropertyValueFactory<>("cne"));
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		sexeCol.setCellValueFactory(new PropertyValueFactory<>("sex"));
		AgeCol.setCellValueFactory(new PropertyValueFactory<>("age"));
		numCol.setCellValueFactory(new PropertyValueFactory<>("numTel"));
		adressCol.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		specialCol.setCellValueFactory(new PropertyValueFactory<>("specialisation"));
		enServiceCol.setCellValueFactory(new PropertyValueFactory<>("enservice"));
		
		
		
		//update button in table rows for update employee----------------------------------------------------------------------------------------------------------------
		
				Callback<TableColumn<Medecin, String>, TableCell<Medecin, String>> cellFactory =
						new Callback<TableColumn<Medecin, String>, TableCell<Medecin, String>>() {
		            public TableCell<Medecin, String> call(final TableColumn<Medecin, String> param) {
		                final TableCell<Medecin, String> cell = new TableCell<Medecin, String>() {
		                    @Override
		                    public void updateItem(String item, boolean empty) {
		                        super.updateItem(item, empty);
		                        if (empty) {
		                            setGraphic(null); 
		                            setText(null);
		                        } else {
		                        	  final Button update = new Button("Edit");
		                        	  final Button delete = new Button("X");
		                        	  
		                        	  update.setOnAction(event -> {
		                        		  Medecin medecin=getTableView().getItems().get(getIndex());
		                        		  
		                        		  id0=medecin.getId();
		                        		  
		                        		  String cne=medecin.getCne();
		                        		  String nom=medecin.getNom();
		                        		  String prenom=medecin.getPrenom();
		                        		
		                        		  int age=medecin.getAge();
		                        		  String numTel=medecin.getNumTel();
		                        		  String adress=medecin.getAdresse();
		                        		  String specialite=medecin.getSpecialisation();
		                        		
		                        		 
		                        		 String age1=Integer.toString(age);
		                        		
		                        		
		                        		 
		                        		 cneField1.setText(cne);
		                        		 nomField1.setText(nom);
		                        		 prenomField1.setText(prenom);
		                        		
		                        		 ageField1.setText(age1);
		                        		 numField1.setText(numTel);
		                        		 adressField1.setText(adress);
		                        		 specialisayionField1.setText(specialite);
		                        		
		                        		 tabPane.getSelectionModel().select(modifierTab);
		                        		 
		                        		
		                        	  });
		                        	  delete.setOnAction(event -> {
			                        		Medecin medecin=getTableView().getItems().get(getIndex());
			                        		int status=medecinDao.deleteMedecin(medecin);
			                        		medecinlist.remove(medecin);
			                        		if(status>0) {
			                      	    		Alert alert=new Alert(AlertType.INFORMATION);
			                      	    		alert.setTitle("supprimer medecin ");
			                      	    		alert.setContentText("medecin est supprime");
			                      	    		alert.showAndWait();
			                      	    		
			                      	    	}
			                      	    	else {
			                      	    		Alert alert=new Alert(AlertType.ERROR);
			                      	    		alert.setTitle("supprimer medecin ");
			                      	    		alert.setContentText("medecin n'est pas suppriome");
			                      	    		alert.showAndWait();
			                      	    	}
			                      	    	
			                      	    	
			                        	  });
		                        	  
		                        	  HBox editCell = new HBox(15);
		   			    		      editCell.getChildren().addAll(update , delete);
		   			    		      
		                        	 setGraphic(editCell);
		                             setText(null);
		                        }
		                    }
		                    
		                };
		                return cell;
		            }
		        };
		        
		        
	    
		deleteCol.setCellFactory(cellFactory);
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
		List<Medecin> list=new ArrayList<>();
		this.medecinDao =new MedecinDAOimpl();
		list=medecinDao.getAllMdcs();
		this.medecinlist=FXCollections.observableArrayList(list);
		tableView.setItems(medecinlist);
	}
	
	@FXML
    void searchMedecin(KeyEvent event) {
		List<Medecin> list=new ArrayList<>();
	    this.medecinDao=new MedecinDAOimpl();
	    list=medecinDao.searchMdc(searchField.getText());
	    this.medecinlist=FXCollections.observableArrayList(list);
	    tableView.setItems(medecinlist);
    }
	
	@FXML
	public void clearSearchField()throws Exception {
		searchField.setText("");
		LoadTable();
	}
	
	@FXML
	public void insertLoad(Medecin medecin) throws Exception {
		List<Medecin> list=new ArrayList<>();
		list.add(medecin);
		this.medecinlist=FXCollections.observableArrayList(list);
		tableView.setItems(null);
		tableView.setItems(medecinlist);
		System.out.println("check 1111");
		LoadTable();
	    tableView.refresh();
		System.out.println("check 22222");
	}


	
	
	
	

	
	@FXML
	public void update(ActionEvent e) throws Exception {
		medecinDao=new MedecinDAOimpl();
		
		String sexe=null;
		  
		String cne=cneField1.getText();
		String nom=nomField1.getText();
		String prenom=prenomField1.getText();
		String age=ageField1.getText();
		String numTel= numField1.getText();
		String adress=adressField1.getText();
		String special=specialisayionField1.getText();
		
		
		int age0=Integer.parseInt(age);
	
		boolean serv=false;
		
		if(homme1.isSelected()) sexe="homme";
		if(femme1.isSelected()) sexe="femme";
		
		if(enService1.isSelected())serv=true;
		if(horsService1.isSelected())serv=false;
		
		Medecin med=new Medecin(id0,nom,prenom,cne,sexe,age0,numTel,adress,special,serv);
		
		int status=medecinDao.updateMedecin(med);
		
		if(status>0) {
			tabPane.getSelectionModel().select(medecinTab);
			LoadTable();
	    		Alert alert=new Alert(AlertType.INFORMATION);
	    		alert.setTitle("modifier medecin ");
	    		alert.setContentText("medecin est modifier");
	    		alert.showAndWait();
	    		
	    	}
	    	else {
	    		Alert alert=new Alert(AlertType.ERROR);
	    		alert.setTitle("modifier medecin ");
	    		alert.setContentText("medecin n'est pas modifie");
	    		alert.showAndWait();
	    	}
		
	}
	
	 @FXML
	    void insert(ActionEvent event) throws Exception {
		 medecinDao=new MedecinDAOimpl();
		 boolean serv=false;
		 String age0=ageField2.getText();
		 String sexe=null;
		 
			String cne=cneField2.getText();
			String nom=nomField2.getText();
			String prenom=prenomField2.getText();
			int age=Integer.parseInt(age0);
			String numTel= numField2.getText();
			String adress=adressField2.getText();
			String special=specialisationField2.getText();

			if(homme2.isSelected()) sexe="homme";
			if(femme2.isSelected()) sexe="femme";
			
			if(enService2.isSelected())serv=true;
			if(horsService2.isSelected())serv=false;
			
			
			
			
			
			Medecin medecin=new Medecin(0,nom,prenom,cne,sexe,age,numTel,adress,special,serv);
			
			
			
			int status=medecinDao.addMedecin(medecin);
			if(status>0) {
				tabPane.getSelectionModel().select(medecinTab);
				this.insertLoad(medecin);
	    		Alert alert=new Alert(AlertType.INFORMATION);
	    		alert.setTitle("ajouter medecin ");
	    		alert.setContentText("medecin est ahoute");
	    		alert.showAndWait();
	    		
	    	}
	    	else {
	    		Alert alert=new Alert(AlertType.ERROR);
	    		alert.setTitle("ajouter medecin ");
	    		alert.setContentText("medecin n'est pas ajoute");
	    		alert.showAndWait();
	    	}

	    }
	
	 
	  @FXML
	    void goToAjouter(ActionEvent event) {
		  tabPane.getSelectionModel().select(ajouterTab);
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
	
	
	
	

	
	
	
	
	
}
