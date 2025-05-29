package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.*;

import java.util.Scanner;

public class CustomizeSandwichScreen implements ScreenState {
    @Override
    public void display() {
        System.out.println("TODO: Display menu here");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        Sandwich sandwich = new Sandwich();
        SandwichBuilderHelper.chooseSize(scanner, sandwich);
        SandwichBuilderHelper.chooseBread(scanner, sandwich);
        System.out.println("Add to your sandwich:");

        int userChoice;
        do {
            System.out.println("\n\t1) Add Meat\n\t2) Add Cheese\n\t3) Add Other Toppings\n\t4) Add Sauce\n\t5) Add " +
                    "Sides\n\t6) Continue\n\t0) Cancel");
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 0 -> {
                    System.out.println("Canceling sandwich.");
                    return new OrderScreen();
                }
                case 1 -> SandwichBuilderHelper.chooseMeat(scanner, sandwich);
                case 2 -> SandwichBuilderHelper.chooseCheese(scanner, sandwich);
                case 3 -> SandwichBuilderHelper.chooseVeggies(scanner, sandwich);
                case 4 -> SandwichBuilderHelper.chooseSauces(scanner, sandwich);
                case 5 -> SandwichBuilderHelper.chooseSides(scanner, sandwich);
                case 6 -> {
                    System.out.println("Continuing");

                }
                default -> System.out.println("Choose a valid option");
            }
        } while (userChoice != 6);
        SandwichBuilderHelper.chooseToasted(scanner, sandwich);

        System.out.println("Here's your sandwich order");
        System.out.println(sandwich.getOrderLine());
        System.out.println("Does everything look ok?");
        String[] confirmationOptions = {"1) Confirm", "0) Cancel Sandwich"};
        for (String option : confirmationOptions) {
            System.out.println("\t" + option);
        }
        int input = 0;
        do {
            input = scanner.nextInt();
            switch (input) {
                case 1 -> {
                    System.out.println("Adding sandwich to order.");
                    currentOrder.addToOrder(sandwich);
                }
                case 0 -> {
                    System.out.println("Canceling sandwich.");
                }
                default -> System.out.println("Invalid input, please choose 1–2.");
            }
        } while (input != 0 && input > confirmationOptions.length);
        return new OrderScreen();
    }
}
