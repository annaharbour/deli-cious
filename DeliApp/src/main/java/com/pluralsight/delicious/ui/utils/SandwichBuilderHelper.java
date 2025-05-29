package com.pluralsight.delicious.ui.utils;

import com.pluralsight.delicious.models.*;

import java.util.List;
import java.util.Scanner;

public class SandwichBuilderHelper {

    public static void chooseBread(Scanner scanner, Sandwich sandwich) {
        Sandwich.BreadType[] breadOptions = Sandwich.getAllBreadOptions();
        System.out.println("Select bread type:");
        for (int i = 0; i <= breadOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, breadOptions[i].getValue());
        }
        int choice = handleInput(scanner, breadOptions.length);
        sandwich.setBreadType(breadOptions[choice - 1]);
    }

    public static void chooseSize(Scanner scanner, Sandwich sandwich) {
        Sandwich.SandwichSize[] sizeOptions = Sandwich.getAllSizeOptions();
        System.out.println("Select sandwich size:");
        for (int i = 0; i <= sizeOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, sizeOptions[i].getValue());
        }
        int choice = handleInput(scanner, sizeOptions.length);
        sandwich.setSandwichSize(sizeOptions[choice - 1]);
    }

    public static void chooseMeat(Scanner scanner, Sandwich sandwich) {
        ClearScreen.clearScreen();
        MeatTopping.MeatType[] meatOptions = MeatTopping.getAllMeatOptions();
        PrintColored.printColored("Select meat:", "yellow");
        for (int i = 0; i <= meatOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, meatOptions[i].getValue());
        }
        int choice = handleInput(scanner, meatOptions.length);
        System.out.printf("Extra %s?\t1) Yes\t2) No\n", meatOptions[choice - 1].getValue());
        int extra;
        do {
            extra = scanner.nextInt();
            if (extra == 1) {
                sandwich.addTopping(new MeatTopping(meatOptions[choice - 1], true));
            } else if (extra == 2) {
                sandwich.addTopping(new MeatTopping(meatOptions[choice - 1], false));
            } else {
                PrintColored.printColored("Invalid choice, please try again.", "red");
            }
        } while (extra != 1 && extra != 2);
    }

    public static void chooseCheese(Scanner scanner, Sandwich sandwich) {
        ClearScreen.clearScreen();
        CheeseTopping.CheeseType[] cheeseOptions = CheeseTopping.getAllCheeseOptions();
        PrintColored.printColored("Select cheese:", "yellow");
        for (int i = 0; i <= cheeseOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, cheeseOptions[i].getValue());
        }
        int choice = handleInput(scanner, cheeseOptions.length);
        System.out.printf("Would you like to add extra %s?\t1) Yes\t2) No\n", cheeseOptions[choice - 1].getValue());
        int extra;
        do {
            extra = scanner.nextInt();
            if (extra == 1) {
                sandwich.addTopping(new CheeseTopping(cheeseOptions[choice - 1], true));
            } else if (extra == 2) {
                sandwich.addTopping(new CheeseTopping(cheeseOptions[choice - 1], false));
            } else {
                PrintColored.printColored("Invalid choice, please try again.", "red");
            }
        } while (extra != 1 && extra != 2);
    }

    public static void chooseVeggies(Scanner scanner, Sandwich sandwich) {
        ClearScreen.clearScreen();
        RegularTopping.FreeTopping[] toppingOptions = RegularTopping.getAllRegularToppings();
        PrintColored.printColored("Select other toppings:", "yellow");
        for (int i = 0; i <= toppingOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, toppingOptions[i].getValue());
        }
        int choice = handleInput(scanner, toppingOptions.length);
        System.out.printf("Extra %s?\t1) Yes\t2) No\n", toppingOptions[choice - 1].getValue());
        int extra;
        do {
            extra = scanner.nextInt();
            if (extra == 1) {
                sandwich.addTopping(new RegularTopping(toppingOptions[choice - 1], true));
            } else if (extra == 2) {
                sandwich.addTopping(new RegularTopping(toppingOptions[choice - 1], false));
            } else {
                PrintColored.printColored("Invalid choice, please try again.", "red");
            }
        } while (extra != 1 && extra != 2);
    }

    public static void chooseSauces(Scanner scanner, Sandwich sandwich) {
        ClearScreen.clearScreen();
        Sauce.SauceType[] sauceOptions = Sauce.getAllSauceOptions();
        PrintColored.printColored("Select sauce:", "yellow");
        for (int i = 0; i <= sauceOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, sauceOptions[i].getValue());
        }
        int choice = handleInput(scanner, sauceOptions.length);
        sandwich.addSauce(new Sauce(sauceOptions[choice - 1]));
    }

    public static void chooseSides(Scanner scanner, Sandwich sandwich) {
        ClearScreen.clearScreen();
        PrintColored.printColored("Add Sides", "yellow");
        Side.SideType[] sideOptions = Side.getAllSideOptions();
        for (int i = 0; i <= sideOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, sideOptions[i].getValue());
        }
        int sideChoice = handleInput(scanner, sideOptions.length);
        switch (sideChoice) {
            case 1 -> sandwich.addSide(new Side(sideOptions[sideChoice - 1]));
            case 2 -> {
                Sauce.SauceType[] sauceOptions = Sauce.getAllSauceOptions();
                PrintColored.printColored("Select a sauce as a side:", "yellow");
                for (int i = 0; i < sauceOptions.length; i++) {
                    System.out.printf("\t%d) %s", i + 1, sauceOptions[i].getValue());
                }
                System.out.println();
                int sauceChoice = handleInput(scanner, sauceOptions.length);
                sandwich.addSide(new Side(Side.SideType.SAUCE, new Sauce(sauceOptions[sauceChoice - 1])));
            }
            default -> PrintColored.printColored("Invalid choice, please select a valid side option.", "red");
        }
    }

    public static void chooseToasted(Scanner scanner, Sandwich sandwich) {
        ClearScreen.clearScreen();
        PrintColored.printColored("Would you like your sandwich toasted?", "yellow");
        String[] toastOptions = {"Toast", "Don't Toast"};
        for (int i = 0; i <= toastOptions.length - 1; i++) {
            System.out.printf("\t%d) %s\n", i + 1, toastOptions[i]);
        }
        int choice = handleInput(scanner, 2);
        sandwich.setToasted(choice == 1);
    }


    public static void removeSide(SignatureSandwich sandwich, Scanner scanner) {
        ClearScreen.clearScreen();
        List<Side> sides = sandwich.getSides();
        if (sides.isEmpty()) {
            PrintColored.printColored("There are no sides to remove.", "yellow");
            return;
        }
        PrintColored.printColored("Select a side to remove:", "yellow");
        for (int i = 0; i < sides.size(); i++) {
            System.out.printf("\t%d) %s%n", i + 1, sides.get(i));
        }
        int sideChoice;
        do {
            PrintColored.printColored("Your choice: ", "yellow");
            sideChoice = scanner.nextInt();
            if (sideChoice < 1 || sideChoice > sides.size()) {
                PrintColored.printColored("Invalid choice, please select a valid sauce.", "red");
            }
        } while (sideChoice < 1 || sideChoice > sides.size());

        Side removedSide = sides.get(sideChoice - 1);
        sandwich.removeSide(removedSide);
        PrintColored.printColored(removedSide + " removed.", "green");
    }

    public static void removeSauce(SignatureSandwich sandwich, Scanner scanner) {
        ClearScreen.clearScreen();
        List<Sauce> sauces = sandwich.getSauces();
        if (sauces.isEmpty()) {
            PrintColored.printColored("There are no sauces to remove.", "yellow");
            return;
        }

        PrintColored.printColored("Select a sauce to remove:", "yellow");
        for (int i = 0; i < sauces.size(); i++) {
            System.out.printf("\t%d) %s%n", i + 1, sauces.get(i));
        }

        int sauceChoice;
        do {
            System.out.print("Your choice: ");
            sauceChoice = scanner.nextInt();
            if (sauceChoice < 1 || sauceChoice > sauces.size()) {
                PrintColored.printColored("Invalid choice, please select a valid sauce.", "red");
            }
        } while (sauceChoice < 1 || sauceChoice > sauces.size());

        Sauce removedSauce = sauces.get(sauceChoice - 1);
        sandwich.removeSauce(removedSauce);
        System.out.println(removedSauce + " removed.");
    }

    public static void removeTopping(SignatureSandwich sandwich, Scanner scanner) {
        ClearScreen.clearScreen();
        List<Topping> toppings = sandwich.getToppings();
        if (toppings.isEmpty()) {
            PrintColored.printColored("There are no toppings to remove.", "yellow");
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
        PrintColored.printColored(toRemove + " removed.", "green");
    }

    private static int handleInput(Scanner scanner, int maxOption) {
        int choice = -1;
        while (choice < 1 || choice > maxOption) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice < 1 || choice > maxOption) {
                    PrintColored.printColored("Invalid choice. Try again.", "red");
                }
            } else {
                PrintColored.printColored("Please enter a valid number.", "red");
                scanner.next();
            }
        }
        return choice;
    }
}
