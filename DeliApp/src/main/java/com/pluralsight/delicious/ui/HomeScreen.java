package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;

import java.util.Scanner;

public class HomeScreen implements ScreenState {
    private String[] options = {"1) New Order", "0) Exit"};

    @Override
    public void display(){
        System.out.println("Welcome to Deli-cious! Select from the following options: ");
        for(int i = 0; i < options.length; i++){
            System.out.println("\t" + options[i]);;
        }
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        String input = scanner.nextLine();
        return switch (input) {
            case "1" -> new OrderScreen();
            case "0" -> null;
            default -> {
                System.out.println("Invalid input. Try again");
                yield this;
            }
        };
    }

}
