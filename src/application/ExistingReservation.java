package application;

public class ExistingReservation extends Reservation {
	
	private String reservationNumber;
    private Room room;
    private double totalPrice;
	


	public ExistingReservation(String reservationNumber,Room room, double totalPrice, Guest guest) {
		super(guest);
		this.reservationNumber = reservationNumber;
		this.room = room;
		this.totalPrice = totalPrice;
	}

	public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
    	this.reservationNumber = reservationNumber;
    }

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	public String toString() {
		return 	guest.getFirstName() + " " + guest.getLastName() +
		" Room number: " + reservationNumber + " "+ room.getRoomType();

	}
	
}