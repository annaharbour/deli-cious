package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.ui.utils.ClearScreen;
import com.pluralsight.delicious.ui.utils.PrintColored;

import java.util.Scanner;

public class AddSandwichScreen implements ScreenState {

    @Override
    public void display() {
        ClearScreen.clearScreen();
        PrintColored.printColored("\uD83E\uDD6A====== SANDWICHES ======\uD83E\uDD6A", "magenta");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        PrintColored.printColored("What kind of sandwich would you like to order?", "yellow");
        String[] options = {"1) Create Custom Sandwich", "2) View Signature Sandwiches"};
        for(String option: options){
            System.out.println("\t" + option);
        }
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
                PrintColored.printColored("Invalid selection", "red");
                ClearScreen.clearScreen();
                yield this;
            }

        };
    }
}