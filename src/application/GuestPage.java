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

public class GuestPage extends Application {
	
	@FXML
	private Button SignOutButton;
	@FXML
	private Button DashBoardButton;
	
	@FXML
	private void handleDashBoardButton(ActionEvent event) {
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
	        Scene scene = new Scene(root1);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();
	        
	        Stage primaryStage = (Stage) DashBoardButton.getScene().getWindow();
            primaryStage.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@FXML
	private void handleSignOutButton(ActionEvent event) {
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("login.fxml"));
	        Scene scene = new Scene(root1);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();
	        
	        Stage primaryStage = (Stage) SignOutButton.getScene().getWindow();
            primaryStage.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("guestpage.fxml"));
		primaryStage.setTitle("Hello World");
		primaryStage.setScene(new Scene(root, 600, 400));
		primaryStage.show();
		
		SignOutButton = (Button) root.lookup("#SignOutButton");
		SignOutButton.setOnAction(event -> {
			handleSignOutButton(event);
        });
		
		DashBoardButton = (Button) root.lookup("#DashBoardButton");
		DashBoardButton.setOnAction(event -> {
			handleDashBoardButton(event);
        });
	}

	public static void main(String[] args) {
		launch(args);
	}
}
  

