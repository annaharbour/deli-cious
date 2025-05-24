package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class AddDrinkScreen implements ScreenState {
    private String[] drinkSizes = {"1) Small", "2) Medium", "3) Large", "0) Go back"};
    @Override
    public void display() {
        System.out.println("What size drink would you like?");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        int input = scanner.nextInt();
        return switch (input){
            case 0 -> new OrderScreen();
            case 1 -> {
                System.out.println("Ordering small drink");
                yield new OrderScreen();
            }
            case 2 -> {
                System.out.println("Ordering medium drink");
                yield new OrderScreen();
            }
            case 3 -> {
                System.out.println("Ordering large drink");
                yield new OrderScreen();
            }
            default -> {
                System.out.println("Invalid option");;
                yield this;
            }
        };
    }
}
