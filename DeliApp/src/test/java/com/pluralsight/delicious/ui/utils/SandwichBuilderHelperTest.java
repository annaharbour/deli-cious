package com.pluralsight.delicious.ui.utils;

import com.pluralsight.delicious.models.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SandwichBuilderHelperTest {
    private Sandwich BLT;
    private Sandwich sandwich;

    @BeforeEach
    void setUp() {
        BLT = new SignatureSandwich(SignatureSandwich.SignatureSandwichType.BLT.getSandwich());
        sandwich = new Sandwich();
    }

    @Test
    void chooseBread() {
        BLT.setBreadType(Sandwich.BreadType.WHITE);
        assertEquals(Sandwich.BreadType.WHITE, BLT.getBreadType());
    }

    @Test
    void chooseSize() {
        BLT.setSandwichSize(Sandwich.SandwichSize.EIGHT_INCH);
        assertEquals(Sandwich.SandwichSize.EIGHT_INCH, BLT.getSize());
    }

    @Test
    void chooseRemoveCheese() {
        CheeseTopping.CheeseType cheddar = CheeseTopping.CheeseType.CHEDDAR;
        BLT.addTopping(new CheeseTopping(cheddar, true));

        List<Topping> toppings = BLT.getToppings();
        boolean found = false;
        for (Topping topping : toppings) {
            if (topping instanceof CheeseTopping cheeseTopping) {
                if (cheeseTopping.getName().equals("Cheddar")) {
                    found = true;
                    break;
                }
            }
        }
        assertTrue(found, "BLT should contain cheddar topping.");
    }

    @Test
    void chooseVeggies() {
        RegularTopping.FreeTopping guac = RegularTopping.FreeTopping.GUACAMOLE;
        BLT.addTopping(new RegularTopping(guac, true));
        List<Topping> toppings = BLT.getToppings();
        boolean found = false;
        for (Topping topping : toppings) {
            if (topping instanceof RegularTopping regularTopping) {
                if (regularTopping.getName().equals("Guacamole")) {
                    found = true;
                    break;
                }
            }
        }
        assertTrue(found, "BLT should contain extra guacamole topping.");
    }

    @Test
    void removeTopping() {
        RegularTopping.FreeTopping guac = RegularTopping.FreeTopping.GUACAMOLE;
        RegularTopping topping = new RegularTopping(guac, true);
        sandwich.addTopping(topping);
        List<Topping> toppings = sandwich.getToppings();

        // Remove the topping
        sandwich.removeTopping(topping);
        toppings.forEach(System.out::println);
        // Assert that the topping is no longer in the list
        assertTrue(sandwich.getToppings().isEmpty(), "Topping should be removed from the sandwich.");
    }

    @Test
    void chooseSauces() {
        Sauce sauce = new Sauce(Sauce.SauceType.MAYO);
        BLT.addSauce(sauce);
        assertTrue(BLT.getSauces().contains(sauce));
    }

    @Test
    void chooseSides() {
        Side auJus = new Side(Side.SideType.AU_JUS);
        BLT.addSide(auJus);
        assertEquals(1, BLT.getSides().size());
        assertTrue(BLT.getSides().contains(auJus));
    }

    @Test
    void removeSide() {
        Side auJus = new Side(Side.SideType.AU_JUS);
        BLT.addSide(auJus);
        BLT.removeSide(auJus);
        assertTrue(BLT.getSides().isEmpty());
    }

    @Test
    void removeSauce() {
        Sauce sauce = new Sauce(Sauce.SauceType.MAYO);
        sandwich.addSauce(sauce);
        sandwich.removeSauce(sauce);
        assertTrue(sandwich.getSauces().isEmpty());
    }

}