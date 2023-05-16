package application;

import java.time.LocalDate;
import java.util.*;


public class Main {
    public static void main(String[] args) throws Exception {
    	// Create instances of the classes required for the booking system
    	List<Room> rooms = new ArrayList<>();  // Initialize the list with actual rooms
    	BookingSystem bookingSystem = new BookingSystem(rooms);
        Room room1 = new Room("101", "Single", 100.0, "available" );
        Room room2 = new Room("102", "Double", 150.0, "available");
        Room room3 = new Room("201", "Suite", 200.0, "available");
        Guest guest1 = new Guest("Mr.", "John", "D.", "Doe", "1234567890", "john.doe@example.com");
        Guest guest2 = new Guest("Ms.", "Jane", "E.", "Smith", "9876543210", "jane.smith@example.com");
        rooms.add(room1);
        rooms.add(room2);
        rooms.add(room3);
        // Add available rooms to the booking system
        bookingSystem.getAvailableRooms().add(room1);
        bookingSystem.getAvailableRooms().add(room2);
        bookingSystem.getAvailableRooms().add(room3);

        // Test the booking system
        System.out.println("=== Hotel Reservation System ===");
        System.out.println(LocalDate.now().getDayOfMonth());

        // Make a new booking
        System.out.println("\nMaking a new booking:");
        LocalDate checkInDate = LocalDate.of(2023,5,5); // Replace with desired check-in date
        LocalDate checkOutDate = LocalDate.of(2023,5,8); // Replace with desired check-out date
        bookingSystem.checkAvailability(checkInDate, checkOutDate);

        // Assume the user selects room2
        Room selectedRoom = room2;
        
        // Assume the user fills out personal details and address
        Address address = new Address("USA", "123 Main St", "", "New York", "12345", "Additional details");

        guest1.setAddress(address);
        guest1.setAdults(2);
        guest1.setChildren(0);

        // Book the room
        String num = bookingSystem.bookRoom(selectedRoom, guest1, checkInDate, checkOutDate);
        System.out.println(num);
        
        
        //Book 2nd
        LocalDate checkInDate2 = LocalDate.of(2023,5,10); // Replace with desired check-in date
        LocalDate checkOutDate2 = LocalDate.of(2023,5,15);
        bookingSystem.checkAvailability(checkInDate, checkOutDate);
        
        //check 3rd
        bookingSystem.checkAvailability(checkInDate2, checkOutDate2);

        // Check existing booking
        System.out.println("\nChecking an existing booking:");
        Reservation existingBooking = bookingSystem.retrieveBookingDetails(num); // Replace with actual booking number

        if (existingBooking != null) {
            System.out.println("Booking details: " + existingBooking.toString());

            // Retrieve guest details
            String guestDetails = bookingSystem.retrieveGuestDetails(existingBooking.getReservationNumber());
            if (guestDetails != null) {
                System.out.println("Guest details: " + guestDetails.toString());
            } else {
                System.out.println("Guest details not found.");
            }
        } else {
            System.out.println("Booking not found.");
        }
        
        // Create a sample EmailSender
        //EmailSender emailSender = new EmailSender("tuhocoder@gmail.com", "Th@nhtu@10");
        //emailSender.sendEmail("tuho9428@gmail.com", num);
        System.out.println("An email send to " + guest1.getEmailAddress() + "\nwith your booking number: " + num);

    }
    	/**



        

        // Create a sample ExistingReservation
        ExistingReservation existingReservation1 = new ExistingReservation(bookingNum , room1, 200.0, guest1);
        System.out.println(existingReservation1);
        
        // Create a sample EmailSender
        //EmailSender emailSender = new EmailSender("smtp.example.com", 587, "your-email@example.com", "your-password");

        // Test the EmailSender by sending an email to the guest
//        try {
//            String to = guest1.getEmailAddress();
//            String subject = "Reservation Confirmation";
//            String body = "Dear " + guest1.getFirstName() + ",\n\nThank you for your reservation!\n\nWe look forward to welcoming you on your stay.\n\nBest regards,\nThe Hotel Team";
//            emailSender.sendEmail(to, subject, body);
//            System.out.println("Email sent successfully to: " + to);
//        } catch (MessagingException e) {
//            System.out.println("Failed to send email: " + e.getMessage());
//        }
           
    }
    **/
}
