package com.pluralsight.delicious.models;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements MenuItem {
    public enum SandwichSize {
        FOUR_INCH("4\""), EIGHT_INCH("8\""), TWELVE_INCH("12\"");

        private final String sandwichSizeName;

        SandwichSize(String sandwichSizeName) {
            this.sandwichSizeName = sandwichSizeName;
        }

        public String getValue() {
            return sandwichSizeName;
        }
    }

    public enum BreadType {
        WHITE("White"), WHOLE_GRAIN("Whole Grain"), MULTIGRAIN("Multi-grain"), RYE("Rye");

        private final String breadTypeName;

        BreadType(String breadTypeName) {
            this.breadTypeName = breadTypeName;
        }

        public String getValue() {
            return breadTypeName;
        }
    }

    private SandwichSize size;
    private BreadType breadType;
    private List<Topping> toppings = new ArrayList<>();
    private List<Sauce> sauces = new ArrayList<>();
    private List<Side> sides = new ArrayList<>();
    boolean toasted;

    public Sandwich() {
    }

    public Sandwich(SandwichSize size, BreadType breadType, List<Topping> toppings, List<Sauce> sauces,
                    List<Side> sides, boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toppings = (toppings != null) ? toppings : new ArrayList<>();
        this.sauces = (sauces != null) ? sauces : new ArrayList<>();
        this.sides = (sides != null) ? sides : new ArrayList<>();
        this.toasted = toasted;
    }

    public void setSandwichSize(SandwichSize size) {
        this.size = size;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(String toppingString) {
        this.toppings.removeIf(topping -> topping.toString().equalsIgnoreCase(toppingString));
    }

    public void addSauce(Sauce sauce) {
        this.sauces.add(sauce);
    }

    public void removeSauce(String sauceString) {
        this.sauces.removeIf(sauce -> sauce.toString().equalsIgnoreCase(sauceString));
    }

    public void addSide(Side side) {
        this.sides.add(side);
    }

    public void removeSide(String sideString) {
        this.sides.removeIf(side -> side.toString().equalsIgnoreCase(sideString));
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    public static Sandwich.SandwichSize[] getAllSizeOptions() {
        return SandwichSize.values();
    }

    public static Sandwich.BreadType[] getAllBreadOptions() {
        return BreadType.values();
    }


    @Override
    public double getPrice() {
        double totalPrice = 0;

        switch (size) {
            case FOUR_INCH -> totalPrice += 5.50;
            case EIGHT_INCH -> totalPrice += 7.00;
            case TWELVE_INCH -> totalPrice += 8.50;
        }

        for (Topping topping : toppings) {
            if (topping instanceof PremiumTopping premiumTopping) {
                totalPrice += premiumTopping.getPrice(size);
            }
        }
        return totalPrice;
    }

    @Override
    public String getReceiptLine() {
        StringBuilder receiptItem = new StringBuilder();
        receiptItem.append(size.getValue()).append("\" Sandwich on ");
        receiptItem.append(breadType.getValue()).append(" Bread");

        StringBuilder receiptDetails = new StringBuilder();
        for (Topping topping : toppings) {
            receiptDetails.append(" - ");
            if (topping instanceof MeatTopping meatTopping) {
                receiptDetails.append(meatTopping);
            } else if (topping instanceof CheeseTopping cheeseTopping) {
                receiptDetails.append(cheeseTopping);
            } else if (topping instanceof RegularTopping regularTopping) {
                receiptDetails.append(regularTopping);
            }

        }
        for (Sauce sauce : sauces) {
            receiptDetails.append(" - ");
            receiptDetails.append(sauce);

        }
        for (Side side : sides) {
            receiptDetails.append(" - ");
            receiptDetails.append("Side of ").append(side);
        }
        receiptDetails.append(" - ");
        receiptDetails.append(toasted ? " | Toasted" : "Not Toasted");

        return String.format("%s, %s, %.2f", receiptItem, receiptDetails, getPrice());
    }

    @Override
    public String getOrderLine() {
        StringBuilder orderLine = new StringBuilder("Sandwich Details:\n");
        orderLine.append("\tSize: ").append(size.getValue()).append("\n");
        orderLine.append("\tBread Type: ").append(breadType.getValue()).append("\n");
        orderLine.append("\tToppings: ");
        for (Topping topping : toppings) {
            if (topping instanceof MeatTopping meatTopping) {
                orderLine.append(meatTopping);
            } else if (topping instanceof CheeseTopping cheeseTopping) {
                orderLine.append(cheeseTopping);
            } else if (topping instanceof RegularTopping regularTopping) {
                orderLine.append(regularTopping);
            }
            orderLine.append(", ");
            if (toppings.size() == toppings.indexOf(topping) + 1) {
                orderLine.setLength(orderLine.length() - 2);
            }
        }
        orderLine.append("\n\tSauces: ");
        for (Sauce sauce : sauces) {
            orderLine.append(sauce).append(", ");
        }
        if (!sauces.isEmpty()) {
            orderLine.setLength(orderLine.length() - 2);
        }

        orderLine.append("\n\tSides: ");
        for (Side side : sides) {
            orderLine.append(side).append(", ");
        }
        if (!sides.isEmpty()) {
            orderLine.setLength(orderLine.length() - 2);
        }

        orderLine.append("\n\t").append(toasted ? "Toasted" : "Not Toasted").append("\n");
        orderLine.append("Total Price: $ ").append(String.format("%.2f", getPrice()));
        return orderLine.toString();
    }

}
