package application;

import java.time.LocalDate;

public class RoomReservation extends Reservation {
	

	protected Room room;
	protected double totalPrice;
	protected static int adults;
	protected static int children;
	
	public RoomReservation(Room room, LocalDate checkInDate, LocalDate checkOutDate, Guest guest) {
		super(checkInDate,checkOutDate,guest);
		this.room = room;
	}

	public RoomReservation(Room room, double totalPrice, LocalDate checkInDate, LocalDate checkOutDate, Guest guest,
			int adults, int children) {
		super(checkInDate,checkOutDate,guest);
		this.room = room;
		this.totalPrice = totalPrice;
		RoomReservation.adults = adults;
		RoomReservation.children = children;
		
	}

	public RoomReservation(Guest guest, Room room, LocalDate checkInDate, LocalDate checkOutDate) {
		super(checkInDate,checkOutDate,guest);
		this.room = room;
	}

	public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public static int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        RoomReservation.adults = adults;
    }

    public static int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        RoomReservation.children = children;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    } 
    
	public String toString() {
		return 	guest.getFirstName() + " " + guest.getLastName() +
		" Date In: " + checkInDate + " Date Out: " + checkOutDate + " " + room;

	}
//
//	public void setReservationNumber(String bookingNumber) {
//		 ExistingReservation.setReservationNumber(bookingNumber);
//		
//	}
}