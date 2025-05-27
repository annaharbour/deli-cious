package com.pluralsight.delicious.models;

public class Chips implements MenuItem {
    private double price;

    public Chips() {
        this.price = 1.00;
    }

    @Override
    public double getPrice() {
        return 1.00;
    }

    @Override
    public String getReceiptLine() {
        return String.format("Chips,%.2f,", getPrice());
    }


    @Override
    public String getOrderLine() {
        return "Chips{" +
                "price=" + price +
                '}';
    }
}