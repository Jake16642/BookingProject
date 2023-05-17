package application;
	
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GuestLogin extends Application {
	
	private static Connection connection;
	static final String QUERY = "SELECT * FROM guestLogin WHERE username=? AND password=?";
	@FXML
	private Button SignInButton;
	@FXML
	private Button RegisterButton;
	@FXML
	private TextField user;
	@FXML
	private PasswordField pass;
	@FXML
	private Text line;
	@FXML
	private Button BackButton;

	@FXML
	private void handleBackButton(ActionEvent event) {
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("login.fxml"));
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
	private void handleSignInButton(ActionEvent event) throws Exception {
		ConnectSQL();
		
		if (userExists(user.getText(), pass.getText())) {
			Stage primaryStage = (Stage) SignInButton.getScene().getWindow();
			primaryStage.close();

		    Stage adminPageStage = new Stage();
		    Parent root = FXMLLoader.load(getClass().getResource("/application/guestpage.fxml"));
		    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    adminPageStage.setScene(scene);
		    adminPageStage.show();
		        
	    } else {
	    	line.setText("Login Failed");
	    }
	}
	
	public static void ConnectSQL() {
		try {
			connection =
			DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "5alasomot");
			System.out.println("Connected With the database successfully");
		} catch (SQLException e) {
			System.out.println("Error while connecting to the database");
			System.out.println(e);
		}
	}
	
	public boolean userExists(String username, String password) throws SQLException {
	    PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
	    // Setting values for Each Parameter
	    preparedStatement.setString(1, username);
	    preparedStatement.setString(2, password);
	    ResultSet rs = preparedStatement.executeQuery();

	    // display result
	    while (rs.next()) {
	    	System.out.println(rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3));
	    	return true;
	    }

	    return false;
	  }
	
	@FXML
	private void handleRegisterButton(ActionEvent event) {
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("guestregister.fxml"));
	        Scene scene = new Scene(root1);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();
	        
	        Stage primaryStage = (Stage) RegisterButton.getScene().getWindow();
            primaryStage.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("guestlogin.fxml"));
		primaryStage.setTitle("JATISOFT");
		primaryStage.setScene(new Scene(root, 550, 500));
		primaryStage.show();
		
		RegisterButton = (Button) root.lookup("#RegisterButton");
		RegisterButton.setOnAction(event -> {
			handleRegisterButton(event);
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