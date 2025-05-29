package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.*;

import java.util.List;
import java.util.Scanner;

public class SandwichBuilderHelper {

    public static void chooseBread(Scanner scanner, Sandwich sandwich) {
        Sandwich.BreadType[] breadOptions = Sandwich.getAllBreadOptions();
        System.out.println("Select bread type:");
        for (int i = 0; i <= breadOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, breadOptions[i].getValue());
        }
        int choice = handleInput(scanner, breadOptions.length);
        sandwich.setBreadType(breadOptions[choice - 1]);
    }

    public static void chooseSize(Scanner scanner, Sandwich sandwich) {
        Sandwich.SandwichSize[] sizeOptions = Sandwich.getAllSizeOptions();
        System.out.println("Select sandwich size:");
        for (int i = 0; i <= sizeOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, sizeOptions[i].getValue());
        }
        int choice = handleInput(scanner, sizeOptions.length);
        sandwich.setSandwichSize(sizeOptions[choice - 1]);
    }

    public static void chooseMeat(Scanner scanner, Sandwich sandwich) {
        MeatTopping.MeatType[] meatOptions = MeatTopping.getAllMeatOptions();
        System.out.println("Select meat:");
        for (int i = 0; i <= meatOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, meatOptions[i].getValue());
        }
        int choice = handleInput(scanner, meatOptions.length);
        System.out.printf("Extra %s?\n\t1) Yes\n\t2) No\n", meatOptions[choice - 1].getValue());
        int extra;
        do {
            extra = scanner.nextInt();
            if (extra == 1) {
                sandwich.addTopping(new MeatTopping(meatOptions[choice - 1], true));
            } else if (extra == 2) {
                sandwich.addTopping(new MeatTopping(meatOptions[choice - 1], false));
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        } while (extra != 1 && extra != 2);
    }

    public static void chooseCheese(Scanner scanner, Sandwich sandwich) {
        CheeseTopping.CheeseType[] cheeseOptions = CheeseTopping.getAllCheeseOptions();
        System.out.println("Select cheese:");
        for (int i = 0; i <= cheeseOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, cheeseOptions[i].getValue());
        }
        int choice = handleInput(scanner, cheeseOptions.length);
        System.out.printf("Would you like to add extra %s?\n\t1) Yes\n\t2) No\n", cheeseOptions[choice - 1].getValue());
        int extra;
        do {
            extra = scanner.nextInt();
            if (extra == 1) {
                sandwich.addTopping(new CheeseTopping(cheeseOptions[choice - 1], true));
            } else if (extra == 2) {
                sandwich.addTopping(new CheeseTopping(cheeseOptions[choice - 1], false));
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        } while (extra != 1 && extra != 2);
    }

    public static void chooseVeggies(Scanner scanner, Sandwich sandwich) {
        RegularTopping.FreeTopping[] toppingOptions = RegularTopping.getAllRegularToppings();
        System.out.println("Select other toppings:");
        for (int i = 0; i <= toppingOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, toppingOptions[i].getValue());
        }
        int choice = handleInput(scanner, toppingOptions.length);
        sandwich.addTopping(new RegularTopping(toppingOptions[choice - 1]));
    }

    public static void chooseSauces(Scanner scanner, Sandwich sandwich) {
        Sauce.SauceType[] sauceOptions = Sauce.getAllSauceOptions();
        System.out.println("Select sauce:");
        for (int i = 0; i <= sauceOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, sauceOptions[i].getValue());
        }
        int choice = handleInput(scanner, sauceOptions.length);
        sandwich.addSauce(new Sauce(sauceOptions[choice - 1]));
    }

    public static void chooseSides(Scanner scanner, Sandwich sandwich) {
        System.out.println("Add Sides");
        Side.SideType[] sideOptions = Side.getAllSideOptions();
        for (int i = 0; i <= sideOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, sideOptions[i].getValue());
        }
        int sideChoice = handleInput(scanner, sideOptions.length);
        switch (sideChoice) {
            case 1 -> sandwich.addSide(new Side(sideOptions[sideChoice - 1]));
            case 2 -> {
                Sauce.SauceType[] sauceOptions = Sauce.getAllSauceOptions();
                System.out.println("Select a sauce as a side:");
                for (int i = 0; i < sauceOptions.length; i++) {
                    System.out.printf("\n\t%d) %s", i + 1, sauceOptions[i].getValue());
                }
                int sauceChoice = handleInput(scanner, sauceOptions.length);
                sandwich.addSide(new Side(Side.SideType.SAUCE, new Sauce(sauceOptions[sauceChoice - 1])));
            }
            default -> System.out.println("Invalid choice, please select a valid side option.");
        }
    }

    public static void chooseToasted(Scanner scanner, Sandwich sandwich) {
        System.out.println("Would you like your sandwich toasted?");
        String[] toastOptions = {"Toast", "Don't Toast"};
        for (int i = 0; i <= toastOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, toastOptions[i]);
        }
        int choice = handleInput(scanner, 2);
        sandwich.setToasted(choice == 1);
    }


    public static void removeSide(SignatureSandwich sandwich, Scanner scanner) {
        List<Side> sides = sandwich.getSides();
        if (sides.isEmpty()) {
            System.out.println("There are no sides to remove.");
            return;
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

    public static void removeSauce(SignatureSandwich sandwich, Scanner scanner) {
        List<Sauce> sauces = sandwich.getSauces();
        if (sauces.isEmpty()) {
            System.out.println("There are no sauces to remove.");
            return;
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

    public static void removeTopping(SignatureSandwich sandwich, Scanner scanner) {
        List<Topping> toppings = sandwich.getToppings();
        if (toppings.isEmpty()) {
            System.out.println("There are no toppings to remove.");
            return;
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

    private static int handleInput(Scanner scanner, int maxOption) {
        int choice = -1;
        while (choice < 1 || choice > maxOption) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 1 || choice > maxOption) {
                    System.out.println("Invalid choice. Try again.");
                }
            } else {
                System.out.println("Please enter a valid number.");
                scanner.next(); // Clear the invalid input
            }
        }
        return choice;
    }
}
