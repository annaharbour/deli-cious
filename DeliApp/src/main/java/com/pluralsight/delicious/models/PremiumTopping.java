package com.pluralsight.delicious.models;

public abstract class PremiumTopping extends Topping {
    protected double basePrice;

    public abstract double getPrice(Sandwich.SandwichSize sandwichSize);

    protected abstract double getExtraCost(Sandwich.SandwichSize sandwichSize);
}
