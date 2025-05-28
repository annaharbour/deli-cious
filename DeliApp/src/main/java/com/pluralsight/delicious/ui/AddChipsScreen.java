package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Chips;
import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class AddChipsScreen implements ScreenState {
    @Override
    public void display() {
        System.out.println("Would you like to add chips for $1.50?");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        Chips chips = new Chips();
        System.out.println("What flavor chips would you like?");
        Chips.Flavor[] flavorOptions = Chips.getAllChipFlavors();
        for (int i = 0; i <= flavorOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s", i + 1, flavorOptions[i].getValue());
        }
        int flavorChoice;
        do {
            flavorChoice = scanner.nextInt();
            if (flavorChoice < 1 || flavorChoice > flavorOptions.length) {
                System.out.println("Invalid choice, please select a valid flavor option.");
            }
        } while (flavorChoice < 1 || flavorChoice > flavorOptions.length);
        chips.setFlavor(flavorOptions[flavorChoice - 1]);

        System.out.printf("Would you like to add a bag of %s chips for $%.2f to your order?", chips.getFlavor(),
                chips.getPrice());
        int confirmation;
        do{
            System.out.println("Enter 1) Confirm 0) Cancel Chips");
            confirmation = scanner.nextInt();
            if(confirmation == 1){
                currentOrder.addToOrder(chips);
                System.out.println("Adding chips to your order");
            }
        } while (confirmation != 0 && confirmation != 1);
        return new OrderScreen();
    }
}
