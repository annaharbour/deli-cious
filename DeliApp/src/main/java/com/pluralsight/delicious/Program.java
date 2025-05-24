package com.pluralsight.delicious;

import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.ui.HomeScreen;
import com.pluralsight.delicious.ui.ScreenState;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Order currentOrder = new Order();
        ScreenState currentScreen = new HomeScreen();

        while (currentScreen != null) {
            currentScreen.display();
            currentScreen = currentScreen.handleInput(scanner, currentOrder);
        }
        System.out.println("Thank you for using Deli-cious");
    }
}