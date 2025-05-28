package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class AddSandwichScreen implements ScreenState {
    private String[] options = {"Create Custom Sandwich", "View Signature Sandwiches"};

    @Override
    public void display() {
        System.out.println("What kind of sandwich would you like to order?");
        for (int i = 0; i < options.length; i++){
            System.out.printf("\n\t%d) %s\n", i + 1, options[i]);
        }
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        int input = scanner.nextInt();
        return switch (input) {
            case 1 -> new CustomizeSandwichScreen();
            case 2 -> new SignatureSandwichesScreen();
            default -> {
                System.out.println("Invalid selection");
                yield this;
            }

        };
    }
}
