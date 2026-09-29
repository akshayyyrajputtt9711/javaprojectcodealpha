package org.example;

import java.util.Random;

public class Payment {

    public static boolean processPayment(double amount) {

        System.out.println(
                "Processing payment of ₹" + amount
        );

        // Simulated payment
        return new Random().nextInt(10) < 9;
    }
}