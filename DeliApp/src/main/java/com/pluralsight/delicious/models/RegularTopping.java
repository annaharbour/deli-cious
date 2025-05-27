package com.pluralsight.delicious.models;

public class RegularTopping extends Topping {
    public enum FreeTopping {
        LETTUCE("Lettuce"),
        PEPPERS("Peppers"),
        ONIONS("Onions"),
        TOMATOES("Tomatoes"),
        JALAPENOS("Jalapeños"),
        CUCUMBERS("Cucumbers"),
        PICKLES("Pickles"),
        GUACAMOLE("Guacamole"),
        MUSHROOMS("Mushrooms");

        private final String toppingName;

        FreeTopping(String toppingName) {
            this.toppingName = toppingName;

        }

        public String getValue() {
            return toppingName;
        }
    }


    public RegularTopping(FreeTopping freeTopping) {
        this.name = freeTopping.getValue();
    }

    public static RegularTopping.FreeTopping[] getAllRegularToppings() {
        return RegularTopping.FreeTopping.values();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public double getPrice(Sandwich.SandwichSize size) {
        return 0;
    }
}
