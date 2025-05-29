package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.ui.utils.ClearScreen;
import com.pluralsight.delicious.ui.utils.PrintColored;

import java.util.Scanner;

public class HomeScreen implements ScreenState {

    @Override
    public void display() {
        ClearScreen.clearScreen();
        PrintColored.printColored("\uD83E\uDD64============ Welcome to Deli-cious! ===========\uD83E\uDD64", "magenta");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        PrintColored.printColored("Select from the following options: ", "yellow");
        String[] options = {"1) New Order", "0) Exit"};
        for (String option : options) {
            System.out.println("\t" + option);
        }
        String input = scanner.nextLine();
        return switch (input) {
            case "1" -> new OrderScreen();
            case "0" -> null;
            default -> {
                PrintColored.printColored("Invalid input. Try again", "red");
                yield this;
            }
        };
    }

}
