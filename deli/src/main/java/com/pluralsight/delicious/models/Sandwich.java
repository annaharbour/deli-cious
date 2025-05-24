package com.pluralsight.delicious.models;

import java.util.List;

public class Sandwich {
    public enum Size {
        SMALL(4),
        MEDIUM(8),
        LARGE(12);

        private final int value;

        Size(int value) {
            this.value = value;
        }
        public int getValue() {
            return value;
        }
    }

    public enum BreadType {
        WHITE,
        WHOLE_GRAIN,
        MULTIGRAIN,
        RYE
    }

    public enum SauceType {
        MAYO,
        MUSTARD,
        KETCHUP,
        BBQ
    }

    private Size size;
    private BreadType breadType;
    private SauceType sauceType;
    private List<Topping> toppings;
    boolean toasted;

    public Sandwich(Size size, BreadType breadType, SauceType sauceType, List<Topping> toppings, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.sauceType = sauceType;
        this.toppings = toppings;
        this.toasted = toasted;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }

    public SauceType getSauceType() {
        return sauceType;
    }

    public void setSauceType(SauceType sauceType) {
        this.sauceType = sauceType;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void setToppings(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }
}
