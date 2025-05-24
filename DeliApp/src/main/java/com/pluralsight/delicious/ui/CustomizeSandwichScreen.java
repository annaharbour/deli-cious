package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.*;

import java.awt.*;
import java.util.Scanner;

public class CustomizeSandwichScreen implements ScreenState {
    @Override
    public void display() {
        System.out.println("TODO: Display menu here");
    }


    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        Sandwich sandwich = new Sandwich();
        System.out.println("Select sandwich size: 4 for 4\", 8 for 8\", 12 for 12\"");
        int sizeChoice = scanner.nextInt();
        switch (sizeChoice) {
            case 4 -> sandwich.setSize(Sandwich.Size.FOUR_INCH);
            case 8 -> sandwich.setSize(Sandwich.Size.EIGHT_INCH);
            case 12 -> sandwich.setSize(Sandwich.Size.TWELVE_INCH);
            default -> {
                System.out.println("Incorrect size, try again.");
                return new CustomizeSandwichScreen();
            }
        }

        chooseBread(scanner, sandwich);
        chooseMeat(scanner, sandwich);
        chooseCheese(scanner, sandwich);
        chooseVeggies(scanner, sandwich);
        chooseSauces(scanner, sandwich);
        chooseToasted(scanner, sandwich);

        // TODO: Return the next screen or the current screen based on user input
        return new OrderScreen();
    }

    private static void chooseToasted(Scanner scanner, Sandwich sandwich) {
        System.out.println("Do you want it toasted? (1) Yes 2) No");
        int toastedChoice = scanner.nextInt();
        switch (toastedChoice) {
            case 1 -> sandwich.setToasted(true);
            case 2 -> sandwich.setToasted(false);
            default -> System.out.println("Incorrect input");
        }
    }

    private static void chooseSauces(Scanner scanner, Sandwich sandwich) {
        System.out.println("Add Sauces");
        for (RegularTopping.FreeTopping topping : RegularTopping.FreeTopping.values()) {
            System.out.println(topping);
        }
    }

    private static void chooseVeggies(Scanner scanner, Sandwich sandwich) {
        System.out.println("Add veggies:");
        for (RegularTopping.FreeTopping cheeseTopping : RegularTopping.FreeTopping.values()) {
            System.out.println(cheeseTopping);
        }
        RegularTopping.FreeTopping[] toppingOptions = RegularTopping.FreeTopping.values();
        int toppingChoice;
        do {
            toppingChoice = scanner.nextInt();
            if (toppingChoice < 1 || toppingChoice > toppingOptions.length) {
                System.out.println("Invalid choice, please select a valid veg option.");
            }
        } while (toppingChoice < 1 || toppingChoice > toppingOptions.length);

        System.out.println("Would you like extra veg?\n\t1) Yes\n\t2) No");
        int extra = scanner.nextInt();
        if (extra == 1) {
            sandwich.addTopping(new RegularTopping(toppingOptions[toppingChoice - 1]));
        } else if (extra == 2) {
            sandwich.addTopping(new RegularTopping(toppingOptions[toppingChoice - 1]));
        } else {
            System.out.println("Invalid choice, please try again.");
            chooseMeat(scanner, sandwich);
        }
    }

    private static void chooseCheese(Scanner scanner, Sandwich sandwich) {
        System.out.println("Select a cheese option:");
        for (CheeseTopping.CheeseType cheeseTopping : CheeseTopping.CheeseType.values()) {
            System.out.println(cheeseTopping);
        }
        CheeseTopping.CheeseType[] cheeseOptions = CheeseTopping.CheeseType.values();
        int cheeseChoice;
        do {
            cheeseChoice = scanner.nextInt();
            if (cheeseChoice < 1 || cheeseChoice > cheeseOptions.length) {
                System.out.println("Invalid choice, please select a valid cheese option.");
            }
        } while (cheeseChoice < 1 || cheeseChoice > cheeseOptions.length);

        System.out.println("Would you like extra cheese?\n\t1) Yes\n\t2) No");
        int extra = scanner.nextInt();
        if (extra == 1) {
            sandwich.addTopping(new CheeseTopping(cheeseOptions[cheeseChoice - 1], true));
        } else if (extra == 2) {
            sandwich.addTopping(new CheeseTopping(cheeseOptions[cheeseChoice - 1], false));
        } else {
            System.out.println("Invalid choice, please try again.");
            chooseMeat(scanner, sandwich);
        }
    }

    private static void chooseMeat(Scanner scanner, Sandwich sandwich) {
        System.out.println("Select a meat option:");
        for (MeatTopping.MeatType topping : MeatTopping.MeatType.values()) {
            System.out.println(topping);
        }
        MeatTopping.MeatType[] meatOptions = MeatTopping.MeatType.values();
        int meatChoice;
        do {
            meatChoice = scanner.nextInt();
            if (meatChoice < 1 || meatChoice > meatOptions.length) {
                System.out.println("Invalid choice, please select a valid meat option.");
            }
        } while (meatChoice < 1 || meatChoice > meatOptions.length);

        System.out.println("Would you like extra meat?\n\t1) Yes\n\t2) No");
        int extra = scanner.nextInt();
        if (extra == 1) {
            sandwich.addTopping(new MeatTopping(meatOptions[meatChoice - 1], true));
        } else if (extra == 2) {
            sandwich.addTopping(new MeatTopping(meatOptions[meatChoice - 1], false));
        } else {
            System.out.println("Invalid choice, please try again.");
            chooseMeat(scanner, sandwich);
        }
    }

    private static void chooseBread(Scanner scanner, Sandwich sandwich) {
        System.out.println("Select your bread:\n\t1) White \n\t2) Rye\n\t3) Multigrain\n\t4) Whole Grain");
        int breadChoice = scanner.nextInt();
        switch (breadChoice) {
            case 1 -> sandwich.setBreadType(Sandwich.BreadType.WHITE);
            case 2 -> sandwich.setBreadType(Sandwich.BreadType.RYE);
            case 3 -> sandwich.setBreadType(Sandwich.BreadType.MULTIGRAIN);
            case 4 -> sandwich.setBreadType(Sandwich.BreadType.WHOLE_GRAIN);
            default -> {
                System.out.println("Incorrect selection");
                chooseBread(scanner, sandwich);
            }
        }
    }
}
