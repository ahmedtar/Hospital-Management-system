package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import daoImpl.UtilisateurDaoImpl;
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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
import model.Utilisateur;

public class UtilisateurController implements Initializable{

	
	    @FXML private TabPane tabPane;
	    @FXML private Tab infoTab;
	    @FXML private Tab userTab;
	    @FXML private Tab modifTab;
	    @FXML private Tab insertTab;

	    @FXML private TableView<Utilisateur> tableView;
	    @FXML private TableColumn<Utilisateur,String> loginCol;
	    @FXML private TableColumn<Utilisateur,String> nomCol;
	    @FXML private TableColumn<Utilisateur,String> prenomCol;
	    @FXML private TableColumn<Utilisateur,String> updateCol;

	    

	    @FXML private TextField loginField;
	    @FXML private TextField nomField;
	    @FXML private TextField prenomField;
	    @FXML private TextField passwordField;
	    
	    
	    @FXML private TextField loginField1;
	    @FXML private TextField nomField1;
	    @FXML private TextField prenomField1;
	    @FXML private TextField passwordField1;

	    
	    @FXML private Label nomLabel;
	    @FXML private Label prenomLabel;
	    @FXML private Label actifLabel;
	    @FXML private Label loginLabel;

	    
	    private UtilisateurDaoImpl utilisateurDao;
	    private ObservableList<Utilisateur> userList;
	    private static int id0;
	    
	    
	    public void getUser(Utilisateur user) {
	    	user.setEstActif(true);
	    	loginLabel.setText(user.getLogin());
	    	nomLabel.setText(user.getNom());
	    	prenomLabel.setText(user.getPrenom());
	    	String str="";
	    	if(user.EstActif()) str="actif";
	    	else str="n'est pas actif";
	    	actifLabel.setText(str);
	    }
	    
	    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			populateTableView();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void populateTableView() throws Exception {		
		
		loginCol.setCellValueFactory(new PropertyValueFactory<>("login"));
		nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
		prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
		
		
		//update button in table rows----------------------------------------------------------------------------------------------------------------
		
		Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFactory =
				new Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>>() {
            public TableCell<Utilisateur, String> call(final TableColumn<Utilisateur, String> param) {
                final TableCell<Utilisateur, String> cell = new TableCell<Utilisateur, String>() {
                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null); 
                            setText(null);
                        } else {
                        	  final Button update = new Button("MODIFIER");
                        	  
                        	  
                        	  update.setOnAction(event -> {
                        		 Utilisateur user=getTableView().getItems().get(getIndex());
                        		 loginField.setText(user.getLogin());
                        		 nomField.setText(user.getNom());
                        		 prenomField.setText(user.getPrenom());
                        		 passwordField.setText(user.getPassword());
                        		 id0=user.getId();
                        		 tabPane.getSelectionModel().select(modifTab);
                        	  });
                        	  
                        	  
                        	  HBox editCell = new HBox(15);
   			    		      editCell.getChildren().addAll(update);
   			    		      
                        	 setGraphic(editCell);
                             setText(null);
                        }
                    }
                    
                };
                return cell;
            }
        };
        updateCol.setCellFactory(cellFactory);
        
		
			this.LoadTable();
			
		
		
	}
	
	@FXML
	public void LoadTable() throws Exception {
		this.utilisateurDao=new UtilisateurDaoImpl();
		List<Utilisateur> list=new ArrayList<Utilisateur>();
		list=utilisateurDao.getAllUtilisateurs();
		this.userList=FXCollections.observableArrayList(list);
		tableView.setItems(userList);
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
	    void goToInsert(ActionEvent event) {
		  tabPane.getSelectionModel().select(insertTab);
	    }
	  

	  
	  @FXML
	    void update(ActionEvent event) throws Exception {
		  utilisateurDao=new UtilisateurDaoImpl();
		  Utilisateur user=new Utilisateur();
		  user.setId(id0);
		  user.setLogin(loginField.getText());
		  user.setNom(nomField.getText());
		  user.setPrenom(prenomField.getText());
		  user.setPassword(passwordField.getText());
		  int status=utilisateurDao.updateUtilisateur(user);
		  
		  if(status>0) {
				tabPane.getSelectionModel().select(userTab);
				LoadTable();
		    		Alert alert=new Alert(AlertType.INFORMATION);
		    		alert.setTitle("modifier utilisateur ");
		    		alert.setContentText("utilisateur est modifier");
		    		alert.showAndWait();
		    		
		    	}
		    	else {
		    		Alert alert=new Alert(AlertType.ERROR);
		    		alert.setTitle("modifier utilisateur ");
		    		alert.setContentText("utilisateur n'est pas modifie");
		    		alert.showAndWait();
		    	}
				  

	    }
	  
	  @FXML
	    void insert(ActionEvent event) throws Exception {
		  utilisateurDao=new UtilisateurDaoImpl();
		  Utilisateur user=new Utilisateur();
		 
		  user.setLogin(loginField1.getText());
		  user.setNom(nomField1.getText());
		  user.setPrenom(prenomField1.getText());
		  user.setPassword(passwordField1.getText());
		  int status=utilisateurDao.AddUtilisateur(user);
		  if(status>0) {
				tabPane.getSelectionModel().select(userTab);
				LoadTable();
		    		Alert alert=new Alert(AlertType.INFORMATION);
		    		alert.setTitle("ajouter utilisateur ");
		    		alert.setContentText("utilisateur est ajoute");
		    		alert.showAndWait();
		    		
		    	}
		    	else {
		    		Alert alert=new Alert(AlertType.ERROR);
		    		alert.setTitle("ajouter utilisateur ");
		    		alert.setContentText("utilisateur n'est pas ajoute");
		    		alert.showAndWait();
		    	}
		  

	    }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
}
