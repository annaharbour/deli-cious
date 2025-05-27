package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Customer;
import com.pluralsight.delicious.models.Order;

import java.util.ArrayList;
import java.util.Scanner;

public class CheckoutScreen implements ScreenState {
    @Override
    public void display() {
        System.out.println("Enter the name for your order?");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        scanner.nextLine();
        System.out.println("Here is your order");
        System.out.println(currentOrder);
        System.out.println("Enter 1) Confirm or 0) Cancel");
        int input;
        do {
            input = scanner.nextInt();
            switch (input) {
                case 1 -> {
                    System.out.println("TODO: add order to receipt folder");
                    System.out.println(currentOrder);
                }
                case 2 -> {
                    currentOrder = new Order(new ArrayList<>());

                }
            }
        } while (input != 0 && input != 1);
        return new OrderScreen();
    }
}
