package org.example;

import java.util.ArrayList;
import java.util.List;

public class HotelManager {

    private List<Room> rooms =
            new ArrayList<>();

    private List<Reservation> bookings;

    private int nextBookingId = 1001;

    public HotelManager() {

        loadRooms();

        bookings =
                FileManager.loadBookings();

        // Mark already-booked rooms
        for (Reservation r : bookings) {

            for (Room room : rooms) {

                if (room.getRoomNumber()
                        == r.getRoomNumber()) {

                    room.setAvailable(false);
                }
            }

            if (r.getBookingId()
                    >= nextBookingId) {

                nextBookingId =
                        r.getBookingId() + 1;
            }
        }
    }

    private void loadRooms() {

        rooms.add(
                new Room(
                        101,
                        "Standard",
                        1500
                )
        );

        rooms.add(
                new Room(
                        102,
                        "Standard",
                        1500
                )
        );

        rooms.add(
                new Room(
                        201,
                        "Deluxe",
                        2500
                )
        );

        rooms.add(
                new Room(
                        202,
                        "Deluxe",
                        2500
                )
        );

        rooms.add(
                new Room(
                        301,
                        "Suite",
                        4000
                )
        );

        rooms.add(
                new Room(
                        302,
                        "Suite",
                        4000
                )
        );
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Reservation> getBookings() {
        return bookings;
    }

    public List<Room> searchRooms(
            String category) {

        List<Room> result =
                new ArrayList<>();

        for (Room room : rooms) {

            if (
                    category.equals("All")
                            ||
                            room.getCategory()
                                    .equalsIgnoreCase(category)
            ) {

                result.add(room);
            }
        }

        return result;
    }

    public Reservation bookRoom(
            String guest,
            String phone,
            int roomNumber,
            int nights) {

        for (Room room : rooms) {

            if (
                    room.getRoomNumber()
                            == roomNumber
                            &&
                            room.isAvailable()
            ) {

                double total =
                        room.getPricePerNight()
                                * nights;

                if (!Payment.processPayment(total)) {
                    return null;
                }

                room.setAvailable(false);

                Reservation reservation =
                        new Reservation(
                                nextBookingId++,
                                guest,
                                phone,
                                roomNumber,
                                room.getCategory(),
                                nights,
                                total,
                                "PAID"
                        );

                bookings.add(reservation);

                FileManager.saveBookings(
                        bookings
                );

                return reservation;
            }
        }

        return null;
    }

    public boolean cancelBooking(
            int bookingId) {

        for (int i = 0;
             i < bookings.size();
             i++) {

            Reservation r =
                    bookings.get(i);

            if (
                    r.getBookingId()
                            == bookingId
            ) {

                for (Room room : rooms) {

                    if (
                            room.getRoomNumber()
                                    == r.getRoomNumber()
                    ) {

                        room.setAvailable(true);
                    }
                }

                bookings.remove(i);

                FileManager.saveBookings(
                        bookings
                );

                return true;
            }
        }

        return false;
    }
}