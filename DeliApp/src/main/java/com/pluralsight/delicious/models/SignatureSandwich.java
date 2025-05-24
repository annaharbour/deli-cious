package com.pluralsight.delicious.models;

import java.util.List;

public class SignatureSandwich extends Sandwich {
    private String name;

    public SignatureSandwich(Size size, BreadType breadType, List<Topping> toppings, List<Sauce> sauces,
                             List<Side> sides, boolean toasted, String name) {
        super(size, breadType, toppings, toasted);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
