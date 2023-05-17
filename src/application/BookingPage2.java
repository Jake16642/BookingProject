package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookingPage2 extends Application{
	
	private static Connection connection;
	static final String QUERY = "SELECT * FROM datebooking";
	static final String QUERY2 = "SELECT * FROM room";
	private static final String SQL_INSERT = "INSERT INTO roominfo(roomno, roomtype, price, isavailabe) VALUES(?,?,?,?)";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "5alasomot";

	private static String roomType;
	private static int roomNumber;
	private static int price;
	private static boolean isAvailable;
	@FXML
	private VBox roomContainer;
	private BookingSystem bookingSystem;
	
	@FXML
	private Button BackButton;
	
	public void initialize() throws SQLException {
        List<Room> rooms = new ArrayList<>();
        rooms = getRoom(retrieveData(QUERY2));
        int guestNum = getNumGuest(retrieveData(QUERY));
        List<Room> filterRoom = new ArrayList<>();
        bookingSystem = new BookingSystem(rooms);

        List<Room> availableRooms = bookingSystem.getAvailableRooms();
       
        for (Room room : availableRooms) {
                    if (guestNum <= 2 && room.getRoomType().equals("single")) {
                    	filterRoom.add(room);
                    } else if (guestNum >= 3 && guestNum <= 4 && room.getRoomType().equals("double")) {
                    	filterRoom.add(room);
                    } else if (guestNum >= 5 && guestNum <= 6 && room.getRoomType().equals("triple")) {
                    	filterRoom.add(room);
                    } else if (guestNum >= 7 && guestNum <= 8 && room.getRoomType().equals("quad")) {
                    	filterRoom.add(room);
                    }
                }
        for (Room room : filterRoom) {
            Label roomLabel = new Label(room.getRoomNumber() + " (" + room.getRoomType() + ")");
            Button bookButton = new Button("Book");
            bookButton.setOnAction(e -> {
                // Handle button action here
            	Stage primaryStage = (Stage) bookButton.getScene().getWindow();
                primaryStage.close();
        	    try {
        	        Parent root = FXMLLoader.load(getClass().getResource("guestinfo.fxml"));
        	        Scene scene = new Scene(root);
        	        Stage stage = new Stage();
        	        stage.setScene(scene);
        	        stage.show();
        	    } catch (IOException ex) {
        	        ex.printStackTrace();
        	    }
        	    int roomID = room.getRoomNumber();
        	    String roomType = room.getRoomType();
        	    int price = room.getPrice();
        	    try {
        	    	connection =
        	    			DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "5alasomot");
        	    	System.out.println("Connected With the database successfully");
        	    			// Creating PreparedStatement object
        	    	PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
        	    	// Setting values for Each Parameter
        	    	preparedStatement.setInt(1, roomID);
        	    	preparedStatement.setString(2, roomType);
        	    	preparedStatement.setInt(3, price);
        	    	preparedStatement.setInt(4, 0);
        	    }
        	    catch(SQLException e1) {
        	    	System.out.println("Error while connecting to the database");
        	    	System.out.println(e1);
        	    }
        	    Connection connection = null;
				try {
					connection = getConnection();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
        	    String query = "UPDATE room SET isavailable = ? WHERE roomno = ?";

                try (PreparedStatement statement = connection.prepareStatement(query)) {
                    statement.setInt(1, 0);
                    statement.setInt(2, roomID);
                    statement.executeUpdate();
                } catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                System.out.println("Book button clicked for room: " + roomID);
            });
            HBox roomBox = new HBox(roomLabel, bookButton);
            roomContainer.getChildren().add(roomBox);
        }
    }

	    

	public static Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
	}
	
	public static ResultSet retrieveData(String line) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        return statement.executeQuery(line);
    }
	
	public static ObservableList<Room> getRoom(ResultSet resultSet) throws SQLException {
	        ObservableList<Room> roomList = FXCollections.observableArrayList();

	        while (resultSet.next()) {
	            int roomID = resultSet.getInt("roomno");
	            String roomType = resultSet.getString("roomtype");
	            int price = resultSet.getInt("price");
	            int isAvailable = resultSet.getInt("isavailable");

	            Room room = new Room(roomID, roomType, price, isAvailable);
	            roomList.add(room);
	        }

	        return roomList;
	}
	
	public static int getNumGuest(ResultSet resultSet) throws SQLException {
		int total = 0;
		while (resultSet.next()) {
            int adult = resultSet.getInt("aldutNum");
            int child = resultSet.getInt("childNum");
            total = adult + child;
		}
		
		return total;
		
	}
	
	 public void checkAvailability() {
	        // Your code to handle the button action goes here
	        System.out.println("Check availability button clicked");
	        
		    try {
		        Parent root = FXMLLoader.load(getClass().getResource("dashboard1.fxml"));
		        Scene scene = new Scene(root);
		        Stage stage = new Stage();
		        stage.setScene(scene);
		        stage.show();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
	    }
	
	/*public static void ConnectSQL() {
			try {
				connection =
				DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "5alasomot");
				System.out.println("Connected With the database successfully");
				// Creating PreparedStatement object
	            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);
	            // Setting values for Each Parameter
	            preparedStatement.setString(1, username);
	            preparedStatement.setString(2, password);;

	            int row = preparedStatement.executeUpdate();
	            // rows affected
	            System.out.println(row); // 1
			} catch (SQLException e) {
				System.out.println("Error while connecting to the database");
				System.out.println(e);
			}
		}*/
	 
	@FXML
	private void handleBackButton(ActionEvent event) {
	    try {
	        Parent root1 = FXMLLoader.load(getClass().getResource("datebooking.fxml"));
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

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("roombooking.fxml"));
		primaryStage.setTitle("JATISOFT");
		primaryStage.setScene(new Scene(root, 600, 500));
		primaryStage.show();
		
		BackButton = (Button) root.lookup("#BackButton");
		BackButton.setOnAction(event -> {
			handleBackButton(event);
        });
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
