package com.pluralsight.delicious.models;

public abstract class Topping {
    protected String name;
    public String getName() { return name; }
    public abstract double getPrice(Sandwich.SandwichSize sizeInInches);
}
