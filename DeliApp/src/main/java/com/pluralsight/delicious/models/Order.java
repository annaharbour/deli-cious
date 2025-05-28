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

    public List<MenuItem> getOrderItems() {
        return orderItems;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public double getPrice(){
        double total = 0;
        for(MenuItem item : orderItems){
            total += item.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder order = new StringBuilder();
        for (MenuItem orderItem : orderItems) {
            order.append(orderItem.getOrderLine()).append("\n");
        }
        order.append(String.format("$%.2f", getPrice()));
        return order.toString();
    }
}
