package com.pluralsight.delicious.models;

public class Drink implements MenuItem {
    private Size size;

    public enum Size {
        SMALL,
        MEDIUM,
        LARGE;
    }

    public Drink(Size size) {
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

    public double getPrice(Size size) {
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