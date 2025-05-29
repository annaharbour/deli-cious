package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.Order;
import com.pluralsight.delicious.models.SignatureSandwich;
import com.pluralsight.delicious.ui.utils.ClearScreen;
import com.pluralsight.delicious.ui.utils.PrintColored;
import com.pluralsight.delicious.ui.utils.SandwichBuilderHelper;

import java.util.Scanner;

public class SignatureSandwichesScreen implements ScreenState {

    @Override
    public void display() {
        ClearScreen.clearScreen();
        PrintColored.printColored("\uD83E\uDD64========= SIGNATURE SANDWICHES ===========\uD83E\uDD64", "magenta");
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        SignatureSandwich.SignatureSandwichType[] sandwichOptions =
                SignatureSandwich.getAllSignatureSandwiches();
        PrintColored.printColored("Select an option from our signature sandwiches:", "yellow");
        for (int i = 0; i < sandwichOptions.length; i++) {
            System.out.printf("\t%d) %s\n", i + 1, sandwichOptions[i].getSandwich().getSignatureSandwichName());
        }
        int input;
        input = scanner.nextInt();
        if (input > 0 && input <= sandwichOptions.length) {
            SignatureSandwich original = sandwichOptions[input - 1].getSandwich();
            SignatureSandwich selected = new SignatureSandwich(original);
            ClearScreen.clearScreen();
            PrintColored.printColored("You have selected :\n" + selected.getReceiptLine(), "green");
            String[] signatureSandwichOptions = {"1) Order", "2) Customize", "0) Cancel"};
            for (String option : signatureSandwichOptions) {
                System.out.printf("\n\t%s %s", option, selected.getSignatureSandwichName());
            }
            System.out.println();
            input = scanner.nextInt();
            switch (input) {
                case 0 -> PrintColored.printColored("No problem!", "yellow");
                case 1 -> currentOrder.addToOrder(selected);
                case 2 -> {
                    SignatureSandwich customized = customizeSandwich(selected, scanner);
                    currentOrder.addToOrder(customized);
                }
                case 3 -> PrintColored.printColored("Canceling sandwich order...", "yellow");
                default -> PrintColored.printColored("Invalid Option", "red");
            }
        }
        return new OrderScreen();
    }

    private static SignatureSandwich customizeSandwich(SignatureSandwich sandwich, Scanner scanner) {
        String[] options = {"\uD83E\uDD69 Add Meat", "\uD83E\uDDC0 Add Cheese", "\uD83E\uDD51 Add Toppings", "\uD83E" +
                "\uDD6B Add " +
                "Sauce", "\uD83E\uDD63 Add " +
                "Side", "➕➖ Change " +
                "Size",
                "\uD83C\uDF5E Change Bread", "\uD83D\uDD25 Change Toasted", "\uD83E\uDD53 Remove Topping", "\uD83E" +
                "\uDD6B Remove " +
                "Sauce",
                "\uD83E\uDD63 Remove Side", "✅ Done"};
        int choice;
        do {
            sandwich.getReceiptLine();
            ClearScreen.clearScreen();
            PrintColored.printColored("Customize your sandwich", "yellow");
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
