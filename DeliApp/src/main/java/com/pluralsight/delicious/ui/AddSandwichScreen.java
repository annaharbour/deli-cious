package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class AddSandwichScreen implements ScreenState {
    private String[] options = {};

    @Override
    public void display() {
        System.out.println("Select from the following options: 1) custom sandwich or view our signature sandwiches?");
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
