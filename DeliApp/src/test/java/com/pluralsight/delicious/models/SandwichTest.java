package com.pluralsight.delicious.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SandwichTest {

    @Test
    void initializeSandwich() {
        Sandwich sandwich = new Sandwich();
        sandwich.setSandwichSize(Sandwich.SandwichSize.FOUR_INCH);
        sandwich.setBreadType(Sandwich.BreadType.WHITE);

        assertEquals(Sandwich.SandwichSize.FOUR_INCH, sandwich.getSize());
        assertEquals(Sandwich.BreadType.WHITE, sandwich.getBreadType());
    }

    @Test
    void addRemoveTopping() {
        Sandwich sandwich = new Sandwich();
        Topping topping = new RegularTopping(RegularTopping.FreeTopping.LETTUCE, false);
        sandwich.addTopping(topping);
        MeatTopping meatTopping = new MeatTopping(MeatTopping.MeatType.SALAMI, true);
        sandwich.addTopping(meatTopping);
        assertTrue(sandwich.getToppings().contains(topping));
        assertTrue(sandwich.getToppings().contains(meatTopping));
        sandwich.removeTopping(topping);
        assertFalse(sandwich.getToppings().contains(topping));
        sandwich.removeTopping(meatTopping);
        assertFalse(sandwich.getToppings().contains(meatTopping));
    }

    @Test
    void addRemoveSauce() {
        Sandwich sandwich = new Sandwich();
        Sauce mayo = new Sauce(Sauce.SauceType.MAYO);
        sandwich.addSauce(mayo);
        assertTrue(sandwich.getSauces().contains(mayo), "Sauce should be added");
        sandwich.removeSauce(mayo);
        assertFalse(sandwich.getSauces().contains(mayo), "Sauce should be removed");
    }

    @Test
    void addRemoveSide() {
        Sandwich sandwich = new Sandwich();
        Side auJus = new Side(Side.SideType.AU_JUS);
        Side mayo = new Side(Side.SideType.SAUCE, new Sauce(Sauce.SauceType.MAYO));
        sandwich.addSide(auJus);
        sandwich.addSide(mayo);
        assertTrue(sandwich.getSides().contains(auJus), "Side should be added");
        assertTrue(sandwich.getSides().contains(mayo), "Side of sauce should be added");
        sandwich.removeSide(auJus);
        sandwich.removeSide(mayo);
        assertFalse(sandwich.getSides().contains(auJus), "Side should be removed");
        assertFalse(sandwich.getSides().contains(mayo), "Side of sauce should be removed");
    }

    @Test
    void getPrice() {
        Sandwich sandwich = new Sandwich();
        sandwich.setSandwichSize(Sandwich.SandwichSize.EIGHT_INCH);
        sandwich.setBreadType(Sandwich.BreadType.RYE);

        Topping meat = new MeatTopping(MeatTopping.MeatType.BACON, true);
        sandwich.addTopping(meat);

        double expected = 10.00;
        assertEquals(expected, sandwich.getPrice());
    }
}