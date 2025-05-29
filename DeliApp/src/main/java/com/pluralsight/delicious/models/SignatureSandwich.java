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
                        new RegularTopping(RegularTopping.FreeTopping.LETTUCE),
                        new RegularTopping(RegularTopping.FreeTopping.GUACAMOLE),
                        new RegularTopping(RegularTopping.FreeTopping.TOMATOES)
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
                        new RegularTopping(RegularTopping.FreeTopping.PEPPERS),
                        new RegularTopping(RegularTopping.FreeTopping.MUSHROOMS),
                        new RegularTopping(RegularTopping.FreeTopping.ONIONS)
                ),
                List.of(new Sauce(Sauce.SauceType.MAYO)),
                List.of(new Side(Side.SideType.AU_JUS)),
                true,
                "Philly Cheese Steak"
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
        return String.format("%s, ,%.2f", getSignatureSandwichName(), getPrice());
    }
}
