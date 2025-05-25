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
    private List<Sauce> sauces;
    private List<Side> sides;
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
        this.toppings.remove(sauce);
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
        return "Sandwich{" +
                "size=" + size +
                ", breadType=" + breadType +
                ", toppings=" + toppings +
                ", toasted=" + toasted +
                '}';
    }

}
