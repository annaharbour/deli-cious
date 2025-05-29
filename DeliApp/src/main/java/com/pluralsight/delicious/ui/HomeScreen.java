package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.ui.utils.ClearScreen;

import java.util.Scanner;

public class HomeScreen implements ScreenState {

    @Override
    public void display() {
        ClearScreen.clearScreen();
        System.out.println("============ Welcome to Deli-cious! ===========");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        System.out.println("Select from the following options: ");
        String[] options = {"1) New Order", "0) Exit"};
        for (String option : options) {
            System.out.println("\t" + option);
        }
        String input = scanner.nextLine();
        return switch (input) {
            case "1" -> new OrderScreen();
            case "0" -> null;
            default -> {
                System.out.println("Invalid input. Try again");
                yield this;
            }
        };
    }

}
