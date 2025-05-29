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
                    List.of(
                            new MeatTopping(MeatTopping.MeatType.BACON, false),
                            new RegularTopping(RegularTopping.FreeTopping.LETTUCE),
                            new RegularTopping(RegularTopping.FreeTopping.GUACAMOLE),
                            new RegularTopping(RegularTopping.FreeTopping.TOMATOES)
                    ),
                    List.of(
                            new Sauce(Sauce.SauceType.RANCH)
                    ),
                    List.of(),
                    false,
                    "BLT"
            );
    SignatureSandwich philly =
            new SignatureSandwich(
                    Sandwich.SandwichSize.EIGHT_INCH,
                    Sandwich.BreadType.WHITE,
                    List.of(
                            new MeatTopping(MeatTopping.MeatType.STEAK, false),
                            new CheeseTopping(CheeseTopping.CheeseType.PROVOLONE, false),
                            new RegularTopping(RegularTopping.FreeTopping.PEPPERS),
                            new RegularTopping(RegularTopping.FreeTopping.MUSHROOMS),
                            new RegularTopping(RegularTopping.FreeTopping.ONIONS)
                    ),
                    List.of(
                            new Sauce(Sauce.SauceType.MAYO)
                    ),
                    List.of(
                            new Side(Side.SideType.AU_JUS)
                    ),
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
        do {
            input = scanner.nextInt();
            if (input > 0 && input <= signatureSandwiches.size()) {
                SignatureSandwich selected = signatureSandwiches.get(input - 1);
                System.out.println("You have selected :\n" + selected);
                System.out.printf("Choose From the Following:\n\t1)Order %s?\n\t2) Customize %s\n\t3) Cancel",
                        selected.getSignatureSandwichName(), selected.getSignatureSandwichName());
                input = scanner.nextInt();
                switch(input){
                    case 1 -> currentOrder.addToOrder(selected);
                    case 2 -> {

                    }
                    case 3 -> System.out.println("Canceling sandwich order...");
                }
            }
        } while (input != 0);
        return new OrderScreen();
    }
}
