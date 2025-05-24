package com.pluralsight.delicious;

import com.pluralsight.delicious.models.Customer;
import com.pluralsight.delicious.models.MenuItem;
import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.ui.HomeScreen;
import com.pluralsight.delicious.ui.ScreenState;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<MenuItem> menuItems = new ArrayList<>();
        Order currentOrder = new Order(menuItems);
        ScreenState currentScreen = new HomeScreen();

        while (currentScreen != null) {
            currentScreen.display();
            currentScreen = currentScreen.handleInput(scanner, currentOrder);
        }
        System.out.println("Thank you for using Deli-cious");
    }
}