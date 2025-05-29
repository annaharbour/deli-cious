package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.dao.ReceiptWriter;
import com.pluralsight.delicious.models.Customer;
import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.ui.utils.ClearScreen;

import java.util.Scanner;

public class CheckoutScreen implements ScreenState {
    @Override
    public void display() {
        ClearScreen.clearScreen();
        System.out.println("========= CHECKOUT =========");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        System.out.println(currentOrder);
        System.out.println("Enter \t1) Confirm \t2) Add More\t0) Cancel Order");
        int input;
        do {
            input = scanner.nextInt();
            switch (input) {
                case 1 -> {
                    System.out.println("Enter your name: ");
                    scanner.nextLine();
                    String customerName = scanner.nextLine();
                    currentOrder.setCustomer(new Customer(customerName));
                    ReceiptWriter rw = new ReceiptWriter(currentOrder);
                    rw.writeLines();
                    return null;
                }
                case 2 -> {
                    ClearScreen.clearScreen();
                    return new OrderScreen();
                }
                case 0 -> {
                    currentOrder.clear();
                    return new OrderScreen();
                }
            }
        } while (true);
    }
}
