package com.pluralsight.delicious.models;

public class Chips implements MenuItem {
    private double price;

    public Chips(double price) {
        this.price = 1.0;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String getReceiptLine() {
        return "Chips{" +
                "price=" + price +
                '}';
    }


    @Override
    public String getOrderLine() {
        return "Chips{" +
                "price=" + price +
                '}';
    }
}