package com.pluralsight.delicious.ui;

import com.pluralsight.delicious.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SignatureSandwichesScreen implements ScreenState {
    SignatureSandwich BLT =
            new SignatureSandwich(
                    Sandwich.SandwichSize.EIGHT_INCH,
                    Sandwich.BreadType.WHITE,
                    new ArrayList<>(List.of(
                            new MeatTopping(MeatTopping.MeatType.BACON, false),
                            new RegularTopping(RegularTopping.FreeTopping.LETTUCE),
                            new RegularTopping(RegularTopping.FreeTopping.GUACAMOLE),
                            new RegularTopping(RegularTopping.FreeTopping.TOMATOES)
                    )),
                    new ArrayList<>(List.of(
                            new Sauce(Sauce.SauceType.RANCH)
                    )),
                    new ArrayList<>(List.of()),
                    false,
                    "BLT"
            );
    SignatureSandwich philly =
            new SignatureSandwich(
                    Sandwich.SandwichSize.EIGHT_INCH,
                    Sandwich.BreadType.WHITE,
                    new ArrayList<>(List.of(
                            new MeatTopping(MeatTopping.MeatType.STEAK, false),
                            new CheeseTopping(CheeseTopping.CheeseType.PROVOLONE, false),
                            new RegularTopping(RegularTopping.FreeTopping.PEPPERS),
                            new RegularTopping(RegularTopping.FreeTopping.MUSHROOMS),
                            new RegularTopping(RegularTopping.FreeTopping.ONIONS)
                    )),
                    new ArrayList<>(List.of(
                            new Sauce(Sauce.SauceType.MAYO)
                    )),
                    new ArrayList<>(List.of(
                            new Side(Side.SideType.AU_JUS)
                    )),
                    true,
                    "Philly Cheese Steak"
            );
    private final ArrayList<SignatureSandwich> signatureSandwiches = new ArrayList<>(List.of(BLT, philly));

    @Override
    public void display() {
        System.out.println("Here are our signature sandwiches: ");
        System.out.println("Choose an option or enter 0 to return to Order Screen");
        signatureSandwiches.forEach(signatureSandwich -> {
            System.out.printf("\n\t%d) %s", signatureSandwiches.indexOf(signatureSandwich) + 1,
                    signatureSandwich.getSignatureSandwichName());
        });
    }

    @Override
    public ScreenState handleInput(Scanner scanner, Order currentOrder) {
        int input;
        input = scanner.nextInt();
        if (input > 0 && input <= signatureSandwiches.size()) {
            SignatureSandwich selected = new SignatureSandwich(signatureSandwiches.get(input - 1));            System.out.println("You have selected :\n" + selected.getOrderLine());
            System.out.printf("Choose From the Following:\n\t1)Order %s?\n\t2) Customize %s\n\t3) Cancel",
                    selected.getSignatureSandwichName(), selected.getSignatureSandwichName());
            input = scanner.nextInt();
            switch (input) {
                case 1 -> currentOrder.addToOrder(selected);
                case 2 -> {
                    SignatureSandwich customizedSandwich = customizeSandwich(selected, scanner);
                    currentOrder.addToOrder(customizedSandwich);
                }
                case 3 -> System.out.println("Canceling sandwich order...");
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
                        String name = (topping instanceof MeatTopping) ? topping.toString()
                                : (topping instanceof CheeseTopping) ? topping.toString()
                                : (topping instanceof RegularTopping) ? topping.toString()
                                : "Unknown";
                        System.out.printf("\t%d) %s%n", i + 1, name);
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
                    scanner.next();
                    String sauceName = scanner.nextLine();
                    sandwich.removeSauce(sauceName);
                }
                case 11 -> {
                    scanner.next();
                    String sideName = scanner.nextLine();
                    sandwich.removeSide(sideName);
                }
                case 12 -> {
                    System.out.println("Adding Sandwich");
                    System.out.println(sandwich.getOrderLine());
                    return sandwich;
                }
                default -> System.out.println("Invalid option");
            }
        }
        while (true);
    }
}
