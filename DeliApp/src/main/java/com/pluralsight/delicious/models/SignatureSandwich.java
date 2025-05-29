package com.pluralsight.delicious.models;

import java.util.List;

public class SignatureSandwich extends Sandwich {
    private final String signatureSandwichName;

    public SignatureSandwich(SandwichSize size, BreadType breadType, List<Topping> toppings, List<Sauce> sauces,
                             List<Side> sides, boolean toasted, String signatureSandwichName) {
        super(size, breadType, toppings, sauces, sides, toasted);
        this.signatureSandwichName = signatureSandwichName;
    }

    public String getSignatureSandwichName() {
        return this.signatureSandwichName;
    }

    @Override
    public String getOrderLine() {
        return String.format("%s: $%.2f", getSignatureSandwichName(), getPrice());
    }

    @Override
    public String getReceiptLine() {
        return String.format("%s, ,%.2f", getSignatureSandwichName(), getPrice());
    }
}
