package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public interface ScreenState {
    void display();
    ScreenState handleInput(Scanner scanner, Order currentOrder);
}
