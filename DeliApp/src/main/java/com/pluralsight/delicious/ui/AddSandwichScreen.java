package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class AddSandwichScreen implements ScreenState {
    private final String[] options = {"1) Create Custom Sandwich", "2) View Signature Sandwiches"};

    @Override
    public void display() {
        System.out.println("What kind of sandwich would you like to order?");
        for(String option: options){
            System.out.println("\t" + option);
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