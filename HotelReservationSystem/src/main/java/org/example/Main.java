package org.example;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            HotelGUI gui = new HotelGUI();

            gui.setVisible(true);
        });
    }
}