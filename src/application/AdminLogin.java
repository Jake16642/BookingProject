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

public class AdminLogin extends Application {
	
	private static Connection connection;
	static final String QUERY = "SELECT * FROM adminLogin WHERE username=? AND password=?";
	@FXML
	private Button SignInButton;
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
	public void start(Stage primaryStage) throws Exception {
		
		Parent root = FXMLLoader.load(getClass().getResource("adminlogin.fxml"));
		primaryStage.setTitle("JATISOFT");
		primaryStage.setScene(new Scene(root, 500, 500));
		primaryStage.show();
		
		BackButton = (Button) root.lookup("#BackButton");
        BackButton.setOnAction(event -> {
        	handleBackButton(event);
        });
	}
	
	@FXML
	private void handleSignInButton(ActionEvent event)  throws Exception {
		ConnectSQL();
		
		if (userExists(user.getText(), pass.getText())) {
			Stage primaryStage = (Stage) SignInButton.getScene().getWindow();
			primaryStage.close();

		    Stage adminPageStage = new Stage();
		    Parent root = FXMLLoader.load(getClass().getResource("/application/adminpage.fxml"));
		    Scene scene = new Scene(root);
		    scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		    adminPageStage.setScene(scene);
		    adminPageStage.show();
		        
			/*Stage Stage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("/application/adminpage.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage.setScene(scene);
			Stage.show();*/
			
			/*Stage primaryStage = (Stage) SignInButton.getScene().getWindow();
            primaryStage.close();*/
	    } else {
	    	line.setText("Login Failed");
	    }
	}
	
	public static void main(String[] args) {
		launch(args);
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
	      //System.out.println(rs.getString(1) + "|" + rs.getString(2) + "|" + rs.getString(3));
	      return true;
	    }

	    return false;
	  }
}