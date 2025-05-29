package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class OrderScreen implements ScreenState {
    private final String[] options = {"1) Add Sandwich", "2) Add Drink", "3) Add Chips", "4) Checkout", "0) Cancel " +
            "Order"};

    @Override
    public void display() {
//        TODO: Clear screen
        System.out.println("What can I get you today?");
        for (String option : options) {
            System.out.println("\t" + option);
        }
    }

    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        int input = scanner.nextInt();
        return switch (input) {
            case 0 -> null;
            case 1 -> new AddSandwichScreen();
            case 2 -> new AddDrinkScreen();
            case 3 -> new AddChipsScreen();
            case 4 -> new CheckoutScreen();
            default -> {
                System.out.println("Invalid input. Try again");
                yield this;
            }
        };
    }
}
