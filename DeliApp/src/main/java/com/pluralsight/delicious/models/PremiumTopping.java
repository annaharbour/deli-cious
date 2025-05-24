package com.pluralsight.delicious.models;

public abstract class PremiumTopping extends Topping {
    protected double basePrice;

    @Override
    public double getPrice(int sandwichSize, boolean extra) {
        double price = switch (sandwichSize) {
            case 4 -> basePrice;
            case 8 -> basePrice * 2;
            case 12 -> basePrice * 3;
            default -> 0;
        };
        return extra ? price + getExtraCost(sandwichSize) : price;
    }

    protected abstract double getExtraCost(int sandwichSize);
}
