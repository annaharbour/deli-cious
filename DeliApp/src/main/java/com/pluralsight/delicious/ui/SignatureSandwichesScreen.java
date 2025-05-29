package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.models.SignatureSandwich;
import com.pluralsight.delicious.ui.utils.ClearScreen;
import com.pluralsight.delicious.ui.utils.SandwichBuilderHelper;

import java.util.Scanner;

public class SignatureSandwichesScreen implements ScreenState {

    @Override
    public void display() {
        ClearScreen.clearScreen();
        System.out.println("========= SIGNATURE SANDWICHES ===========");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        SignatureSandwich.SignatureSandwichType[] sandwichOptions =
                SignatureSandwich.getAllSignatureSandwiches();
        System.out.println("Select an option from our signature sandwiches:");
        for (int i = 0; i < sandwichOptions.length; i++) {
            System.out.printf("\t%d) %s\n", i + 1, sandwichOptions[i].getSandwich().getSignatureSandwichName());
        }
        int input;
        input = scanner.nextInt();
        if (input > 0 && input <= sandwichOptions.length) {
            SignatureSandwich original = sandwichOptions[input - 1].getSandwich();
            SignatureSandwich selected = new SignatureSandwich(original);
            ClearScreen.clearScreen();
            System.out.println("You have selected :\n" + selected.getReceiptLine());
            String[] signatureSandwichOptions = {"1) Order", "2) Customize", "0) Cancel"};
            for (String option : signatureSandwichOptions) {
                System.out.printf("\n\t%s %s", option, selected.getSignatureSandwichName());
            }
            System.out.println();
            input = scanner.nextInt();
            switch (input) {
                case 0 -> System.out.println("No problem!");
                case 1 -> currentOrder.addToOrder(selected);
                case 2 -> {
                    SignatureSandwich customized = customizeSandwich(selected, scanner);
                    currentOrder.addToOrder(customized);
                }
                case 3 -> System.out.println("Canceling sandwich order...");
                default -> System.out.println("Invalid Option");
            }
        }
        return new OrderScreen();
    }

    private static SignatureSandwich customizeSandwich(SignatureSandwich sandwich, Scanner scanner) {

        String[] options = {"Add Meat", "Add Cheese", "Add Toppings", "Add Sauce", "Add Side", "Change Size",
                "Change Bread", "Change Toasted", "Remove Topping", "Remove Sauce",
                "Remove Side", "Done"};
        int choice;
        do {
            sandwich.getReceiptLine();
            ClearScreen.clearScreen();
            System.out.println("Customize your sandwich");
            for (int i = 0; i <= options.length - 1; i++) {
                System.out.printf("\t%d) %s\n", i + 1, options[i]);
            }
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> SandwichBuilderHelper.chooseMeat(scanner, sandwich);
                case 2 -> SandwichBuilderHelper.chooseCheese(scanner, sandwich);
                case 3 -> SandwichBuilderHelper.chooseVeggies(scanner, sandwich);
                case 4 -> SandwichBuilderHelper.chooseSauces(scanner, sandwich);
                case 5 -> SandwichBuilderHelper.chooseSides(scanner, sandwich);
                case 6 -> SandwichBuilderHelper.chooseSize(scanner, sandwich);
                case 7 -> SandwichBuilderHelper.chooseBread(scanner, sandwich);
                case 8 -> SandwichBuilderHelper.chooseToasted(scanner, sandwich);
                case 9 -> SandwichBuilderHelper.removeTopping(sandwich, scanner);
                case 10 -> SandwichBuilderHelper.removeSauce(sandwich, scanner);
                case 11 -> SandwichBuilderHelper.removeSide(sandwich, scanner);
            }
        } while (choice != 12);
        return sandwich;
    }

}
