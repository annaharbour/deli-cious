package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.ui.utils.ClearScreen;

import java.util.Scanner;

public class AddSandwichScreen implements ScreenState {
    private final String[] options = {"1) Create Custom Sandwich", "2) View Signature Sandwiches"};

    @Override
    public void display() {
        ClearScreen.clearScreen();
        System.out.println("What kind of sandwich would you like to order?");
        for(String option: options){
            System.out.println("\t" + option);
        }
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        int input = scanner.nextInt();
        return switch (input) {
            case 1 -> {
                ClearScreen.clearScreen();
                yield new CustomizeSandwichScreen();
            }
            case 2 -> {
                ClearScreen.clearScreen();
                yield new SignatureSandwichesScreen();
            }
            default -> {
                System.out.println("Invalid selection");
                ClearScreen.clearScreen();
                yield this;
            }

        };
    }
}