package com.pluralsight.delicious.models;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class Order {
    private List<MenuItem> orderItems;
    private Customer customer;
    private final LocalDateTime timeStamp;

    public Order(List<MenuItem> orderItems) {
        this.orderItems = orderItems;
        this.timeStamp = LocalDateTime.now();
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addToOrder(MenuItem menuItem) {
        this.orderItems.add(menuItem);
    }

    public void removeFromOrder(MenuItem menuItem) {
        this.orderItems = this.orderItems.stream().filter(item -> item != menuItem).collect(Collectors.toList());
    }

    public List<MenuItem> getOrderItems() {
        return orderItems;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        StringBuilder order = new StringBuilder();
        for (MenuItem orderItem : orderItems) {
            order.append(orderItem.getOrderLine());
        }
        return order.toString();
    }
}
