package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Drink;
import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.ui.utils.ClearScreen;
import com.pluralsight.delicious.ui.utils.PrintColored;

import java.util.Scanner;

public class AddDrinkScreen implements ScreenState {
    @Override
    public void display() {
        ClearScreen.clearScreen();

        PrintColored.printColored("\uD83E\uDD64====== Order a Drink ======\uD83E\uDD64", "magenta");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        Drink drink = new Drink();
        PrintColored.printColored("What size drink would you like?","yellow");
        Drink.Size[] drinkSizeOptions = Drink.getAllDrinkSizeOptions();
        for (int i = 0; i <= drinkSizeOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, drinkSizeOptions[i].getValue());
        }
        int drinkSizeChoice;
        do {
            drinkSizeChoice = scanner.nextInt();
            if (drinkSizeChoice < 1 || drinkSizeChoice > drinkSizeOptions.length) {
                PrintColored.printColored("Invalid choice, please select a valid size option.", "red");
            }
        } while (drinkSizeChoice < 1 || drinkSizeChoice > drinkSizeOptions.length);

        drink.setSize(drinkSizeOptions[drinkSizeChoice - 1]);
        ClearScreen.clearScreen();
        PrintColored.printColored("What flavor drink would you like?", "yellow");
        Drink.Flavor[] flavorOptions = Drink.getAllDrinkFlavorOptions();
        for (int i = 0; i <= flavorOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, flavorOptions[i].getValue());
        }
        System.out.println();
        int flavorChoice;
        do {
            flavorChoice = scanner.nextInt();
            if (flavorChoice < 1 || flavorChoice > flavorOptions.length) {
                PrintColored.printColored("Invalid choice, please select a valid flavor option.", "red");
            }
        } while (flavorChoice < 1 || flavorChoice > flavorOptions.length);
        drink.setFlavor(flavorOptions[flavorChoice - 1]);
        ClearScreen.clearScreen();
        System.out.printf("Would you like to add a %s %s for $%.2f to your order?\n", drink.getSize(),
                drink.getFlavor(), drink.getPrice());
        int confirmation;

        System.out.println("Enter \t1) Confirm \t0) Cancel Drink");
        confirmation = scanner.nextInt();
        if (confirmation == 1) {
            currentOrder.addToOrder(drink);
            System.out.printf("Adding a %s %s to your order\n", drink.getSize(), drink.getFlavor());
        }
        ClearScreen.clearScreen();
        return new OrderScreen();

    }
}
