package com.pluralsight.delicious.models;

public class Chips implements MenuItem {
    private double price;

    public Chips() {
    }

    @Override
    public double getPrice() {
        return 1.00;
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