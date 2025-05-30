package com.pluralsight.delicious.dao;

import com.pluralsight.delicious.models.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReceiptWriterTest {
    private final Path path = Paths.get("receipts");

    @Test
    void writeLines() {
//       Setup
        Order order = new Order(new ArrayList<>());
        Customer customer = new Customer("Anna Harbour");
        order.setCustomer(customer);
        Sandwich sandwich = new Sandwich(Sandwich.SandwichSize.EIGHT_INCH, Sandwich.BreadType.WHITE,
                List.of(new MeatTopping(MeatTopping.MeatType.STEAK, false),
                        new CheeseTopping(CheeseTopping.CheeseType.PROVOLONE, false),
                        new RegularTopping(RegularTopping.FreeTopping.PEPPERS, false),
                        new RegularTopping(RegularTopping.FreeTopping.MUSHROOMS, false),
                        new RegularTopping(RegularTopping.FreeTopping.ONIONS, false)
                ),
                List.of(new Sauce(Sauce.SauceType.MAYO)),
                List.of(new Side(Side.SideType.AU_JUS)),
                true);
        order.addToOrder(sandwich);

        ReceiptWriter writer = new ReceiptWriter(order);

        // Act
        writer.writeLines();

        // Assert
        try (DirectoryStream<Path> files = Files.newDirectoryStream(path)) {
            Path receipt = files.iterator().next();
            assertTrue(Files.exists(receipt), "Receipt file should exist");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}