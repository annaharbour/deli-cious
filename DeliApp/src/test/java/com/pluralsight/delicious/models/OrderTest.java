package com.pluralsight.delicious.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order(new ArrayList<>());
    }


    @Test
    void addToOrder() {
        Chips chips = new Chips();
        chips.setFlavor(Chips.Flavor.Barbecue);
        order.addToOrder(chips);
        assertEquals(1, order.getOrderItems().size());
        assertSame(chips, order.getOrderItems().get(0));
    }

    @Test
    void getOrderItems() {
        MenuItem drink = new Drink(Drink.Size.SMALL, Drink.Flavor.FOUNTAIN_SODA);
        order.addToOrder(drink);

        List<MenuItem> items = order.getOrderItems();
        assertEquals(1, items.size());
        assertInstanceOf(Drink.class, items.get(0));

    }

    @Test
    void getPrice() {
        order.addToOrder(new Drink(Drink.Size.MEDIUM, Drink.Flavor.JUICE)); // $1.50
        Chips chips = new Chips();
        chips.setFlavor(Chips.Flavor.CLASSIC);
        order.addToOrder(chips);

        double expectedTotal = 3.00;
        assertEquals(expectedTotal, order.getPrice());
    }

    @Test
    void clear() {
        order.addToOrder(new Drink(Drink.Size.SMALL, Drink.Flavor.COFFEE));
        order.addToOrder(new Chips());

        order.clear();

        assertEquals(0, order.getOrderItems().size());
    }
}