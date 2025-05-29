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
        chooseSandwichSize(scanner, sandwich);
        chooseBread(scanner, sandwich);
        System.out.println("Add to your sandwich:");
        System.out.println("\n\t1) Add Meat\n\t2) Add Cheese\n\t3) Add Other Toppings\n\t4) Add Sauce\n\t5) Add " +
                "Sides\n\t0) Cancel");
        int userChoice;
        do {
            userChoice = scanner.nextInt();
            switch (userChoice) {
                case 0 -> {
                    return new OrderScreen();
                }
                case 1 -> chooseMeat(scanner, sandwich);
                case 2 -> chooseCheese(scanner, sandwich);
                case 3 -> chooseVeggies(scanner, sandwich);
                case 4 -> chooseSauces(scanner, sandwich);
                case 5 -> chooseSides(scanner, sandwich);
                case 6 -> {
                    chooseToasted(scanner, sandwich);
                }
                default -> System.out.println("Choose a valid option");
            }
        } while (userChoice != 6);

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


    private static void chooseSandwichSize(Scanner scanner, Sandwich sandwich) {
        Sandwich.SandwichSize[] sizeOptions = Sandwich.getAllSizeOptions();
        System.out.println("Select sandwich size:");
        for (int i = 0; i <= sizeOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, sizeOptions[i].getValue());
        }
        int sizeChoice;
        do {
            sizeChoice = scanner.nextInt();
            if (sizeChoice < 1 || sizeChoice > sizeOptions.length) {
                System.out.println("Invalid choice, please select a valid meat option.");
            }
        } while (sizeChoice < 1 || sizeChoice > sizeOptions.length);

        sandwich.setSandwichSize(sizeOptions[sizeChoice - 1]);
    }

    private static void chooseToasted(Scanner scanner, Sandwich sandwich) {
        System.out.println("Do you want it toasted? \n\t1) Yes \n\t2) No\n");
        int toastedChoice;
        do {
            toastedChoice = scanner.nextInt();
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
            System.out.printf("\n\t%d) %s\n", i + 1, sauceOptions[i].getValue());
        }
        int sauceChoice;
        do {
            sauceChoice = scanner.nextInt();
            if (sauceChoice < 1 || sauceChoice > sauceOptions.length) {
                System.out.println("Invalid choice, please select a valid sauce option.");
            }
        } while (sauceChoice < 1 || sauceChoice > sauceOptions.length);
        sandwich.addSauce(new Sauce(sauceOptions[sauceChoice - 1]));
    }


    private static void chooseSides(Scanner scanner, Sandwich sandwich) {
        System.out.println("Add Sides");
        Side.SideType[] sideOptions = Side.getAllSideOptions();
        for (int i = 0; i <= sideOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, sideOptions[i].getValue());
        }
        int sideChoice;
        do {
            sideChoice = scanner.nextInt();
            switch (sideChoice) {
                case 1 -> {
                    sandwich.addSide(new Side(sideOptions[sideChoice - 1]));
                }
                case 2 -> {
                    Sauce.SauceType[] sauceOptions = Sauce.getAllSauceOptions();
                    System.out.println("Select a sauce as a side:");
                    for (int i = 0; i < sauceOptions.length; i++) {
                        System.out.printf("\n\t%d) %s", i + 1, sauceOptions[i].getValue());
                    }
                    int sauceChoice;
                    do {
                        sauceChoice = scanner.nextInt();
                        if (sauceChoice < 1 || sauceChoice > sauceOptions.length) {
                            System.out.println("Invalid choice, please select a valid sauce option.");
                        }
                    } while (sauceChoice < 1 || sauceChoice > sauceOptions.length);
                    sandwich.addSide(new Side(Side.SideType.SAUCE, new Sauce(sauceOptions[sauceChoice - 1])));
                }
                default -> System.out.println("Invalid choice, please select a valid side option.");
            }
        } while (sideChoice < 1 || sideChoice > sideOptions.length);
    }

    private static void chooseVeggies(Scanner scanner, Sandwich sandwich) {
        System.out.println("Add veggies:");
        RegularTopping.FreeTopping[] toppingOptions = RegularTopping.getAllRegularToppings();
        for (int i = 0; i <= toppingOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, toppingOptions[i].getValue());
        }
        int toppingChoice;
        do {
            toppingChoice = scanner.nextInt();
            if (toppingChoice < 1 || toppingChoice > toppingOptions.length) {
                System.out.println("Invalid choice, please select a valid topping option.");
            }
        } while (toppingChoice < 1 || toppingChoice > toppingOptions.length);
    }

    private static void chooseCheese(Scanner scanner, Sandwich sandwich) {
        System.out.println("\nSelect a cheese option:\n");
        CheeseTopping.CheeseType[] cheeseOptions = CheeseTopping.getAllCheeseOptions();
        for (int i = 0; i <= cheeseOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, cheeseOptions[i].getValue());
        }
        int cheeseChoice;
        do {
            cheeseChoice = scanner.nextInt();
            if (cheeseChoice < 1 || cheeseChoice > cheeseOptions.length) {
                System.out.println("Invalid choice, please select a valid cheese option.");
            }
        } while (cheeseChoice < 1 || cheeseChoice > cheeseOptions.length);

        System.out.printf("Would you like extra %s?\n\t1) Yes\n\t2) No\n",
                cheeseOptions[cheeseChoice - 1].getValue());
        int extra;
        do {
            extra = scanner.nextInt();
            if (extra == 1) {
                sandwich.addTopping(new CheeseTopping(cheeseOptions[cheeseChoice - 1], true));
            } else if (extra == 2) {
                sandwich.addTopping(new CheeseTopping(cheeseOptions[cheeseChoice - 1], false));
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        } while (extra != 1 && extra != 2);
    }

    private static void chooseMeat(Scanner scanner, Sandwich sandwich) {
        System.out.println("\nSelect a meat option:\n");
        MeatTopping.MeatType[] meatOptions = MeatTopping.getAllMeatOptions();
        for (int i = 0; i <= meatOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, meatOptions[i].getValue());
        }
        int meatChoice;
        do {
            meatChoice = scanner.nextInt();
            if (meatChoice < 1 || meatChoice > meatOptions.length) {
                System.out.println("Invalid choice, please select a valid meat option.");
            }
        } while (meatChoice < 1 || meatChoice > meatOptions.length);

        System.out.printf("Would you like extra %s?\n\t1) Yes\n\t2) No\n", meatOptions[meatChoice - 1].getValue());
        int extra;
        do {
            extra = scanner.nextInt();
            if (extra == 1) {
                sandwich.addTopping(new MeatTopping(meatOptions[meatChoice - 1], true));
            } else if (extra == 2) {
                sandwich.addTopping(new MeatTopping(meatOptions[meatChoice - 1], false));
            } else {
                System.out.println("Invalid choice, please try again.");
            }
        } while (extra != 1 && extra != 2);
    }

    private static void chooseBread(Scanner scanner, Sandwich sandwich) {
        Sandwich.BreadType[] breadOptions = Sandwich.getAllBreadOptions();
        System.out.println("Select sandwich size:");
        for (int i = 0; i <= breadOptions.length - 1; i++) {
            System.out.printf("\n\t%d) %s\n", i + 1, breadOptions[i].getValue());
        }
        int breadChoice;
        do {
            breadChoice = scanner.nextInt();
            if (breadChoice < 1 || breadChoice > breadOptions.length) {
                System.out.println("Invalid choice, please select a valid meat option.");
            }
        } while (breadChoice < 1 || breadChoice > breadOptions.length);

        sandwich.setBreadType(breadOptions[breadChoice - 1]);
    }
}
