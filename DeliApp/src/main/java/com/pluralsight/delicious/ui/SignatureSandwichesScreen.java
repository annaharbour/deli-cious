package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.*;

import java.util.List;
import java.util.Scanner;

public class SignatureSandwichesScreen implements ScreenState {
    private final SignatureSandwich.SignatureSandwichType[] sandwichOptions =
            SignatureSandwich.getAllSignatureSandwiches();

    @Override
    public void display() {
        System.out.println("Here are our signature sandwiches:");
        System.out.println("Choose an option or enter 0 to return to Order Screen:");
        for (int i = 0; i < sandwichOptions.length; i++) {
            System.out.printf("\t%d) %s\n", i + 1, sandwichOptions[i].getSandwich().getSignatureSandwichName());
        }
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        int input;
        input = scanner.nextInt();
        if (input > 0 && input <= sandwichOptions.length) {

            SignatureSandwich original = sandwichOptions[input - 1].getSandwich();
            SignatureSandwich selected = new SignatureSandwich(original);
            System.out.println("You have selected :\n" + selected.getOrderLine());
            System.out.printf("Choose From the Following:\n\t1)Order %s?\n\t2) Customize %s\n\t3) Cancel",
                    selected.getSignatureSandwichName(), selected.getSignatureSandwichName());
            input = scanner.nextInt();
            switch (input) {
                case 0 -> System.out.println("Ok!");
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
            sandwich.getOrderLine();
            for (int i = 0; i <= options.length - 1; i++) {
                System.out.printf("\n\t%d) %s\n", i + 1, options[i]);
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
                case 9 -> {
                    List<Topping> toppings = sandwich.getToppings();
                    if (toppings.isEmpty()) {
                        System.out.println("There are no toppings to remove.");
                        break;
                    }
                    System.out.println("Select a topping to remove:");
                    for (int i = 0; i < toppings.size(); i++) {
                        Topping topping = toppings.get(i);
                        if (topping instanceof MeatTopping meatTopping) {
                            System.out.printf("\t%d) %s%n", i + 1, meatTopping);
                        } else if (topping instanceof CheeseTopping cheeseTopping) {
                            System.out.printf("\t%d) %s%n", i + 1, cheeseTopping);
                        } else if (topping instanceof RegularTopping regularTopping) {
                            System.out.printf("\t%d) %s%n", i + 1, regularTopping);
                        }
                    }
                    int toppingChoice;
                    do {
                        System.out.print("Your choice: ");
                        toppingChoice = scanner.nextInt();
                        if (toppingChoice < 1 || toppingChoice > toppings.size()) {
                            System.out.println("Invalid choice, please select a valid topping.");
                        }
                    } while (toppingChoice < 1 || toppingChoice > toppings.size());

                    Topping toRemove = toppings.get(toppingChoice - 1);
                    sandwich.removeTopping(toRemove);
                    System.out.println(toRemove + " removed.");
                }
                case 10 -> {
                    List<Sauce> sauces = sandwich.getSauces();
                    if (sauces.isEmpty()) {
                        System.out.println("There are no sauces to remove.");
                        break;
                    }

                    System.out.println("Select a sauce to remove:");
                    for (int i = 0; i < sauces.size(); i++) {
                        System.out.printf("\t%d) %s%n", i + 1, sauces.get(i));
                    }

                    int sauceChoice;
                    do {
                        System.out.print("Your choice: ");
                        sauceChoice = scanner.nextInt();
                        if (sauceChoice < 1 || sauceChoice > sauces.size()) {
                            System.out.println("Invalid choice, please select a valid sauce.");
                        }
                    } while (sauceChoice < 1 || sauceChoice > sauces.size());

                    Sauce removedSauce = sauces.get(sauceChoice - 1);
                    sandwich.removeSauce(removedSauce);
                    System.out.println(removedSauce + " removed.");

                }
                case 11 -> {
                    List<Side> sides = sandwich.getSides();
                    if (sides.isEmpty()) {
                        System.out.println("There are no sides to remove.");
                        break;
                    }
                    System.out.println("Select a side to remove:");
                    for (int i = 0; i < sides.size(); i++) {
                        System.out.printf("\t%d) %s%n", i + 1, sides.get(i));
                    }
                    int sideChoice;
                    do {
                        System.out.print("Your choice: ");
                        sideChoice = scanner.nextInt();
                        if (sideChoice < 1 || sideChoice > sides.size()) {
                            System.out.println("Invalid choice, please select a valid sauce.");
                        }
                    } while (sideChoice < 1 || sideChoice > sides.size());

                    Side removedSide = sides.get(sideChoice - 1);
                    sandwich.removeSide(removedSide);
                    System.out.println(removedSide + " removed.");
                }
            }
        } while (choice != 12);
        return sandwich;
    }
}
