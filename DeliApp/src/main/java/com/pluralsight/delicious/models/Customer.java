package com.pluralsight.delicious.models;
import java.util.UUID;

public class Customer {
    private final String customerName;
    private final String sessionId;

    public Customer(String customerId, String customerName) {
        this.customerName = customerName;
        this.sessionId = UUID.randomUUID().toString();
    }

    public String getSessionId() {
        return sessionId;
    }
}