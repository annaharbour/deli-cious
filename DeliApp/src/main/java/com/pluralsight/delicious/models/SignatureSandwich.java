package com.pluralsight.delicious.models;

import java.util.ArrayList;
import java.util.List;

public class SignatureSandwich extends Sandwich {
    public enum SignatureSandwichType {
        BLT(
                Sandwich.SandwichSize.EIGHT_INCH,
                Sandwich.BreadType.WHITE,
                List.of(
                        new MeatTopping(MeatTopping.MeatType.BACON, false),
                        new RegularTopping(RegularTopping.FreeTopping.LETTUCE, false),
                        new RegularTopping(RegularTopping.FreeTopping.GUACAMOLE, false),
                        new RegularTopping(RegularTopping.FreeTopping.TOMATOES, false)
                ),
                List.of(new Sauce(Sauce.SauceType.RANCH)),
                List.of(),
                false,
                "BLT"
        ),
        PHILLY(
                Sandwich.SandwichSize.EIGHT_INCH,
                Sandwich.BreadType.WHITE,
                List.of(
                        new MeatTopping(MeatTopping.MeatType.STEAK, false),
                        new CheeseTopping(CheeseTopping.CheeseType.PROVOLONE, false),
                        new RegularTopping(RegularTopping.FreeTopping.PEPPERS, false),
                        new RegularTopping(RegularTopping.FreeTopping.MUSHROOMS, false),
                        new RegularTopping(RegularTopping.FreeTopping.ONIONS, false)
                ),
                List.of(new Sauce(Sauce.SauceType.MAYO)),
                List.of(new Side(Side.SideType.AU_JUS)),
                true,
                "Philly"
        );

        private final SignatureSandwich sandwich;

        SignatureSandwichType(Sandwich.SandwichSize size, Sandwich.BreadType breadType, List<Topping> toppings,
                              List<Sauce> sauces, List<Side> sides, boolean toasted, String name) {
            this.sandwich = new SignatureSandwich(size, breadType, toppings, sauces, sides, toasted, name);
        }

        public SignatureSandwich getSandwich() {
            return sandwich;
        }
    }

    private SignatureSandwichType signatureSandwichType;
    private final String signatureSandwichName;

    public SignatureSandwich(SandwichSize size, BreadType breadType, List<Topping> toppings, List<Sauce> sauces,
                             List<Side> sides, boolean toasted, String signatureSandwichName) {
        super(size, breadType, toppings, sauces, sides, toasted);
        this.signatureSandwichName = signatureSandwichName;
    }

    public SignatureSandwich(SignatureSandwich original) {
        super(original.getSize(), original.getBreadType(),
                new ArrayList<>(original.getToppings()),
                new ArrayList<>(original.getSauces()),
                new ArrayList<>(original.getSides()),
                original.isToasted());
        this.signatureSandwichName = original.getSignatureSandwichName();
    }


    public String getSignatureSandwichName() {
        return this.signatureSandwichName;
    }

    public static SignatureSandwich.SignatureSandwichType[] getAllSignatureSandwiches() {
        return SignatureSandwichType.values();
    }

    @Override
    public String getReceiptLine() {
        StringBuilder receipt = new StringBuilder();

        String sandwichHeader = String.format("\t%-25s %10s",
                size.getValue() + " " + signatureSandwichName.toUpperCase() + " ON " + breadType.getValue().toUpperCase(),
                String.format("$%.2f", getPrice()));
        receipt.append(sandwichHeader).append("\n");
        receipt.append(String.format("\t%-25s %10s\n", "Base Price", String.format("$%.2f",
                getBasePrice())));

        for (Topping topping : toppings) {
            if (topping instanceof MeatTopping meatTopping) {
                receipt.append(String.format("\t%-25s %10s\n", meatTopping,
                        String.format("$%.2f", meatTopping.getPrice(size))));
            } else if (topping instanceof CheeseTopping cheeseTopping) {
                receipt.append(String.format("\t%-25s %10s\n", cheeseTopping,
                        String.format("$%.2f", cheeseTopping.getPrice(size))));
            } else if (topping instanceof RegularTopping regularTopping) {
                receipt.append(String.format("\t%-25s %10s\n", regularTopping, "$0.00"));
            }
        }

        for (Sauce sauce : sauces) {
            receipt.append(String.format("\t%-25s %10s\n", sauce, "$0.00"));
        }

        for (Side side : sides) {
            receipt.append(String.format("\t%-25s %10s\n", "Side of " + side, "$0.00"));
        }

        receipt.append(String.format("\t%-25s %10s", toasted ? "Toasted" : "Not Toasted", "$0.00"));

        return receipt.toString();
    }
}
