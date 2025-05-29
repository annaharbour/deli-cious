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

    public void clear() {
        this.orderItems.clear();
    }

    @Override
    public String toString() {

        StringBuilder order = new StringBuilder();
        orderItems.forEach(item -> {
            order.append(String.format(item.getReceiptLine() + "\n"));
        });
        order.append("----------------------------------------------------");
        String total = String.format("$%.2f", getPrice());
        order.append(String.format("\n%-30s%10s%n", "ORDER TOTAL:", total));
        return order.toString();
    }
}
