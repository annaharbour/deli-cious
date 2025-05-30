package com.pluralsight.delicious.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegularToppingTest {

    @Test
    void getAllRegularToppings() {
        RegularTopping.FreeTopping[] toppings = RegularTopping.getAllRegularToppings();
        assertEquals(9, toppings.length);
        RegularTopping.FreeTopping[] expected = RegularTopping.FreeTopping.values();
        assertArrayEquals(expected, toppings);
    }

    @Test
    void getPrice() {
        RegularTopping topping = new RegularTopping(RegularTopping.FreeTopping.CUCUMBERS, false);
        assertEquals(0.0, topping.getPrice(Sandwich.SandwichSize.FOUR_INCH));
        RegularTopping toppingWithExtra = new RegularTopping(RegularTopping.FreeTopping.PEPPERS, true);
        assertEquals(0.0, toppingWithExtra.getPrice(Sandwich.SandwichSize.TWELVE_INCH));
    }
}