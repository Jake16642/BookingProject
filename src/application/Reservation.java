package application;

import java.time.LocalDate;


public class Reservation {
	protected LocalDate checkInDate;
	protected LocalDate checkOutDate;
	protected Guest guest;
	private String reservationNumber;

	

	public Reservation(LocalDate checkInDate, LocalDate checkOutDate, Guest guest) {
		this.checkInDate = checkInDate;
		this.checkOutDate = checkOutDate;
		this.guest = guest;
		this.reservationNumber = "";
	}

	public Reservation(Guest guest) {
		this.guest = guest;
	}

	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}

	public void setCheckOutDate(LocalDate CheckOutDate) {
		this.checkOutDate = CheckOutDate;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public LocalDate getCheckInDate() {
		return this.checkInDate;
	}

	public LocalDate getCheckOutDate() {
		return this.checkOutDate;
	}

	public Guest getGuest() {
		return this.guest;
	}
	
	public static String generateBookingNumber() {
        // Implementation to generate a unique booking number
        // You can use your own logic here, such as generating a random string or using a counter
        // For simplicity, let's assume it generates a random 6-digit number
        return String.format("%06d", (int) (Math.random() * 1000000));
    }
	
	public String toString() {
		return 	guest.getPhoneNumber() + " " + guest.getLastName() +
		" Date In: " + checkInDate + " Date Out: " + checkOutDate;

	}

	public void setReservationNumber(String bookingNumber) {
		this.reservationNumber = bookingNumber;
		
	}

	public String getReservationNumber() {
		return this.reservationNumber;
	}

	
}
