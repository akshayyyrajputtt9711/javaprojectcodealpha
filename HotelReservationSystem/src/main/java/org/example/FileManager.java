package org.example;

import java.io.*;
import java.util.*;

public class FileManager {

    private static final String FILE_NAME =
            "bookings.txt";

    public static void saveBookings(
            List<Reservation> bookings) {

        try (
                BufferedWriter writer =
                        new BufferedWriter(
                                new FileWriter(FILE_NAME)
                        )
        ) {

            for (Reservation r : bookings) {

                writer.write(
                        r.toFileString()
                );

                writer.newLine();
            }

        } catch (IOException e) {

            System.out.println(
                    "Save error: " +
                            e.getMessage()
            );
        }
    }

    public static List<Reservation> loadBookings() {

        List<Reservation> list =
                new ArrayList<>();

        File file =
                new File(FILE_NAME);

        if (!file.exists()) {
            return list;
        }

        try (
                BufferedReader reader =
                        new BufferedReader(
                                new FileReader(FILE_NAME)
                        )
        ) {

            String line;

            while ((line = reader.readLine())
                    != null) {

                if (!line.isBlank()) {

                    list.add(
                            Reservation
                                    .fromFileString(line)
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Load error: " +
                            e.getMessage()
            );
        }

        return list;
    }
}
