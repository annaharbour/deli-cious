package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class CustomizeSandwichScreen implements ScreenState {
    @Override
    public void display() {
        System.out.println("TODO: Display menu here");
    }


    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {

        System.out.println("Select bread type (e.g., 1 for White, 2 for Wheat):");
        int breadChoice = scanner.nextInt();

        int[] sandwichSizes = {4, 8, 12};
        System.out.println("Select sandwich size: 4 for 4\", 8 for 8\", 12 for 12\"");
        int sizeChoice = scanner.nextInt();

        System.out.println("Select toppings");
        int toppingChoice = scanner.nextInt();

        System.out.println("Do you want it toasted? (1) Yes 2) No");
        int toastedChoice = scanner.nextInt();

        // Return the next screen or the current screen based on user input
        return new OrderScreen();
    }
}
