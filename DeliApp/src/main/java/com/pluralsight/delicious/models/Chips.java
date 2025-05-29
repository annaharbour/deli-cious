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

    public String getReceiptLine() {
        return String.format("\t%-25s %10s",flavor.getValue() + " Chips", String.format("$%.2f",
                getPrice()));
    }

    @Override
    public String getOrderLine() {
        return String.format("Chips: $%.2f,", getPrice());
    }
}