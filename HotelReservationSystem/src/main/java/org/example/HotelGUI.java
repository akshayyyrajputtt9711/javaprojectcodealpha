package org.example;

import javax.swing.*;
import java.awt.*;

public class HotelGUI extends JFrame {

    private HotelManager manager =
            new HotelManager();

    private JTextArea output;

    private JComboBox<String> category;

    private JTextField guest;
    private JTextField phone;
    private JTextField room;
    private JTextField nights;
    private JTextField bookingId;

    public HotelGUI() {

        setTitle("Hotel Reservation System");

        setSize(800, 600);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);

        createGUI();

        showRooms();
    }

    private void createGUI() {

        JPanel main =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        main.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        JLabel title =
                new JLabel(
                        "HOTEL RESERVATION SYSTEM",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        main.add(
                title,
                BorderLayout.NORTH
        );

        output =
                new JTextArea();

        output.setEditable(false);

        output.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        14
                )
        );

        main.add(
                new JScrollPane(output),
                BorderLayout.CENTER
        );

        JPanel form =
                new JPanel(
                        new GridLayout(
                                0,
                                2,
                                6,
                                6
                        )
                );

        category =
                new JComboBox<>(
                        new String[]{
                                "All",
                                "Standard",
                                "Deluxe",
                                "Suite"
                        }
                );

        guest = new JTextField();
        phone = new JTextField();
        room = new JTextField();
        nights = new JTextField();
        bookingId = new JTextField();

        form.add(
                new JLabel("Room Category:")
        );
        form.add(category);

        form.add(
                new JLabel("Guest Name:")
        );
        form.add(guest);

        form.add(
                new JLabel("Phone:")
        );
        form.add(phone);

        form.add(
                new JLabel("Room Number:")
        );
        form.add(room);

        form.add(
                new JLabel("Nights:")
        );
        form.add(nights);

        form.add(
                new JLabel("Booking ID:")
        );
        form.add(bookingId);

        JButton search =
                new JButton("Search Rooms");

        JButton book =
                new JButton("Book Room");

        JButton cancel =
                new JButton("Cancel Booking");

        JButton view =
                new JButton("View Bookings");

        form.add(search);
        form.add(book);

        form.add(cancel);
        form.add(view);

        main.add(
                form,
                BorderLayout.SOUTH
        );

        search.addActionListener(
                e -> searchRooms()
        );

        book.addActionListener(
                e -> bookRoom()
        );

        cancel.addActionListener(
                e -> cancelBooking()
        );

        view.addActionListener(
                e -> showBookings()
        );

        add(main);
    }

    private void showRooms() {

        output.setText(
                "ROOM LIST\n" +
                        "========================================\n"
        );

        for (Room r :
                manager.getRooms()) {

            output.append(
                    r + "\n"
            );
        }
    }

    private void searchRooms() {

        output.setText(
                "SEARCH RESULTS\n" +
                        "========================================\n"
        );

        String selectedCategory =
                (String) category.getSelectedItem();

        for (Room r :
                manager.searchRooms(
                        selectedCategory
                )) {

            output.append(
                    r + "\n"
            );
        }
    }

    private void bookRoom() {

        try {

            String guestName =
                    guest.getText().trim();

            String phoneNumber =
                    phone.getText().trim();

            int roomNumber =
                    Integer.parseInt(
                            room.getText().trim()
                    );

            int numberOfNights =
                    Integer.parseInt(
                            nights.getText().trim()
                    );

            if (
                    guestName.isEmpty()
                            ||
                            phoneNumber.isEmpty()
                            ||
                            numberOfNights <= 0
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter valid guest details and nights."
                );

                return;
            }

            Reservation reservation =
                    manager.bookRoom(
                            guestName,
                            phoneNumber,
                            roomNumber,
                            numberOfNights
                    );

            if (reservation != null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking successful!\n\n" +
                                "Booking ID: " +
                                reservation.getBookingId() +
                                "\nTotal: ₹" +
                                reservation.getTotalAmount()
                );

                showRooms();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking failed.\n" +
                                "Room may be unavailable " +
                                "or payment failed."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Room number and nights " +
                            "must be numbers."
            );
        }
    }

    private void cancelBooking() {

        try {

            int id =
                    Integer.parseInt(
                            bookingId
                                    .getText()
                                    .trim()
                    );

            if (
                    manager.cancelBooking(id)
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking cancelled successfully."
                );

                showRooms();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Booking ID not found."
                );
            }

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Enter a valid booking ID."
            );
        }
    }

    private void showBookings() {

        output.setText(
                "BOOKING DETAILS\n" +
                        "========================================\n"
        );

        if (
                manager.getBookings().isEmpty()
        ) {

            output.append(
                    "No bookings found.\n"
            );

            return;
        }

        for (Reservation r :
                manager.getBookings()) {

            output.append(
                    r + "\n"
            );
        }
    }
}