package application;

import java.time.LocalDate;


public class Room {
	private String roomNumber;
	private String roomType;
	private double price;
	private String availabilityStatus;

	public Room(String roomNumber, String roomType, double price, String availabilityStatus) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.price = price;
		this.availabilityStatus = availabilityStatus;
	}

	public Room(String roomNumber, String roomType, double price) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.price = price;
	}

	public boolean isAvailable() {
		return availabilityStatus.equals("available");
	}
	
	public boolean isAvailable(LocalDate checkInDate,LocalDate checkOutDate) {
		return availabilityStatus.equals("available");
	}

	public String getDetails() {
		return "Room Number: " + roomNumber + "\nRoom Type: " + roomType + "\nPrice per Night: " + price
				+ "\nAvailability: " + availabilityStatus;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomNumber() {
		return this.roomNumber;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getRoomType() {
		return this.roomType;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return this.price;
	}

	public void setAvailabilityStatus(String availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}

	public String getAvailabilityStatus() {
		return this.availabilityStatus;
	}
	
	public String toString() {
		return 	roomNumber + " " + roomType + " " +  availabilityStatus;
	}
	
	
}
