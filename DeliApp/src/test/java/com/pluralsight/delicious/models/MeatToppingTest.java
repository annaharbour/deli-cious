package com.pluralsight.delicious.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MeatToppingTest {

    @Test
    void getPrice() {
        MeatTopping ham = new MeatTopping(MeatTopping.MeatType.HAM, false);
        MeatTopping steak = new MeatTopping(MeatTopping.MeatType.STEAK, false);
        MeatTopping chicken = new MeatTopping(MeatTopping.MeatType.CHICKEN, false);
        MeatTopping bacon = new MeatTopping(MeatTopping.MeatType.BACON, true);
        MeatTopping roastBeef = new MeatTopping(MeatTopping.MeatType.ROAST_BEEF, true);
        MeatTopping salami = new MeatTopping(MeatTopping.MeatType.SALAMI, true);

        assertEquals(1.00, ham.getPrice(Sandwich.SandwichSize.FOUR_INCH));
        assertEquals(2.00, steak.getPrice(Sandwich.SandwichSize.EIGHT_INCH));
        assertEquals(3.00, chicken.getPrice(Sandwich.SandwichSize.TWELVE_INCH));
        assertEquals(1.50, bacon.getPrice(Sandwich.SandwichSize.FOUR_INCH));
        assertEquals(3.00, roastBeef.getPrice(Sandwich.SandwichSize.EIGHT_INCH));
        assertEquals(4.50, salami.getPrice(Sandwich.SandwichSize.TWELVE_INCH));
    }

    @Test
    void getExtraCost() {
        MeatTopping meat = new MeatTopping(MeatTopping.MeatType.SALAMI, true);

        assertEquals(0.50, meat.getExtraCost(Sandwich.SandwichSize.FOUR_INCH));
        assertEquals(1.00, meat.getExtraCost(Sandwich.SandwichSize.EIGHT_INCH));
        assertEquals(1.50, meat.getExtraCost(Sandwich.SandwichSize.TWELVE_INCH));
    }

    @Test
    void getAllMeatOptions() {
        MeatTopping.MeatType[] expected = MeatTopping.MeatType.values();
        MeatTopping.MeatType[] actual = MeatTopping.getAllMeatOptions();
        assertArrayEquals(expected, actual);
    }
}