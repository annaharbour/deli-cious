package com.pluralsight.delicious.models;

public class RegularTopping extends Topping {
    private String toppingName;

    public enum FreeTopping {
        LETTUCE,
        PEPPERS,
        ONIONS,
        TOMATOES,
        JALAPENOS,
        CUCUMBERS,
        PICKLES,
        GUACAMOLE,
        MUSHROOMS
    }

    public RegularTopping(FreeTopping freeTopping) {
        this.toppingName = freeTopping.name();
    }

    @Override
    public double getPrice(int sizeInInches, boolean extra) {
        return 0;
    }
}
