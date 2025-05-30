package com.pluralsight.delicious.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class ChipsTest {

    @Test
    void getPrice() {
        Chips chips = new Chips();
        assertEquals(1.50, chips.getPrice(), 0.001);
    }

    @Test
    void setFlavor() {
        Chips chips = new Chips();
        chips.setFlavor(Chips.Flavor.Barbecue);
        assertEquals("Barbecue", chips.getFlavor());
    }

    @Test
    void getFlavor() {
        Chips chips = new Chips();
        chips.setFlavor(Chips.Flavor.VINEGAR);
        assertEquals("Salt and Vinegar", chips.getFlavor());
    }

    @Test
    void getAllChipFlavors() {
        Chips.Flavor[] flavors = Chips.getAllChipFlavors();
        assertEquals(7, flavors.length);
        assertTrue(java.util.Arrays.asList(flavors).contains(Chips.Flavor.PICKLE));
    }
}