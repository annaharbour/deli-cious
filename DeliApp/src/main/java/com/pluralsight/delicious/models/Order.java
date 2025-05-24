package com.pluralsight.delicious.models;

import java.util.List;

public class Order {
    private Customer customer;
    private List<MenuItem> orderItems;

    public Order(Customer customer, List<MenuItem> orderItems) {
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<MenuItem> getOrderItems() {
        return orderItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", orderItems=" + orderItems +
                '}';
    }
}
