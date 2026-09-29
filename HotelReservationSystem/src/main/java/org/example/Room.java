package org.example;

public class Room {

    private int roomNumber;
    private String category;
    private double pricePerNight;
    private boolean available;

    public Room(int roomNumber, String category, double pricePerNight) {

        this.roomNumber = roomNumber;
        this.category = category;
        this.pricePerNight = pricePerNight;
        this.available = true;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {

        return "Room " + roomNumber +
                " | " + category +
                " | ₹" + pricePerNight +
                "/night | " +
                (available ? "Available" : "Booked");
    }
}