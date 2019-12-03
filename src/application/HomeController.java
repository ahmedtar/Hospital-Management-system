package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HomeController implements Initializable{ 
	
	 @FXML
		public void exit(ActionEvent event) throws IOException {
			AnchorPane pane=FXMLLoader.load(getClass().getResource("Sample.fxml"));
			Scene scene=new Scene(pane);
			Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}
	 @FXML
		public void changeToPatient(ActionEvent event) throws IOException {
			AnchorPane pane=FXMLLoader.load(getClass().getResource("Patient.fxml"));
			Scene scene=new Scene(pane);
			Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}

		@FXML
		
		public void changeToDoctor(ActionEvent event) throws IOException {
			AnchorPane pane=FXMLLoader.load(getClass().getResource("Medecin.fxml"));
			Scene scene=new Scene(pane);
			Stage stage=(Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(scene);
			stage.show();
		}
	 
	 @FXML
	 private AnchorPane pane1,pane2,pane3,pane4;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pane1.setStyle("-fx-background-image: url(\"/application/imgs/1.jpg\")" ); 
		pane2.setStyle("-fx-background-image: url(\"/application/imgs/10.jpg\")" );
		pane3.setStyle("-fx-background-image: url(\"/application/imgs/5.jpg\")" );
		pane4.setStyle("-fx-background-image: url(\"/application/imgs/11.jpg\")" );
		Animation();
		
	}
	public  void  Animation(){
        FadeTransition fadeTransition=new FadeTransition(Duration.seconds(3),pane4);
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.play();

        fadeTransition.setOnFinished(event -> {
            FadeTransition fadeTransition1=new FadeTransition(Duration.seconds(30),pane3);
            fadeTransition1.setFromValue(1);
            fadeTransition1.setToValue(0);
            fadeTransition1.play();

            fadeTransition1.setOnFinished(event1 -> {

                FadeTransition fadeTransition2=new FadeTransition(Duration.seconds(3),pane2);
                fadeTransition2.setFromValue(1);
                fadeTransition2.setToValue(0);
                fadeTransition2.play();

                fadeTransition2.setOnFinished(event2 -> {

                    FadeTransition fadeTransition00=new FadeTransition(Duration.seconds(3),pane2);
                    fadeTransition00.setFromValue(0);
                    fadeTransition00.setToValue(1);
                    fadeTransition00.play();


                    fadeTransition00.setOnFinished(event3 -> {
                        FadeTransition fadeTransition11=new FadeTransition(Duration.seconds(3),pane3);
                        fadeTransition11.setFromValue(0);
                        fadeTransition11.setToValue(1);
                        fadeTransition11.play();

                        fadeTransition11.setOnFinished(event4 -> {
                            FadeTransition fadeTransition22=new FadeTransition(Duration.seconds(3),pane4);
                            fadeTransition22.setFromValue(0);
                            fadeTransition22.setToValue(1);
                            fadeTransition22.play();

                            fadeTransition22.setOnFinished(event5 -> {
                                Animation();
                            });
                        });
                    });
                });
            });
        });
    }

}
