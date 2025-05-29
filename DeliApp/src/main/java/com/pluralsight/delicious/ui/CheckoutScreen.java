package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.dao.ReceiptWriter;
import com.pluralsight.delicious.models.Customer;
import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.ui.utils.ClearScreen;
import com.pluralsight.delicious.ui.utils.PrintColored;

import java.util.Scanner;

public class CheckoutScreen implements ScreenState {
    @Override
    public void display() {
        ClearScreen.clearScreen();
        PrintColored.printColored("\uD83D\uDCB3========= CHECKOUT =========\uD83D\uDCB3", "cyan");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        PrintColored.printColored(currentOrder.toString(), "blue");
        System.out.println("Enter \t1) Confirm \t2) Add More\t0) Cancel Order");
        int input;
        do {
            input = scanner.nextInt();
            switch (input) {
                case 1 -> {
                    PrintColored.printColored("Enter your name: ", "yellow");
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
                    return new HomeScreen();
                }
            }
        } while (true);
    }
}
