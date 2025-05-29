package com.pluralsight.delicious.dao;

import com.pluralsight.delicious.models.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SignatureSandwichesLoader {
    public static List<SignatureSandwich> loadFromCSV(String filePath) {
        List<SignatureSandwich> sandwiches = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                Sandwich.SandwichSize size = Sandwich.SandwichSize.valueOf(fields[0].toUpperCase());
                Sandwich.BreadType breadType = Sandwich.BreadType.valueOf(fields[1].toUpperCase());
                List<Topping> toppings = new ArrayList<>();
                String[] toppingNames = fields[2].split(";");
                for (String toppingName : toppingNames) {

                }
                List<Sauce> sauces = new ArrayList<>();
                String[] sauceNames = fields[3].split(";");
                for (String sauceName : sauceNames) {
                    // Add logic to parse and create Sauce objects based on your implementation
                }
                List<Side> sides = new ArrayList<>();
                String[] sideNames = fields[4].split(";");
                for (String sideName : sideNames) {
                    // Add logic to parse and create Side objects based on your implementation
                }
                boolean toasted = Boolean.parseBoolean(fields[5]);
                String name = fields[6];
                sandwiches.add(new SignatureSandwich(size, breadType, toppings, sauces, sides, toasted, name));
            }
        } catch (IOException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }
        return sandwiches;
    }

}