package application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BookingSystem {
    private List<Room> availableRooms;
    private List<Room> bookedRooms;
    private List<Reservation> reservations;
    private List<Guest> guests;

    public BookingSystem(List<Room> availableRooms) {
        this.availableRooms = availableRooms;
        this.bookedRooms = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.guests = new ArrayList<>();
    }

    public BookingSystem() {
		// TODO Auto-generated constructor stub
	}
    
    public List<Room> getBookedRooms() {
        return bookedRooms;
    }

    
    public List<Room> getAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();

        for (Room room : this.availableRooms) {
            boolean isBooked = false;

            for (Reservation reservation : this.reservations) {
                if (reservation instanceof RoomReservation) {
                    RoomReservation roomReservation = (RoomReservation) reservation;

                    if (roomReservation.getRoom().equals(room)) {
                        LocalDate today = LocalDate.now();
                        if (today.isBefore(roomReservation.getCheckInDate()) || today.isAfter(roomReservation.getCheckOutDate())) {
                            isBooked = true;
                            break;
                        }
                    }
                }
            }

            if (!isBooked) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }


	public void checkAvailability(LocalDate checkInDate, LocalDate checkOutDate) {
        List<Room> availableRooms = new ArrayList<>();
        boolean isBooked = false;
        
        for (Room room : this.availableRooms) {
        	if (room.getAvailabilityStatus().equals("available")) {
        		 isBooked = false;
        	}
           
            for (Reservation reservation : this.reservations) {
                if (reservation instanceof RoomReservation) {
                    RoomReservation roomReservation = (RoomReservation) reservation;

                    if (roomReservation.getRoom().equals(room) && 
                            ((checkInDate.isAfter(roomReservation.getCheckInDate()) &&
                                    checkInDate.isBefore(roomReservation.getCheckOutDate())) || 
                                    (checkOutDate.isAfter(roomReservation.getCheckInDate()) && 
                                            checkOutDate.isBefore(roomReservation.getCheckOutDate())) || 
                                    (checkInDate.isEqual(roomReservation.getCheckInDate()) || 
                                            checkOutDate.isEqual(roomReservation.getCheckOutDate())))) {
                        isBooked = true;
                        break;
                    }
                }
            }

            if (!isBooked) {
                availableRooms.add(room);
            }
        }

        System.out.println("Available rooms for the selected dates: " +checkInDate +" " + checkOutDate);

        for (Room room : availableRooms) {
            System.out.println(room.getRoomNumber() + " (" + room.getRoomType() + ")");
        }
    }

    public String bookRoom(Room room, Guest guest, LocalDate checkInDate, LocalDate checkOutDate) {
        Reservation reservation = new RoomReservation(guest, room, checkInDate, checkOutDate);
        this.reservations.add(reservation);
        this.bookedRooms.add(room);
        //
     
        
        // generate booking number
        Random rand = new Random();
        String bookingNumber = String.format("%04d", rand.nextInt(10000));
        reservation.setReservationNumber(bookingNumber);

        // send confirmation email
//        String message = "Dear " + guest.getFullName() + ",\n\nThank you for your booking. Your booking number is " + bookingNumber + ".\n\nWe look forward to welcoming you at our hotel!\n\nBest regards,\nThe Hotel Team";
//        EmailSender.sendEmail(guest.getEmailAddress(), "Booking Confirmation", message);

        
        // add guest to list of guests
        if (!this.guests.contains(guest)) {
            this.guests.add(guest);
        }

        System.out.println("Booking successful! Your booking number is " + bookingNumber + ".");
        return bookingNumber;
    }

    public String generateBookingNumber() {
        Random rand = new Random();
        String bookingNumber = String.format("%04d", rand.nextInt(10000));
        return bookingNumber;
    }

    public Reservation retrieveBookingDetails(String bookingNumber) {
        for (Reservation reservation : this.reservations) {
            if (reservation.getReservationNumber().equals(bookingNumber)) {
                return reservation;
            }
        }

        return null;
    }

    public String retrieveGuestDetails(String bookingNumber) {
        for (Reservation reservation : this.reservations) {
            if (reservation.getReservationNumber().equals(bookingNumber)) {
                return reservation.getGuest().toString();
            }
        }

        return null;
    }
    
    public void removeRoom(Room room) {
        availableRooms.remove(room);
    }


}
