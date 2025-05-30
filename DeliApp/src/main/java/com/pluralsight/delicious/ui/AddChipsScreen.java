package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Chips;
import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.ui.utils.ClearScreen;
import com.pluralsight.delicious.ui.utils.PrintColored;

import java.util.Scanner;

public class AddChipsScreen implements ScreenState {
    @Override
    public void display() {
        ClearScreen.clearScreen();
        PrintColored.printColored("🥔====== Chips ======🥔", "magenta");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        Chips chips = new Chips();
        PrintColored.printColored("What flavor chips would you like?", "yellow");
        Chips.Flavor[] flavorOptions = Chips.getAllChipFlavors();
        for (int i = 0; i <= flavorOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, flavorOptions[i].getValue());
        }
        int flavorChoice;
        do {
            if (scanner.hasNextInt()) {
                flavorChoice = scanner.nextInt();
                if (flavorChoice < 1 || flavorChoice > flavorOptions.length) {
                    PrintColored.printColored("Invalid choice, please select a valid flavor option.", "red");
                }
            } else {
                PrintColored.printColored("Please enter a valid number.", "red");
                scanner.next(); // Consume the invalid input
                flavorChoice = -1; // Reset choice to ensure the loop continues
            }
        } while (flavorChoice < 1 || flavorChoice > flavorOptions.length);
        chips.setFlavor(flavorOptions[flavorChoice - 1]);
        ClearScreen.clearScreen();
        System.out.printf("Would you like to add a bag of %s chips for $%.2f to your order?\n", chips.getFlavor(),
                chips.getPrice());
        System.out.println("Enter \t1) Confirm \t0) Cancel Chips");
        int confirmation;
        do {
            if (scanner.hasNextInt()) {
                confirmation = scanner.nextInt();
                if (confirmation == 1) {
                    currentOrder.addToOrder(chips);
                    System.out.printf("\nAdding %s Chips to your order", chips.getFlavor());
                }
            } else {
                PrintColored.printColored("Please enter a valid number.", "red");
                scanner.next();
                confirmation = -1;
            }
        } while (confirmation != 0 && confirmation != 1);
        ClearScreen.clearScreen();
        return new OrderScreen();
    }
}
