package application;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class DashboardController extends Application {
	
	@FXML
	private Button bookingButton;
	@FXML
	private Button BackButton;

	@FXML
	private void handleBackButton(ActionEvent event) {
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("guestpage.fxml"));
	        Scene scene = new Scene(root1);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();
	        
	        Stage primaryStage = (Stage) BackButton.getScene().getWindow();
            primaryStage.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
	@FXML
	private void handleBookingButton(ActionEvent event) {
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("datebooking.fxml"));
	        Scene scene = new Scene(root1);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();
	        
	        Stage primaryStage = (Stage) bookingButton.getScene().getWindow();
            primaryStage.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
		primaryStage.setTitle("JATISOFT");
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();
		
		bookingButton = (Button) root.lookup("#bookingButton");
        bookingButton.setOnAction(event -> {
            handleBookingButton(event);
        });
        
        BackButton = (Button) root.lookup("#BackButton");
        BackButton.setOnAction(event -> {
        	handleBackButton(event);
        });
	}

	public static void main(String[] args) {
		launch(args);
	}
}
  

