package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.*;
import com.pluralsight.delicious.ui.utils.ClearScreen;
import com.pluralsight.delicious.ui.utils.PrintColored;
import com.pluralsight.delicious.ui.utils.SandwichBuilderHelper;

import java.util.Scanner;

public class CustomizeSandwichScreen implements ScreenState {
    @Override
    public void display() {
        PrintColored.printColored("\uD83E\uDD6A====== BUILD YOUR SANDWICH ======\uD83E\uDD6A", "magenta");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        Sandwich sandwich = new Sandwich();

        SandwichBuilderHelper.chooseSize(scanner, sandwich);
        SandwichBuilderHelper.chooseBread(scanner, sandwich);

        int userChoice;
        do {
            ClearScreen.clearScreen();
            PrintColored.printColored(sandwich.getReceiptLine(), "green");
            PrintColored.printColored("Add to your sandwich: ", "yellow");
            String[] customSandwichOptions = {"1) \uD83C\uDF56 Add Meat", "2) \uD83E\uDDC0 Add Cheese", "3) \uD83E" +
                    "\uDD6C Add Other" +
                    " Toppings",
                    "4) \uD83E\uDD6B Add Sauce",
                    "5) \uD83E\uDD63 Add Sides", "6) ✔\uFE0F Continue", "0) ❌ Cancel"};
            for(String option : customSandwichOptions){
                System.out.println("\t " + option);
            }
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 0 -> {
                    PrintColored.printColored("Canceling sandwich.", "yellow");
                    return new OrderScreen();
                }
                case 1 -> SandwichBuilderHelper.chooseMeat(scanner, sandwich);
                case 2 -> SandwichBuilderHelper.chooseCheese(scanner, sandwich);
                case 3 -> SandwichBuilderHelper.chooseVeggies(scanner, sandwich);
                case 4 -> SandwichBuilderHelper.chooseSauces(scanner, sandwich);
                case 5 -> SandwichBuilderHelper.chooseSides(scanner, sandwich);
                case 6 -> SandwichBuilderHelper.chooseToasted(scanner, sandwich);
                default -> PrintColored.printColored("Choose a valid option", "red");
            }
        } while (userChoice != 6);
        ClearScreen.clearScreen();
        PrintColored.printColored("Here's your sandwich order", "yellow");
        PrintColored.printColored(sandwich.getReceiptLine(), "green");
        PrintColored.printColored("\nDoes everything look ok?", "yellow");
        String[] confirmationOptions = {"1) Confirm", "0) Cancel Sandwich"};
        for (String option : confirmationOptions) {
            System.out.println("\t" + option);
        }
        int input = 0;
        do {
            input = scanner.nextInt();
            switch (input) {
                case 1 -> {
                    PrintColored.printColored("Adding sandwich to order.", "yellow");
                    currentOrder.addToOrder(sandwich);
                }
                case 0 -> {
                    PrintColored.printColored("Canceling sandwich.", "yellow");
                }
                default -> PrintColored.printColored("Invalid input, please choose 1–2.", "red");
            }
        } while (input != 0 && input > confirmationOptions.length);
        return new OrderScreen();
    }
}
