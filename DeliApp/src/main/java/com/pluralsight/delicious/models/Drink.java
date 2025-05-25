package com.pluralsight.delicious.models;

public class Drink implements MenuItem {
    private Size size;

    public enum Size {
        SMALL("Small"),
        MEDIUM("Medium"),
        LARGE("Large");

        private final String size;

        Size(String size) {
            this.size = size;
        }

        public String getSize() {
            return size;
        }
    }

    public Drink(Size size) {
        this.size = size;
    }

    public static Drink.Size[] getAllDrinkOptions() {
        return Drink.Size.values();
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
        return "Drink{" +
                "price=" + getPrice() +
                ", size=" + size +
                '}';
    }

    @Override
    public String getOrderLine() {
        return "Drink{" +
                "price=" + getPrice() +
                ", size=" + size +
                '}';
    }
}