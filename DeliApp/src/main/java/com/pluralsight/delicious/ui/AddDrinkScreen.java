package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Drink;
import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class AddDrinkScreen implements ScreenState {
    @Override
    public void display() {
        System.out.println("What size drink would you like?");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        Drink.Size[] drinkOptions = Drink.getAllDrinkOptions();
        for (int i = 0; i <= drinkOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s", i + 1, drinkOptions[i].getValue());
        }
        int drinkChoice;
        do {
            drinkChoice = scanner.nextInt();
            if (drinkChoice < 1 || drinkChoice > drinkOptions.length) {
                System.out.println("Invalid choice, please select a valid meat option.");
            }
        } while (drinkChoice < 1 || drinkChoice > drinkOptions.length);

        return switch (drinkChoice) {
            case 0 -> new OrderScreen();
            case 1 -> {
                System.out.println("Ordering small drink");
                currentOrder.addToOrder(new Drink(Drink.Size.SMALL));
                yield new OrderScreen();
            }
            case 2 -> {
                System.out.println("Ordering medium drink");
                currentOrder.addToOrder(new Drink(Drink.Size.MEDIUM));
                yield new OrderScreen();
            }
            case 3 -> {
                System.out.println("Ordering large drink");
                currentOrder.addToOrder(new Drink(Drink.Size.LARGE));
                yield new OrderScreen();
            }
            default -> {
                System.out.println("Invalid option");
                ;
                yield this;
            }
        };
    }
}
