package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Drink;
import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.models.Sauce;

import java.util.Scanner;

public class AddDrinkScreen implements ScreenState {
    @Override
    public void display() {
        System.out.println("What size drink would you like?");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        Drink.Size[] drinkSizeOptions = Drink.getAllDrinkSizeOptions();
        for (int i = 0; i <= drinkSizeOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s", i + 1, drinkSizeOptions[i].getValue());
        }
        int drinkSizeChoice;
        do {
            drinkSizeChoice = scanner.nextInt();
            if (drinkSizeChoice < 1 || drinkSizeChoice > drinkSizeOptions.length) {
                System.out.println("Invalid choice, please select a valid meat option.");
            }
        } while (drinkSizeChoice < 1 || drinkSizeChoice > drinkSizeOptions.length);

        Drink drink = new Drink();
        switch (drinkSizeChoice) {
            case 1 -> {
                System.out.println("Ordering small drink");
                drink.setSize(Drink.Size.SMALL);
            }
            case 2 -> {
                System.out.println("Ordering medium drink");
                drink.setSize(Drink.Size.MEDIUM);
            }
            case 3 -> {
                System.out.println("Ordering large drink");
                drink.setSize(Drink.Size.LARGE);
            }
            default -> {
                System.out.println("Invalid option");
            }
        };
//TODO: Flavor and ERROR HANDLING
        System.out.println("What flavor drink?");
        Drink.Flavor[] flavorOptions = Drink.getAllDrinkFlavorOptions();
        for (int i = 0; i <= flavorOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s", i + 1, flavorOptions[i].getValue());
        }
        int sauceChoice;
        do {
            sauceChoice = scanner.nextInt();
            if (sauceChoice < 1 || sauceChoice > flavorOptions.length) {
                System.out.println("Invalid choice, please select a valid sauce option.");
            }
        } while (sauceChoice < 1 || sauceChoice > flavorOptions.length);
        drink.setFlavor(flavorOptions[flavorChoice -1]);

            currentOrder.addToOrder(drink);
        return this;
    }
}
