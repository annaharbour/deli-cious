package com.pluralsight.delicious.models;

import java.util.ArrayList;
import java.util.List;

public class Sandwich implements MenuItem {
    public enum SandwichSize {
        FOUR_INCH("4\""),
        EIGHT_INCH("8\""),
        TWELVE_INCH("12\"");

        private final String sandwichSizeName;

        SandwichSize(String sandwichSizeName) {
            this.sandwichSizeName = sandwichSizeName;
        }

        public String getValue() {
            return sandwichSizeName;
        }
    }

    public enum BreadType {
        WHITE("White"),
        WHOLE_GRAIN("Whole Grain"),
        MULTIGRAIN("Multi-grain"),
        RYE("Rye");


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
                    List<Side> sides,
                    boolean toasted) {
        this.size = size;
        this.breadType = breadType;
        this.toppings = (toppings != null) ? toppings : new ArrayList<>();
        this.sauces = (sauces != null) ? sauces : new ArrayList<>();
        this.sides = (sides != null) ? sides : new ArrayList<>();
        this.toasted = toasted;
    }

    public SandwichSize getSandwichSize() {
        return size;
    }

    public void setSandwichSize(SandwichSize size) {
        this.size = size;
    }

    public BreadType getBreadType() {
        return breadType;
    }

    public void setBreadType(BreadType breadType) {
        this.breadType = breadType;
    }

    public List<Topping> getToppings() {
        return toppings;
    }

    public void addTopping(Topping topping) {
        this.toppings.add(topping);
    }

    public void removeTopping(Topping topping) {
        this.toppings.remove(topping);
    }

    public void addSauce(Sauce sauce) {
        this.sauces.add(sauce);
    }

    public void removeSauce(Sauce sauce) {
        this.sauces.remove(sauce);
    }

    public void addSide(Side side) {
        this.sides.add(side);
    }

    public void removeSide(Side side) {
        this.sides.remove(side);
    }

    public boolean isToasted() {
        return toasted;
    }

    public void setToasted(boolean toasted) {
        this.toasted = toasted;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    public static Sandwich.SandwichSize[] getAllSizeOptions() {
        return SandwichSize.values();
    }

    public static Sandwich.BreadType[] getAllBreadOptions() {
        return BreadType.values();
    }

    @Override
    public String getReceiptLine() {
        return "Sandwich{" +
                "size=" + size +
                ", breadType=" + breadType +
                ", toppings=" + toppings +
                ", toasted=" + toasted +
                '}';
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
                orderLine.append(cheeseTopping).append(" Cheese");
            } else if(topping instanceof RegularTopping regularTopping) {
                orderLine.append(regularTopping.getName());
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
            orderLine.append(side.toString()).append(", ");
        }
        if (!sides.isEmpty()) {
            orderLine.setLength(orderLine.length() - 2);
        }

        orderLine.append("\n\tToasted: ").append(toasted ? "Yes" : "No").append("\n");
        return orderLine.toString();
    }

}
