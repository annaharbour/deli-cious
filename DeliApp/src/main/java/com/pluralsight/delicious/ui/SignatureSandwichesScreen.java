package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class SignatureSandwichesScreen implements ScreenState {
    @Override
    public void display() {
        System.out.println("Display list of signature sandwich options here");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        return null;
    }
}
