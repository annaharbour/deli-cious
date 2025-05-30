package com.pluralsight.delicious.models;

import static org.junit.jupiter.api.Assertions.*;

class CheeseToppingTest {

    @org.junit.jupiter.api.Test
    void getAllCheeseOptions() {
        CheeseTopping.CheeseType[] expected = CheeseTopping.CheeseType.values();
        CheeseTopping.CheeseType[] actual = CheeseTopping.getAllCheeseOptions();
        assertArrayEquals(expected, actual);
    }

    @org.junit.jupiter.api.Test
    void getPrice() {
        CheeseTopping provolone = new CheeseTopping(CheeseTopping.CheeseType.PROVOLONE, true);
        assertEquals(0.75 + 0.30, provolone.getPrice(Sandwich.SandwichSize.FOUR_INCH));
        assertEquals(1.50 + 0.60, provolone.getPrice(Sandwich.SandwichSize.EIGHT_INCH));
        assertEquals(2.25 + 0.90, provolone.getPrice(Sandwich.SandwichSize.TWELVE_INCH));

    }

    @org.junit.jupiter.api.Test
    void getExtraCost() {
        CheeseTopping swiss = new CheeseTopping(CheeseTopping.CheeseType.SWISS, true);
        assertEquals(0.30, swiss.getExtraCost(Sandwich.SandwichSize.FOUR_INCH));
        assertEquals(0.60, swiss.getExtraCost(Sandwich.SandwichSize.EIGHT_INCH));
        assertEquals(0.90, swiss.getExtraCost(Sandwich.SandwichSize.TWELVE_INCH));

    }
}