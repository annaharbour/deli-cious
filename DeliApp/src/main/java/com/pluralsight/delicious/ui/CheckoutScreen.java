package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class CheckoutScreen implements ScreenState{
    @Override
    public void display() {
        System.out.println("Checking out...");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        return null;
    }
}
