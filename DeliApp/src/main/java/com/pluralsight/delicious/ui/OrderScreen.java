package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class OrderScreen implements ScreenState {
    private String[] options = {"1) Add Sandwich", "2) Add Drink", "3) Add Chips", "4) Checkout", "0) Cancel Order"};

    @Override
    public void display() {
        System.out.println("What can I get you today?");
        for (int i = 0; i < options.length; i++) {
            System.out.println("\n\t" + options[i]);

        }
    }


    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        int input = scanner.nextInt();
        return switch (input) {
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
