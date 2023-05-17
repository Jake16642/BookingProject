package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BookingPage4 extends Application{
	
	static final String QUERY = "SELECT * FROM guest";
	static final String QUERY2 = "SELECT * FROM address";
	static final String QUERY3 = "SELECT * FROM roominfo";
	static final String QUERY4 = "SELECT * FROM datebooking";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "5alasomot";
	@FXML
	private Button FinishButton;
	@FXML
    private Label dates;

    @FXML
    private Button fButton;

    @FXML
    private Text guestInfo;

    @FXML
    private Label peopleNum;

    @FXML
    private Text roomInfo;
    
    @FXML
    private Text addressInfo;

    @FXML
    private Label totalPrice;
    
    public void initialize() throws SQLException {
    	
        Address address1 = getAddress(retrieveData(QUERY2));
    	
    	Guest guest1 = getGuestInfo(retrieveData(QUERY));
    	
        Room room1 = getRoom(retrieveData(QUERY3));
    	
        LocalDate checkIn = LocalDate.of(2023, 5, 16);
        LocalDate checkOut = LocalDate.of(2023, 5, 20);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
		String checkInformattedDate = checkIn.format(formatter);
		String checkOutformattedDate = checkOut.format(formatter);
		
		
        guestInfo.setText(guest1.toString());
        roomInfo.setText(room1.toString());
        addressInfo.setText(address1.toString());
        String datesInOut = "Check in date: " + checkInformattedDate + 
        		"\nCheck out date: " + checkOutformattedDate;
   
        dates.setText(datesInOut);
        int numAdults = 2;
        int numChild = 0;
        peopleNum.setText("Number of Adults: " +numAdults +
        		"\nNumber of Children: " + numChild);
        long daysBetween = ChronoUnit.DAYS.between(checkIn, checkOut);
        double price = daysBetween*room1.getPrice();
        String str = "Total price: " + price;
        totalPrice.setText(str);
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
    }

    public static ResultSet retrieveData(String line) throws SQLException {
    	Connection connection = getConnection();
    	Statement statement = connection.createStatement();
    	return statement.executeQuery(line);
    }
    
    public static Guest getGuestInfo(ResultSet resultSet) throws SQLException {
    	
    	String pfx = resultSet.getString("pf");
        String fname = resultSet.getString("fname");
        String mname = resultSet.getString("mname");
        String lname = resultSet.getString("lname");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        Guest guest = new Guest(pfx, fname, mname, lname, phone, email);

        return guest;
    }
    
    public static Address getAddress(ResultSet resultSet) throws SQLException {
    	
    	String country = resultSet.getString("country");
        String address1 = resultSet.getString("ad1");
        String address2 = resultSet.getString("ad2");
        String city = resultSet.getString("cty");
        String zipcode = resultSet.getString("zipcode");
        String detail = resultSet.getString("detail");
        Address address = new Address(country, address1, address2, city, zipcode, detail);

        return address;
    }
    
   public static Room getRoom(ResultSet resultSet) throws SQLException {
    	
	   int roomID = resultSet.getInt("roomno");
       String roomType = resultSet.getString("roomtype");
       int price = resultSet.getInt("price");
       int isAvailable = resultSet.getInt("isavailable");

       Room room = new Room(roomID, roomType, price, isAvailable);

        return room;
    }
	
	@FXML
	private void handleFinishButton(ActionEvent event) {
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("thankpage.fxml"));
	        Scene scene = new Scene(root1);
	        Stage stage = new Stage();
	        stage.setScene(scene);
	        stage.show();
	        
	        Stage primaryStage = (Stage) FinishButton.getScene().getWindow();
            primaryStage.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("confirmation.fxml"));
		primaryStage.setTitle("JATISOFT");
		primaryStage.setScene(new Scene(root, 600, 500));
		primaryStage.show();

		FinishButton = (Button) root.lookup("#FinishButton");
		FinishButton.setOnAction(event -> {
			handleFinishButton(event);
        });
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}


