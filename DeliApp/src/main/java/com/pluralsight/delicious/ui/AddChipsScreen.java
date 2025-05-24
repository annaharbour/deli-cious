package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Chips;
import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class AddChipsScreen implements ScreenState {
    @Override
    public void display() {
        System.out.println("Would you like to add chips for $1.50?");
        System.out.println("\n\t1) Yen\n\t0) Cancel");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        int input = scanner.nextInt();
        return switch (input) {
            case 1 -> {
                System.out.println("How many bags of chips would you like?");
                int numChips = scanner.nextInt();
                for(int i = 0; i <= numChips; i++){
                    currentOrder.addToOrder(new Chips());
                }
                yield new OrderScreen();
            }
            case 2 -> new OrderScreen();
            default -> {
                System.out.println("Must select 1 or 2");
                yield this;
            }
        };
    }
}
