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
        Sandwich.SandwichSize[] sizeOptions = Sandwich.getAllSizeOptions();
        System.out.println("Select sandwich size:");
        for (int i = 0; i <= sizeOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s", i + 1, sizeOptions[i].getValue());
        }
        int sizeChoice = scanner.nextInt();
        switch (sizeChoice) {
            case 1 -> sandwich.setSandwichSize(Sandwich.SandwichSize.FOUR_INCH);
            case 2 -> sandwich.setSandwichSize(Sandwich.SandwichSize.EIGHT_INCH);
            case 3 -> sandwich.setSandwichSize(Sandwich.SandwichSize.TWELVE_INCH);
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
        System.out.println("Here's your order");
        System.out.println(sandwich.getOrderLine());
        System.out.println("Would you like to: \n\t1) Order another sandwich\n\t2) Order multiple of this " +
                "sandwich\n\t3) Order another sandwich\n\t4) Edit this sandwich\n\t5) Return to order " +
                "screen\n\t6) Checkout\n\t0) " +
                "Cancel Order");
//        TODO: screens switch
        currentOrder.addToOrder(sandwich);
        return new OrderScreen();
    }

    private static void chooseToasted(Scanner scanner, Sandwich sandwich) {
        System.out.println("Do you want it toasted? \n\t(1) Yes \n\t2) No");
        int toastedChoice = scanner.nextInt();
        do {
            switch (toastedChoice) {
                case 1 -> sandwich.setToasted(true);
                case 2 -> sandwich.setToasted(false);
                default -> {
                    System.out.println("Incorrect input");
                }
            }
        } while (toastedChoice != 1 && toastedChoice != 2);
    }

    private static void chooseSauces(Scanner scanner, Sandwich sandwich) {
        System.out.println("Add Sauces");
        Sauce.SauceType[] sauceOptions = Sauce.getAllSauceOptions();
        for (int i = 0; i <= sauceOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s", i + 1, sauceOptions[i].getSauceName());
        }
        int sauceChoice;
        do {
            sauceChoice = scanner.nextInt();
            if (sauceChoice < 1 || sauceChoice > sauceOptions.length) {
                System.out.println("Invalid choice, please select a valid sauce option.");
            }
        } while (sauceChoice < 1 || sauceChoice > sauceOptions.length);

    }

    private static void chooseVeggies(Scanner scanner, Sandwich sandwich) {
        System.out.println("Add veggies:");
        RegularTopping.FreeTopping[] toppingOptions = RegularTopping.getAllRegularToppings();
        for (int i = 0; i <= toppingOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s", i + 1, toppingOptions[i].getToppingName());
        }
        int toppingChoice;
        do {
            toppingChoice = scanner.nextInt();
            if (toppingChoice < 1 || toppingChoice > toppingOptions.length) {
                System.out.println("Invalid choice, please select a valid veg option.");
            }
        } while (toppingChoice < 1 || toppingChoice > toppingOptions.length);

        System.out.println("Would you like more veggies?\n\t1) Yes\n\t2) No");
        int moreVeggies = scanner.nextInt();
        if (moreVeggies == 1) {
            sandwich.addTopping(new RegularTopping(toppingOptions[toppingChoice - 1]));
            chooseVeggies(scanner, sandwich);
        } else if (moreVeggies == 2) {
            sandwich.addTopping(new RegularTopping(toppingOptions[toppingChoice - 1]));
        } else {
            System.out.println("Invalid choice, please try again.");
            chooseVeggies(scanner, sandwich);
        }
    }

    private static void chooseCheese(Scanner scanner, Sandwich sandwich) {
        System.out.println("Select a cheese option:");
        CheeseTopping.CheeseType[] cheeseOptions = CheeseTopping.getAllCheeseOptions();
        for (int i = 0; i <= cheeseOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s", i + 1, cheeseOptions[i].getCheeseName());
        }
        int cheeseChoice;
        do {
            cheeseChoice = scanner.nextInt();
            if (cheeseChoice < 1 || cheeseChoice > cheeseOptions.length) {
                System.out.println("Invalid choice, please select a valid cheese option.");
            }
        } while (cheeseChoice < 1 || cheeseChoice > cheeseOptions.length);

        System.out.printf("Would you like extra %s?\n\t1) Yes\n\t2) No", cheeseChoice);
        int extra = scanner.nextInt();
        if (extra == 1) {
            sandwich.addTopping(new CheeseTopping(cheeseOptions[cheeseChoice - 1], true));
        } else if (extra == 2) {
            sandwich.addTopping(new CheeseTopping(cheeseOptions[cheeseChoice - 1], false));
        } else {
            System.out.println("Invalid choice, please try again.");
            chooseCheese(scanner, sandwich);
        }
    }

    private static void chooseMeat(Scanner scanner, Sandwich sandwich) {
        System.out.println("Select a meat option:");
        MeatTopping.MeatType[] meatOptions = MeatTopping.getAllMeatOptions();
        for (int i = 0; i <= meatOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s", i + 1, meatOptions[i].getMeatName());
        }
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
        Sandwich.BreadType[] breadOptions = Sandwich.getAllBreadOptions();
        System.out.println("Select sandwich size:");
        for (int i = 0; i <= breadOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s", i + 1, breadOptions[i].getValue());
        }
        int breadChoice;
        do {
            breadChoice = scanner.nextInt();
            switch (breadChoice) {
                case 1 -> sandwich.setBreadType(Sandwich.BreadType.WHITE);
                case 2 -> sandwich.setBreadType(Sandwich.BreadType.RYE);
                case 3 -> sandwich.setBreadType(Sandwich.BreadType.MULTIGRAIN);
                case 4 -> sandwich.setBreadType(Sandwich.BreadType.WHOLE_GRAIN);
                default -> {
                    System.out.println("Incorrect selection");
                }
            }
        } while (breadChoice < 1 || breadChoice > breadOptions.length);
    }
}
