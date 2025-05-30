package com.pluralsight.delicious.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrinkTest {

    @Test
    void getAllDrinkSizeOptions() {
        Drink.Size[] sizes = Drink.getAllDrinkSizeOptions();
        assertArrayEquals(Drink.Size.values(), sizes);
    }

    @Test
    void getAllDrinkFlavorOptions() {
        Drink.Flavor[] flavors = Drink.getAllDrinkFlavorOptions();
        assertArrayEquals(Drink.Flavor.values(), flavors);
    }

    @Test
    void setFlavor() {
        Drink drink = new Drink();
        drink.setFlavor(Drink.Flavor.COFFEE);
        assertEquals("Coffee", drink.getFlavor());
    }

    @Test
    void getSize() {
        Drink drink = new Drink(Drink.Size.SMALL, Drink.Flavor.JUICE);
        assertEquals("Small", drink.getSize());
    }

    @Test
    void getFlavor() {
        Drink drink = new Drink(Drink.Size.MEDIUM, Drink.Flavor.ICED_TEA);
        assertEquals("Tea", drink.getFlavor());
    }

    @Test
    void setSize() {
        Drink drink = new Drink();
        drink.setSize(Drink.Size.LARGE);
        assertEquals("Large", drink.getSize());
    }

    @Test
    void getPrice() {
        Drink small = new Drink(Drink.Size.SMALL, Drink.Flavor.COFFEE);
        Drink medium = new Drink(Drink.Size.MEDIUM, Drink.Flavor.COFFEE);
        Drink large = new Drink(Drink.Size.LARGE, Drink.Flavor.COFFEE);

        assertEquals(1.00, small.getPrice());
        assertEquals(1.50, medium.getPrice());
        assertEquals(3.00, large.getPrice());

    }
}