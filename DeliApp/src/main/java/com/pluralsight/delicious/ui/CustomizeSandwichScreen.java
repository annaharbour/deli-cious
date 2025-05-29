package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.*;
import com.pluralsight.delicious.ui.utils.ClearScreen;
import com.pluralsight.delicious.ui.utils.SandwichBuilderHelper;

import java.util.Scanner;

public class CustomizeSandwichScreen implements ScreenState {
    @Override
    public void display() {
        ClearScreen.clearScreen();
        System.out.println("====== BUILD YOUR SANDWICH ======");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        Sandwich sandwich = new Sandwich();
        SandwichBuilderHelper.chooseSize(scanner, sandwich);
        SandwichBuilderHelper.chooseBread(scanner, sandwich);
        int userChoice;
        do {
            ClearScreen.clearScreen();
            System.out.println("Add to your sandwich: ");
            String[] customSandwichOptions = {"1) Add Meat", "2) Add Cheese", "3) Add Other Toppings", "4) Add Sauce",
                    "5) Add Sides", "6) Continue", "0) Cancel"};
            for(String option : customSandwichOptions){
                System.out.println("\t " + option);
            }
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
                case 6 -> SandwichBuilderHelper.chooseToasted(scanner, sandwich);
                default -> System.out.println("Choose a valid option");
            }
        } while (userChoice != 6);
        ClearScreen.clearScreen();
        System.out.println("Here's your sandwich order");
        System.out.println(sandwich.getReceiptLine());
        System.out.println("\nDoes everything look ok?");
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
