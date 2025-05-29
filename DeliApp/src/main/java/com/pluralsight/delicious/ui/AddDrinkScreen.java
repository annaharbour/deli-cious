package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Drink;
import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class AddDrinkScreen implements ScreenState {
    @Override
    public void display() {
        System.out.println("====== Order a Drink ======");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        Drink drink = new Drink();
        System.out.println("What size drink would you like?");
        Drink.Size[] drinkSizeOptions = Drink.getAllDrinkSizeOptions();
        for (int i = 0; i <= drinkSizeOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, drinkSizeOptions[i].getValue());
        }
        System.out.println();
        int drinkSizeChoice;
        do {
            drinkSizeChoice = scanner.nextInt();
            if (drinkSizeChoice < 1 || drinkSizeChoice > drinkSizeOptions.length) {
                System.out.println("Invalid choice, please select a valid size option.");
            }
        } while (drinkSizeChoice < 1 || drinkSizeChoice > drinkSizeOptions.length);

        drink.setSize(drinkSizeOptions[drinkSizeChoice - 1]);

        System.out.println("What flavor drink would you like?");
        Drink.Flavor[] flavorOptions = Drink.getAllDrinkFlavorOptions();
        for (int i = 0; i <= flavorOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, flavorOptions[i].getValue());
        }
        System.out.println();
        int flavorChoice;
        do {
            flavorChoice = scanner.nextInt();
            if (flavorChoice < 1 || flavorChoice > flavorOptions.length) {
                System.out.println("Invalid choice, please select a valid flavor option.");
            }
        } while (flavorChoice < 1 || flavorChoice > flavorOptions.length);
        drink.setFlavor(flavorOptions[flavorChoice - 1]);
//TODO: CLEAR SCREEN
        System.out.printf("Would you like to add a %s %s for $%.2f to your order?\n", drink.getSize(),
                drink.getFlavor(), drink.getPrice());
        int confirmation;

        System.out.println("Enter \t1) Confirm \t0) Cancel Drink");
        confirmation = scanner.nextInt();
        if (confirmation == 1) {
            currentOrder.addToOrder(drink);
            System.out.printf("Adding a %s %s to your order\n", drink.getSize(), drink.getFlavor());
        }
        return new OrderScreen();

    }
}
