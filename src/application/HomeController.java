package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.text.StyledEditorKit.BoldAction;

import daoImpl.PatientDaoImpl;
import daoImpl.UtilisateurDaoImpl;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Utilisateur;

public class HomeController implements Initializable{ 
	
	private static String pass0;
    private UtilisateurDaoImpl userDao;
	
    public Boolean admin;
    
    @FXML Button recepBtn = new Button();
    @FXML Button medecinBtn = new Button();
    @FXML Button patientBtn = new Button();
    @FXML Button chambreBtn = new Button();
    
    @FXML Label recepLabel = new Label();
    @FXML Label medecinLabel = new Label();
    @FXML Label patientLabel = new Label();
    @FXML Label chambreLabel = new Label();
    
    @FXML Label adminLabel = new Label();
	
	public void getPass(String pass) {
		pass0=pass;
    }
	
	
	 @FXML
	    void goToUser(ActionEvent event) throws Exception {
		 FXMLLoader loader=new FXMLLoader(getClass().getResource("Utilisateur.fxml"));
 		AnchorPane pane =(AnchorPane) loader.load();
 		
 		userDao=new UtilisateurDaoImpl();
 		UtilisateurController userContr=loader.getController();
	    userContr.getUser(userDao.getUserByPassword(pass0));
	    
			Scene scene=new Scene(pane);
			Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
			
			
			stage.setScene(scene);
			stage.setX(180);
			stage.setY(50);
			stage.show(); 

	    }
	 
	 @FXML
		public void exit(ActionEvent event) throws IOException {
			AnchorPane pane=FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene=new Scene(pane);
			Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setX(400);
			stage.setY(50);
			stage.show();
		}
	 @FXML
		public void changeToPatient(ActionEvent event) throws IOException {
			AnchorPane pane=FXMLLoader.load(getClass().getResource("Patient.fxml"));
			Scene scene=new Scene(pane);
			Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setX(180);
			stage.setY(50);
			stage.show();
		}

		@FXML
		
		public void changeToDoctor(ActionEvent event) throws IOException {
			AnchorPane pane=FXMLLoader.load(getClass().getResource("Medecin.fxml"));
			Scene scene=new Scene(pane);
			Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.setX(180);
			stage.setY(50);
			stage.show();
		}
		
		@FXML 
		void changeToChambre(ActionEvent event) throws IOException{
			PatientDaoImpl p = new PatientDaoImpl();
//			System.out.println("CLICK : "+p.getNom());
			
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Patient.fxml"));
//		    AnchorPane pane=FXMLLoader.load(getClass().getResource("LitPanel.fxml"));
			
			Parent pane = (Parent) loader.load();
			PatientController pCtrl = loader.getController();
//			litPanel.setLit(p.getLit().getId());
			pCtrl.tabPane.getSelectionModel().select(pCtrl.LitTabPane);
			
			Scene scene=new Scene(pane);

			stage.setScene(scene);
			stage.setX(180);
			stage.setY(50);
			stage.show();
		}
		
	 @FXML
	 private AnchorPane pane1,pane2,pane3,pane4;
	 
	 
	 public void hideIcons() {
		 recepLabel.setVisible(false);
		 medecinLabel.setVisible(false);
		 recepBtn.setVisible(false);
		 medecinBtn.setVisible(false);
		 adminLabel.setText("RECEPTIONNISTE");
	 }
	 
	
	 public void showIcons() {
		 recepLabel.setVisible(true);
		 medecinLabel.setVisible(true);
		 recepBtn.setVisible(true);
		 medecinBtn.setVisible(true);
		 adminLabel.setText("ADMIN");
	 }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	//	pane1.setStyle("-fx-background-image: url(\"/application/imgs/1.jpg\")" ); 
		pane2.setStyle("-fx-background-image: url(\"/application/imgs/7972.jpg\")" );
		
	}
	
}
