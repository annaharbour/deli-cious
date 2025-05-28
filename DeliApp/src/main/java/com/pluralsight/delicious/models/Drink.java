package com.pluralsight.delicious.models;

public class Drink implements MenuItem {
    private Size size;
    private Flavor flavor;

    public enum Size {
        SMALL("Small"),
        MEDIUM("Medium"),
        LARGE("Large");

        private final String size;

        Size(String size) {
            this.size = size;
        }

        public String getValue() {
            return size;
        }
    }

    public enum Flavor {
        FOUNTAIN_SODA("Fountain Soda"),
        BOTTLED_WATER("Bottled Water"),
        JUICE("JUICE"),
        ICED_TEA("Iced Tea"),
        COFFEE("Coffee");

        private final String flavor;

        Flavor(String flavor) {
            this.flavor = flavor;
        }

        public String getValue() {
            return flavor;
        }
    }

    public Drink(){}
    public Drink(Size size, Flavor flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public static Drink.Size[] getAllDrinkSizeOptions() {
        return Drink.Size.values();
    }

    public static Drink.Flavor[] getAllDrinkFlavorOptions() {
        return Drink.Flavor.values();
    }

    public void setFlavor(Flavor flavor) {
        this.flavor = flavor;
    }

    public Size getSize() {
        return size;
    }

    public Flavor getFlavor() {
        return flavor;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    @Override
    public double getPrice() {
        return switch (size) {
            case SMALL -> 1.00;
            case MEDIUM -> 1.50;
            case LARGE -> 3.00;
        };
    }

    @Override
    public String getReceiptLine() {
        return String.format("Drink,%s%.2f,", size.getValue(), getPrice());
    }

    @Override
    public String getOrderLine() {
        return String.format("%s %s: %.2f,", size.getValue(), flavor.getValue(), getPrice());
    }
}