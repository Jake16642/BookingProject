package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class BookingPage3 extends Application{
	
	private static Connection connection;
	private static Guest guest;
	private static Address address;
	private static final String SQL_INSERT1 = "INSERT INTO guest(pf, fname, mname, lname, phone, email) VALUES(?,?,?,?,?,?)";
	private static final String SQL_INSERT2 = "INSERT INTO address(country, ad1, ad2, cty, zipcode, detail) VALUES(?,?,?,?,?,?)";
	
	@FXML
	private TextField prfix;
	@FXML
	private TextField fname;
	@FXML
	private TextField mname;
	@FXML
	private TextField lname;
	@FXML
	private TextField phone;
	@FXML
	private TextField email;
	@FXML
	private TextField country;
	@FXML
	private TextField adress1;
	@FXML
	private TextField address2;
	@FXML
	private TextField city;
	@FXML
	private TextField zip;
	@FXML
	private TextArea detail;
	@FXML
	private Button InfoButton;
	@FXML
	private Text line;
	
	/*@FXML
	 public void SaveButtonAction() { 
		String prefix = prfix.getText();
		String firstName = fname.getText();
		String middleInitial = mname.getText();
		String lastName = lname.getText();
		String phoneNumber = phone.getText();
		String emailAddress = email.getText();
		guest = new Guest(prefix, firstName, middleInitial, lastName, phoneNumber, emailAddress);	
		
		String country1 = country.getText();
		String ad1 = adress1.getText();
		String ad2 = address2.getText();
		String cty = city.getText();
		String zipCode = zip.getText();
		String additionalDetails = detail.getText();
		address = new Address(country1, ad1, ad2, cty, zipCode, additionalDetails);
		ConnectSQL();
		line.setText("Saved");
	}*/
	
	public static void ConnectSQL() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "5alasomot");
            System.out.println("Connected With the database successfully");
            // Creating PreparedStatement object
            PreparedStatement preparedStatement1 = connection.prepareStatement(SQL_INSERT1);
            PreparedStatement preparedStatement2 = connection.prepareStatement(SQL_INSERT2);
            // Setting values for Each Parameter
            preparedStatement1.setString(1, guest.getPrefix());
            preparedStatement1.setString(2, guest.getFirstName());
            preparedStatement1.setString(3, guest.getMiddleInitial());
            preparedStatement1.setString(4, guest.getLastName());
            preparedStatement1.setString(5, guest.getPhoneNumber());
            preparedStatement1.setString(6, guest.getEmailAddress());
           
            preparedStatement2.setString(1, address.getCountry());
            preparedStatement2.setString(2, address.getAddress1());
            preparedStatement2.setString(3, address.getAddress2());
            preparedStatement2.setString(4, address.getCity());
            preparedStatement2.setString(5, address.getZipCode());
            preparedStatement2.setString(6, address.getAdditionalDetails());
            
            int row1 = preparedStatement1.executeUpdate();
            int row2 = preparedStatement2.executeUpdate();
            // rows affected
            System.out.println(row1); // 1
            System.out.println(row2);
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database");
            System.out.println(e);
        }
    }
	
	@FXML
	private void handleInfoButton(ActionEvent event) {
		String country1 = country.getText();
		String ad1 = adress1.getText();
		String ad2 = address2.getText();
		String cty = city.getText();
		String zipCode = zip.getText();
		String additionalDetails = detail.getText();
		address = new Address(country1, ad1, ad2, cty, zipCode, additionalDetails);
		
		String prefix = prfix.getText();
		String firstName = fname.getText();
		String middleInitial = mname.getText();
		String lastName = lname.getText();
		String phoneNumber = phone.getText();
		String emailAddress = email.getText();
		guest = new Guest(prefix, firstName, middleInitial, lastName, phoneNumber, emailAddress, address);	

		if(prefix.isEmpty() || firstName.isEmpty() || middleInitial.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || emailAddress.isEmpty()
				|| country1.isEmpty() || ad1.isEmpty() || ad2.isEmpty() || cty.isEmpty() || zipCode.isEmpty() || additionalDetails.isEmpty()) {
			line.setText("Please enter the information above!");
		}
		else {
			ConnectSQL();
		
			Stage primaryStage = (Stage) InfoButton.getScene().getWindow();
			primaryStage.close();
			try {
				Parent root1 = FXMLLoader.load(getClass().getResource("confirmation.fxml"));
				Scene scene = new Scene(root1);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show();
	        
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("guestinfo.fxml"));
		primaryStage.setTitle("JATISOFT");
		primaryStage.setScene(new Scene(root, 600, 500));
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
