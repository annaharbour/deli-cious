package com.pluralsight.delicious.models;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private List<MenuItem> orderItems;
    private Customer customer;
    private LocalDateTime timeStamp;

    public Order(List<MenuItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void addToOrder(MenuItem menuItem){
        this.orderItems.add(menuItem);
    }

    public void removeFromOrder(MenuItem menuItem) {
            this.orderItems.remove(menuItem);
        }

    public List<MenuItem> getOrderItems() {
        return orderItems;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        StringBuilder order = new StringBuilder();
        for(MenuItem orderItem: orderItems){
            order.append(orderItem.getOrderLine());
        }
        return order.toString();
    }
}
