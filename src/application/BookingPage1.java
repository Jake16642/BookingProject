package application;
	
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.application.Application;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import javafx.util.StringConverter;
import javafx.util.converter.LocalDateStringConverter;
import javafx.scene.control.DateCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.scene.control.Button;
import java.time.format.DateTimeFormatter;

import java.time.LocalDate;



public class BookingPage1 extends Application {
	
	private static Connection connection;
	private static final String SQL_INSERT = "INSERT INTO registration(aldutNum, childNum, checkindate, checkoutdate) VALUES(?,?,?,?)";
    private static int adultNum;
    private static int childNum;
    private static LocalDate checkIn;
    private static LocalDate checkOut;
	
	@FXML
	private ChoiceBox<String> adultNumBox;
	@FXML
	private ChoiceBox<String> childNumBox;
    @FXML
    private DatePicker checkInDate;
    @FXML
    private DatePicker checkOutDate;
    @FXML
    private Button actionButton;
    @FXML
    private Text details;
   
	@FXML
	private void initialize() {
		//actionButton
		
		
	    // Configure date picker converters
        StringConverter<LocalDate> converter = new LocalDateStringConverter();
        checkInDate.setConverter(converter);
        checkOutDate.setConverter(converter);
        
        checkInDate.setDayCellFactory(getDayCellFactory(LocalDate.now()));
        checkOutDate.setDayCellFactory(getDayCellFactory(LocalDate.now()));

        // Add change listener to the first date picker
        checkInDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            // Set the minimum date of the second date picker
        	checkOutDate.setDayCellFactory(getDayCellFactory(newValue));
        });
 
	    ObservableList<String> adultNumBoxList = FXCollections.observableArrayList("0", "1", "2", "3", "4");
	    adultNumBox.setValue("0");
	    adultNumBox.setItems(adultNumBoxList);
	    //adultNumBoxList.add("5");
	    
	    ObservableList<String> childNumBoxList = FXCollections.observableArrayList("0", "1", "2", "3", "4");
	    childNumBox.setValue("0");
	    childNumBox.setItems(childNumBoxList);
	    //adultNumBoxList.add("5");
	    
	    actionButton.setOnAction(event -> handleButtonAction());
	
	    
	}
	
	@FXML
    public void handleButtonAction() {
		
		adultNum = Integer.parseInt(adultNumBox.getValue());
		childNum = Integer.parseInt(childNumBox.getValue());
		checkIn = checkInDate.getValue();
		checkOut = checkOutDate.getValue();
		
		if(adultNumBox != null && childNumBox != null && checkIn != null && checkOut != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");
			String checkInformattedDate = checkIn.format(formatter);
			String checkOutformattedDate = checkOut.format(formatter);
		
			System.out.println(adultNum);
			System.out.println(childNum);
			System.out.println(checkInformattedDate);
			System.out.println(checkOutformattedDate);
			ConnectSQL();
		
			Stage primaryStage = (Stage) actionButton.getScene().getWindow();
			primaryStage.close();
        
			try {
				Parent root1 = FXMLLoader.load(getClass().getResource("roombooking.fxml"));
				Scene scene = new Scene(root1);
				Stage stage = new Stage();
				stage.setScene(scene);
				stage.show(); 

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			details.setText("Please enter the information above!");
		}
    }

	
	public static void ConnectSQL() {
        try {
            connection =
            DriverManager.getConnection("jdbc:mysql://localhost:3306/hotel", "root", "5alasomot");
            System.out.println("Connected With the database successfully");
            // Creating PreparedStatement object
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            // Setting values for Each Parameter
            preparedStatement.setInt(1, adultNum);
            preparedStatement.setInt(2, childNum);
            Date newDateIn = Date.valueOf(checkIn);
            //System.out.println(newDateIn);
            Date newDateOut = Date.valueOf(checkOut);
            preparedStatement.setDate(3, newDateIn);
            preparedStatement.setDate(4, newDateOut);
           //preparedStatement.setInt(5, n);
            int row = preparedStatement.executeUpdate();
            // rows affected
            System.out.println(row); // 1
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database");
            System.out.println(e);
        }
    }
	
    private Callback<DatePicker, DateCell> getDayCellFactory(LocalDate minDate) {
        return picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(date.isBefore(minDate));
            }
        };
    }
    
	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root2 = FXMLLoader.load(getClass().getResource("datebooking.fxml"));
		primaryStage.setTitle("Hello");
		primaryStage.setScene(new Scene(root2, 500, 500));
		primaryStage.show();
		
	}
	

	public static void main(String[] args) {
		
		launch(args);	

	}
}
