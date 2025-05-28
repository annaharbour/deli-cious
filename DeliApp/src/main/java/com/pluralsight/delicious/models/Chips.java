package com.pluralsight.delicious.models;

public class Chips implements MenuItem {
    private final double price;

    public Chips() {
        this.price = 1.00;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getReceiptLine() {
        return String.format("Chips,2.5oz Bag,%.2f,", getPrice());
    }

    @Override
    public String getOrderLine() {
        return String.format("Chips: $%.2f,", getPrice());
    }
}