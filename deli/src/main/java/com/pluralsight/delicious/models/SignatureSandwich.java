package com.pluralsight.delicious.models;

import java.util.List;

public class SignatureSandwich extends Sandwich {
    private String name;

    public SignatureSandwich(Size size, BreadType breadType, SauceType sauceType, List<Topping> toppings, boolean toasted, String name) {
        super(size, breadType, sauceType, toppings, toasted);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
