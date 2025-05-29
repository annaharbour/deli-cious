package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.ui.utils.ClearScreen;

import java.util.Scanner;

public class OrderScreen implements ScreenState {
    @Override
    public void display() {
        ClearScreen.clearScreen();
        System.out.println("========= Place an Order ========");
    }

    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        String[] options = {"1) Add Sandwich", "2) Add Drink", "3) Add Chips", "4) Checkout", "0) Cancel " +
                "Order"};
        for (String option : options) {
            System.out.println("\t" + option);
        }
        int input = scanner.nextInt();
        return switch (input) {
            case 0 -> {
                currentOrder.clear();
                yield new OrderScreen();
            }
            case 1 -> new AddSandwichScreen();
            case 2 -> new AddDrinkScreen();
            case 3 -> new AddChipsScreen();
            case 4 -> new CheckoutScreen();
            default -> {
                System.out.println("Invalid input. Try again");
                yield this;
            }
        };
    }
}
