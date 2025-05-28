package com.pluralsight.delicious.models;

public class Chips implements MenuItem {
    private final double price;
    private Flavor flavor;

    public enum Flavor {
        CLASSIC("Classic Salted"),
        Barbecue("Barbecue"),
        SOUR_CREAM("Sour Cream and Onion"),
        VINEGAR("Salt and Vinegar"),
        CHEDDAR("Sharp Cheddar"),
        HOT("Flaming Hot"),
        PICKLE("Dill Pickle");

        private final String flavor;

        Flavor(String flavor) {
            this.flavor = flavor;
        }

        public String getValue() {
            return flavor;
        }
    }

    public Chips() {
        this.price = 1.50;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    public String getFlavor() {
        return flavor.getValue();
    }

    public static Chips.Flavor[] getAllChipFlavors() {
        return Chips.Flavor.values();
    }

    @Override
    public String getReceiptLine() {
        return String.format("Chips,2.5oz Bag %s,%.2f,", getFlavor(), getPrice());
    }

    @Override
    public String getOrderLine() {
        return String.format("Chips: $%.2f,", getPrice());
    }
}