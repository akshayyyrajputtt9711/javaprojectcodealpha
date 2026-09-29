package org.example;

public class Reservation {

    private int bookingId;
    private String guestName;
    private String phone;
    private int roomNumber;
    private String category;
    private int nights;
    private double totalAmount;
    private String paymentStatus;

    public Reservation(
            int bookingId,
            String guestName,
            String phone,
            int roomNumber,
            String category,
            int nights,
            double totalAmount,
            String paymentStatus) {

        this.bookingId = bookingId;
        this.guestName = guestName;
        this.phone = phone;
        this.roomNumber = roomNumber;
        this.category = category;
        this.nights = nights;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getPhone() {
        return phone;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getCategory() {
        return category;
    }

    public int getNights() {
        return nights;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public String toFileString() {

        return bookingId + "|" +
                guestName + "|" +
                phone + "|" +
                roomNumber + "|" +
                category + "|" +
                nights + "|" +
                totalAmount + "|" +
                paymentStatus;
    }

    public static Reservation fromFileString(String line) {

        String[] p = line.split("\\|");

        return new Reservation(
                Integer.parseInt(p[0]),
                p[1],
                p[2],
                Integer.parseInt(p[3]),
                p[4],
                Integer.parseInt(p[5]),
                Double.parseDouble(p[6]),
                p[7]
        );
    }

    @Override
    public String toString() {

        return "Booking ID: " + bookingId +
                " | Guest: " + guestName +
                " | Phone: " + phone +
                " | Room: " + roomNumber +
                " | " + category +
                " | Nights: " + nights +
                " | Total: ₹" + totalAmount +
                " | Payment: " + paymentStatus;
    }
}